package com.javarush.khmelov.cmd;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartPageTest {
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    @Test
    public void doGetTest() {
        StartPage startPage = new StartPage();
        String actualPage = startPage.doGet(request);
        assertEquals("start-page", actualPage);
    }
}
