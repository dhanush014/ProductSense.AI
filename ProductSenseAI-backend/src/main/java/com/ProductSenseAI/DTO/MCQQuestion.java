package com.ProductSenseAI.DTO;

import java.util.List;

public class MCQQuestion {
	private String question;
    private List<String> options;
    
public MCQQuestion() {}
    
    public MCQQuestion(String question, List<String> options) {
        this.question = question;
        this.options = options;
    }
    
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; 
    
    }

    
}
