package ru.otus.servlet;

import com.google.gson.Gson;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.services.TemplateProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdminServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "admin.html";
    private static final String TEMPLATE_ATTR_All_USER = "userList";

    private final ru.otus.core.service.DbServiceUserImpl DbServiceUserImpl;
    private final TemplateProcessor templateProcessor;
    private List<User> userList;

    public AdminServlet(TemplateProcessor templateProcessor, DbServiceUserImpl DbServiceUserImpl) {
        this.templateProcessor = templateProcessor;
        this.DbServiceUserImpl = DbServiceUserImpl;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        userList = DbServiceUserImpl.getAllUsers();
        Gson gson = new Gson();
        String userListJson = gson.toJson(userList);
        System.err.println(userListJson);
        paramsMap.put(TEMPLATE_ATTR_All_USER, userListJson);
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userList = DbServiceUserImpl.getAllUsers();
        String name = req.getParameter("fullName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        System.err.println("------------------------------------" + name +"--------------------------------");
        System.err.println("------------------------------------" + userList.size() +"--------------------------------");
        if((name != null && !name.equals("")) || (login != null && login.equals("")) || (password != null && password.equals("")) || (age != null && age.equals(""))){
            User user = new User(userList.size(), name, Integer.parseInt(age), login, password);
            System.err.println(user);
            DbServiceUserImpl.saveUser(user);
        }
        doGet(req, resp);
    }
}
