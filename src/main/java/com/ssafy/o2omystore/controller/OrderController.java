package com.ssafy.o2omystore.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public void createOrder() {
    }

    @GetMapping
    public void getOrders() {
    }

    @GetMapping("/{orderId}")
    public void getOrderDetail(@PathVariable int orderId) {
    }
}
