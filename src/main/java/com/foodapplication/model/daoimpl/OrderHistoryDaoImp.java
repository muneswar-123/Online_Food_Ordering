package com.foodapplication.model.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foodapplication.model.dao.OrderHistoryDao;
import com.foodapplication.model.model.CartItems;
import com.foodapplication.model.model.OrderHistory;
import com.my.connect.MyConnection;

public class OrderHistoryDaoImp implements OrderHistoryDao{

	private static final String INSERT_QUERY = "insert into `order_history` (`orderId`, `userId`, `restaurantId`, `total`) values (?, ?, ?, ?)";
	private static final String FETCH_BY_ID = "select * from `order_history` where `userId` = ?";
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet result;
	private List<OrderHistory> orderHistoryList = new ArrayList<OrderHistory>();

	public OrderHistoryDaoImp() {
		con = MyConnection.connect();
	}
	@Override
	public void insertAll(Map<Integer, CartItems> cart , int orderID ,int userID) {
		try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY)){
			for (CartItems cartItem : cart.values()) {
				pstmt.setInt(1, orderID);
				pstmt.setInt(2, userID);
				pstmt.setInt(3, cartItem.getRestaurant_id());
				pstmt.setInt(4, cartItem.getPrice() * cartItem.getQuantity());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
		} catch (Exception e) {
		}
	}

	 @Override
	    public List<OrderHistory> FetchOrderHistory(int userId) {
	        List<OrderHistory> orderHistoryList = new ArrayList<>();
	        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_ID);) {
	            pstmt.setInt(1, userId);
	            ResultSet result = pstmt.executeQuery();
	            orderHistoryList = extractOrderHistoryFromResultSet(result);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderHistoryList;
	    }
	 
	    private List<OrderHistory> extractOrderHistoryFromResultSet(ResultSet result) {
	        List<OrderHistory> orderHistoryList = new ArrayList<>();
	        try {
	            while (result.next()) {
	                orderHistoryList.add(new OrderHistory(
	                    result.getInt("order_historyId"),
	                    result.getInt("orderId"),
	                    result.getInt("userId"),
	                    result.getInt("restaurantId"),
	                    result.getInt("total"),
	                    result.getString("status")
	                ));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return orderHistoryList;
	    }

}
