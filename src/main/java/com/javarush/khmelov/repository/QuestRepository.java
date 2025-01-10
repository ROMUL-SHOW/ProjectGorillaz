package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class QuestRepository {

    private final Map<Long, Quest> map = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public QuestRepository() {
        map.put(1L, new Quest(1L, " потерял память.", "Принять вызов НЛО?", "Принять", "Отклонить", "assets/img/1.jpg"));
        map.put(2L, new Quest(2L, " принял вызов.", "Подняться на мостик к капитану?", "Подняться", "Отказаться", "assets/img/president.gif"));
        map.put(3L, new Quest(3L, " поднялся на мостик.", "Кто ты, воин?", "Рассказать правду", "Солгать", "assets/img/cat-cap.jpg"));
        map.put(4L, new Quest(4L, " вернулся домой.","ПОБЕДА!", "Начать сначала",null, "assets/img/happy-end.gif"));
        map.put(0L, new Quest(0L, "Причина", null,  "Начать сначала", null, "assets/img/game_over.jpg"));
    }

    public Optional<Quest> get(long id) {
        return Optional.ofNullable(map.get(id));
    }
}