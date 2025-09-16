package com.gemini.TestGemini.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GeminiController {

    private final ChatClient chatClient;

    public GeminiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/ask")
    public String ask( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Ask Gemini anything:", required = true) @Valid @RequestBody String queryUser) {
    	
    	if(log.isDebugEnabled()) {
    		log.debug(String.format("userQuestion: %s", queryUser));
    	}
        String response =chatClient.prompt("you are a very friendly assistant")
            .user(queryUser)
            .call()
            .content();
        return response;
    }
}
