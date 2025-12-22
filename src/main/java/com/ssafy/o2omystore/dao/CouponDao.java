package com.ssafy.o2omystore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.o2omystore.dto.Coupon;

@Mapper
public interface CouponDao {
	List<Coupon> selectCouponsByUserId(String userId);

	int countAvailableCouponsByUserId(String userId);

	Coupon selectCouponById(long couponId);

	int markCouponUsed(long couponId);
}
