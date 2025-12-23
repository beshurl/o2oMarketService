package com.ssafy.o2omystore.chat.controller;

import com.ssafy.o2omystore.chat.dto.ChatRequest;
import com.ssafy.o2omystore.chat.dto.ChatResponse;
import com.ssafy.o2omystore.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {

        ChatResponse response = chatService.ask(
                request.getUserId(),
                request.getMessage()
        );

        return response;
    }
}
