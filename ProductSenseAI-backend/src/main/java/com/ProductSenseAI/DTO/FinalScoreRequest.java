package com.ProductSenseAI.DTO;

public class FinalScoreRequest {
	private String sessionId;
    private String allAnswers; // All user answers as a combined string
    
    public FinalScoreRequest() {}
    
    public FinalScoreRequest(String sessionId, String allAnswers) {
        this.sessionId = sessionId;
        this.allAnswers = allAnswers;
    }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getAllAnswers() { return allAnswers; }
    public void setAllAnswers(String allAnswers) { this.allAnswers = allAnswers; }
}
