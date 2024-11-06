package com.example.questionAnswer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class QuestionDTO {

    private Long id;
    private String text;
    private List<String> answers;
    private String correctAnswer; // For Trivia
    private String type;
    private Map<String, Integer> votes;
}
