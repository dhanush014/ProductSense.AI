package com.ProductSenseAI.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ProductSenseAI.DTO.*;
import java.util.List;


@Service
public class GeminiAIService {
	private final ChatClient chatClient;
    private final ObjectMapper objectMapper;
    
    public GeminiAIService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
        this.objectMapper = new ObjectMapper();
    }

    public String generateWittyGreeting(String userName) {
        String prompt = "You are a Principal PM at a big tech company conducting a product management interview. " +
                        "Greet " + userName + " with a witty and professional introduction in 2-3 sentences. " +
                        "Keep it brief and engaging. Return only the greeting text, no extra formatting.";
        
        return chatClient.prompt()
                         .user(prompt)
                         .call()
                         .content();
    }
    public List<MCQQuestion> generateAllMCQQuestions(String choice) {
        String prompt = "You are conducting a product management interview for a " + choice + " PM role. " +
                        "Generate exactly 5 multiple-choice questions. " +
                        "Return ONLY a valid JSON array of 5 objects. Each object must have this exact structure:\n" +
                        "{\n" +
                        "  \"question\": \"Your question text here\",\n" +
                        "  \"options\": [\"Option A: text\", \"Option B: text\", \"Option C: text\", \"Option D: text\"]\n" +
                        "}\n\n" +
                        "Example format:\n" +
                        "[\n" +
                        "  {\"question\": \"What is...\", \"options\": [\"Option A: ...\", \"Option B: ...\", \"Option C: ...\", \"Option D: ...\"]},\n" +
                        "  {\"question\": \"Which metric...\", \"options\": [\"Option A: ...\", \"Option B: ...\", \"Option C: ...\", \"Option D: ...\"]}\n" +
                        "]\n\n" +
                        "Do NOT include markdown formatting, explanations, or any text outside the JSON array.";
        
        String response = chatClient.prompt()
                                    .user(prompt)
                                    .call()
                                    .content();
        
        return parseJSONArray(response, new TypeReference<List<MCQQuestion>>(){});
    }

    public RCAQuestion generateRCAQuestion(String choice) {
        String focusArea = choice.equals("growth") ? "go-to-market strategy" : "root cause analysis with data points";
        
        String prompt = "You are conducting a product management interview for a " + choice + " PM role. " +
                        "Generate exactly ONE " + focusArea + " question. " +
                        "Return ONLY a valid JSON object with this exact structure:\n" +
                        "{\n" +
                        "  \"scenario\": \"Detailed scenario description\",\n" +
                        "  \"dataPoints\": [\"Data point 1\", \"Data point 2\", \"Data point 3\"],\n" +
                        "  \"followUp\": \"How would you investigate the root cause?\"\n" +
                        "}\n" +
                        "Do NOT include explanations, markdown formatting, or any text outside the JSON object.";
        
        String response = chatClient.prompt()
                                    .user(prompt)
                                    .call()
                                    .content();
        
        return parseJSON(response, RCAQuestion.class);
    }
    public EstimationQuestion generateEstimationQuestion(String choice) {
        String prompt = "You are conducting a product management interview for a " + choice + " PM role. " +
                        "Generate exactly ONE estimation question relevant to this role. " +
                        "Return ONLY a valid JSON object with this exact structure:\n" +
                        "{\n" +
                        "  \"question\": \"Your estimation question text\",\n" +
                        "  \"hints\": [\"Hint 1\", \"Hint 2\", \"Hint 3\", \"Hint 4\"]\n" +
                        "}\n" +
                        "Do NOT include explanations, markdown formatting, or any text outside the JSON object.";
        
        String response = chatClient.prompt()
                                    .user(prompt)
                                    .call()
                                    .content();
        
        return parseJSON(response, EstimationQuestion.class);
    }
    public FinalScoreResponse gradeInterview(FinalScoreRequest request) {
        String prompt = buildGradingPrompt(request);
        
        String response = chatClient.prompt()
                                    .user(prompt)
                                    .call()
                                    .content();
        
        return parseJSON(response, FinalScoreResponse.class);
    }

    
    private String buildGradingPrompt(FinalScoreRequest req) {
    	StringBuilder prompt = new StringBuilder();
        
        prompt.append("You are a product management interviewer. Grade the candidate's interview for a ")
              .append(req.getChoice())
              .append(" PM role.\n\n");
        prompt.append("=== MCQ Questions and Candidate Answers ===\n");
        int mcqIndex = 1;
        for (MCQAnswer mcq : req.getMcqAnswers()) {
            prompt.append("Q").append(mcqIndex).append(": ").append(mcq.getQuestion()).append("\n");
            prompt.append("A").append(mcqIndex).append(": ").append(mcq.getAnswer()).append("\n\n");
            mcqIndex++;
        }
        prompt.append("=== Root Cause Analysis ===\n");
        prompt.append("Scenario: ").append(req.getRca().getQuestion().getScenario()).append("\n");
        prompt.append("Data Points:\n");
        for (String dp : req.getRca().getQuestion().getDataPoints()) {
            prompt.append("  - ").append(dp).append("\n");
        }
        prompt.append("Follow-up: ").append(req.getRca().getQuestion().getFollowUp()).append("\n");
        prompt.append("Candidate's Answer: ").append(req.getRca().getAnswer()).append("\n\n");

        prompt.append("=== Estimation Question ===\n");
        prompt.append("Question: ").append(req.getEstimation().getQuestion().getQuestion()).append("\n");
        prompt.append("Hints:\n");
        for (String hint : req.getEstimation().getQuestion().getHints()) {
            prompt.append("  - ").append(hint).append("\n");
        }
        prompt.append("Estimate: ").append(req.getEstimation().getEstimate()).append("\n");
        prompt.append("Reasoning: ").append(req.getEstimation().getReasoning()).append("\n\n");

        prompt.append("=== Grading Instructions ===\n");
        prompt.append("Return ONLY valid JSON with this exact structure:\n");
        prompt.append("{\n");
        prompt.append("  \"score\": <number 0-100>,\n");
        prompt.append("  \"feedback\": \"<Detailed feedback on strengths and areas for improvement>\",\n");
        prompt.append("  \"suggestion\": \"<At least one actionable suggestion for growth>\",\n");
        prompt.append("  \"closing\": \"<Encouraging closing message>\"\n");
        prompt.append("}\n\n");
        prompt.append("Do NOT include markdown formatting or any text outside the JSON object.");

        return prompt.toString();
        
    }
    


    private <T> T parseJSON(String jsonString, Class<T> valueType) {
        try {
            jsonString = cleanJSON(jsonString);
            return objectMapper.readValue(jsonString, valueType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response: " + jsonString, e);
        }
    }
    

    private <T> T parseJSONArray(String jsonString, TypeReference<T> typeReference) {
        try {
            jsonString = cleanJSON(jsonString);
            return objectMapper.readValue(jsonString, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON array response: " + jsonString, e);
        }
    }

    private String cleanJSON(String jsonString) {
        jsonString = jsonString.trim();
        if (jsonString.startsWith("```")) {
            jsonString = jsonString.substring(7);
        }
        if (jsonString.startsWith("```")) {
            jsonString = jsonString.substring(3);
        }
        if (jsonString.endsWith("```")) {
            jsonString = jsonString.substring(0, jsonString.length() - 3);
        }
        return jsonString.trim();
    }
}
