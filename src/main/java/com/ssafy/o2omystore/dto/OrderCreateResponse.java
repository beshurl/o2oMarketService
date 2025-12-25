package com.ssafy.o2omystore.dto;

public class OrderCreateResponse {

    private int orderId;

    public OrderCreateResponse() {
    }

    public OrderCreateResponse(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
