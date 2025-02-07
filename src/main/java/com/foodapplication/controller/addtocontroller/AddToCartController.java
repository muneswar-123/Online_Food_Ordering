package com.foodapplication.controller.addtocontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapplication.model.daoimpl.MenuDaoImp;
import com.foodapplication.model.daoimpl.cartDaoImp;
import com.foodapplication.model.model.CartItems;
import com.foodapplication.model.model.Menu;

public class AddToCartController extends HttpServlet {

	private Menu m;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        int menuId = Integer.parseInt(req.getParameter("menuId"));
	        int quantity = Integer.parseInt(req.getParameter("quantity"));
	        String source = req.getParameter("source");

	        // Retrieve cart from session
	        Map<Integer, CartItems> cart = (Map<Integer, CartItems>) req.getSession().getAttribute("cart");
	        if (cart == null) {
	            cart = new HashMap<>();
	        }

	        // Fetch menu item
	        MenuDaoImp mdi = new MenuDaoImp();
	        m = mdi.getMenuItemBySource(source, menuId);

	        if (m == null) {
	            req.setAttribute("errorMessage", "Menu item not found for source: " + source);
	            req.getRequestDispatcher("menu.jsp").forward(req, resp);
	            return;
	        }

	        // Add to cart
	        cartDaoImp cartDao = new cartDaoImp();
	        cart = cartDao.addItem(cart, new CartItems(menuId, m.getRid(), m.getName(), quantity, m.getPrice()));


	        // Store updated cart in session
	        req.getSession().setAttribute("cart", cart);

	        // Success message
	        req.setAttribute("message", "Item added to cart successfully!");
	        req.getRequestDispatcher("menu.jsp").forward(req, resp);

	    } catch (Exception e) {
	        e.printStackTrace();
	        req.setAttribute("errorMessage", "Error adding item to cart: " + e.getMessage());
	        req.getRequestDispatcher("menu.jsp").forward(req, resp);
	    }
	}


}