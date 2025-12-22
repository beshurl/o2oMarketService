package com.ssafy.o2omystore.dto;

public class MyPageSummaryResponse {

	private String userName;
	private int inProgressOrderCount;
	private int commentCount;
	private int orderCount;
	private int point;
	private int couponCount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getInProgressOrderCount() {
		return inProgressOrderCount;
	}

	public void setInProgressOrderCount(int inProgressOrderCount) {
		this.inProgressOrderCount = inProgressOrderCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}
}
