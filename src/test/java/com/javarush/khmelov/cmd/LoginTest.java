package com.javarush.khmelov.cmd;

import com.javarush.khmelov.repository.UserRepository;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    @Test
    public void doGetTest() {
        Mockito.doReturn("Carl").when(request).getParameter("login");
        Mockito.doReturn("admin").when(request).getParameter("password");
        Login login = new Login(new UserService(new UserRepository()));
        String actualPage = login.doGet(request);
        assertEquals("start-page", actualPage);
    }
    @Test
    public void doGetTestWithoutLogin() {
        Login login = new Login(new UserService(new UserRepository()));
        String actualPage = login.doGet(request);
        assertEquals("login", actualPage);
    }
}
