package com.ProductSenseAI.controller;

import java.util.UUID;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ProductSenseAI.DTO.*;
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
	    @PostMapping("/questions/mcq")
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

	    @PostMapping("/answer")
	    public ResponseEntity<Void> submitAnswer(@RequestBody AnswerRequest request) {
	        InterviewSession session = sessionManager.getSession(request.getSessionId());
	        
	        if (session == null) {
	            return ResponseEntity.badRequest().build();
	        }
	        
	        String key = request.getQuestionType() + 
	                    (request.getQuestionNumber() != null ? "-" + request.getQuestionNumber() : "");
	        session.addAnswer(key, request.getAnswer());
	        
	        return ResponseEntity.ok().build();
	    }
	    
	    @PostMapping("/final-score")
	    public ResponseEntity<FinalScoreResponse> getFinalScore(@RequestBody FinalScoreRequest request) {
	        InterviewSession session = sessionManager.getSession(request.getSessionId());
	        
	        if (session == null) {
	            return ResponseEntity.badRequest().build();
	        }
	        
	        String feedback = geminiAIService.gradeInterview(
	            session.getUserName(), 
	            session.getChoice(), 
	            session.getAllAnswersAsString()
	        );
	        
	        String thankYou = "Thank you for taking the time to interview with us, " + 
	                         session.getUserName() + "!";
	        
	        sessionManager.removeSession(request.getSessionId());
	        
	        FinalScoreResponse response = new FinalScoreResponse(feedback, thankYou);
	        return ResponseEntity.ok(response);
	    }
}
