package com.ProductSenseAI.DTO;

import java.util.List;

public class EstimationQuestion {
	private String question;
private List<String> hints;
    
    public EstimationQuestion() {}
    
    public EstimationQuestion(String question, List<String> hints) {
        this.question = question;
        this.hints = hints;
    }
    
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    
    public List<String> getHints() { return hints; }
    public void setHints(List<String> hints) { this.hints = hints; }
}
