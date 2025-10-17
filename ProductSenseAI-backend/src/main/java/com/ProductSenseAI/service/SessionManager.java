package com.ProductSenseAI.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.ProductSenseAI.DTO.InterviewSession;
@Service
public class SessionManager {
    private final ConcurrentHashMap<String, InterviewSession> sessions = new ConcurrentHashMap<>();
    
    public void saveSession(InterviewSession session) {
        sessions.put(session.getSessionId(), session);
    }
    
    public InterviewSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }
    
    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }
}
