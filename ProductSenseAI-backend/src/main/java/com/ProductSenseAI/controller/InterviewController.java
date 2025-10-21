package com.ProductSenseAI.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProductSenseAI.DTO.EstimationQuestion;
import com.ProductSenseAI.DTO.FinalScoreRequest;
import com.ProductSenseAI.DTO.FinalScoreResponse;
import com.ProductSenseAI.DTO.InterviewSession;
import com.ProductSenseAI.DTO.MCQQuestion;
import com.ProductSenseAI.DTO.MCQQuestionsResponse;
import com.ProductSenseAI.DTO.QuestionRequest;
import com.ProductSenseAI.DTO.RCAQuestion;
import com.ProductSenseAI.DTO.StartRequest;
import com.ProductSenseAI.DTO.StartResponse;
import com.ProductSenseAI.service.GeminiAIService;
import com.ProductSenseAI.service.SessionManager;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InterviewController {
	 private final GeminiAIService geminiAIService;
	    private final SessionManager sessionManager;

	    public InterviewController(GeminiAIService geminiAIService, SessionManager sessionManager) {
	        this.geminiAIService = geminiAIService;
	        this.sessionManager = sessionManager;
	    }

	    @PostMapping("/start")
	    public ResponseEntity<StartResponse> startInterview(@RequestBody StartRequest request) {
	        String sessionId = UUID.randomUUID().toString();
	        String greeting = geminiAIService.generateWittyGreeting(request.getName());
	        
	        InterviewSession session = new InterviewSession(sessionId, request.getName(), request.getChoice());
	        sessionManager.saveSession(session);
	        
	        StartResponse response = new StartResponse(sessionId, greeting);
	        return ResponseEntity.ok(response);
	    }

	    // NEW: Get all 5 MCQ questions at once
	    @PostMapping("/question/mcq")
	    public ResponseEntity<MCQQuestionsResponse> getAllMCQQuestions(@RequestBody QuestionRequest request) {
	        InterviewSession session = sessionManager.getSession(request.getSessionId());
	        
	        if (session == null) {
	            return ResponseEntity.badRequest().build();
	        }
	        
	        List<MCQQuestion> questions = geminiAIService.generateAllMCQQuestions(session.getChoice());
	        MCQQuestionsResponse response = new MCQQuestionsResponse(questions);
	        
	        return ResponseEntity.ok(response);
	    }

	    @PostMapping("/question/rca")
	    public ResponseEntity<RCAQuestion> getRCAQuestion(@RequestBody QuestionRequest request) {
	        InterviewSession session = sessionManager.getSession(request.getSessionId());
	        
	        if (session == null) {
	            return ResponseEntity.badRequest().build();
	        }
	        
	        RCAQuestion question = geminiAIService.generateRCAQuestion(session.getChoice());
	        return ResponseEntity.ok(question);
	    }

	    @PostMapping("/question/estimation")
	    public ResponseEntity<EstimationQuestion> getEstimationQuestion(@RequestBody QuestionRequest request) {
	        InterviewSession session = sessionManager.getSession(request.getSessionId());
	        
	        if (session == null) {
	            return ResponseEntity.badRequest().build();
	        }
	        
	        EstimationQuestion question = geminiAIService.generateEstimationQuestion(session.getChoice());
	        return ResponseEntity.ok(question);
	    }


	    
	    @PostMapping("/final-score")
	    public ResponseEntity<FinalScoreResponse> getFinalScore(@RequestBody FinalScoreRequest request) {
	        // Validate request (optional but recommended)
	        if (request.getMcqAnswers() == null || request.getRca() == null || request.getEstimation() == null) {
	            return ResponseEntity.badRequest().build();
	        }
	        
	        	        FinalScoreResponse response = geminiAIService.gradeInterview(request);
	        
	        return ResponseEntity.ok(response);
	    }
	    }

