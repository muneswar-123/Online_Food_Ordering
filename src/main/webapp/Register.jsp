<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        /* Import Roboto font */
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);

        /* Body styling */
        body {
			background: linear-gradient(to bottom, #ffcc80, #ffe082); /* Yellow and orange gradient */
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden; /* Prevent scrolling */
        }

        /* Semi-transparent background */
        .register-page {
            width: 100%;
            max-width: 360px;
            padding: 5px;
            margin: auto;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 8px;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }

        /* Form container styling */
        .form {
            position: relative;
            z-index: 1;
            text-align: center;
            padding: 30px 40px;
        }

        /* Input field styling */
        .form input {
            font-family: "Roboto", sans-serif;
            outline: 0;
            background: #FFF8DC; /* Light yellow background */
            width: 100%;
            border: 1px solid #FFA500; /* Orange border */
            margin: 0 0 15px;
            padding: 12px;
            box-sizing: border-box;
            font-size: 14px;
            border-radius: 4px;
        }

        /* Button styling */
        .form button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #FFA500; /* Orange background */
            width: 100%;
            border: 0;
            padding: 12px;
            color: #ffffff;
            font-size: 14px;
            font-weight: bold;
            transition: all 0.3s ease;
            cursor: pointer;
            border-radius: 4px;
        }

        .form button:hover, .form button:active, .form button:focus {
            background: #FF8C00; /* Darker orange */
        }

        /* Label styling */
        .form label {
            display: block;
            margin-top: 10px;
            font-size: 14px;
            color: #FFA500; /* Orange text */
            text-align: left;
        }

        /* Message styling */
        .form .message {
            margin: 15px 0 0;
            color: #795548;
            font-size: 12px;
        }

        .form .message a {
            color: #ff6f00; 
            text-decoration: none;
            font-weight: bold;
        }

        .form .message a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="register-page">
    <div class="form">
        <h2>User Registration</h2>

        <form action="Register " method="POST">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" placeholder="Enter your name" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>

            <label for="number">Mobile Number</label>
            <input type="number" id="number" name="number" placeholder="Enter your mobile number" required>

            <button type="submit">Register</button>

            <p class="message">Already registered? <a href="Login.jsp">Sign In</a></p>
        </form>
    </div>
</div>

</body>
</html>
