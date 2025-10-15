package com.ProductSenseAI.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProductSenseAI.DTO.FinalScoreRequest;
import com.ProductSenseAI.DTO.FinalScoreResponse;
import com.ProductSenseAI.DTO.InterviewSession;
import com.ProductSenseAI.DTO.StartRequest;
import com.ProductSenseAI.DTO.StartResponse;
import com.ProductSenseAI.service.GeminiAIService;
import com.ProductSenseAI.service.SessionManager;

@RestController
@RequestMapping("/api")
public class InterviewController {
	private final GeminiAIService geminiAIService;
    private final SessionManager sessionManager;

    public InterviewController(GeminiAIService geminiAIService, SessionManager sessionManager) {
        this.geminiAIService = geminiAIService;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/start")
    public ResponseEntity<StartResponse> startInterview(@RequestBody StartRequest request) {
        // Generate unique session ID
        String sessionId = UUID.randomUUID().toString();
        
        // Generate witty greeting
        String greeting = geminiAIService.generateWittyGreeting(request.getName());
        
        // Generate all interview questions in one call
        String questions = geminiAIService.generateAllInterviewQuestions(request.getChoice());
        
        // Save session
        InterviewSession session = new InterviewSession(sessionId, request.getName(), 
                                                        request.getChoice(), questions);
        sessionManager.saveSession(session);
        
        // Return response
        StartResponse response = new StartResponse(sessionId, greeting, questions);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/final-score")
    public ResponseEntity<FinalScoreResponse> getFinalScore(@RequestBody FinalScoreRequest request) {
        // Retrieve session
        InterviewSession session = sessionManager.getSession(request.getSessionId());
        
        if (session == null) {
            return ResponseEntity.badRequest().build();
        }
        
        // Save answers to session
        session.setAllAnswers(request.getAllAnswers());
        
        // Grade the interview
        String feedback = geminiAIService.gradeInterview(session.getUserName(), 
                                                         session.getChoice(), 
                                                         request.getAllAnswers());
        
        String thankYou = "Thank you for taking the time to interview with us, " + 
                         session.getUserName() + "!";
        
        // Clean up session
        sessionManager.removeSession(request.getSessionId());
        
        FinalScoreResponse response = new FinalScoreResponse(feedback, thankYou);
        return ResponseEntity.ok(response);
    }

}
