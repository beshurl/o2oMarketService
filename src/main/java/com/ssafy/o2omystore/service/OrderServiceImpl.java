package com.ssafy.o2omystore.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.o2omystore.dao.OrderDao;
import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetail;
import com.ssafy.o2omystore.dto.OrderDetailResponse;
import com.ssafy.o2omystore.dto.OrderProductResponse;
import com.ssafy.o2omystore.dto.OrderSummaryResponse;
import com.ssafy.o2omystore.dto.Product;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	private static final DateTimeFormatter ORDER_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
			
			Product product = productService.getProductById(od.getProductId());
			int originalPrice = 0;
			if (product != null && product.getPrice() != null) {
				originalPrice = product.getPrice();
			} else {
				originalPrice = productService.getPriceByProductId(od.getProductId());
			}

			int finalPrice = originalPrice;
			boolean discountApplied = false;
			if (product != null && product.getFinalPrice() != null && product.getFinalPrice() > 0
					&& product.getFinalPrice() < originalPrice) {
				finalPrice = product.getFinalPrice();
				discountApplied = true;
			}

			od.setPrice(finalPrice);
			od.setDiscountApplied(discountApplied);
			totalPrice += (od.getQuantity() * finalPrice);
			
		}
		
		order.setProductTotal(totalPrice);
		int deliveryFee = order.getDeliveryFee();
		int discount = order.getDiscount();
		order.setTotalPrice(totalPrice + deliveryFee - discount);
		if (order.getStatus() == null || order.getStatus().isBlank()) {
			order.setStatus("PENDING");
		}
//		int totalPrice = 0;
//		
//		// totalPrice 구하기
//		for (OrderDetail od : order.getOrderDetails()) {
//			
//			int price = productService.getPriceByProductId(od.getProductId());
//			
//			od.setPrice(price);
//			totalPrice += (od.getQuantity() * price);
//			
//		}
//		
//		order.setTotalPrice(totalPrice);
		order.setStatus("주문 완료");

		order.setOrderTime(LocalDateTime.now());
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

	@Override
	public List<OrderSummaryResponse> getOrderSummariesByUserId(String userId) {
		List<Order> orders = orderDao.selectOrdersByUserId(userId);
		List<OrderSummaryResponse> responses = new ArrayList<>();
		if (orders == null) {
			return responses;
		}

		for (Order order : orders) {
			OrderSummaryResponse response = new OrderSummaryResponse();
			response.setOrderNumber(String.valueOf(order.getOrderId()));
			response.setOrderDate(formatDate(order.getOrderTime()));
			response.setStatus(order.getStatus());
			response.setTotalAmount(order.getTotalPrice());

			List<OrderDetail> details = order.getOrderDetails();
			if (details != null && !details.isEmpty()) {
				OrderDetail first = details.stream()
					.min(Comparator.comparingInt(OrderDetail::getOrderItemId))
					.orElse(details.get(0));
				Product product = productService.getProductById(first.getProductId());
				if (product != null) {
					response.setProductName(product.getName());
					response.setProductImage(product.getImage());
				}

				int productCount = details.size();
				response.setProductCount(productCount);
				response.setAdditionalCount(Math.max(productCount - 1, 0));
			} else {
				response.setProductCount(0);
				response.setAdditionalCount(0);
			}

			responses.add(response);
		}

		return responses;
	}

	@Override
	public OrderDetailResponse getOrderDetailResponse(int orderId) {
		Order order = orderDao.selectOrderById(orderId);
		if (order == null) {
			return null;
		}

		OrderDetailResponse response = new OrderDetailResponse();
		response.setOrderNumber(String.valueOf(order.getOrderId()));
		response.setOrderDate(formatDate(order.getOrderTime()));
		response.setStatus(order.getStatus());
		response.setRecipientName(order.getRecipientName());
		response.setPhone(order.getPhone());
		response.setAddress(order.getAddress());
		response.setAddressDetail(order.getAddressDetail());
		response.setZipCode(order.getZipCode());
		response.setPaymentMethod(order.getPaymentMethod());
		response.setCarrier(order.getCarrier());
		response.setTrackingNumber(order.getTrackingNumber());
		response.setEstimatedDate(formatDate(order.getEstimatedDate()));

		List<OrderProductResponse> products = new ArrayList<>();
		int calculatedProductTotal = 0;
		if (order.getOrderDetails() != null) {
			for (OrderDetail detail : order.getOrderDetails()) {
				OrderProductResponse productResponse = new OrderProductResponse();
				productResponse.setId(detail.getProductId());
				productResponse.setQuantity(detail.getQuantity());

				Product product = productService.getProductById(detail.getProductId());
				if (product != null) {
					productResponse.setName(product.getName());
					productResponse.setImage(product.getImage());
				}

				int originalPrice = detail.getPrice();
				if (originalPrice == 0 && product != null && product.getPrice() != null) {
					originalPrice = product.getPrice();
				}
				int discountedPrice = originalPrice;
				if (product != null && product.getFinalPrice() != null && product.getFinalPrice() > 0) {
					discountedPrice = product.getFinalPrice();
				}

				int discountRate = 0;
				if (product != null && product.getDiscountRate() != null) {
					discountRate = product.getDiscountRate();
				} else if (originalPrice > 0 && discountedPrice > 0 && originalPrice != discountedPrice) {
					discountRate = (originalPrice - discountedPrice) * 100 / originalPrice;
				}

				productResponse.setOriginalPrice(originalPrice);
				productResponse.setDiscountedPrice(discountedPrice);
				productResponse.setDiscountRate(discountRate);
				int lineTotal = discountedPrice * detail.getQuantity();
				productResponse.setTotalPrice(lineTotal);

				calculatedProductTotal += lineTotal;
				products.add(productResponse);
			}
		}

		response.setProducts(products);
		int productTotal = order.getProductTotal() > 0 ? order.getProductTotal() : calculatedProductTotal;
		response.setProductTotal(productTotal);
		response.setDeliveryFee(order.getDeliveryFee());
		response.setDiscount(order.getDiscount());
		response.setTotalDiscount(order.getDiscount());
		int totalAmount = order.getTotalPrice();
		if (totalAmount == 0) {
			totalAmount = productTotal + order.getDeliveryFee() - order.getDiscount();
		}
		response.setTotalAmount(totalAmount);

		return response;
	}

	@Override
	public void cancelOrders(int orderId) {
		orderDao.deletelOrders(orderId);
	}

	private String formatDate(LocalDateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		return dateTime.format(ORDER_DATE_FORMAT);
	}

}
