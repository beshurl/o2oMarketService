package com.ssafy.o2omystore.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.o2omystore.dao.OrderDao;
import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetail;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	private final OrderDao orderDao;
	private final ProductService productService;
	
	public OrderServiceImpl(OrderDao orderDao, ProductService productService) {
		this.orderDao = orderDao;
		this.productService = productService;
	}

	@Transactional
	public void createOrder(Order order) {
		
		// 상품을 추가하지 않고 주문한 경우
		if (order.getOrderDetails() == null || order.getOrderDetails().isEmpty()) {
			
			throw new IllegalArgumentException("주문 상품이 없습니다.");
		}
		
		// 주문한 개수 만큼 현재 재고 차감
		for (OrderDetail od : order.getOrderDetails()) {
						
			int update = productService.decreaseStock(od.getProductId(), od.getQuantity());
			
			if (update == 0) {
				throw new IllegalStateException("재고가 부족합니다.");
			}
			
		}
		
		int totalPrice = 0;
		
		// totalPrice 구하기
		for (OrderDetail od : order.getOrderDetails()) {
			
			int price = productService.getPriceByProductId(od.getProductId());
			
			od.setPrice(price);
			totalPrice += (od.getQuantity() * price);
			
		}
		
		order.setTotalPrice(totalPrice);
		order.setStatus("주문 완료");
		orderDao.insertOrder(order);
		
		// orderDetail에 orderId 넣기
		for (OrderDetail od : order.getOrderDetails()) {
			
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

	public List<Order> getOrdersByUserId(String userId) {
		return orderDao.selectOrdersByUserId(userId);
	}

}
