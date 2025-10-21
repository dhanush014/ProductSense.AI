package com.ProductSenseAI.DTO;


	import java.util.List;

	public class FinalScoreRequest {
	    private String userName;
	    private String choice;
	    private List<MCQAnswer> mcqAnswers;
	    private RCASection rca;
	    private EstimationSection estimation;

	    // Constructors
	    public FinalScoreRequest() {}
	    
	    public FinalScoreRequest(String userName, String choice, List<MCQAnswer> mcqAnswers, 
	                            RCASection rca, EstimationSection estimation) {
	        this.userName = userName;
	        this.choice = choice;
	        this.mcqAnswers = mcqAnswers;
	        this.rca = rca;
	        this.estimation = estimation;
	    }

	    // Getters and Setters
	    public String getUserName() { return userName; }
	    public void setUserName(String userName) { this.userName = userName; }
	    public String getChoice() { return choice; }
	    public void setChoice(String choice) { this.choice = choice; }
	    public List<MCQAnswer> getMcqAnswers() { return mcqAnswers; }
	    public void setMcqAnswers(List<MCQAnswer> mcqAnswers) { this.mcqAnswers = mcqAnswers; }
	    public RCASection getRca() { return rca; }
	    public void setRca(RCASection rca) { this.rca = rca; }
	    public EstimationSection getEstimation() { return estimation; }
	    public void setEstimation(EstimationSection estimation) { this.estimation = estimation; }
	}

