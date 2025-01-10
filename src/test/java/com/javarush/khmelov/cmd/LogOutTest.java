package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LogOutTest {
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private HttpSession session = Mockito.mock(HttpSession.class);

    @BeforeEach
    void setUp() {
        Mockito.when(request.getSession()).thenReturn(session);
    }

    @Test
    public void doGetTest() {
        Optional<User> userOptional = new UserRepository().get(1L);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        Mockito.doReturn(user).when(session).getAttribute("user");
        LogOut logOut = new LogOut();
        String actualPage = logOut.doGet(request);
        assertEquals("start-page",actualPage);
        Mockito.verify(session).removeAttribute("user");
    }
}
