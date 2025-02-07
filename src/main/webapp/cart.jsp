<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.foodapplication.model.model.Menu"%>
<%@ page import="com.foodapplication.model.model.CartItems"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
	        body {
	            background-size: cover;
	            background-attachment: fixed;
	            background-position: center;
	            height: 100vh;
	            margin: 0;
	            font-family: 'Arial', sans-serif;
	        }
	
	        .container {
	            margin-top: 50px;
	        }
	
	        .dropdown-container {
	            background-color: #FFE47A; /* Soft Yellow */
	            padding: 15px;
	            border-radius: 10px;
	            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
	        }
		
	        .previous-btn .btn {
	            margin-right: 20px;
	        }
	        
	        .btns {
	        	background-color: #FFAA00;;
            	color: #fff;
            	padding:8px;
            	margin-left : 50px;
            	margin-right : 20px;
            	border-radius:5px;
            	padding-left: 100px;
            	padding-right: 100px;
            	font-weight:bold;
            	display: flex;
	            justify-content: space-between;
	            align-items: center;
	            gap: 20px;
	        }
	        .btns:hover,.btn:hover {
	        	 background-color: #E68A00;
	        	 color:black;
	        	 border-color: transparent;
	        	 text-decoration: none;
	        }
	        #btn:hover {
			    background-color: red;
			    border-color: transparent;
			}
	        #ordercfm:hover {
			    background-color: LightGreen;
			    border-color: transparent;
			}
	        .btns-container {
	  			display: flex;
	            justify-content: space-between;
	            align-items: center;
	            gap: 20px;
	            padding: 10px;
	        }
	        .btn {
	        	background-color: #FFAA00;;
            	color: #fff;
            	border-color: transparent;
	        }	
	        .alert {
	            margin-top: 20px;
	        }
    </style>
</head>
<body>

  <!-- Top Navigation Buttons -->
    <div class = "container previous-btn">
    	<div class="btns-container">
	        <a href="home.jsp" class="btns">Home</a>
	        <a href="menu.jsp" class="btns">Menu</a>
	        <a href="restaurant.jsp" class="btns">Restaurants<a>
    	</div>
    </div>

    <!-- Cart Dropdown for Update and Remove -->
    <div class="container mt-4 dropdown-container">
        <h4>Cart</h4>
        
        <% 
            // Get the cart from session
            Map<Integer, CartItems> cart = (Map<Integer, CartItems>) session.getAttribute("cart");

            if (cart != null && !cart.isEmpty()) {
        %>
        
        <!-- Update Cart Form -->
        <form action="UpdateController" method="get">
            <label for="menuId">Select Item to Update:</label>
            <select name="menuId" id="menuId" class="form-control">
                <% 
                    for (Map.Entry<Integer, CartItems> entry : cart.entrySet()) {
                        CartItems item = entry.getValue();
                %>
                    <option value="<%= item.getMenu_id() %>">
                        <%= item.getName() %> - Quantity: <%= item.getQuantity() %> - Sub Total: <%= item.getQuantity() * item.getPrice() %>
                    </option>
                <% 
                    }
                %>
            </select>
            <label for="quantity" class="mt-2">New Quantity:</label>
            <input type="number" name="quantity" min="1" class="form-control" required>
            <button type="submit" class="btn btn-secondary mt-2">Update Cart</button>
        </form>

        <!-- Remove Item Form -->
        <form action="RemoveItemController" method="post" class="mt-3">
            <label for="removeMenuId">Select Item to Remove:</label>
            <select name="menuId" id="removeMenuId" class="form-control">
                <% 
                    for (Map.Entry<Integer, CartItems> entry : cart.entrySet()) {
                        CartItems item = entry.getValue();
                %>
                    <option value="<%= item.getMenu_id() %>"><%= item.getName() %></option>
                <% 
                    }
                %>
            </select>
            <div>
                <button type="submit" id ="btn"class="btn btn-danger mt-2">Remove Item</button>
                <a href="checkout.jsp" id ="ordercfm" class="btn btn-secondary mt-2">Proceed To Order</a>
            </div>
        </form>

        <% 
            } else {
        %>

        <!-- Alert when cart is empty -->
        <div class="alert alert-warning" role="alert">
            Your cart is empty! Please add some items to proceed with your order.
        </div>

        <% 
            }
        %>
        
    </div>

</body>
</html>
