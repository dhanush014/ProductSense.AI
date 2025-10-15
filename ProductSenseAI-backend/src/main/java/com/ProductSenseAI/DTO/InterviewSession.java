package com.ProductSenseAI.DTO;

public class InterviewSession {
	private String sessionId;
    private String userName;
    private String choice;
    private String questions;
    private String allAnswers;
    
    public InterviewSession(String sessionId, String userName, String choice, String questions) {
        this.sessionId = sessionId;
        this.userName = userName;
        this.choice = choice;
        this.questions = questions;
    }
    
    // Getters and Setters
    public String getSessionId() { return sessionId; }
    public String getUserName() { return userName; }
    public String getChoice() { return choice; }
    public String getQuestions() { return questions; }
    public String getAllAnswers() { return allAnswers; }
    public void setAllAnswers(String allAnswers) { this.allAnswers = allAnswers; }
}
