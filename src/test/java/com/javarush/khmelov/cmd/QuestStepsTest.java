package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.mockito.Mockito.*;

class QuestStepsTest {
    private QuestService questService;
    private UserService userService;
    private HttpServletRequest request;
    private HttpSession session;
    private QuestSteps questSteps;

    @BeforeEach
    void setUp() {
        questService = mock(QuestService.class);
        userService = mock(UserService.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        questSteps = new QuestSteps(questService, userService);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void doGet_shouldHandleQuestWithId4() {
        // Настройка данных
        when(request.getParameter("id")).thenReturn("4");
        User user = new User(1L, "Alisa", "qwerty", Role.USER);

        when(session.getAttribute("user")).thenReturn(user);

        // Вызов метода
        questSteps.doGet(request);

        // Проверка взаимодействий
        verify(session, times(2)).getAttribute("user");
        verify(session).setAttribute(eq("gamesWon"), eq(1L));
        verify(userService).update(user);
    }

    @Test
    void doGet_shouldHandleQuestById() {

        when(request.getParameter("id")).thenReturn("2");
        Quest quest = new Quest();
        quest.setId(2L);
        when(questService.get(2L)).thenReturn(Optional.of(quest));


        questSteps.doGet(request);

        verify(request).setAttribute("quest", quest);
        verify(questService).get(2L);
    }
}