package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Statistic;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.mockito.Mockito.*;

public class StatisticsPageTest {

    private UserService userService;
    private HttpServletRequest request;
    private HttpSession session;
    private StatisticsPage statisticsPage;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        statisticsPage = new StatisticsPage(userService);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void doGet_shouldSetGamesPlayedAndGamesWon() {
        // Настройка
        Collection<User> users = Arrays.asList(
                createTestUser(5L, 3L),
                createTestUser(10L, 7L)
        );
        when(userService.getAll()).thenReturn(users);
        when(session.getAttribute("gamesPlayed")).thenReturn(null);
        when(session.getAttribute("gamesWon")).thenReturn(null);

        // Вызов
        statisticsPage.doGet(request);

        // Проверки для gamesPlayed
        verify(session).getAttribute("gamesPlayed");
        verify(session).setAttribute("gamesPlayed", 15L);

        // Проверки для gamesWon
        verify(session).getAttribute("gamesWon");
        verify(session).setAttribute("gamesWon", 10L);

        // Проверка вызова userService.getAll()
        verify(userService, times(3)).getAll();
    }

    @Test
    void doGet_shouldSetUsersAttribute() {
        // Настройка
        Collection<User> users = Arrays.asList(
                createTestUser(5L, 3L),
                createTestUser(10L, 7L)
        );
        when(userService.getAll()).thenReturn(users);

        // Вызов
        statisticsPage.doGet(request);

        // Проверка атрибута "users"
        verify(request).setAttribute("users", users);
    }

    @Test
    void doGet_shouldReuseSessionAttributesIfPresent() {
        // Настройка
        when(session.getAttribute("gamesPlayed")).thenReturn(20L);
        when(session.getAttribute("gamesWon")).thenReturn(15L);

        // Вызов
        statisticsPage.doGet(request);

        // Проверка: gamesPlayed и gamesWon не обновляются
        verify(session, never()).setAttribute(eq("gamesPlayed"), anyLong());
        verify(session, never()).setAttribute(eq("gamesWon"), anyLong());
    }

    private User createTestUser(Long gamesPlayed, Long gamesWon) {
        User user = new User();
        Statistic statistic = new Statistic();
        statistic.setGamesPlayed(gamesPlayed);
        statistic.setGamesWon(gamesWon);
        user.setStatistic(statistic);
        return user;
    }
}
