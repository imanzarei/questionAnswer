package com.example.questionAnswer.repository;

import com.example.questionAnswer.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
