package com.ProductSenseAI.DTO;
import java.util.List;

public class MCQQuestionsResponse {
    private List<MCQQuestion> questions;
    
    public MCQQuestionsResponse() {}
    
    public MCQQuestionsResponse(List<MCQQuestion> questions) {
        this.questions = questions;
    }
    
    public List<MCQQuestion> getQuestions() { return questions; }
    public void setQuestions(List<MCQQuestion> questions) { this.questions = questions; }
}
