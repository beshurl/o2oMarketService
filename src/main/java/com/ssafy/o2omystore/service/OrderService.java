package com.ssafy.o2omystore.service;

import java.util.List;

import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetailResponse;
import com.ssafy.o2omystore.dto.OrderDetail;
import com.ssafy.o2omystore.dto.OrderSummaryResponse;

public interface OrderService {

	void createOrder(Order order);

	List<Order> getOrders();

	List<OrderDetail> getOrderDetail(int orderId);

	List<Order> getOrdersByUserId(String userId);

	List<OrderSummaryResponse> getOrderSummariesByUserId(String userId);

	OrderDetailResponse getOrderDetailResponse(int orderId);

	void cancelOrders(int orderId);

	void updateOrderStatus(int orderId, String status);

	int countOrdersByUserId(String userId);

	int countInProgressOrdersByUserId(String userId);
}
