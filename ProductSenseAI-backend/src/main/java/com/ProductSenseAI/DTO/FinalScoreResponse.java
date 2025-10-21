package com.ProductSenseAI.DTO;

public class FinalScoreResponse {
    private int score;
    private String feedback;
    private String suggestion;
    private String closing;

    // Constructors
    public FinalScoreResponse() {}
    
    public FinalScoreResponse(int score, String feedback, String suggestion, String closing) {
        this.score = score;
        this.feedback = feedback;
        this.suggestion = suggestion;
        this.closing = closing;
    }

    // Getters and Setters
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    public String getSuggestion() { return suggestion; }
    public void setSuggestion(String suggestion) { this.suggestion = suggestion; }
    public String getClosing() { return closing; }
    public void setClosing(String closing) { this.closing = closing; }
}
