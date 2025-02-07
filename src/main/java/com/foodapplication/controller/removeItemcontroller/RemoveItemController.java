package com.foodapplication.controller.removeItemcontroller;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodapplication.model.daoimpl.cartDaoImp;
import com.foodapplication.model.model.CartItems;

public class RemoveItemController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        
        HttpSession session = request.getSession();
        Map<Integer, CartItems> cart = (Map<Integer, CartItems>) session.getAttribute("cart");
        
        if (cart != null) {
            cartDaoImp dao = new cartDaoImp();
            cart = dao.removeItem(cart, menuId);
            session.setAttribute("cart", cart);
        }
        
        request.setAttribute("message", "Item removed successfully!");
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
