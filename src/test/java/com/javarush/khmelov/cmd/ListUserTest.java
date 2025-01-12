package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ListUserTest {

    private UserService userService;
    private HttpServletRequest request;
    private ListUser listUser;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        request = mock(HttpServletRequest.class);
        listUser = new ListUser(userService);
    }

    @Test
    void doGet_shouldSetUsersAttribute() {
        // Настройка
        Collection<User> users = Arrays.asList(
                new User(1L, "Alice", "password1", null),
                new User(2L, "Bob", "password2", null)
        );
        when(userService.getAll()).thenReturn(users);

        // Вызов
        String view = listUser.doGet(request);

        // Проверка
        verify(userService).getAll();
        verify(request).setAttribute("users", users);
        assertNotNull(view);
    }
}
