package com.example.quizapp.dto;

import java.util.Map;

public class SubmitRequest {
    private Long userId;              // optional for anonymous
    private Map<Long, String> answers; // { questionId: "A"|"B"|"C"|"D" }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Map<Long, String> getAnswers() { return answers; }
    public void setAnswers(Map<Long, String> answers) { this.answers = answers; }
}
