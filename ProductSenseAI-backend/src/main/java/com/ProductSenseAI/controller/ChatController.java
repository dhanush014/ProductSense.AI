package com.ProductSenseAI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ChatController {
private final ChatClient chatClient;

public ChatController(ChatClient.Builder builder) {

	this.chatClient = builder.build();
}

@GetMapping("/chat")
public ChatResponse chat() {
	return chatClient.prompt().user("tell me something fun about google taht the employees get to do").call().chatResponse();
}
}
