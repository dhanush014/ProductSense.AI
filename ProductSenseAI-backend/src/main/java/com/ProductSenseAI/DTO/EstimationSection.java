package com.ProductSenseAI.DTO;


public class EstimationSection {
    private EstimationQuestion question;
    private double estimate;
    private String reasoning;

    // Constructors
    public EstimationSection() {}
    public EstimationSection(EstimationQuestion question, double estimate, String reasoning) {
        this.question = question;
        this.estimate = estimate;
        this.reasoning = reasoning;
    }

    // Getters and Setters
    public EstimationQuestion getQuestion() { return question; }
    public void setQuestion(EstimationQuestion question) { this.question = question; }
    public double getEstimate() { return estimate; }
    public void setEstimate(double estimate) { this.estimate = estimate; }
    public String getReasoning() { return reasoning; }
    public void setReasoning(String reasoning) { this.reasoning = reasoning; }
}
