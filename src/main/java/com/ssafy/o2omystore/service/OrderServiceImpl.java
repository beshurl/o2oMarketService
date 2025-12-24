package com.ssafy.o2omystore.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.o2omystore.FCM.FCMService;
import com.ssafy.o2omystore.dao.OrderDao;
import com.ssafy.o2omystore.dto.Coupon;
import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetail;
import com.ssafy.o2omystore.dto.OrderDetailResponse;
import com.ssafy.o2omystore.dto.OrderProductResponse;
import com.ssafy.o2omystore.dto.OrderSummaryResponse;
import com.ssafy.o2omystore.dto.PointHistory;
import com.ssafy.o2omystore.dto.Product;
import com.ssafy.o2omystore.dto.User;
import com.ssafy.o2omystore.dto.Notification;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	private static final DateTimeFormatter ORDER_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private static final int PURCHASE_POINT_RATE = 1;
	private final OrderDao orderDao;
	private final ProductService productService;
	private final CouponService couponService;
	private final UserService userService;
	private final PointHistoryService pointHistoryService;
	private final NotificationService notificationService;
	private final FCMService fcmService;
	
	public OrderServiceImpl(OrderDao orderDao, ProductService productService,
			CouponService couponService, UserService userService,
			PointHistoryService pointHistoryService,
			NotificationService notificationService,
			FCMService fcmService) {
		this.orderDao = orderDao;
		this.productService = productService;
		this.couponService = couponService;
		this.userService = userService;
		this.pointHistoryService = pointHistoryService;
		this.notificationService = notificationService;
		this.fcmService = fcmService;
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
		
		if (order.getOrderTime() == null) {
			order.setOrderTime(LocalDateTime.now());
		}
		if (order.getEstimatedDate() == null) {
			LocalDateTime baseTime = order.getOrderTime();
			order.setEstimatedDate(baseTime.plusDays(2).withHour(18).withMinute(0).withSecond(0).withNano(0));
		}
		if (order.getCarrier() == null || order.getCarrier().isBlank()) {
			order.setCarrier("준용배달업체");
		}
		if (order.getTrackingNumber() == null || order.getTrackingNumber().isBlank()) {
			order.setTrackingNumber(generateTrackingNumber());
		}

		int couponDiscount = order.getCouponDiscount() == null ? 0 : order.getCouponDiscount();
		order.setCouponDiscount(couponDiscount);
		
		Coupon coupon = null;
		if (order.getCouponId() != null && couponDiscount <= 0) {
			coupon = couponService.getCouponById(order.getCouponId());
			validateCoupon(order, coupon);
			couponDiscount = calculateCouponDiscount(coupon, totalPrice, order.getDeliveryFee());
			order.setCouponDiscount(couponDiscount);
		}

		order.setProductTotal(totalPrice);
		int deliveryFee = order.getDeliveryFee();
		int usedPoints = order.getUsedPoints() == null ? 0 : order.getUsedPoints();
		int discount = usedPoints + couponDiscount;
		int providedTotalPrice = order.getTotalPrice();
		if (providedTotalPrice > 0) {
			int calculatedDiscount = totalPrice + deliveryFee - providedTotalPrice;
			order.setDiscount(Math.max(calculatedDiscount, 0));
		} else {
			order.setDiscount(discount);
			int calculatedTotal = totalPrice + deliveryFee - discount;
			order.setTotalPrice(Math.max(calculatedTotal, 0));
		}
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

		orderDao.insertOrder(order);

		Notification notification = new Notification();
		notification.setUserId(order.getUserId());
		notification.setType("order");
		notification.setTitle("Order completed");
		notification.setMessage("Your order has been completed.");
		notification.setRead(false);
		notification.setDeleted(false);
		notification.setTargetType("order");
		notification.setTargetId((long) order.getOrderId());
		notificationService.createNotification(notification);

		User user = userService.getUserById(order.getUserId());
		if (user == null) {
			throw new IllegalStateException("사용자 정보가 없습니다.");
		}
		usedPoints = order.getUsedPoints() == null ? 0 : order.getUsedPoints();
		if (user.getPoint() < usedPoints) {
			throw new IllegalStateException("보유 포인트가 부족합니다.");
		}
		int earnedPoints = calculateEarnedPoints(totalPrice);
		int newPointBalance = user.getPoint() - usedPoints + earnedPoints;
		userService.updatePoint(user.getUserId(), newPointBalance);

		LocalDateTime now = order.getOrderTime();
		if (usedPoints > 0) {
			PointHistory usedHistory = new PointHistory();
			usedHistory.setUserId(user.getUserId());
			usedHistory.setEarnedPoints(0);
			usedHistory.setUsedPoints(usedPoints);
			usedHistory.setExpiredPoints(0);
			usedHistory.setEarnMethod(null);
			usedHistory.setExpireReason(null);
			usedHistory.setValidFrom(null);
			usedHistory.setValidTo(null);
			usedHistory.setOccurredAt(now);
			pointHistoryService.createPointHistory(usedHistory);
		}
		if (earnedPoints > 0) {
			PointHistory earnedHistory = new PointHistory();
			earnedHistory.setUserId(user.getUserId());
			earnedHistory.setEarnedPoints(earnedPoints);
			earnedHistory.setUsedPoints(0);
			earnedHistory.setExpiredPoints(0);
			earnedHistory.setEarnMethod("PURCHASE");
			earnedHistory.setExpireReason(null);
			earnedHistory.setValidFrom(now);
			earnedHistory.setValidTo(now.plusYears(1));
			earnedHistory.setOccurredAt(now);
			pointHistoryService.createPointHistory(earnedHistory);
		}

		if (coupon != null || (order.getCouponId() != null && couponDiscount > 0)) {
			couponService.markCouponUsed(order.getCouponId());
		}
		
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
				if (product != null && product.getPrice() != null && product.getPrice() > 0) {
					originalPrice = product.getPrice();
				} else if (originalPrice <= 0) {
					originalPrice = detail.getPrice();
				}

				boolean discountApplied = detail.isDiscountApplied();
				int discountedPrice = detail.getPrice();
				if (discountedPrice <= 0) {
					discountedPrice = originalPrice;
					if (product != null && product.getFinalPrice() != null && product.getFinalPrice() > 0) {
						discountedPrice = product.getFinalPrice();
						discountApplied = discountedPrice < originalPrice;
					}
				} else if (!discountApplied) {
					discountedPrice = originalPrice;
				}

				int discountRate = 0;
				if (originalPrice > 0 && discountedPrice > 0 && originalPrice != discountedPrice) {
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
		int usedPoints = order.getUsedPoints() == null ? 0 : order.getUsedPoints();
		int couponDiscount = order.getCouponDiscount() == null ? 0 : order.getCouponDiscount();
		response.setUsedPoints(usedPoints);
		response.setCouponDiscount(couponDiscount);
		response.setTotalDiscount(usedPoints + couponDiscount);
		int totalAmount = order.getTotalPrice();
		if (totalAmount == 0) {
			totalAmount = productTotal + order.getDeliveryFee() - order.getDiscount();
		}
		response.setTotalAmount(totalAmount);

		return response;
	}

	@Override
	@Transactional
	public void cancelOrders(int orderId) {
		Order order = orderDao.selectOrderById(orderId);
		if (order == null) {
			return;
		}
		if (isDeliveredStatus(order.getStatus())) {
			throw new IllegalStateException("Delivered orders cannot be cancelled.");
		}

		if (order.getOrderDetails() != null) {
			for (OrderDetail detail : order.getOrderDetails()) {
				if (detail == null || detail.getQuantity() <= 0) {
					continue;
				}
				productService.increaseStock(detail.getProductId(), detail.getQuantity());
			}
		}

		orderDao.deletelOrders(orderId);
	}

	@Override
	@Transactional
	public void updateOrderStatus(int orderId, String status) {
		if (status == null || status.isBlank()) {
			return;
		}

		Order order = orderDao.selectOrderById(orderId);
		if (order == null) {
			throw new IllegalArgumentException("Order not found.");
		}

		String currentStatus = order.getStatus();
		if (currentStatus != null && currentStatus.equalsIgnoreCase(status)) {
			return;
		}

		if (isCancelledStatus(status)) {
			if (isDeliveredStatus(currentStatus)) {
				throw new IllegalStateException("Delivered orders cannot be cancelled.");
			}
			restoreStock(order);
			orderDao.updateOrderStatus(orderId, status);
			return;
		}

		if (isReturnedStatus(status)) {
			if (!isDeliveredStatus(currentStatus)) {
				throw new IllegalStateException("Only delivered orders can be returned.");
			}
			restoreStock(order);
			decreaseSoldCount(order);
			orderDao.updateOrderStatus(orderId, status);
			return;
		}

		orderDao.updateOrderStatus(orderId, status);
		if (isDeliveredStatus(status) && !isDeliveredStatus(currentStatus)) {
			increaseSoldCount(order);
		}
	}

	@Override
	public int countOrdersByUserId(String userId) {
		return orderDao.countOrdersByUserId(userId);
	}

	@Override
	public int countInProgressOrdersByUserId(String userId) {
		return orderDao.countInProgressOrdersByUserId(userId);
	}


	private void sendStatusNotification(String userId, String status) {
		if (userId == null || userId.isBlank()) {
			return;
		}
		String fcmToken = fcmService.getTokenByUserId(userId);
		if (fcmToken == null || fcmToken.isBlank()) {
			return;
		}

		String normalized = status == null ? "" : status.trim();
		String key = normalized.toUpperCase();
		String title;
		String body;

		if ("주문 완료".equals(normalized)) {
			title = "주문이 완료되었습니다.";
			body = "배송이 시작되면 알려드릴게요.";
		} else if ("배송 준비중".equals(normalized) || "상품 준비중".equals(normalized)) {
			title = "주문을 준비중입니다.";
			body = "상품을 준비하고 있어요.";
		} else if ("배송 중".equals(normalized) || "배송 시작".equals(normalized)) {
			title = "배송이 시작되었습니다.";
			body = "배송이 시작되었어요.";
		} else if ("배송 완료".equals(normalized)) {
			title = "배송이 완료되었습니다.";
			body = "구매해주셔서 감사합니다.";
		} else if ("주문 취소".equals(normalized) || "취소".equals(normalized)) {
			title = "주문이 취소되었습니다.";
			body = "취소 요청이 처리되었어요.";
		} else {
			switch (key) {
				case "PENDING":
					title = "주문이 접수되었습니다.";
					body = "주문 확인 후 처리할게요.";
					break;
				case "PROCESSING":
					title = "주문을 준비중입니다.";
					body = "상품을 준비하고 있어요.";
					break;
				case "SHIPPING":
					title = "배송이 시작되었습니다.";
					body = "배송이 시작되었어요.";
					break;
				case "DELIVERED":
					title = "배송이 완료되었습니다.";
					body = "구매해주셔서 감사합니다.";
					break;
				case "CANCELLED":
					title = "주문이 취소되었습니다.";
					body = "취소 요청이 처리되었어요.";
					break;
				default:
					title = "주문 상태가 변경되었습니다.";
					body = "현재 상태: " + normalized;
					break;
			}
		}

		fcmService.sendMessage(fcmToken, title, body);
	}

	private String formatDate(LocalDateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		return dateTime.format(ORDER_DATE_FORMAT);
	}

	private String generateTrackingNumber() {
		long value = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
		return String.valueOf(value);
	}

	private void validateCoupon(Order order, Coupon coupon) {
		if (coupon == null) {
			throw new IllegalStateException("쿠폰 정보를 찾을 수 없습니다.");
		}
		if (!order.getUserId().equals(coupon.getUserId())) {
			throw new IllegalStateException("쿠폰 사용자 정보가 일치하지 않습니다.");
		}
		if (coupon.isUsed()) {
			throw new IllegalStateException("이미 사용된 쿠폰입니다.");
		}
		LocalDateTime now = order.getOrderTime();
		if (coupon.getValidFrom() != null && now.isBefore(coupon.getValidFrom())) {
			throw new IllegalStateException("쿠폰 사용 기간이 시작되지 않았습니다.");
		}
		if (coupon.getValidTo() != null && now.isAfter(coupon.getValidTo())) {
			throw new IllegalStateException("쿠폰 사용 기간이 만료되었습니다.");
		}
	}

	private int calculateCouponDiscount(Coupon coupon, int productTotal, int deliveryFee) {
		if (coupon == null) {
			return 0;
		}
		String type = coupon.getCouponType();
		int value = coupon.getDiscountValue();
		if ("AMOUNT".equals(type)) {
			return Math.max(value, 0);
		}
		if ("PERCENT".equals(type)) {
			if (value <= 0) {
				return 0;
			}
			return productTotal * value / 100;
		}
		if ("SHIPPING".equals(type)) {
			return Math.min(Math.max(value, 0), deliveryFee);
		}
		return 0;
	}

	private int calculateEarnedPoints(int productTotal) {
		if (productTotal <= 0) {
			return 0;
		}
		return productTotal * PURCHASE_POINT_RATE / 100;
	}

	private boolean isDeliveredStatus(String status) {
		return status != null && "DELIVERED".equalsIgnoreCase(status);
	}

	private boolean isCancelledStatus(String status) {
		return status != null && "CANCELLED".equalsIgnoreCase(status);
	}

	private boolean isReturnedStatus(String status) {
		return status != null && "RETURNED".equalsIgnoreCase(status);
	}

	private void increaseSoldCount(Order order) {
		if (order.getOrderDetails() == null) {
			return;
		}
		for (OrderDetail detail : order.getOrderDetails()) {
			if (detail == null || detail.getQuantity() <= 0) {
				continue;
			}
			productService.increaseSoldCount(detail.getProductId(), detail.getQuantity());
		}
	}

	private void decreaseSoldCount(Order order) {
		if (order.getOrderDetails() == null) {
			return;
		}
		for (OrderDetail detail : order.getOrderDetails()) {
			if (detail == null || detail.getQuantity() <= 0) {
				continue;
			}
			productService.decreaseSoldCount(detail.getProductId(), detail.getQuantity());
		}
	}

	private void restoreStock(Order order) {
		if (order.getOrderDetails() == null) {
			return;
		}
		for (OrderDetail detail : order.getOrderDetails()) {
			if (detail == null || detail.getQuantity() <= 0) {
				continue;
			}
			productService.increaseStock(detail.getProductId(), detail.getQuantity());
		}
	}

}
