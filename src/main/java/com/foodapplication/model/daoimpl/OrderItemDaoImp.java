package com.foodapplication.model.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foodapplication.model.dao.OrderItemDao;
import com.foodapplication.model.model.CartItems;
import com.foodapplication.model.model.OrderItem;
import com.my.connect.MyConnection;

public class OrderItemDaoImp implements OrderItemDao {

    private static final String INSERT_QUERY = 
        "INSERT INTO order_items (orderId, menuId, quantity, price, item_total) VALUES (?, ?, ?, ?, ?)";

    private static final String FETCH_ALL = "SELECT * from `order_items` where orderId = ?";
    	

    private Connection con;
    private ResultSet result;

    public OrderItemDaoImp() {
        // Initialize the connection to the database
        con = MyConnection.connect();
    }

    @Override
    public void insertAll(Map<Integer, CartItems> cart, int orderId) {
        // Insert multiple order items for a specific orderId
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)) {
            for (CartItems cartItems : cart.values()) {
                pstmt.setInt(1, orderId);
                pstmt.setInt(2, cartItems.getMenu_id());
                pstmt.setInt(3, cartItems.getQuantity());
                pstmt.setInt(4, cartItems.getPrice());
                pstmt.setInt(5, cartItems.getQuantity() * cartItems.getPrice());
                pstmt.addBatch();
            }
            pstmt.executeBatch(); // Execute the batch insert
        } catch (SQLException e) {
            System.err.println("Error while inserting order items: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> fetchAll(int orderId) {
        List<OrderItem> orderItemList = new ArrayList<>(); // Initialize list here for each fetch
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ALL)) {
            pstmt.setInt(1, orderId);
            result = pstmt.executeQuery();
            
            // Clear previous data and populate with fresh data
            orderItemList = extractOrderItemListFromResult(result);
        } catch (SQLException e) {
            System.err.println("Error while fetching order items: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Return an empty list if no order items are found for the given orderId
        return orderItemList.isEmpty() ? List.of() : orderItemList;
    }

    // Extract order items from the ResultSet and populate the list
    private List<OrderItem> extractOrderItemListFromResult(ResultSet result) throws SQLException {
        List<OrderItem> orderItemList = new ArrayList<>();
        while (result.next()) {
            orderItemList.add(new OrderItem(
                result.getInt("order_itemId"),
                result.getInt("orderId"),
                result.getInt("menuId"),
                result.getInt("quantity"),
                result.getInt("price"),
                result.getInt("item_total")
            ));
        }
        return orderItemList;
    }
}