package com.ssafy.o2omystore.dto;

public class OrderDetail {
	
	private int orderItemId;
	private int orderId;
	private int productId;
	private int quantity;
	private int price;
	private boolean discountApplied;
	
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isDiscountApplied() {
		return discountApplied;
	}
	public void setDiscountApplied(boolean discountApplied) {
		this.discountApplied = discountApplied;
	}

}
