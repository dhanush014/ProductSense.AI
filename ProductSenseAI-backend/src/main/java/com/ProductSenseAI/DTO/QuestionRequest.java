package com.ProductSenseAI.DTO;

public class QuestionRequest {
	private String sessionId;
    private String questionType; // "mcq", "rca", "estimation"
    private Integer questionNumber; // For MCQ (1-5)
    
    public QuestionRequest() {}
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getQuestionType() { return questionType; }
    public void setQuestionType(String questionType) { this.questionType = questionType; }
    
    public Integer getQuestionNumber() { return questionNumber; }
    public void setQuestionNumber(Integer questionNumber) { this.questionNumber = questionNumber; }
}
