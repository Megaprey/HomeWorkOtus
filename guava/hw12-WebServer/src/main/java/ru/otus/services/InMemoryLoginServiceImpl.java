package ru.otus.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUserImpl;

import java.util.Optional;

public class InMemoryLoginServiceImpl extends AbstractLoginService {

    private final UserDao userDao;
    private final DbServiceUserImpl dbServiceUser;
    public InMemoryLoginServiceImpl(UserDao userDao, DbServiceUserImpl dbServiceUser) {
        this.userDao = userDao;
        this.dbServiceUser = dbServiceUser;
    }


    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal) {
        return new String[] {"user"};
    }

    @Override
    protected UserPrincipal loadUserInfo(String login) {
        System.out.println(String.format("InMemoryLoginService#loadUserInfo(%s)", login));
        Optional<User> dbUser = dbServiceUser.getUser(login);
        return dbUser.map(u -> new UserPrincipal(u.getLogin(), new Password(u.getPass()))).orElse(null);
    }
}
