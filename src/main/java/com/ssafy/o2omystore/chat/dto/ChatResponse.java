package com.ssafy.o2omystore.chat.dto;

public class ChatResponse {

    private String answer;
    private java.util.List<com.ssafy.o2omystore.dto.Product> products;

    public ChatResponse(String answer) {
        this.answer = answer;
    }

    public ChatResponse(String answer, java.util.List<com.ssafy.o2omystore.dto.Product> products) {
        this.answer = answer;
        this.products = products;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public java.util.List<com.ssafy.o2omystore.dto.Product> getProducts() {
        return products;
    }

    public void setProducts(java.util.List<com.ssafy.o2omystore.dto.Product> products) {
        this.products = products;
    }
}
