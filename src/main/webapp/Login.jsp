<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login/Register</title>
    <style>
        @import url('https://fonts.googleapis.com/css?family=Roboto:300');

        /* Body styling with background color */
        body {
            font-family: "Roboto", sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(to bottom, #ffcc80, #ffe082); /* Yellow and orange gradient */
        }

        /* Main container styling */
        .login-page {
            width: 380px;
            padding: 20px;
            margin: auto;
            background: rgba(255, 255, 255, 0.9);/* Slightly transparent yellow */
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        /* Heading styling */
        .form h2 {
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: 500;
            font-weight: bold;
        }

        /* Input field styling */
        .form input {
            font-family: "Roboto", sans-serif;
            background: #fff8e1; /* Light yellow */
            width: 100%;
            border: 1px solid #ffcc80;
            margin: 10px 0;
            padding: 12px;
            font-size: 15px;
            border-radius: 5px;
            box-sizing: border-box;
            transition: border-color 0.3s ease;
        }
        .form input:focus {
            border-color: #ffa726; /* Bright orange */
        }

        /* Button styling */
        .form button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            background: #ffa726; /* Bright orange */
            width: 100%;
            border: 0;
            padding: 12px;
            color: #FFFFFF;
            font-size: 15px;
            font-weight: bold;
            margin-top: 15px;
            transition: background 0.3s ease;
            cursor: pointer;
            border-radius: 5px;
        }
        .form button:hover {
            background: #fb8c00; /* Slightly darker orange */
        }

        /* Message styling */
        .form .message {
            margin: 20px 0 0;
            font-size: 14px;
            color: #795548; /* Brownish tone */
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

<div class="login-page">
    <div class="form">
        <h2>Login to User Account</h2>
        <form class="login-form" action="Login">
            <input type="text" placeholder="Username" name="email" required/>
            <input type="password" placeholder="Password" name="password" required/>
            <button type="submit">Login</button>
            <p class="message">Not registered? <a href="Register.jsp">Create an account</a></p>
        </form>
    </div>
</div>

</body>
</html>
