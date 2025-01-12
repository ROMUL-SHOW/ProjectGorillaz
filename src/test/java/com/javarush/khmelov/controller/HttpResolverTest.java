package com.javarush.khmelov.controller;

import com.javarush.khmelov.cmd.Command;
import com.javarush.khmelov.cmd.ListUser;
import com.javarush.khmelov.config.Winter;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HttpResolverTest {

    private HttpResolver httpResolver;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        httpResolver = new HttpResolver();  // Создаем экземпляр HttpResolver перед каждым тестом
        request = mock(HttpServletRequest.class);  // Мокируем HttpServletRequest
    }

    @Test
    void resolve_shouldResolveCommandFromRequest() throws Exception {
        // Мокируем поведение запроса
        when(request.getRequestURI()).thenReturn("/list-user");

        // Мокируем возвращаемое значение из Winter.find
        Command mockCommand = mock(ListUser.class);
        Winter.components.put(ListUser.class, mockCommand);  // Добавляем команду в контейнер Winter

        // Вызываем метод resolve
        Command result = httpResolver.resolve(request);

        // Проверяем, что вернулся правильный объект команды
        assertNotNull(result);
        assertSame(mockCommand, result);
    }

    @Test
    void resolve_shouldHandleSlashAndConvertToStartPageCommand() throws Exception {
        // Мокируем поведение запроса
        when(request.getRequestURI()).thenReturn("/");

        // Мокируем класс команды, который должен быть вызван для "/"
        String expectedCommand = "StartPage";
        Class<?> commandClass = Class.forName("com.javarush.khmelov.cmd." + expectedCommand);

        Command mockCommand = mock(Command.class);

        // Мокируем работу Winter.find для команды StartPage
        Winter.components.put(commandClass, mockCommand);  // Добавляем команду StartPage

        // Вызываем resolve
        Command result = httpResolver.resolve(request);

        // Проверяем, что метод возвращает правильную команду
        assertNotNull(result);
        assertSame(mockCommand, result);
    }

    @Test
    void resolve_shouldThrowExceptionWhenClassNotFound() {
        // Мокируем поведение запроса
        when(request.getRequestURI()).thenReturn("/unknown-command");
        // Проверяем, что при несуществующей команде будет выброшено исключение
        assertThrows(RuntimeException.class, () -> httpResolver.resolve(request));
    }
}
