package com.ProductSenseAI.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiAIService {
	private final ChatClient chatClient;
	
	public GeminiAIService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String generateWittyGreeting(String userName) {
        String prompt = "You are an interviewer for a product management role. You are a Principal PM at a big tech company" +
                        "Greet " + userName + " with a witty and professional introduction. " +
                        "Keep it brief and engaging.";
        
        return chatClient.prompt()
                         .user(prompt)
                         .call()
                         .content();
    }

    public String generateAllInterviewQuestions(String choice) {
    	String prompt = "You are conducting a product management interview. " +
                "Generate the following questions for a " + choice + " PM role:\n\n" +
                "1. Five multiple-choice questions (MCQ) with 4 options each (A, B, C, D). " +
                "Clearly mark the correct answer.\n\n" +
                "2. One root cause analysis question " +
                (choice.equals("growth") ? "focused on go-to-market strategy" : "with 3 data points as hints") + ".\n\n" +
                "3. One estimation question with 2 hints.\n\n" +
                "Format the output clearly with section headers.";

    	return chatClient.prompt()
                 .user(prompt)
                 .call()
                 .content();
    }
    
    public String gradeInterview(String userName, String choice, String allAnswers) {
        String prompt = "You are a product management interviewer. " +
                        "Grade the following interview answers for " + userName + 
                        " who interviewed for a " + choice + " PM role.\n\n" +
                        "Interview Answers:\n" + allAnswers + "\n\n" +
                        "Provide:\n" +
                        "1. A score out of 100\n" +
                        "2. Detailed feedback on strengths and areas for improvement\n" +
                        "3. Specific suggestions for each question type\n" +
                        "4. An encouraging closing message";
        
        return chatClient.prompt()
                         .user(prompt)
                         .call()
                         .content();
    }
}
