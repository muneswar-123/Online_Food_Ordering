package com.foodapplication.controller.showorderitems;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.foodapplication.model.daoimpl.OrderItemDaoImp;
import com.foodapplication.model.model.OrderItem;

public class ShowOrderItems extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdParam = request.getParameter("orderId");
        List<OrderItem> orderItemList = null;

        try {
            if (orderIdParam != null && !orderIdParam.trim().isEmpty()) {
                int orderId = Integer.parseInt(orderIdParam);
                OrderItemDaoImp orderItemDAO = new OrderItemDaoImp();
                orderItemList = orderItemDAO.fetchAll(orderId);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid order ID.");
            System.err.println("Invalid Order ID: " + orderIdParam);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred while fetching order items.");
        }

        if (orderItemList == null) {
            orderItemList = List.of(); // Return an empty list if null
        }

        // Set the fetched list and forward to JSP
        request.setAttribute("orderItemList", orderItemList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderdetails.jsp");
        dispatcher.forward(request, response);
    }
}
