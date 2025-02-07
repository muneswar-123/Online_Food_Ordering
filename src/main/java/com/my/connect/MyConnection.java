package com.my.connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	private static Connection con;

	public static Connection connect() {
		 String url = "jdbc:mysql://localhost:3306/foodclone";;
		 String username = "root";
		 String password = "Muneswar@123";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
