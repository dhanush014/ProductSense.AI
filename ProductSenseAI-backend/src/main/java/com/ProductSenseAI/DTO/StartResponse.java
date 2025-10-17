package com.ProductSenseAI.DTO;

public class StartResponse {
	private String sessionId;
    private String wittyGreeting;
    
    public StartResponse(String sessionId, String wittyGreeting) {
        this.sessionId = sessionId;
        this.wittyGreeting = wittyGreeting;
    }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getWittyGreeting() { return wittyGreeting; }
    public void setWittyGreeting(String wittyGreeting) { this.wittyGreeting = wittyGreeting; 
    }
}
