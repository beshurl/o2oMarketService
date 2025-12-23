package com.ssafy.o2omystore.chat.service;

public interface ChatService {
    com.ssafy.o2omystore.chat.dto.ChatResponse ask(String userId, String message);
}
