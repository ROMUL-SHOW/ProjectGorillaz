package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Statistic;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Collection;
import java.util.Optional;

@SuppressWarnings("unused")
public class QuestSteps implements Command {
    private final QuestService questService;
    private final UserService userService;

    public QuestSteps(QuestService questService, UserService userService) {
        this.questService = questService;
        this.userService = userService;
    }

    @Override
    public String doGet(HttpServletRequest request) {
        String stringId = request.getParameter("id");

        if (stringId.equals("4")) {
            setVictory(request);
        }
        if (stringId.equals("5")) {
            stringId = "1";
        }
        if (request.getParameter("name") != null) {
            setQuestName(request);
        }

        if (stringId != null) {
            long id = Long.parseLong(stringId);
            Optional<Quest> optionalQuest = questService.get(id);
            if (optionalQuest.isPresent()) {
                Quest quest = optionalQuest.get();
                if (id == 0) {
                    quest.setQuestion(request.getSession().getAttribute("questName") + getReason(request));
                }
                request.setAttribute("quest", quest);
            }
        }
        if (stringId.equals("1")) {
            setStartQuest(request);
        }
        return getView();
    }

    @Override
    public String doPost(HttpServletRequest request) {
        String stringId = request.getParameter("id");

        if (request.getParameter("name") != null) {
            setQuestName(request);
        }
        if (stringId != null) {
            long id = Long.parseLong(stringId);
            Optional<Quest> optionalQuest = questService.get(id);
            if (optionalQuest.isPresent()) {
                Quest quest = optionalQuest.get();
                request.setAttribute("quest", quest);
            }
        }
        if (stringId.equals("1")) {
            setStartQuest(request);
        }
        return getView();
    }

    private void setStartQuest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return;
        }

        User sessionUser = (User) session.getAttribute("user");
        Statistic statistic = sessionUser.getStatistic();
        statistic.setGamesPlayed(statistic.getGamesPlayed() + 1);
        sessionUser.setStatistic(statistic);
        userService.update(sessionUser);

        Long gamesPlayed = (Long) session.getAttribute("gamesPlayed");
        if (gamesPlayed == null) {
            Collection<User> users = userService.getAll();
            gamesPlayed = 1L;
            for (User user : users) {
                gamesPlayed += user.getStatistic().getGamesPlayed();
            }
            session.setAttribute("gamesPlayed", gamesPlayed);
        } else {
            session.setAttribute("gamesPlayed", ++gamesPlayed);
        }
    }

    private void setVictory(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return;
        }

        User sessionUser = (User) session.getAttribute("user");
        Statistic statistic = sessionUser.getStatistic();
        statistic.setGamesWon(statistic.getGamesWon() + 1);
        sessionUser.setStatistic(statistic);
        userService.update(sessionUser);

        Long gamesWon = (Long) session.getAttribute("gamesWon");
        if (gamesWon == null) {
            Collection<User> users = userService.getAll();
            gamesWon = 1L;
            for (User user : users) {
                gamesWon += user.getStatistic().getGamesWon();
            }
            session.setAttribute("gamesWon", gamesWon);
        } else {
            session.setAttribute("gamesWon", ++gamesWon);
        }
    }

    private void setQuestName(HttpServletRequest request) {
        String name = request.getParameter("name");
        HttpSession session = request.getSession();
        session.setAttribute("questName", name);
    }

    private String getReason(HttpServletRequest request) {
        String requestParameter = request.getParameter("reason");
        String result = "";
        if (requestParameter.equals("1")) {
            result = " отклонил вызов.";
        }
        if (requestParameter.equals("2")) {
            result = " не пошел на переговоры.";
        }
        if (requestParameter.equals("3")) {
            result = " не умеет врать.";
        }
        return result;
    }
}
