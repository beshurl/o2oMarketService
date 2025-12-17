package com.ssafy.o2omystore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.OrderDao;
import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetail;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderDao orderDao;
	
	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void createOrder(Order order) {
		
		int totalPrice = 0;
		
		for (OrderDetail od : order.getOrderDetails()) {
			
			// totalPrice 구하기
			totalPrice += (od.getQuantity() * od.getPrice());
			
		}
		
		order.setTotalPrice(totalPrice);
		orderDao.insertOrder(order);
		
		for (OrderDetail od : order.getOrderDetails()) {
			
			// orderDetail에 orderId 넣기
			od.setOrderId(order.getOrderId());
			
		}
		
		orderDao.insertOrderDetail(order.getOrderDetails());
		
	}

	public List<Order> getOrders() {
		
		return orderDao.selectAllOrder();
		
	}
	
	public List<OrderDetail> getOrderDetail(int orderId) {
		
		return orderDao.selectOrderDetailByOrderId(orderId);
	}
	

	
	

}
