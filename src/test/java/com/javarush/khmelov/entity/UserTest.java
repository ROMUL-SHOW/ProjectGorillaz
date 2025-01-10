package com.javarush.khmelov.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void ConstructorUser() {
        long id = 23L;
        String login = "khmelov";
        String password = "khmelov";
        Role role = Role.USER;
        User user = new User(id, login, password, role);
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(login, user.getLogin());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(role, user.getRole());
    }
    @Test
    public void nullUser() {
        User user = new User();
        Assertions.assertNull(user.getId());
        Assertions.assertNull(user.getLogin());
        Assertions.assertNull(user.getPassword());
        Assertions.assertNull(user.getRole());
    }
}
