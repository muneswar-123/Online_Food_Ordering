package com.foodapplication.controller.orderhistory;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodapplication.model.daoimpl.OrderHistoryDaoImp;
import com.foodapplication.model.daoimpl.RestaurantDaoImp;
import com.foodapplication.model.model.OrderHistory;
import com.foodapplication.model.model.Restaurant;
import com.foodapplication.model.model.User;


public class OrderHistoryController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    User user = (User) session.getAttribute("logedInUser");
	    
	    if (user == null) {
	        resp.sendRedirect("login.jsp"); 
	        return;
	    }
	    
	    int userID = user.getUserid();
	    OrderHistoryDaoImp orderHistoryDaoImp = new OrderHistoryDaoImp();
	    List<OrderHistory> orderHistoryList = orderHistoryDaoImp.FetchOrderHistory(userID);
	    
	    Set<Integer> restaurantIds = new HashSet<>();
        for (OrderHistory order : orderHistoryList) {
            restaurantIds.add(order.getRestaurantId());
        }

        RestaurantDaoImp restaurantDaoImp = new RestaurantDaoImp();
        List<Restaurant> restaurantList = restaurantDaoImp.fetchRestaurantsByIds(restaurantIds);

        session.setAttribute("orderHistoryList", orderHistoryList);
        session.setAttribute("restaurantList", restaurantList);

        resp.sendRedirect("orderhistory.jsp");
	}

}
