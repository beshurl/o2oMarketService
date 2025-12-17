package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetail;
import com.ssafy.o2omystore.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		
		this.orderService = orderService;
		
	}

    @PostMapping
    public void createOrder(@RequestBody Order order) {
    	
    	orderService.createOrder(order);
    	
    }

    @GetMapping
    public List<Order> getOrders() {
    	    	
    	return orderService.getOrders();
    	
    }

    @GetMapping("/{orderId}")
    public List<OrderDetail> getOrderDetail(@PathVariable int orderId) {
    	
    	return orderService.getOrderDetail(orderId);
    }
}
