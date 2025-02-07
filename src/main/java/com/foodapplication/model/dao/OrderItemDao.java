package com.foodapplication.model.dao;

import java.util.List;
import java.util.Map;

import com.foodapplication.model.model.CartItems;
import com.foodapplication.model.model.Menu;
import com.foodapplication.model.model.OrderItem;

public interface OrderItemDao {
	void insertAll(Map<Integer, CartItems >cart , int orderId);
	List<OrderItem> fetchAll(int id);
}
