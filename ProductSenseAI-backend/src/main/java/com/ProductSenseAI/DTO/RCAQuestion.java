package com.ProductSenseAI.DTO;

import java.util.List;
public class RCAQuestion {
	private String scenario;
    private List<String> dataPoints;
    private String followUp;
    
    public RCAQuestion() {}
    
    public RCAQuestion(String scenario, List<String> dataPoints, String followUp) {
        this.scenario = scenario;
        this.dataPoints = dataPoints;
        this.followUp = followUp;
    }
    
    public String getScenario() { return scenario; }
    public void setScenario(String scenario) { this.scenario = scenario; }
    
    public List<String> getDataPoints() { return dataPoints; }
    public void setDataPoints(List<String> dataPoints) { this.dataPoints = dataPoints; }
    
    public String getFollowUp() { return followUp; }
    public void setFollowUp(String followUp) { this.followUp = followUp; }
}
