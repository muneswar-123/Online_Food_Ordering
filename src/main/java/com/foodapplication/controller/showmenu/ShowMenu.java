package com.foodapplication.controller.showmenu;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapplication.model.daoimpl.MenuDaoImp;
import com.foodapplication.model.model.Menu;

public class ShowMenu extends HttpServlet {
    private List<Menu> menuList;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the 'source' parameter to determine the source of the request
		String resid = req.getParameter("restaurant_id");
        String source = req.getParameter("source");
        MenuDaoImp mdi = new MenuDaoImp();

        if ("nonveg".equalsIgnoreCase(source)) {
        	menuList = mdi.fetchAllNonVeg();
            req.getSession().setAttribute("menuList", menuList);
        }
        else if ("veg".equalsIgnoreCase(source)) {
        	menuList = mdi.fetchAllVeg();
            req.getSession().setAttribute("menuList", menuList);
        }
        else if ("biryanispecials".equalsIgnoreCase(source)) {
        	menuList = mdi.fetchAllBiryaniSpecials();
            req.getSession().setAttribute("menuList", menuList);
        }
        else if ("pizza".equalsIgnoreCase(source)) {
        	menuList = mdi.fetchAllPizza();
            req.getSession().setAttribute("menuList", menuList);
        }
        else if ("burger".equalsIgnoreCase(source)) {
        	menuList = mdi.fetchAllBugger();
            req.getSession().setAttribute("menuList", menuList);
        }
        else if ("delights".equalsIgnoreCase(source)) {
        	menuList = mdi.fetchAllDelights();
            req.getSession().setAttribute("menuList", menuList);
        }
        else if ("desserts".equalsIgnoreCase(source)) {
        	menuList = mdi.fetchAllDesserts();
            req.getSession().setAttribute("menuList", menuList);
        }
        else if ("restaurant".equalsIgnoreCase(source)) {
        	int rid  = Integer.parseInt(resid);
            menuList = mdi.fetchAll(rid);
            req.getSession().setAttribute("menuList", menuList);
        }

        // Forward to the menu.jsp page with the appropriate data
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }
}
