package com.foodapplication.controller.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapplication.model.daoimpl.UserDaoImp;
import com.foodapplication.model.model.User;
import com.my.connect.MyConnection;
import com.my.security.Encrypt;

public class Register extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private int status;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = new User(req.getParameter("name") , req.getParameter("email") , Encrypt.data(req.getParameter("password")),  Long.parseLong(req.getParameter("number")));
		
		UserDaoImp udi = new UserDaoImp();
		udi.setUser(user);
		resp.sendRedirect("Login.jsp");
 	}
}
