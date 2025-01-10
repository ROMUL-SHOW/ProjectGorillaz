package com.javarush.khmelov.controller;


import com.javarush.khmelov.cmd.Command;
import com.javarush.khmelov.config.Winter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FrontControllerTest {
    private FrontController frontController;

    @Mock
    private HttpResolver httpResolver;

    @Mock
    private ServletConfig servletConfig;

    @Mock
    private ServletContext servletContext;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Command command;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Winter.components.put(HttpResolver.class, httpResolver);
        frontController = new FrontController();
    }

    @Test
    void testDoGet() throws Exception {
        // Создаем mock для RequestDispatcher
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        // Настраиваем поведение mock-объектов
        when(httpResolver.resolve(request)).thenReturn(command);
        when(command.doGet(request)).thenReturn("login");
        when(request.getRequestDispatcher("/WEB-INF/login.jsp")).thenReturn(requestDispatcher);

        // Вызываем метод doGet
        frontController.doGet(request, response);

        // Проверяем, что вызов forward() произошел
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost() throws Exception {
        when(httpResolver.resolve(request)).thenReturn(command);
        when(command.doPost(request)).thenReturn("/login");

        frontController.doPost(request, response);

        verify(response).sendRedirect("/login");
    }

    @Test
    void testInit() {
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        frontController.init(servletConfig);

        verify(servletContext).setAttribute(eq("roles"), any(Object[].class));
    }
}
