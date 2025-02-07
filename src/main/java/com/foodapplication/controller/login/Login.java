package com.foodapplication.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodapplication.model.daoimpl.UserDaoImp;
import com.foodapplication.model.model.User;
import com.my.security.Decrypt;
import com.my.security.Encrypt;


public class Login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDaoImp daoImp = new UserDaoImp();
		User user= daoImp.getUser(email);

		if(user != null) {
			if(Decrypt.data(user.getPassword()).equals(password)) {
				HttpSession session = req.getSession();
				session.setAttribute("logedInUser", user);
				resp.sendRedirect("home.jsp");
			}
			else {
				resp.sendRedirect("PasswordIncorrect.jsp");
			}
		}
		else {
			resp.sendRedirect("invalidUser.jsp");
		}

	}
}
