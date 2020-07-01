package ru.otus.home.mapper;

import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntitySQLMetaData;

import java.lang.reflect.Field;
import java.util.List;

public class SQLContsructor implements EntitySQLMetaData {
    private EntityClassMetaData objData;

    public SQLContsructor(EntityClassMetaData objData){
        this.objData = objData;
    }

    @Override
    public String getSelectAllSql() {
        return "select * from " + objData.getName().substring(objData.getName().lastIndexOf(".") + 1);
    }

    @Override
    public String getSelectByIdSql() throws IllegalAccessException {
        return "select * from " + objData.getName().substring(objData.getName().lastIndexOf(".") + 1) + " where " + objData.getIdField().getName() + " = ?";
    }

    @Override
    public String getInsertSql() throws IllegalAccessException {
        StringBuilder insert = new StringBuilder("insert into ");
        insert.append(objData.getName().substring(objData.getName().lastIndexOf(".") + 1)).append("(");
        StringBuilder values = new StringBuilder(" values (");
        List<Field> fields = objData.getAllFields();
        int size = fields.size();
        for(int i = 0; i < size; i++) {
            Field field = fields.get(i);
            insert.append(field.getName());
            if(i == size - 1) {
                insert.append(")");
                values.append("?)");
            } else {
                insert.append(", ");
                values.append("?, ");
            }
        }
        insert.append(values);
        return insert.toString();
    }

    @Override
    public String getUpdateSql() throws IllegalAccessException {
        StringBuilder update = new StringBuilder("update " + objData.getName().substring(objData.getName().lastIndexOf(".") + 1) + " set");
        List<Field> fields = objData.getFieldsWithoutId();
        int size = fields.size();
        for(int i = 0; i < size; i++) {
            Field field = fields.get(i);
            if(i == size - 1) {
                update.append(" " + field.getName()).append(" = ?");
            } else {
                update.append(" " + field.getName()).append(" = ?,");
            }
        }
        update.append(" where ").append(objData.getIdField().getName() + " = ?");

        return update.toString();
    }


}
