package com.foodapplication.model.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.foodapplication.model.dao.RestaurantDao;
import com.foodapplication.model.model.Restaurant;



public class RestaurantDaoImp implements RestaurantDao {
	
	List<Restaurant> restaurantList = new ArrayList<Restaurant>();
	private static final String INSERT_QUERY = "insert into `restaurant`(`restaurant_name`,`cusinee_type`, `address`, `rating`)values(?, ?, ?, ?)";
	private static final String FETCH_ALL_QUERY = "select * from `restaurant`";
	private static final String FETCH_SPECIFIC_QUERY = "select * from `restaurant` where restaurant_name = ?" ;
	private static final String UPDATE_QUERY = "update `restaurant` set `restaurant_name` = ? where `restaurant_id` = ?";
	private static final String DELETE_QUERY = "delete from `restaurant` where `restaurant_id` = ?";
	private Connection con;
	private String url = "Jdbc:mysql://localhost:3306/foodclone";
	private String username = "root";
	private String password = "Muneswar@123";
	private PreparedStatement pstmt;
	private int status;
	private ResultSet result;
	private static final String FETCH_BY_IDS = 
	        "SELECT * FROM restaurant WHERE restaurant_id IN (%s)";

	

	public RestaurantDaoImp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url  ,username , password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Restaurant restaurant) throws Exception {
		pstmt = con.prepareStatement(INSERT_QUERY);
		pstmt.setString(1, restaurant.getName());
		pstmt.setString(2, restaurant.getCusineeType());
		pstmt.setString(3, restaurant.getAddress());
		pstmt.setInt(4, restaurant.getRating());
		status = pstmt.executeUpdate();
		if(status != 0) 
			System.out.println("Data Inserted");
		else 
			System.out.println("No Record!");
		
	}

	@Override
	public List<Restaurant> fetchAll() throws Exception {
		pstmt = con.prepareStatement(FETCH_ALL_QUERY);
		result = pstmt.executeQuery();
		restaurantList = extractRestaurantListFromResultSet(result);
		return restaurantList;
	}
	
	public List<Restaurant> extractRestaurantListFromResultSet(ResultSet result) throws Exception {
		while(result.next()) {
			restaurantList.add(
					new Restaurant(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getInt(5),
					result.getTimestamp(6),
					result.getString(7)));
		}
		return restaurantList;
	}

	@Override
	public void fetchSpecific(String name) throws Exception {
		pstmt = con.prepareStatement(FETCH_SPECIFIC_QUERY);
		pstmt.setString(1, name);
		result = pstmt.executeQuery();
		restaurantList = extractRestaurantListFromResultSet(result);
		if(!restaurantList.isEmpty()) {
			System.out.println(restaurantList.get(0));
		}
		else {
			System.out.println("No record!");
		}
	}
	
	 public List<Restaurant> fetchRestaurantsByIds(Set<Integer> restaurantIds) {
	        List<Restaurant> restaurantList = new ArrayList<>();
	        
	        if (restaurantIds == null || restaurantIds.isEmpty()) {
	            return restaurantList; 
	        }

	        String placeholders = String.join(",", restaurantIds.stream().map(id -> "?").toArray(String[]::new));
	        String query = String.format(FETCH_BY_IDS, placeholders);

	        try (PreparedStatement pstmt = con.prepareStatement(query)) {
	            int index = 1;
	            for (int id : restaurantIds) {
	                pstmt.setInt(index++, id);
	            }

	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                while (resultSet.next()) {
	                    Restaurant restaurant = new Restaurant();
	                    restaurant.setRid(resultSet.getInt("restaurant_id"));
	                    restaurant.setName(resultSet.getString("restaurant_name"));
	                    restaurant.setAddress(resultSet.getString("address"));
	                    restaurant.setRating(resultSet.getInt("rating"));
	                    restaurantList.add(restaurant);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }

	        return restaurantList;
	    }

	@Override
	public void update(int id, String name) throws SQLException {
		pstmt = con.prepareStatement(UPDATE_QUERY);
		pstmt.setInt(2, id);
		pstmt.setString(1, name);
		status = pstmt.executeUpdate();
		if(status != 0) {
			System.out.println("Restaurant name updated successfully");
		}
		else {
			System.out.println("No record!");
			System.exit(0);
		}
	}

	@Override
	public void delete(int id) throws Exception {
		pstmt = con.prepareStatement(DELETE_QUERY);
		pstmt.setInt(1,id);
		status = pstmt.executeUpdate();
		if(status != 0) {
			System.out.println("Record deleted successful");
		}
		else {
			System.out.println("No such record!");
			System.exit(0);
		}
	}

}
