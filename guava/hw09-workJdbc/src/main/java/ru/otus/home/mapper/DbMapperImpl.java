package ru.otus.home.mapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.User;
import ru.otus.home.model.Account;
import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntitySQLMetaData;
import ru.otus.jdbc.mapper.JdbcMapper;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;


public class DbMapperImpl<T> implements JdbcMapper<T> {
    private static final String URL = "jdbc:h2:mem:";
    private static final Logger logger = LoggerFactory.getLogger(DbMapperImpl.class);
    private Connection connection;

    public int getRowIserted() {
        return rowIserted;
    }

    private int rowIserted;

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        DbMapperImpl<User> demo = new DbMapperImpl<>();
        User user = new User();
        user.setAge(15);
        user.setId(0);
        user.setName("Ivan");
        try (var connection = getConnection()) {
            demo.setConnection(connection);
            demo.createTable( "create table User(id int, name varchar(50), age int)");
            connection.commit();
            demo.findById(0, (Class<User>) user.getClass());
            demo.insert(user);
            user.setName("Petr");
            demo.update(user);
            User user2 = demo.findById(0, User.class);
            System.out.println(user2);
            user.setName("Kostya");
            user.setId(1);
            demo.insertOrUpdate(user);
            user.setName("Pavel");
            user.setAge(20);
            demo.insertOrUpdate(user);

        } catch (Exception ex) {
            logger.error("Error: " + ex);
        }
        DbMapperImpl<Account> demo2 = new DbMapperImpl<>();

        try (var connection = getConnection()) {
            demo2.setConnection(connection);
            demo2.createTable("create table Account(id int, type varchar(50), rest int)");
            Account acc = new Account();
            acc.setId(0);
            acc.setType("client");
            acc.setRest(200);
            demo2.insertOrUpdate(acc);
            acc.setType("admin");
            demo2.insertOrUpdate(acc);
            Account acc2 = demo2.findById(0, (Class<Account>) acc.getClass());
            System.out.println(acc2);
        } catch (Exception ex) {
            logger.error("Error: " + ex);
        }

    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(T objectData) throws IllegalAccessException {
        EntityClassMetaData<T> classMetaData = new ObjectDataExtractor<T>(objectData);
        EntitySQLMetaData constructor = new SQLContsructor(classMetaData);
        rowIserted = insertOrUpdateFromQuery(constructor.getInsertSql(), "inserted", objectData, classMetaData);
    }

    @Override
    public void update(T objectData) throws IllegalAccessException {
        EntityClassMetaData<T> classMetaData = new ObjectDataExtractor<T>(objectData);
        EntitySQLMetaData constructor = new SQLContsructor(classMetaData);
        rowIserted = insertOrUpdateFromQuery(constructor.getUpdateSql(), "updated", objectData, classMetaData);
    }

    @Override
    public void insertOrUpdate(T objectData) throws IllegalAccessException {
        EntityClassMetaData<T> classMetaData = new ObjectDataExtractor<T>(objectData);
        Field fieldId = classMetaData.getIdField();
        fieldId.setAccessible(true);
        if (findById(fieldId.getInt(objectData), (Class<T>) objectData.getClass()) == null){
            insert(objectData);
        } else {
            update(objectData);
        }
    }

    @Override
    public T findById(long id, Class<T> clazz) {
        EntityClassMetaData<T> classMetaData = new ObjectDataExtractor<T>(clazz);
        EntitySQLMetaData constructor = new SQLContsructor(classMetaData);
        List<Field> fields = classMetaData.getFieldsWithoutId();
        Object obj = null;
        try (PreparedStatement pst = connection.prepareStatement(constructor.getSelectByIdSql())) {
            pst.setLong(1, id);
            obj = classMetaData.getConstructor().newInstance();
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    for (Field field : fields) {
                        setPropertyObj(obj, field, rs);
                    }
                } else {
                    return null;
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return (T)obj;
    }

    private void setPropertyObj(Object obj, Field field, ResultSet rs) throws SQLException, IllegalAccessException {
        Class<?> fieldType = field.getType();
        String fieldName = field.getName();
        field.setAccessible(true);
        if(fieldType.isAssignableFrom(boolean.class)) {
            boolean value =  rs.getBoolean(fieldName);
            field.setBoolean(obj, value);
        }
        if(fieldType.isAssignableFrom(int.class)) {
            int value =  rs.getInt(fieldName);
            field.setInt(obj, value);
        }
        if(fieldType.isAssignableFrom(String.class)) {
            String value =  rs.getString(fieldName);
            field.set(obj, value);
        }
        if(fieldType.isAssignableFrom(long.class)) {
            long value =  rs.getLong(fieldName);
            field.setLong(obj, value);
        }
        if(fieldType.isAssignableFrom(short.class)) {
            short value =  rs.getShort(fieldName);
            field.setShort(obj, value);
        }
        if(fieldType.isAssignableFrom(double.class)) {
            double value =  rs.getDouble(fieldName);
            field.setDouble(obj, value);
        }
        if(fieldType.isAssignableFrom(float.class)) {
            float value =  rs.getFloat(fieldName);
            field.setFloat(obj, value);
        }
        if(fieldType.isAssignableFrom(byte.class)) {
            byte value =  rs.getByte(fieldName);
            field.setByte(obj, value);
        }
    }

    private int insertOrUpdateFromQuery(String query, String sqlOperatorName, T objData, EntityClassMetaData<T> objDataExtractor) {
        List<Field> fields = null;
        if (sqlOperatorName.equals("updated")) {
            fields = objDataExtractor.getFieldsWithoutId();
        } else {
            fields = objDataExtractor.getAllFields();
        }
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            Savepoint savePoint = connection.setSavepoint("savePointName");
            int i = 0;
            for (; i < fields.size(); i++) {
                fields.get(i).setAccessible(true);
                pst.setString(i + 1, "" + fields.get(i).get(objData));
            }
            if (sqlOperatorName.equals("updated")) {
                objDataExtractor.getIdField().setAccessible(true);
                pst.setInt(i + 1, (Integer) objDataExtractor.getIdField().get(objData));
            }
            int rowCount = pst.executeUpdate(); //Блокирующий вызов
            connection.commit();
            logger.info(sqlOperatorName + " rowCount: {}", rowCount);
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException | IllegalAccessException throwables) {
            logger.error(throwables.getMessage(), throwables);
        }
            return 0;
    }


    private static Connection getConnection() throws SQLException {
        var connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
        return connection;
    }

    private void createTable(String sqlQuery) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(sqlQuery)) {
            System.out.println(sqlQuery);
            pst.executeUpdate();
        }
    }

}
