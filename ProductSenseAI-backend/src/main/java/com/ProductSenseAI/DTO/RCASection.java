package com.ProductSenseAI.DTO;

public class RCASection {
    private RCAQuestion question;
    private String answer;

    // Constructors
    public RCASection() {}
    public RCASection(RCAQuestion question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters and Setters
    public RCAQuestion getQuestion() { return question; }
    public void setQuestion(RCAQuestion question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
