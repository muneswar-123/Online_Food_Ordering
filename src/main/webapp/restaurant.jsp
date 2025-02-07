<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapplication.model.model.Restaurant" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }

    .container {
        max-width: 1200px;
        margin: 20px auto;
    }

    .card-container {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
    }

    .card {
        flex: 1 1 calc(33.333% - 20px);
        background-color: #FFE47A; /* Soft Yellow */
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        position: relative;
    }

    .card h3 {
        font-size: 1.5em;
        color: #FF8C00; /* Deep Orange */
        margin-bottom: 10px;
        position: relative;
    }

    .rating {
        position: absolute;
        top: 10px;
        right: 15px;
        background-color: #FFAA00; /* Orange */
        color: white;
        padding: 5px 10px;
        border-radius: 20px;
        font-size: 0.9em;
        font-weight: bold;
    }

    .card p {
        font-size: 1em;
        color: #444;
        margin: 5px 0;
    }

    .card button {
        background-color: #FFAA00; 
        color: #FFF;
        border: none;
        padding: 10px 15px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .card button:hover ,.btn-warning:hover{
        background-color: #E68A00; /* Darker Orange */
        color:black;
    }
    .btn-warning {
		color:#fff;
		font-weight:bold;
    }
    

    @media (max-width: 768px) {
        .card {
            flex: 1 1 calc(50% - 20px);
        }
    }

    @media (max-width: 480px) {
        .card {
            flex: 1 1 100%;
        }
    }
</style>
</head>
<body>

<div class="container">
    <div class="mb-3 d-flex justify-content-between">
        <a href="home.jsp" class="btn btn-warning">Home</a>
        <a href="OrderHistoryController" class="btn btn-warning">Order History</a>
    </div>

    <% 
        List<Restaurant> restaurantList = (List<Restaurant>)session.getAttribute("restaurantList");
    %>

    <div class="card-container">
        <% for (Restaurant restaurant : restaurantList) { %>
            <div class="card">
                <h3>
                    <%= restaurant.getName() %>
                    <span class="rating"><%= restaurant.getRating() %> &#9733;</span>
                </h3>
                <p><strong>Cuisine Type:</strong> <%= restaurant.getCusineeType() %></p>
                <p><strong>Address:</strong> <%= restaurant.getAddress() %></p>
                <p><strong>Active:</strong> <%= restaurant.getIaAchive() %></p>
				<a href="ShowMenu?restaurant_id=<%= restaurant.getRid() %>&source=restaurant">
                    <button>View Menu</button>
                </a>
            </div>
        <% } %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>