package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogOut implements Command {
    @Override
    public String doGet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null) {
            session.removeAttribute("user");
        }
        return "start-page";
    }
}
