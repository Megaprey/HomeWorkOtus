package ru.otus.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.home.mapper.DbMapperImpl;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.SQLException;
import java.util.Optional;

public class UserDaoMapper implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoMapper.class);

    private final SessionManagerJdbc sessionManager;
    private final DbMapperImpl<User> dbMapperImpl;

    public UserDaoMapper(SessionManagerJdbc sessionManager, DbMapperImpl<User> dbMapperImpl) {
        this.sessionManager = sessionManager;
        this.dbMapperImpl = dbMapperImpl;
    }
    @Override
    public Optional<User> findById(long id) {
        dbMapperImpl.setConnection(sessionManager.getCurrentSession().getConnection());
        User user = dbMapperImpl.findById(id,User.class);
        return Optional.ofNullable(user);
    }

    @Override
    public long insertUser(User user) {
        dbMapperImpl.setConnection(sessionManager.getCurrentSession().getConnection());
        try {
            dbMapperImpl.insert(user);
        } catch (IllegalAccessException e) {
            logger.error("Error:", e);
        }
        return dbMapperImpl.getRowIserted();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;    }
}
