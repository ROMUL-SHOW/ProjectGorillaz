package com.javarush.khmelov.config;

import com.javarush.khmelov.cmd.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinterTest {
    @Test
    public void testFind() {
        Command command = Winter.find(com.javarush.khmelov.cmd.StatisticsPage.class);
        Assertions.assertEquals(command.getClass(), com.javarush.khmelov.cmd.StatisticsPage.class);
    }
    @Test
    public void testFindWithNull() {
        Throwable throwable = assertThrows(NullPointerException.class, () -> {
            Winter.find(null);
        });
        Assertions.assertEquals(throwable.getClass(), NullPointerException.class);
    }
}
