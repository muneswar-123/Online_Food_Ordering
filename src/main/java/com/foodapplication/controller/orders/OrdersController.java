package com.foodapplication.controller.orders;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodapplication.model.daoimpl.OrderHistoryDaoImp;
import com.foodapplication.model.daoimpl.OrderItemDaoImp;
import com.foodapplication.model.daoimpl.OrdersDaoImp;
import com.foodapplication.model.model.CartItems;
import com.foodapplication.model.model.Orders;
import com.foodapplication.model.model.User;

public class OrdersController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Retrieve logged-in user from session
        User loggedInUser = (User) session.getAttribute("logedInUser");

        if (loggedInUser == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }

        // Retrieve the cart
        Map<Integer, CartItems> cart = (Map<Integer, CartItems>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            req.setAttribute("cartEmpty", true);
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
            return;
        }

        String paymentMethod = req.getParameter("payment_method");
        String address = req.getParameter("address");

        OrdersDaoImp ordersDao = new OrdersDaoImp();
        try {

            // Insert all orders
            ordersDao.insertAll(cart, loggedInUser.getUserid(), paymentMethod , address);

            // Fetch latest order ID
            List<Orders> orderList = ordersDao.fetchOrderId();
            if (orderList != null && !orderList.isEmpty()) {
                int orderId = orderList.get(0).getOrderID();

                // Insert into order history
                OrderHistoryDaoImp orderHistoryDao = new OrderHistoryDaoImp();
                orderHistoryDao.insertAll(cart, orderId, loggedInUser.getUserid());
                
                // Insert into order Item 
                OrderItemDaoImp orderItemDaoImp = new OrderItemDaoImp();
                orderItemDaoImp.insertAll(cart, orderId);

                // Clear the cart after successful order placement
                session.removeAttribute("cart");
                resp.sendRedirect("home.jsp?orderSuccess=true");

            } else {
                throw new Exception("Failed to retrieve order ID.");
            }
        } catch (Exception e) {
            req.setAttribute("error", "Unable to place your order at this time. Please try again later.");
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        }
    }
}
