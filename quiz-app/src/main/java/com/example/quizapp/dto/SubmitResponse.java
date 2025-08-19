package com.example.quizapp.dto;

public class SubmitResponse {
    private int score;
    private int total;

    public SubmitResponse(int score, int total) {
        this.score = score; this.total = total;
    }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}
