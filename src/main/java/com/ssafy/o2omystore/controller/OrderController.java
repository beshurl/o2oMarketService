package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetailResponse;
import com.ssafy.o2omystore.dto.OrderSummaryResponse;
import com.ssafy.o2omystore.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order API", description = "주문 생성, 조회, 취소 등 주문 관련 API")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		
		this.orderService = orderService;
		
	}

	@Operation(summary = "사용자가 상품을 주문하면 주문과 주문 상세 내역을 생성한다.")
    @PostMapping
    public void createOrder(@RequestBody Order order) {
		    	
    	orderService.createOrder(order);
    	
    }

	@Operation(summary = "모든 사용자의 주문 내역을 반환한다.")
    @GetMapping
    public List<Order> getOrders() {
    	    	
    	return orderService.getOrders();
    	
    }

	@Operation(summary = "{orderId}에 해당 하는 주문의 주문 상세 정보를 반환한다.")
    @GetMapping("/{orderId}")
    public OrderDetailResponse getOrderDetail(@PathVariable int orderId) {
    	return orderService.getOrderDetailResponse(orderId);
    }
	
	@Operation(summary = "{userId}에 해당하는 사용자의 전체 주문 내역을 리스트로 반환한다.")
	@GetMapping("/user/{userId}")
	public List<Order> getOrdersByUserId(@PathVariable String userId) {
		
		return orderService.getOrdersByUserId(userId);
	}

	@Operation(summary = "{userId}에 해당하는 주문 리스트 요약 정보를 반환한다.")
	@GetMapping("/user/{userId}/summary")
	public List<OrderSummaryResponse> getOrderSummariesByUserId(@PathVariable String userId) {
		return orderService.getOrderSummariesByUserId(userId);
	}

	@Operation(summary = "{orderId}에 해당하는 주문 상세 정보를 반환한다.")
	@GetMapping("/{orderId}/detail")
	public OrderDetailResponse getOrderDetailResponse(@PathVariable int orderId) {
		return orderService.getOrderDetailResponse(orderId);
	}
	
	@Operation(summary = "{orderId}에 해당하는 주문을 취소합니다.")
	@DeleteMapping("/delete/{orderId}")
	public void cancelOrders(@PathVariable int orderId) {
		
		orderService.cancelOrders(orderId);
	}
};
