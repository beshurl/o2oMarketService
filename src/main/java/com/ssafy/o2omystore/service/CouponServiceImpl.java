package com.ssafy.o2omystore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.CouponDao;
import com.ssafy.o2omystore.dto.Coupon;

@Service
public class CouponServiceImpl implements CouponService {

	private final CouponDao couponDao;

	public CouponServiceImpl(CouponDao couponDao) {
		this.couponDao = couponDao;
	}

	@Override
	public List<Coupon> getCouponsByUserId(String userId) {
		return couponDao.selectCouponsByUserId(userId);
	}

	@Override
	public int countAvailableCouponsByUserId(String userId) {
		return couponDao.countAvailableCouponsByUserId(userId);
	}
}
