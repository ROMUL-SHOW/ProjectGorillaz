package com.javarush.khmelov.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quest {
    private Long id;
    private String reason;
    private String question;
    private String answerPositive;
    private String answerNegative;
    private String pathToImage;
}
