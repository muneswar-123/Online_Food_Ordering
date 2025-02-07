package com.foodapplication.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.foodapplication.model.model.CartItems;
import com.foodapplication.model.model.Orders;

public interface OrdersDao {
    void insertAll(Map<Integer, CartItems> cart, int userId, String paymentMethod , String address) throws SQLException;
    List<Orders> fetchOrderId();
}
