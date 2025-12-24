package com.ssafy.o2omystore.chat.controller;

import com.ssafy.o2omystore.chat.dto.ChatRequest;
import com.ssafy.o2omystore.chat.dto.ChatResponse;
import com.ssafy.o2omystore.chat.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private static final Logger log =
            LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {

        log.info("Chat 요청 수신 userId={}", request.getUserId());
        log.debug("Chat 메시지 내용={}", request.getMessage());

        ChatResponse response = chatService.ask(
                request.getUserId(),
                request.getMessage()
        );

        log.info("Chat 응답 완료 userId={}", request.getUserId());
        log.debug(
        	    "Chat 응답 answer={}, productCount={}",
        	    response.getAnswer(),
        	    response.getProducts() == null ? 0 : response.getProducts().size()
        	);

        return response;
    }
}
