package com.javarush.khmelov.cmd;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartPageTest {
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    @Test
    public void doGetTest() {
        Mockito.doReturn("btStart").when(request).getParameter("id");
        StartPage startPage = new StartPage();
        String actualPage = startPage.doGet(request);
        assertEquals("quest-start", actualPage);
    }
    @Test
    public void doGetWithNullTest() {
        StartPage startPage = new StartPage();
        String actualPage = startPage.doGet(request);
        assertEquals("start-page", actualPage);
    }
}
