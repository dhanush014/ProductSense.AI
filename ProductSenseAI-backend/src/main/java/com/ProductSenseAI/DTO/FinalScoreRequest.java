package com.ProductSenseAI.DTO;

public class FinalScoreRequest {
private String sessionId;
    
    public FinalScoreRequest() {}
    
    public FinalScoreRequest(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
}
