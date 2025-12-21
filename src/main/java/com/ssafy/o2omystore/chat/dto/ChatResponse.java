package com.ssafy.o2omystore.chat.dto;

public class ChatResponse {

    private String answer;

    public ChatResponse(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
