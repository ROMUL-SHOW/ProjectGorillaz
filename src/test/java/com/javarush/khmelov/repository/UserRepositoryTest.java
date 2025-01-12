package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.Statistic;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void testGetExistingUser() {
        User expectedUser = User.builder()
                .id(3L)
                .login("Carl")
                .password("admin")
                .role(Role.ADMIN)
                .build();
        expectedUser.setStatistic(new Statistic(26L, 18L));
        Optional<User> user = userRepository.get(3L);
        assertTrue(user.isPresent(), "User with ID 3 should exist");
        assertEquals(expectedUser, user.get(), "User with ID 3 does not match expected");
    }
    @Test
    void testGetNonExistingUser() {
        Optional<User> user = userRepository.get(999L);
        assertFalse(user.isPresent(), "User with ID 999 should not exist");
    }

    @Test
    void testRepositoryInitialization() {
        assertTrue(userRepository.get(1L).isPresent(), "User with ID 1 should be initialized");
        assertTrue(userRepository.get(2L).isPresent(), "User with ID 2 should be initialized");
        assertTrue(userRepository.get(3L).isPresent(), "User with ID 3 should be initialized");
        assertTrue(userRepository.get(4L).isPresent(), "User with ID 4 should be initialized");
    }
}
