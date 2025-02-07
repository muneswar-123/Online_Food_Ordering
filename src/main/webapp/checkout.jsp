<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <style>
        /* Global Styles */
        body {
            font-family: Arial, sans-serif;
            margin-top : 100px;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        /* Navbar Styling */
        nav {
            background-color: #ffb300; /* Vibrant yellow-orange */
            color: white;
            display: flex;
            padding: 5px 10px;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
        }

        #heading {
            color: white;
            font-size: 1.8rem;
            font-weight: bold;
            margin: 0 auto; 
            margin-left : 20px;
            letter-spacing: normal;
            text-transform: none;
        }

       
        @keyframes growText {
            from {
                letter-spacing: 2px;
            }
            to {
                letter-spacing: 6px;
            }
        }

        nav .nav-links {
            list-style: none;
            display: flex;
            gap: 1rem;
            margin-inline-end: 40px;
        }

        nav .nav-links a {
            color: white;
            text-decoration: none;
            font-size: 1rem;
            font-weight: 600;
            transition: color 0.3s;
        }

        nav .nav-links a:hover {
            color: #fff59d; /* Light yellow */
        }

        /* Order Confirmation Heading */
        h1 {
            text-align: center;
            color: #FFAA00;
            margin-top: 80px; /* Adjusted to avoid overlap with navbar */
        }

        /* Order Form Styling */
        form {
            max-width: 500px;
            margin: 30px auto;
            padding: 20px;
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        h3 {
            margin-bottom: 10px;
            color: #555;
        }

        input[type="radio"] {
            margin-right: 10px;
        }

        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #FFAA00;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #E68A00; /* Darker Orange */
            color: black;
        }

        /* Responsive Styles */
        @media (max-width: 600px) {
            form {
                margin: 20px;
                padding: 15px;
            }
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav>
        <h1 id="heading">Order Confirmation</h1>
        <ul class="nav-links">
            <li class="nav-item"><a class="nav-link" href="home.jsp">Add More Items ....</a></li>
        </ul>
    </nav>

    <!-- Order Confirmation Form -->
    <form action="OrdersController" method="POST">
        <label for="address">Address:</label>
        <input type="text" name="address" id="address" placeholder="Enter your address" required>

        <h3>Select Payment Method</h3>
        <input type="radio" id="credit_card" name="payment_method" value="credit_card" checked required>
        <label for="credit_card">Credit Card</label><br>

        <input type="radio" id="paypal" name="payment_method" value="paypal" required>
        <label for="paypal">PayPal</label><br>

        <input type="radio" id="bank_transfer" name="payment_method" value="bank_transfer" required>
        <label for="bank_transfer">Bank Transfer</label><br>

        <input type="radio" id="phone_pay" name="payment_method" value="phone_pay" required>
        <label for="phone_pay">Phone Pay</label><br>

        <input type="radio" id="google_pay" name="payment_method" value="google_pay" required>
        <label for="google_pay">Gpay</label><br>

        <input type="radio" id="cash_on_delivery" name="payment_method" value="cash_on_delivery" required>
        <label for="cash_on_delivery">Cash On Delivery</label><br>

        <button type="submit">Place Order</button>
    </form>
</body>
</html>
