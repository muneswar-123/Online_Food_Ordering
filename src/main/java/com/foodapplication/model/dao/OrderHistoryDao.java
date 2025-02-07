package com.foodapplication.model.dao;

import java.util.List;
import java.util.Map;

import com.foodapplication.model.model.CartItems;
import com.foodapplication.model.model.OrderHistory;

public interface OrderHistoryDao {
	void insertAll(Map<Integer, CartItems> cart , int orderID ,int userID);
	List<OrderHistory> FetchOrderHistory(int id );
}
