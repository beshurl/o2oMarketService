package com.ssafy.o2omystore.service;

import java.util.List;

import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetail;

public interface OrderService {
	
	void createOrder(Order order);
	
	List<Order> getOrders();
	
	List<OrderDetail> getOrderDetail(int orderId);
	
	List<Order> getOrdersByUserId(String userId);
}
