package com.foodapplication.controller.getrestaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodapplication.model.daoimpl.RestaurantDaoImp;
import com.foodapplication.model.model.Restaurant;

@WebServlet("/GetRestaurant")
public class GetRestaurant extends HttpServlet{
	List<Restaurant> restaurantList = new ArrayList<Restaurant>();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDaoImp daoImp = new RestaurantDaoImp();
		try {
			restaurantList = daoImp.fetchAll();
			HttpSession session = req.getSession();
			session.setAttribute("restaurantList", restaurantList);
			resp.sendRedirect("restaurant.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
