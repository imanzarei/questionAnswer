package com.example.questionAnswer.model;

import com.example.questionAnswer.util.QuestionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ElementCollection
    private List<String> answers;

    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ElementCollection
    private Map<String, Integer> votes = new HashMap<>();

}
