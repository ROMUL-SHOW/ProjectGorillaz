package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class QuestRepositoryTest {
    private QuestRepository questRepository;

    @BeforeEach
    void setUp() {
        questRepository = new QuestRepository();
    }

    @Test
    void testGetExistingQuest() {
        Quest expectedQuest = Quest.builder()
                .id(1L)
                .reason(" потерял память.")
                .question("Принять вызов НЛО?")
                .answerPositive("Принять")
                .answerNegative("Отклонить")
                .pathToImage("assets/img/1.jpg")
                .build();

        Optional<Quest> quest = questRepository.get(1L);
        assertTrue(quest.isPresent(), "Quest with ID 1 should exist");
        assertEquals(expectedQuest, quest.get(), "Quest with ID 1 does not match expected");
    }

    @Test
    void testGetNonExistingQuest() {
        Optional<Quest> quest = questRepository.get(542L);
        assertFalse(quest.isPresent(), "Quest with ID 999 should not exist");
    }


    @Test
    void testRepositoryInitialization() {
        assertTrue(questRepository.get(1L).isPresent(), "Quest with ID 1 should be initialized");
        assertTrue(questRepository.get(2L).isPresent(), "Quest with ID 2 should be initialized");
        assertTrue(questRepository.get(3L).isPresent(), "Quest with ID 3 should be initialized");
        assertTrue(questRepository.get(4L).isPresent(), "Quest with ID 4 should be initialized");
        assertTrue(questRepository.get(0L).isPresent(), "Quest with ID 0 should be initialized");
    }

}
