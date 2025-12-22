package com.ssafy.o2omystore.service;

import java.util.List;

import com.ssafy.o2omystore.dto.Coupon;

public interface CouponService {
	List<Coupon> getCouponsByUserId(String userId);

	int countAvailableCouponsByUserId(String userId);

	Coupon getCouponById(long couponId);

	void markCouponUsed(long couponId);
}
