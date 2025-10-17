package com.ProductSenseAI.DTO;

public class StartRequest {
	private String name;
    private String choice; // "platform", "data", or "growth"
    
    public StartRequest() {}
    
    public StartRequest(String name, String choice) {
        this.name = name;
        this.choice = choice;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getChoice() { return choice; }
    public void setChoice(String choice) { this.choice = choice; }
}
