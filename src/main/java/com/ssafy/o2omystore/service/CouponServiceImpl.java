package com.ssafy.o2omystore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.CouponDao;
import com.ssafy.o2omystore.dto.Coupon;

@Service
public class CouponServiceImpl implements CouponService {

	private static final String NEW_USER_COUPON_CODE = "정준용최고";
	private static final String NEW_USER_COUPON_DESCRIPTION = "신규 고객을 위한 무려 10만원 할인 쿠폰!";
	private static final int NEW_USER_COUPON_DISCOUNT = 100000;
	private static final String NEW_USER_COUPON_TYPE = "AMOUNT";

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

	@Override
	public Coupon getCouponById(long couponId) {
		return couponDao.selectCouponById(couponId);
	}

	@Override
	public void markCouponUsed(long couponId) {
		couponDao.markCouponUsed(couponId);
	}

	@Override
	public Coupon redeemCouponCode(String userId, String code) {
		if (code == null || code.isBlank()) {
			throw new IllegalArgumentException("Invalid coupon code.");
		}
		if (!NEW_USER_COUPON_CODE.equals(code.trim())) {
			throw new IllegalArgumentException("Invalid coupon code.");
		}
		int existing = couponDao.countActiveCouponByUserIdAndDescription(userId, NEW_USER_COUPON_DESCRIPTION);
		if (existing > 0) {
			throw new IllegalStateException("Coupon already issued.");
		}

		LocalDateTime now = LocalDateTime.now();
		Coupon coupon = new Coupon();
		coupon.setUserId(userId);
		coupon.setCouponType(NEW_USER_COUPON_TYPE);
		coupon.setDescription(NEW_USER_COUPON_DESCRIPTION);
		coupon.setDiscountValue(NEW_USER_COUPON_DISCOUNT);
		coupon.setValidFrom(now);
		coupon.setValidTo(now.plusMonths(1));
		coupon.setUsed(false);
		coupon.setUsedAt(null);

		couponDao.insertCoupon(coupon);
		return coupon;
	}
}
