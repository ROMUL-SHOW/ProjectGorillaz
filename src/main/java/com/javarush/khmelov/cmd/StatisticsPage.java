package com.javarush.khmelov.cmd;


import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Collection;

public class StatisticsPage implements Command {
    private final UserService userService;
    public StatisticsPage(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doGet(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setGamesPlayed(session);
        setGamesWon(session);
        Collection<User> users = userService.getAll();
        request.setAttribute("users", users);
        return getView();
    }
    private void setGamesPlayed(HttpSession session) {
        Long gamesPlayed = (Long) session.getAttribute("gamesPlayed");
        if (gamesPlayed == null) {
            Collection<User> users = userService.getAll();
            gamesPlayed = 0L;
            for (User user : users) {
                gamesPlayed += user.getStatistic().getGamesPlayed();
            }
            session.setAttribute("gamesPlayed", gamesPlayed);
        }
    }

    private void setGamesWon(HttpSession session) {
        Long gamesWon = (Long) session.getAttribute("gamesWon");
        if (gamesWon == null) {
            Collection<User> users = userService.getAll();
            gamesWon = 0L;
            for (User user : users) {
                gamesWon += user.getStatistic().getGamesWon();
            }
            session.setAttribute("gamesWon", gamesWon);
        }
    }
}
