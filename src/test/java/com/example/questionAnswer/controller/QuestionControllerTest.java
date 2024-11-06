package com.example.questionAnswer.controller;

import com.example.questionAnswer.dto.QuestionDTO;
import com.example.questionAnswer.dto.VoteResponseDTO;
import com.example.questionAnswer.exception.ResourceNotFoundException;
import com.example.questionAnswer.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;

    private QuestionDTO questionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        questionDTO = new QuestionDTO();
        questionDTO.setId(1L);
        questionDTO.setText("What is the result of 2+3=");
        questionDTO.setAnswers(List.of("4", "5", "6"));
        questionDTO.setCorrectAnswer("5");
        questionDTO.setType("TRIVIA");
    }

    @Test
    void createQuestion_ShouldReturnId() {
        when(questionService.insertQuestion(any(QuestionDTO.class))).thenReturn(1L);

        ResponseEntity<Long> response = questionController.createQuestion(questionDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody());
        verify(questionService, times(1)).insertQuestion(any(QuestionDTO.class));
    }

    @Test
    void getQuestion_ShouldReturnQuestionDTO() {
        when(questionService.getQuestion(1L)).thenReturn(questionDTO);

        ResponseEntity<QuestionDTO> response = questionController.getQuestion(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(questionDTO, response.getBody());
        verify(questionService, times(1)).getQuestion(1L);
    }

    @Test
    void getQuestion_ShouldReturnNotFound() {
        when(questionService.getQuestion(1L)).thenThrow(new ResourceNotFoundException("Question not found"));

        ResponseEntity<QuestionDTO> response = questionController.getQuestion(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(questionService, times(1)).getQuestion(1L);
    }

    @Test
    void vote_ShouldReturnVoteResponseDTO() {
        VoteResponseDTO voteResponseDTO = new VoteResponseDTO(Map.of("5", 1), true, "TRIVIA");
        when(questionService.vote(1L, "5")).thenReturn(voteResponseDTO);

        ResponseEntity<VoteResponseDTO> response = questionController.vote(1L, "5");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(voteResponseDTO, response.getBody());
        verify(questionService, times(1)).vote(1L, "5");
    }
}
