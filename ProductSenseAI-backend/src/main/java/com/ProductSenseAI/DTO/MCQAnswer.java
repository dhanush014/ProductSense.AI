package com.ProductSenseAI.DTO;

public class MCQAnswer {
    private String question;
    private String answer;

    // Constructors
    public MCQAnswer() {}
    public MCQAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters and Setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
