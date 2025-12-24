package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ssafy.o2omystore.FCM.FCMService;
import com.ssafy.o2omystore.dto.CheckoutInfoResponse;
import com.ssafy.o2omystore.dto.Order;
import com.ssafy.o2omystore.dto.OrderDetailResponse;
import com.ssafy.o2omystore.dto.OrderPaymentRequest;
import com.ssafy.o2omystore.dto.OrderStatusUpdateRequest;
import com.ssafy.o2omystore.dto.OrderSummaryResponse;
import com.ssafy.o2omystore.dto.User;
import com.ssafy.o2omystore.service.CouponService;
import com.ssafy.o2omystore.service.OrderService;
import com.ssafy.o2omystore.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order API", description = "주문 생성, 조회, 취소 등 주문 관련 API")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;
	private final UserService userService;
	private final CouponService couponService;
	private final FCMService fcmService;

	public OrderController(
			OrderService orderService, 
			UserService userService, 
			CouponService couponService,
			FCMService fcmService
			) 
	{
		
		this.orderService = orderService;
		this.userService = userService;
		this.couponService = couponService;
		this.fcmService = fcmService;
		
	}

	@Operation(summary = "사용자가 상품을 주문하면 주문과 주문 상세 내역을 생성한다.")
    @PostMapping
    public void createOrder(@RequestBody OrderPaymentRequest request) {
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setRecipientName(request.getRecipientName());
		order.setPhone(request.getPhone());
		order.setAddress(request.getAddress());
		order.setAddressDetail(request.getAddressDetail());
		order.setZipCode(request.getZipCode());
		order.setPaymentMethod(request.getPaymentMethod());
		order.setDeliveryFee(request.getDeliveryFee());
		order.setDiscount(request.getUsedPoints());
		order.setUsedPoints(request.getUsedPoints());
		order.setCouponDiscount(request.getCouponDiscount());
		order.setCouponId(request.getCouponId());
		order.setTotalPrice(request.getTotalPrice());
		order.setOrderDetails(request.getOrderDetails());

		orderService.createOrder(order);
		
		// 주문 성공 시 DB에서 FCM 토큰 조회
        String fcmToken = fcmService.getTokenByUserId(request.getUserId());
        
        // 토큰 있으면 주문 성공 FCM 알림 발송
        if (fcmToken != null && !fcmToken.isBlank()) {
            fcmService.sendMessage(fcmToken, "주문이 완료되었습니다.", "배송이 시작되면 알려드릴게요.");
        }
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

	@Operation(summary = "{userId}에 해당하는 결제 화면용 사용자/쿠폰/포인트 정보를 반환한다.")
	@GetMapping("/user/{userId}/checkout")
	public CheckoutInfoResponse getCheckoutInfo(@PathVariable String userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			return null;
		}

		CheckoutInfoResponse response = new CheckoutInfoResponse();
		response.setUserId(user.getUserId());
		response.setUserName(user.getName());
		response.setAddress(user.getAddress());
		response.setAddressDetail(user.getAddressDetail());
		response.setPoint(user.getPoint());
		response.setCoupons(couponService.getCouponsByUserId(userId));

		return response;
	}
	
	@Operation(summary = "{orderId}에 해당하는 주문을 취소합니다.")
	@DeleteMapping("/delete/{orderId}")
	public void cancelOrders(@PathVariable int orderId) {
		
		orderService.cancelOrders(orderId);
	}

	@Operation(summary = "관리자가 주문 상태를 변경한다.")
	@PatchMapping("/{orderId}/status")
	public void updateOrderStatus(@PathVariable int orderId, @RequestBody OrderStatusUpdateRequest request) {
		orderService.updateOrderStatus(orderId, request.getStatus());
	}
};
