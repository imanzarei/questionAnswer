package com.example.questionAnswer.service;

import com.example.questionAnswer.dto.QuestionDTO;
import com.example.questionAnswer.dto.VoteResponseDTO;
import com.example.questionAnswer.exception.ResourceNotFoundException;
import com.example.questionAnswer.mapper.QuestionMapper;
import com.example.questionAnswer.model.Question;
import com.example.questionAnswer.repository.QuestionRepository;
import com.example.questionAnswer.util.QuestionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    private QuestionMapper questionMapper = QuestionMapper.INSTANCE;

    private Question question;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        question = new Question();
        question.setId(1L);
        question.setText("What is the result of 2+3=");
        question.setAnswers(List.of("4", "5", "6"));
        question.setCorrectAnswer("5");
        question.setType(QuestionType.TRIVIA);
        question.setVotes(new HashMap<>());
    }

    @Test
    void insertQuestion_ShouldReturnId() {
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        QuestionDTO questionDTO = questionMapper.toDTO(question);
        Long id = questionService.insertQuestion(questionDTO);

        assertEquals(1L, id);
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    void getQuestion_ShouldReturnQuestionDTO() {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        QuestionDTO questionDTO = questionService.getQuestion(1L);

        assertNotNull(questionDTO);
        assertEquals("What is the result of 2+3=", questionDTO.getText());
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    void getQuestion_ShouldThrowResourceNotFoundException() {
        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> questionService.getQuestion(1L));
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    void vote_ShouldReturnVoteResponseDTO() {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        VoteResponseDTO response = questionService.vote(1L, "5");

        assertNotNull(response);
        assertTrue(response.isCorrect());
        assertEquals(1, question.getVotes().get("5"));
        verify(questionRepository, times(1)).findById(1L);
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    void vote_ShouldThrowResourceNotFoundException() {
        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> questionService.vote(1L, "5"));
        verify(questionRepository, times(1)).findById(1L);
    }
}
