package com.ProductSenseAI.DTO;

public class StartResponse {
	private String sessionId;
    private String wittyGreeting;
    private String questions;
    
    public StartResponse(String sessionId, String wittyGreeting, String questions) {
        this.sessionId = sessionId;
        this.wittyGreeting = wittyGreeting;
        this.questions = questions;
    }
    
    // Getters and Setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getWittyGreeting() { return wittyGreeting; }
    public void setWittyGreeting(String wittyGreeting) { this.wittyGreeting = wittyGreeting; }
    
    public String getQuestions() { return questions; }
    public void setQuestions(String questions) { this.questions = questions; }
}
