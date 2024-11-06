package com.example.questionAnswer.controller;

import com.example.questionAnswer.dto.QuestionDTO;
import com.example.questionAnswer.dto.VoteResponseDTO;
import com.example.questionAnswer.exception.ResourceNotFoundException;
import com.example.questionAnswer.mapper.QuestionMapper;
import com.example.questionAnswer.model.Question;
import com.example.questionAnswer.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Long> createQuestion(@RequestBody QuestionDTO questionDTO) {
        Long id = questionService.insertQuestion(questionDTO);
        return ResponseEntity.ok(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long id) {
        try {
            QuestionDTO questionDTO = questionService.getQuestion(id);
            return ResponseEntity.ok(questionDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<VoteResponseDTO> vote(@PathVariable Long id, @RequestParam String answer) {
        VoteResponseDTO voteResponse = questionService.vote(id, answer);
        return ResponseEntity.ok(voteResponse);
    }


}
