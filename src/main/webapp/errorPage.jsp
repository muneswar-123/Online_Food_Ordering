<%@ page isErrorPage="true" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h3 class="alert alert-danger">Oops! Something went wrong.</h3>
        
        <!-- Error message (if any) -->
        <p><strong>Error:</strong> <%= request.getAttribute("javax.servlet.error.message") != null ? request.getAttribute("javax.servlet.error.message") : "Unknown error occurred" %></p>

        <!-- Stack trace for debugging (Only in development) -->
        <p><strong>Error Details:</strong></p>
        <pre>
            <%= exception != null ? exception.toString() : "No exception details available" %>
        </pre>

        <!-- Suggested actions -->
        <p>Please check if you've followed all steps correctly, such as adding items to the cart or selecting an option.</p>
        
        <!-- Navigation Links -->
        <p>What would you like to do next?</p>
        <ul>
            <li><a href="menu.jsp">Go back to the menu</a></li>
            <li><a href="restaurant.jsp">Go back to the restaurant page</a></li>
            <li><a href="home.jsp">Go to the homepage</a></li>
        </ul>
    </div>
</body>
</html>
