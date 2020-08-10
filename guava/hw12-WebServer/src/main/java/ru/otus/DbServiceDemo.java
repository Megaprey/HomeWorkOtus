package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.home.mapper.DbMapperImpl;
import ru.otus.jdbc.dao.UserDaoMapper;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceDemo {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) throws SQLException {
        DataSourceH2 dataSource = new DataSourceH2();
        var sessionManager = new SessionManagerJdbc(dataSource);
        var demo = new DbServiceDemo();
        demo.createTable(dataSource);
        DbMapperImpl<User> mapperUser = new DbMapperImpl<>();
        UserDao userDao = new UserDaoMapper(sessionManager, mapperUser);
        var dbServiceUser = new DbServiceUserImpl(userDao);
        User user = new User();
        user.setAge(15);
        user.setId(0);
        user.setName("Petr");
        var id = dbServiceUser.saveUser(user);
        Optional<User> user2 = dbServiceUser.getUser(id);
        user2.ifPresentOrElse(
                crUser -> logger.info("created user, name:{}", crUser.getName()),
                () -> logger.info("user was not created")
        );
    }

    public void createTable(DataSource dataSource) throws SQLException {
        try (var connection = dataSource.getConnection();
             var pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50), age int, login varchar(50), pass varchar(50))")) {
            pst.executeUpdate();
        }
        System.out.println("table created");
    }
}

