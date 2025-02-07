package com.foodapplication.controller.updatecontroller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapplication.model.daoimpl.cartDaoImp;
import com.foodapplication.model.model.CartItems;

public class UpdateController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String menuIdParam = req.getParameter("menuId");
        String quantityParam = req.getParameter("quantity");
        
        if (menuIdParam != null && quantityParam != null) {
            try {
                int menuId = Integer.parseInt(menuIdParam);
                int quantity = Integer.parseInt(quantityParam);

                Map<Integer, CartItems> cart = (Map<Integer, CartItems>) req.getSession().getAttribute("cart");
                if (cart == null) {
                    resp.sendRedirect("menu.jsp");
                    return;
                }

                cartDaoImp cdi = new cartDaoImp();
                cart = cdi.updateItem(cart, menuId, quantity);
                
                req.getSession().setAttribute("cart", cart);
                resp.sendRedirect("cart.jsp");

            } catch (NumberFormatException e) {
                resp.sendRedirect("menu.jsp?error=Invalid input format");
            }
        } else {
            resp.sendRedirect("menu.jsp?error=Missing parameters");
        }
    }
}
