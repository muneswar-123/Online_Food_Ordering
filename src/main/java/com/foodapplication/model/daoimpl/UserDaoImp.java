package com.foodapplication.model.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodapplication.model.dao.UserDao;
import com.foodapplication.model.model.User;



public class UserDaoImp implements UserDao{

	List<User> UserList = new ArrayList<User>();
	private static final String INSERT_QUERY = "insert into user (username , email , password , mobile) values (? , ? , ? , ?)";
	private static final String FETCHING_ALL_QUERY = "select * from user";
	private static final String FETCHING_SPECIFIC_QUERY = "select * from user where email = ?";
	private static final String FETCHING_SPECIFIC_BY_ID = "select * from user where userId = ?";
	private static final String UPDATE_QUERY = "update user set username = ? where userid = ?";
	private static final String DELETE_QUERY = "delete from user where userid = ?";
	private Connection con;
	private PreparedStatement pstmt;
	private String url = "jdbc:mysql://localhost:3306/foodclone";
	private String username = "root";
	private String password = "Muneswar@123";
	private int res;
	private ResultSet result;
	private User s;
	private User user;
	public UserDaoImp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url , username , password );	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setUser(User u) {
		try {
			pstmt = con.prepareStatement(INSERT_QUERY);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPassword());
			pstmt.setLong(4, u.getMobile());
			res = pstmt.executeUpdate();
			if(res != 0) 
				System.out.println("Success");
			else
				System.out.println("Failure");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> fetchAll() {
		try {
			pstmt = con.prepareStatement(FETCHING_ALL_QUERY);
			result = pstmt.executeQuery();
			UserList = extractUserListFromResultSet(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserList;
	}

	public List<User> extractUserListFromResultSet(ResultSet result) {
		try {
			while(result.next()) {
				UserList.add(new User(result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4),
						result.getLong(5)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserList;
	}

	@Override
	public User getUser(String email) {
		try {
			pstmt = con.prepareStatement(FETCHING_SPECIFIC_QUERY);
			pstmt.setString(1 , email);
			result = pstmt.executeQuery();
			while(result.next()) {
				user = new User(result.getInt("userid"),result.getString("username") , result.getString("email"), 
						result.getString("password"), result.getLong("mobile"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUser(int id) {
		try {
			pstmt = con.prepareStatement(FETCHING_SPECIFIC_BY_ID);
			pstmt.setInt(1 , id);
			result = pstmt.executeQuery();
			while(result.next()) {
				user = new User(result.getString("username") , result.getString("email"), 
						result.getString("password"), result.getLong("mobile"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public void update(int id, String name) {
		try {
			pstmt = con.prepareStatement(UPDATE_QUERY);
			pstmt.setInt(2, id);
			pstmt.setString(1, name);
			res = pstmt.executeUpdate();
			if(res != 0) 
				System.out.println("Success");
			else {
				System.out.println("No Record");
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int delete(int id) {
		try {
			pstmt = con.prepareStatement(DELETE_QUERY);
			pstmt.setInt(1, id);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
}
