package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@SuppressWarnings("unused")
public class QuestSteps implements Command {
    private final QuestService questService;

    public QuestSteps(QuestService questService) {
        this.questService = questService;
    }

    @Override
    public String doGet(HttpServletRequest request) {
        String stringId = request.getParameter("id");

        if(stringId.equals("5")){
            stringId = getVictory(request);
        }
        if(request.getParameter("name")!=null) {
            setQuestName(request);
        }
        
        if (stringId != null) {
            long id = Long.parseLong(stringId);
            Optional<Quest> optionalQuest = questService.get(id);
            if (optionalQuest.isPresent()) {
                Quest quest = optionalQuest.get();
                if(id == 0){
                    quest.setQuestion(request.getSession().getAttribute("questName") + getReason(request));
                }
                request.setAttribute("quest", quest);
            }
        }

        if(stringId.equals("1")){
            HttpSession session = request.getSession();
            Integer totalCount = (Integer) session.getAttribute("totalCount");
            if (totalCount == null) {
                totalCount = 1;
                session.setAttribute("totalCount", totalCount);
            } else {
                session.setAttribute("totalCount", ++totalCount);
            }
        }
        return getView();
    }

    private String getVictory(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer CountVictorys = (Integer) session.getAttribute("CountVictory");
        if (CountVictorys == null) {
            CountVictorys = 1;
            session.setAttribute("CountVictory", CountVictorys);
        } else {
            session.setAttribute("CountVictory", ++CountVictorys);
        }
        return "1";
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
