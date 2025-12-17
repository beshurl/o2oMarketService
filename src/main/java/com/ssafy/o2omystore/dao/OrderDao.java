package com.ssafy.o2omystore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetail;

@Mapper
public interface OrderDao {

	int insertOrder(Order order);
	
	int insertOrderDetail(List<OrderDetail> orderDetails);
	
	List<Order> selectAllOrder();
	
	List<OrderDetail> selectOrderDetailByOrderId(int orderId);
	
	List<Order> selectOrdersByUserId(String userId);
	
	void deletelOrders(int orderId);
	
}
