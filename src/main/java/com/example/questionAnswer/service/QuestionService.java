package com.example.questionAnswer.service;

import com.example.questionAnswer.dto.QuestionDTO;
import com.example.questionAnswer.dto.VoteResponseDTO;
import com.example.questionAnswer.exception.ResourceNotFoundException;
import com.example.questionAnswer.mapper.QuestionMapper;
import com.example.questionAnswer.model.Question;
import com.example.questionAnswer.repository.QuestionRepository;
import com.example.questionAnswer.util.QuestionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;


    public Long insertQuestion(QuestionDTO questionDTO) {
        Question question = questionMapper.toModel(questionDTO);

        // Initialize the votes map if it is a trivia question
        if (question.getType() == QuestionType.TRIVIA) {
            question.setVotes(new HashMap<>()); // Initialize votes if needed
        }
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.getId();
    }

    public QuestionDTO getQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        return questionMapper.toDTO(question);
    }

    public VoteResponseDTO vote(Long id, String answer) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        // Increment the vote for the provided answer
        Map<String, Integer> votes = question.getVotes();
        votes.put(answer, votes.getOrDefault(answer, 0) + 1);
        question.setVotes(votes);

        // Check if the answer is correct for trivia questions
        boolean isCorrect = question.getType() == QuestionType.TRIVIA && question.getCorrectAnswer().equals(answer);

        questionRepository.save(question);

        return new VoteResponseDTO(votes, isCorrect, question.getType().name());
    }
}
