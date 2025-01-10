package com.javarush.khmelov.cmd;

import jakarta.servlet.http.HttpServletRequest;

@SuppressWarnings("unused")
public class StartPage implements Command {
    @Override
    public String doGet(HttpServletRequest request) {
        String stringId = request.getParameter("id");
        if(stringId != null && stringId.equals("btStart")) {
                return "quest-start";
        }
        return getView();
    }
}
