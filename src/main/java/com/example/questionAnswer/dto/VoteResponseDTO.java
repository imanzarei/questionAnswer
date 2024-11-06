package com.example.questionAnswer.dto;


import java.util.Map;

public class VoteResponseDTO {

    private Map<String, Integer> votes;
    private boolean isCorrect;
    private String questionType;

    public VoteResponseDTO(Map<String, Integer> votes, boolean isCorrect,String questionType) {
        this.votes = votes;
        this.isCorrect = isCorrect;
        this.questionType = questionType;
    }

    public Map<String, Integer> getVotes() {
        return votes;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setVotes(Map<String, Integer> votes) {
        this.votes = votes;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
