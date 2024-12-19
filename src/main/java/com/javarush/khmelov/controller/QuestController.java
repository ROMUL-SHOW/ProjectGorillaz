package com.javarush.khmelov.controller;
import com.javarush.khmelov.config.Winter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestController extends HttpServlet {
    private static String player = "Ghost";
    private final HttpResolver httpResolver = Winter.find(HttpResolver.class);
    private static String getJsp(String view) {return "/WEB-INF/quest" + view + ".jsp";}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String redirect = getJsp(request.getRequestURI());
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if(!request.getParameter("name").equals("null"))
        {
            player = request.getParameter("name");
        }
        System.out.println(player);
        String redirect = getJsp(request.getRequestURI());
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
