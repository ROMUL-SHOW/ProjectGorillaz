package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.repository.QuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class QuestServiceTest {
    private QuestRepository questRepository;
    private QuestService questService;

    @BeforeEach
    void setUp() {
        questRepository = new QuestRepository();
        questService = new QuestService(questRepository);
    }

    @Test
    void getAllQuests() {
        Quest expectedQuest = new Quest(3L, " поднялся на мостик.", "Кто ты, воин?", "Рассказать правду", "Солгать", "assets/img/cat-cap.jpg");
        Optional<Quest> questOptional = questService.get(3L);
        Quest actualQuest = questOptional.get();
        assertEquals(expectedQuest.getId(), actualQuest.getId());
        assertEquals(expectedQuest.getReason(), actualQuest.getReason());
        assertEquals(expectedQuest.getQuestion(), actualQuest.getQuestion());
        assertEquals(expectedQuest.getAnswerPositive(), actualQuest.getAnswerPositive());
        assertEquals(expectedQuest.getAnswerNegative(), actualQuest.getAnswerNegative());
        assertEquals(expectedQuest.getPathToImage(), actualQuest.getPathToImage());
    }
}