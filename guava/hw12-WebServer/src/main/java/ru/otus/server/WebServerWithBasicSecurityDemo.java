package ru.otus.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import ru.otus.DbServiceDemo;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.helpers.FileSystemHelper;
import ru.otus.home.mapper.DbMapperImpl;
import ru.otus.jdbc.dao.UserDaoJdbc;
import ru.otus.jdbc.dao.UserDaoMapper;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;
import ru.otus.services.InMemoryLoginServiceImpl;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;

import java.util.List;
import java.util.Optional;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithBasicSecurityDemo {
    private static final int WEB_SERVER_PORT = 8081;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        DataSourceH2 dataSource = new DataSourceH2();
        var sessionManager = new SessionManagerJdbc(dataSource);
        var demo = new DbServiceDemo();
        demo.createTable(dataSource);
        DbMapperImpl<User> mapperUser = new DbMapperImpl<>();
        UserDao userDao = new UserDaoMapper(sessionManager, mapperUser);
        var dbServiceUser = new DbServiceUserImpl(userDao);
        var id = dbServiceUser.saveUser(new User(0, "Petr", 13, "Zak", "333"));
        var id1 = dbServiceUser.saveUser(new User(1, "Petr", 13, "Zak2", "333"));
//        Optional<User> user = dbServiceUser.getUser("Zak");
//        System.err.println(user.get().getPass());
//        List<User> userList = dbServiceUser.getAllUsers();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
//        System.err.println(gson.toJson(userList));

//        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

//        String hashLoginServiceConfigPath = FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);
//        LoginService loginService = new HashLoginService(REALM_NAME, hashLoginServiceConfigPath);
        LoginService loginService = new InMemoryLoginServiceImpl(userDao, dbServiceUser);


        UsersWebServer usersWebServer = new UsersWebServerWithBasicSecurity(WEB_SERVER_PORT,
                loginService, dbServiceUser, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();

    }
}
