<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapplication.model.model.OrderItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #F4F9F9; /* Light Mist Blue */
            color: #4A4E69; /* Dark Charcoal */
        }
        h1 {
            text-align: center;
            color: #3C4043; /* Deep Gray */
        }
        .order-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
            margin: 20px auto;
            max-width: 800px;
        }
        .card {
            background-color: #FFFFFF; /* White */
            border: 2px solid #A0D8B3; /* Aqua Green */
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card .item-id {
            font-size: 16px;
            font-weight: bold;
            color: #3C4043; /* Deep Gray */
            margin-bottom: 10px;
        }
        .card .item-details {
            margin-top: 15px;
            padding: 10px;
            background-color: #F4F9F9; /* Light Mist Blue */
            border-radius: 5px;
        }
        .card .item-details p {
            margin: 5px 0;
            font-size: 14px;
        }
        .card .total {
            font-size: 16px;
            font-weight: bold;
            color: #F4A261; /* Goldenrod */
            margin-top: 10px;
            text-align: right;
        }
        button {
            background-color: #FF6F61; /* Coral Pink */
            color: white;
            border: none;
            padding: 8px 16px;
            font-size: 14px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #E85A4F; /* Slightly darker Coral Pink */
        }
    </style>
</head>
<body>
    <h1>Order Details</h1>

    <div class="order-container">
    <%
        List<OrderItem> orderItemList = (List<OrderItem>) request.getAttribute("orderItemList");
        if (orderItemList == null || orderItemList.isEmpty()) {
    %>
        <p>No order items available.</p>
    <%
        } else {
            for (OrderItem orderItem : orderItemList) {
    %>
    			
                <div class="card">
                    <div class="item-id">Order Item ID: <%= orderItem.getOrderId() %></div>
                    <div class="item-details">
                        <p><strong>OrderID:</strong> <%= orderItem.getOrderId() %></p>
                        <p><strong>MenuID:</strong> <%= orderItem.getMenuId() %></p>
                        <p><strong>Quantity:</strong> <%= orderItem.getQuantity() %></p>
                        <p><strong>Price:</strong> ₹<%= orderItem.getPrice() %></p>
                        <p><strong>Item Total:</strong> ₹<%= orderItem.getItemTotal() %></p>
                    </div>
                    <div class="total">Item Total Price: ₹<%= orderItem.getItemTotal() %></div>
                </div>
    <%
            }
        }
    %>
    </div>
</body>
</html>