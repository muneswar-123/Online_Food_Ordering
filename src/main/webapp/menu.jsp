<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.foodapplication.model.model.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        /* Top Navigation Buttons */
        .container {
            margin: 50px 165px 30px 165px;
        }

        .btns-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 20px;
            padding: 10px;
        }

        .btns-container .btn {
            background-color: #FFAA00;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
            flex: 1;
            padding: 10px;
            font-weight: bold;
            transition: background-color 0.3s ease;
            width: 150px; /* Width to match cards */
            text-align: center;
        }

        .btns-container .btn:hover {
            background-color: #E68A00;
            color: black;
        }

        .cards-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            padding: 20px;
        }

        .food-card {
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 300px;
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .food-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .food-card h3 {
            margin: 15px 0;
            color: #333;
        }

        .food-card p {
            margin: 0 15px 15px;
            font-size: 1rem;
            color: #666;
        }

        .food-card .btn {
            background-color: #FFAA00;
            border: none;
            color: #fff;
            padding: 10px 20px;
            margin: 10px;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }

        .food-card .btn:hover {
            background-color: #E68A00;
            color: black;
        }

        .food-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 10px rgba(0, 0, 0, 0.2);
        }

        .star-rating {
            display: inline-block;
        }

        .filled-star::before {
            content: "★"; 
            color: gold;
            font-size: 20px;
        }

        .empty-star::before {
            content: "★"; 
            color: #ddd;
            font-size: 20px;
        }
    </style>
</head>
<body>
    <!-- Top Navigation Buttons -->
    <div class="container previous-btn">
        <div class="btns-container">
            <a href="home.jsp" class="btn">Home</a>
            <a href="GetRestaurant" class="btn">Restaurant Page</a>
            <a href="OrderHistoryController" class="btn">Order History</a>
        </div>
    </div>

    <!-- Success or Error Message -->
	<% 
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<div id="message-container" 
	    style="text-align: center; margin: 20px 0; font-size: 1.2rem; color: green; font-weight: bold;">
	    <%= message %>
	</div>
	<%
	}
	%>

    <!-- Menu List as Cards -->
    <div class="cards-container">
        <% 
            List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
            if (menuList != null) {
                for (Menu menu : menuList) { 
        %>
            <div class="food-card">
          		  <% String base64Image = menu.getBase64Image(); %>
                <% if (base64Image != null) { %>
                    <img src="data:image/jpeg;base64,<%= base64Image %>" alt="<%= menu.getName() %>" />
                <% } else { %>
                    <img src="default-image.jpg" alt="No image available" />
                <% } %>
                <h3><%= menu.getName() %></h3>
                <p><%= menu.getDescription() %></p>
                <p>Price: <%= menu.getPrice() %></p>

                <!-- Rating Display as Stars -->
                <p>Rating: 
                    <span class="star-rating">
                        <% 
                            int rating = menu.getRating();
                            for (int i = 0; i < 5; i++) {
                                if (i < rating) {
                        %>
                                    <span class="filled-star"></span>
                        <% } else { %>
                                    <span class="empty-star"></span>
                        <% } 
                            }
                        %>
                    </span>
                </p>

              <form action="AddToCartController" method="get">
			    <input type="hidden" name="menuId" value="<%= menu.getMid() %>">
			    <input type="hidden" name="source" value="<%= request.getParameter("source") %>"> <!-- Pass source -->
			    Quantity: <input type="number" name="quantity" min="1" required>
			    <div class="btn-container mt-2">
			        <button type="submit" class="btn">Add To Cart</button>
			        <a href="cart.jsp" class="btn btn-primary">View Cart</a>
			    </div>
			</form>

            </div>
        <% 
                } 
            } else {
        %>
            <p>No menu items available.</p>
        <% } %>
    </div>
</body>
</html>
