package com.my.foodapp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Close {
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		if(con != null) {
			try {
				con.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
				System.out.println("prepare closed");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
