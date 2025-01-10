package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Collection;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @ParameterizedTest
    @ValueSource(longs = {
            1, 2, 3
    })
    void testDeleteUser(Long id) {
        Collection<User> users = userService.getAll();
        int expectedSize = users.size() - 1;
        User user = new User(2L, null, null, Role.GUEST);
        userService.delete(user);
        users = userService.getAll();
        int actualSize = users.size();
        assertEquals(expectedSize, actualSize);

        Optional<User> userOptional = userService.get(user.getId());
        assertFalse(userOptional.isPresent(), "User not found");
    }

    @ParameterizedTest
    @CsvSource({
            "login, Tom",
            "password, gfd132sf",
            "role, USER",
    })
    void testUpdateUser(String parameter , String value) {
        User user = new User(3L, "Carl", "admin", Role.ADMIN);
        if(parameter.equals("login")) {
            user.setLogin(value);
        }
        if(parameter.equals("password")) {
            user.setPassword(value);
        }
        if(parameter.equals("role")) {
            Role role = Role.valueOf(value);
            user.setRole(role);
        }
        Collection<User> users = userService.getAll();
        int expectedSize = users.size();

        userService.update(user);

        users = userService.getAll();
        int actualSize = users.size();

        assertEquals(expectedSize, actualSize);

        Optional<User> userOptional = userService.get(user.getId());

        assertTrue(userOptional.isPresent(), "User is found");

        if(parameter.equals("login")) {
            String expectedLogin = user.getLogin();
            String actualLogin = userOptional.get().getLogin();
            assertEquals(expectedLogin, actualLogin);
        }
        if(parameter.equals("password")) {
            String expectedPassword = user.getPassword();
            String actualPassword = userOptional.get().getPassword();
            assertEquals(expectedPassword, actualPassword);
        }
        if(parameter.equals("role")) {
            String expectedRole = user.getRole().name();
            String actualRole = userOptional.get().getRole().name();
            assertEquals(expectedRole, actualRole);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "5, Tom, 3475, USER",
            "283, Alex, gfd132sf, ADMIN",
            "91, Pol, qWeeWq, USER",
            "432, Dima, 99sad, GUEST"
    })
    void testCreateUser(Long id, String login, String password, String role) {
        Role roleEnum = Role.valueOf(role);
        User user = new User(id, login, password, roleEnum);
        Collection<User> users = userService.getAll();
        int expectedSize = users.size() + 1;
        userService.create(user);
        int actualSize = userService.getAll().size();
        assertEquals(expectedSize, actualSize);
        Optional<User> userOptional = userService.get(user.getId());
        assertTrue(userOptional.isPresent(), "User is found");
    }
}
