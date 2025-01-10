package com.javarush.khmelov.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestTest {
    @Test
    public void ConstructorQuest() {
        long id = 2L;
        String reason = "Reason";
        String question = "How are you?";
        String answerPositive = "Good answer";
        String answerNegative = "Bad answer";
        String pathToImage = "pathToImage.png";
        Quest quest = new Quest(id, reason, question, answerPositive, answerNegative, pathToImage);
        Assertions.assertEquals(id, quest.getId());
        Assertions.assertEquals(reason, quest.getReason());
        Assertions.assertEquals(question, quest.getQuestion());
        Assertions.assertEquals(answerPositive, quest.getAnswerPositive());
        Assertions.assertEquals(answerNegative, quest.getAnswerNegative());
        Assertions.assertEquals(pathToImage, quest.getPathToImage());
    }
    @Test
    public void nullQuest(){
        Quest quest = new Quest();
        Assertions.assertNull(quest.getId());
        Assertions.assertNull(quest.getReason());
        Assertions.assertNull(quest.getQuestion());
        Assertions.assertNull(quest.getAnswerPositive());
        Assertions.assertNull(quest.getAnswerNegative());
        Assertions.assertNull(quest.getPathToImage());
    }

}
