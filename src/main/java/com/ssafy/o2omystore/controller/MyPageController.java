package com.ssafy.o2omystore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.o2omystore.dto.Coupon;
import com.ssafy.o2omystore.dto.CouponRedeemRequest;
import com.ssafy.o2omystore.dto.MyPageSummaryResponse;
import com.ssafy.o2omystore.dto.PointHistory;
import com.ssafy.o2omystore.dto.User;
import com.ssafy.o2omystore.service.CouponService;
import com.ssafy.o2omystore.service.CommentService;
import com.ssafy.o2omystore.service.OrderService;
import com.ssafy.o2omystore.service.PointHistoryService;
import com.ssafy.o2omystore.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MyPage API", description = "마이페이지 요약 정보 API")
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

	private final UserService userService;
	private final OrderService orderService;
	private final CommentService commentService;
	private final CouponService couponService;
	private final PointHistoryService pointHistoryService;

	public MyPageController(UserService userService, OrderService orderService,
			CommentService commentService, CouponService couponService,
			PointHistoryService pointHistoryService) {
		this.userService = userService;
		this.orderService = orderService;
		this.commentService = commentService;
		this.couponService = couponService;
		this.pointHistoryService = pointHistoryService;
	}

	@Operation(summary = "{userId}에 해당하는 마이페이지 요약 정보를 반환한다.")
	@GetMapping("/{userId}")
	public MyPageSummaryResponse getMyPageSummary(@PathVariable String userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			return null;
		}

		MyPageSummaryResponse response = new MyPageSummaryResponse();
		response.setUserName(user.getName());
		response.setPoint(user.getPoint());
		response.setOrderCount(orderService.countOrdersByUserId(userId));
		response.setInProgressOrderCount(orderService.countInProgressOrdersByUserId(userId));
		response.setCommentCount(commentService.countCommentsByUserId(userId));
		response.setCouponCount(couponService.countAvailableCouponsByUserId(userId));

		return response;
	}

	@Operation(summary = "{userId}에 해당하는 쿠폰 전체 목록을 반환한다.")
	@GetMapping("/{userId}/coupons")
	public List<Coupon> getMyPageCoupons(@PathVariable String userId) {
		return couponService.getCouponsByUserId(userId);
	}

	@Operation(summary = "Redeem a coupon code and issue the welcome discount coupon.")
	@PostMapping("/{userId}/coupons/redeem")
	public Coupon redeemCoupon(@PathVariable String userId, @RequestBody CouponRedeemRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Invalid coupon code.");
		}
		return couponService.redeemCouponCode(userId, request.getCode());
	}

	@Operation(summary = "{userId}에 해당하는 포인트 내역 전체를 반환한다.")
	@GetMapping("/{userId}/points")
	public List<PointHistory> getMyPagePoints(@PathVariable String userId) {
		return pointHistoryService.getPointHistoryByUserId(userId);
	}
}
