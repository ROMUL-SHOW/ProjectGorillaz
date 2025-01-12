package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class EditUserTest {
    private EditUser editUser;
    private UserService userService;
    private HttpServletRequest req;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        req = mock(HttpServletRequest.class);
        editUser = new EditUser(userService);
    }

    @Test
    public void doGetTest() {

        String userId = "1";
        User user = User.builder()
                .id(1L)
                .login("testUser")
                .password("testPassword")
                .role(Role.USER)
                .build();

        when(req.getParameter("id")).thenReturn(userId);
        when(userService.get(1L)).thenReturn(Optional.of(user));

        String view = editUser.doGet(req);

        assertEquals("edit-user", view);
        verify(req).setAttribute("user", user);
        verify(userService).get(1L);
    }

    @Test
    void doPostUpdateTest() {
        // Arrange
        User sessionUser = User.builder()
                .id(2L)
                .role(Role.ADMIN)
                .build();

        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(sessionUser);

        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("login")).thenReturn("updatedUser");
        when(req.getParameter("password")).thenReturn("newPassword");
        when(req.getParameter("role")).thenReturn("USER");
        when(req.getParameter("update")).thenReturn("true");

        // Act
        String view = editUser.doPost(req);

        // Assert
        verify(userService).update(argThat(user ->
                user.getId() == 1L &&
                        user.getLogin().equals("updatedUser") &&
                        user.getPassword().equals("newPassword") &&
                        user.getRole() == Role.USER
        ));
        assertTrue(view.startsWith("edit-user?id=1"));
    }

}
