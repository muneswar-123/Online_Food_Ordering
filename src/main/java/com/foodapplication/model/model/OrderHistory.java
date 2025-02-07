package com.foodapplication.model.model;

public class OrderHistory {
	private int orderHistoryId;
	private int orderId;
	private int UserId;
	private int restaurantId;
	private int total;
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrderHistoryId() {
		return orderHistoryId;
	}
	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public OrderHistory(int orderHistoryId, int orderId, int userId, int restaurantId, int total, String status) {
		super();
		this.orderHistoryId = orderHistoryId;
		this.orderId = orderId;
		UserId = userId;
		this.restaurantId = restaurantId;
		this.total = total;
		this.status = status;
	}
	public OrderHistory(int orderId, int userId, int restaurantId, int total) {
		super();
		this.orderId = orderId;
		UserId = userId;
		this.restaurantId = restaurantId;
		this.total = total;
	}
	
	public OrderHistory() {
		super();
	}
}
