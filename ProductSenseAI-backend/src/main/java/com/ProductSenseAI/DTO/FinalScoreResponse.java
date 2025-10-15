package com.ProductSenseAI.DTO;

public class FinalScoreResponse {
	private String feedback;
    private String thankYouMessage;
    
    public FinalScoreResponse(String feedback, String thankYouMessage) {
        this.feedback = feedback;
        this.thankYouMessage = thankYouMessage;
    }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    
    public String getThankYouMessage() { return thankYouMessage; }
    public void setThankYouMessage(String thankYouMessage) { this.thankYouMessage = thankYouMessage; }

}
