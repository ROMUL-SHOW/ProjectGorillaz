package com.javarush.khmelov.cmd;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class StatisticsPage implements Command {

    @Override
    public String doGet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer totalCount = (Integer) session.getAttribute("totalCount");
        if (totalCount == null) {
            totalCount = 0;
            session.setAttribute("totalCount", totalCount);

        }
        Integer CountVictorys = (Integer) session.getAttribute("CountVictory");
        if (CountVictorys == null) {
            CountVictorys = 0;
            session.setAttribute("CountVictory", CountVictorys);
        }
        return getView();
    }
}
