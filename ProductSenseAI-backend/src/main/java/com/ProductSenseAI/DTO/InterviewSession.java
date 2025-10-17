package com.ProductSenseAI.DTO;

import java.util.HashMap;
import java.util.Map;
public class InterviewSession {
	private String sessionId;
    private String userName;
    private String choice;
    private Map<String, String> answers; // key: questionType-number, value: answer
    
    public InterviewSession(String sessionId, String userName, String choice) {
        this.sessionId = sessionId;
        this.userName = userName;
        this.choice = choice;
        this.answers = new HashMap<>();
    }
    public String getSessionId() { return sessionId; }
    public String getUserName() { return userName; }
    public String getChoice() { return choice; }
    public Map<String, String> getAnswers() { return answers; }
    
    public void addAnswer(String key, String answer) {
        answers.put(key, answer);
    }
    
    public String getAllAnswersAsString() {
        StringBuilder sb = new StringBuilder();
        answers.forEach((key, value) -> {
            sb.append(key).append(": ").append(value).append("\n");
        });
        return sb.toString();
    }
}
