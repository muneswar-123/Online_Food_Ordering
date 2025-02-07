package com.foodapplication.model.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foodapplication.model.dao.OrdersDao;
import com.foodapplication.model.model.CartItems;
import com.foodapplication.model.model.Orders;
import com.my.connect.MyConnection;

public class OrdersDaoImp implements OrdersDao {

    List<Orders> orderList = new ArrayList<>();
    private static final String INSERT_QUERY = 
        "insert into `orders` (`orderId`, `userId`, `restaurantId`, `menuId`, `quantity`,`address`, `payment_method`) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String FETCH_MAX_ORDER_ID = "select ifnull(max(orderId), 0) as maxOrderId from `orders`";

    private ResultSet result;

    @Override
    public void insertAll(Map<Integer, CartItems> cart, int userId, String paymentMethod ,String address) throws SQLException {
        int orderId = generateOrderId(); // Generate a new order ID
        try (Connection con = MyConnection.connect(); 
             PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {
            
            con.setAutoCommit(false); // Begin transaction

            for (CartItems cartItem : cart.values()) {
                pstmt.setInt(1, orderId); // Set the same order ID for all items
                pstmt.setInt(2, userId); 
                pstmt.setInt(3, cartItem.getRestaurant_id()); 
                pstmt.setInt(4, cartItem.getMenu_id()); 
                pstmt.setInt(5, cartItem.getQuantity()); 
                pstmt.setString(6, address); 
                pstmt.setString(7, paymentMethod); 

                pstmt.addBatch(); 
            }

            pstmt.executeBatch(); // Execute all inserts
            con.commit(); // Commit transaction

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while inserting order items", e);
        }
    }

    @Override
    public List<Orders> fetchOrderId() {
        try (Connection con = MyConnection.connect();
             PreparedStatement pstmt = con.prepareStatement(FETCH_MAX_ORDER_ID)) {
            result = pstmt.executeQuery();
            extractMenuListFromResultSet(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public int generateOrderId() {
        int maxOrderId = 0;
        try (Connection con = MyConnection.connect();
             PreparedStatement pstmt = con.prepareStatement(FETCH_MAX_ORDER_ID);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                maxOrderId = rs.getInt("maxOrderId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxOrderId + 1; // Increment to generate the next order ID
    }

    public List<Orders> extractMenuListFromResultSet(ResultSet result) {
        try {
            while (result.next()) {
                Orders order = new Orders();
                order.setOrderID(result.getInt("maxOrderId"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
