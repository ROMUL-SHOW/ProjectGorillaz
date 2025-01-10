package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Collection;

public class Login implements Command {
    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String doGet(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Collection<User> userCollection = userService.getAll();
        for (User user : userCollection) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                if(request.getSession() != null) {                  // for testing
                    request.getSession().setAttribute("user", user);
                }
                return "start-page";
            }
        }
        return getView();
    }
}
