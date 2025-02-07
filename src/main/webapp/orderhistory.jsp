<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.foodapplication.model.model.OrderHistory" %>
<%@ page import="com.foodapplication.model.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #EEEDF7; /* Light Lavender */
            color: #343131; /* Dark Grey */
        }
        h1 {
            text-align: center;
            color: #343131; /* Dark Grey */
        }
        .order-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
            margin: 20px auto;
            max-width: 800px;
        }
        .card {
            background-color: #fff;
            border: 2px solid #D8A25E; /* Golden Yellow */
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card .order-id {
            font-size: 16px;
            font-weight: bold;
            color: #343131; /* Dark Grey */
            margin-bottom: 10px;
        }
        .card .restaurant-name {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .card .order-history {
            margin-top: 15px;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
            position: relative;
        }
        .card .order-history button.view-details {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #A04747; /* Red */
            color: white;
            border: none;
            padding: 5px 10px;
            font-size: 14px;
            cursor: pointer;
            border-radius: 5px;
        }
        .card .order-history button.view-details:hover {
            background-color: #343131; /* Dark Grey */
        }
        .card .order-history p {
            margin: 5px 0;
            font-size: 14px;
        }
        .card .total {
            font-size: 16px;
            font-weight: bold;
            color: #D8A25E; /* Golden Yellow */
            margin-top: 10px;
            text-align: right;
        }
        .status {
            font-size: 14px;
            margin-top: 5px;
        }
        .status.pending {
            color: #D8A25E; /* Golden Yellow */
        }
        .status.delivered {
            color: #28a745; /* Green for delivered */
        }
        .status.cancelled {
            color: #A04747; /* Red for cancelled */
        }
    </style>
</head>
<body>
    <h1>Your Order History</h1>

    <div class="order-container">
    <%
        List<OrderHistory> orderHistoryList = (List<OrderHistory>) session.getAttribute("orderHistoryList");
        List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");

       
        if (orderHistoryList == null || orderHistoryList.isEmpty()) {
    %>
        <p>No order history available.</p>
    <%
        } else {
            // Group by orderId
            Map<Integer, List<OrderHistory>> groupedOrders = orderHistoryList.stream()
                .collect(Collectors.groupingBy(OrderHistory::getOrderId));

            for (Map.Entry<Integer, List<OrderHistory>> entry : groupedOrders.entrySet()) {
                int orderId = entry.getKey();
                List<OrderHistory> orders = entry.getValue();
                int totalAmount = orders.stream().mapToInt(OrderHistory::getTotal).sum();

    %>
                <div class="card">
                    <div class="order-id">Order ID: <%= orderId %></div>
                    <div class="order-history">
                        <!-- Pass the source dynamically to ShowOrderItems -->
                        <a href="ShowOrderItems?orderId=<%= orderId %>">
						    <button class="view-details">View Details</button>
						</a>

                        <strong>Order History Details:</strong>
                        <ul>
                            <%
                                for (OrderHistory order : orders) {
                            %>
                                <li>Order History ID: <%= order.getOrderHistoryId() %>, 
                                    Status: <span class="status <%= order.getStatus().toLowerCase() %>">
                                        <%= order.getStatus() %>
                                    </span>, Total: ₹<%= order.getTotal() %>
                                </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                    <div class="total">Order Total: ₹<%= totalAmount %></div>
                </div>
    <%
            }
        }
    %>
    </div>
</body>
</html>
