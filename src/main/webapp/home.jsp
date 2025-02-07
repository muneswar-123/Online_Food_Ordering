<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GrubHub</title>
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        /* Root Styling */
        body {
            background-color: #fff9c4; /* Light yellow background */
            color: #333;
        }

        /* Navbar Styling */
        nav {
            background-color: #ffb300; /* Vibrant yellow-orange */
            color: white;
            padding: 1rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        nav .logo {
            font-size: 1.5rem;
            font-weight: bold;
            letter-spacing: 2px;
            text-transform: uppercase;
            animation: growText 1s infinite alternate;
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

        /* Header Styling */
        header {
            text-align: center;
            padding: 2rem 1rem;
        }

        header h1 {
            font-size: 2.5rem;
            color: #ff8f00; /* Darker yellow-orange */
            margin-bottom: 1rem;
        }

        header p {
            font-size: 1.2rem;
            color: #555;
        }

        header .btn-container {
            margin-top: 1.5rem;
        }

        header .btn {
            background-color: #ffb300; /* Yellow-orange button */
            color: white;
            padding: 0.8rem 1.5rem;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            margin: 0 0.5rem;
            transition: background-color 0.3s, transform 0.2s;
        }

        header .btn:hover {
            background-color: #ff8f00; /* Darker yellow-orange */
            transform: scale(1.05);
        }

        /* Cards Section */
        .cards-container {
            display: flex;
            overflow-x: auto;
            padding: 1rem;
            gap: 1rem;
        }

        .cards-container::-webkit-scrollbar {
            height: 8px;
        }

        .cards-container::-webkit-scrollbar-thumb {
            background-color: #ffb300; /* Yellow-orange scrollbar */
            border-radius: 4px;
        }

        .food-card {
            flex: 0 0 auto;
            width: 250px;
            text-align: center;
            transition: transform 0.3s;
            border: none;
    		background: none; 
        }

        .food-card:hover {
            transform: translateY(-10px);
        }

        .food-card img {
		    width: 120px;
		    height: 120px;
		    object-fit: cover;
		    border-radius: 50%; /* Circular image */
		    margin-top: 1rem;
		}

        .food-card h3 {
		    font-size: 1.2rem;
		    margin-top: 1rem;
		    color: #ff8f00; /* Text color for the food name */
		}

        /* Footer Styling */
        footer {
            text-align: center;
            background-color: #ffb300; /* Yellow-orange footer */
            color: white;
            padding: 1rem;
            margin-top: 2rem;
        }

        /* About GrubHub Section */
        .about-section {
            background: linear-gradient(135deg, #ffb300, #fff59d); /* Yellow-orange gradient */
            padding: 3rem 1rem;
            text-align: center;
            color: white;
            border-radius: 10px;
            margin-top: 3rem;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
        }

        .about-section h2 {
            font-size: 2.5rem;
            color: #fff;
            margin-bottom: 1.5rem;
            font-weight: bold;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .about-section p {
            font-size: 1.2rem;
            color: #fff;
            max-width: 800px;
            margin: 0 auto;
            line-height: 1.8;
        }

        /* About Food Explorer (GrubHub) */
        #about {
            padding: 2rem 1rem;
            background-color: #fff9c4; /* Light yellow background */
        }

        #about h2 {
            text-align: center;
            color: #ff8f00; /* Yellow-orange text */
            font-size: 2rem;
            margin-bottom: 1rem;
            font-weight: bold;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        #about p {
            text-align: center;
            font-size: 1.1rem;
            color: #555;
            max-width: 900px;
            margin: 0 auto;
        }

        #about .services-list {
            text-align: center;
            margin-top: 2rem;
        }

        #about .services-list h3 {
            color: #ff8f00; /* Yellow-orange header */
            font-size: 1.5rem;
            margin-bottom: 1rem;
            font-weight: bold;
        }

        #about .services-list ul {
            list-style: none;
            padding: 0;
            font-size: 1.1rem;
            color: #555;
            display: flex;
            justify-content: center;
            gap: 3rem;
        }

        #about .services-list ul li {
            max-width: 250px;
            padding: 1rem;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, box-shadow 0.3s;
        }

        #about .services-list ul li:hover {
            transform: translateY(-5px);
            box-shadow: 0px 8px 12px rgba(0, 0, 0, 0.15);
        }

        #about .services-list ul li strong {
            color: #ffb300; /* Yellow-orange strong text */
            font-size: 1.1rem;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav>
        <div class="logo">GrubHub</div>
        <ul class="nav-links">
            <li class="nav-item"><a class="nav-link" href="GetRestaurant">Restaurants</a></li>
			<li class="nav-item"><a class="nav-link" href="OrderHistoryController">Order History</a></li>
			<li class="nav-item"><a class="nav-link" href="Login.jsp">Log Out</a></li>
        </ul>
    </nav>

    <!-- Header -->
    <header>
        <h1>Welcome to GrubHub</h1>
        <p>Discover delicious food from the best restaurants around you!</p>
    </header>

    <!-- Food Cards -->
    <section class="cards-container">
        <div class="food-card">
            <a href="ShowMenu?source=nonveg">
                <img src="Images/foodcardnames/tandoorichicken.jpg" alt="TandooriChicken">
            </a>
            <h3>Non-Veg</h3>
        </div>
        
        <div class="food-card">
            <a href="ShowMenu?source=veg">
                <img src="Images/foodcardnames/veg.jpg" alt="vegimage">
            </a>
            <h3>Veg</h3>
        </div>
        
         <div class="food-card">
         	<a href="ShowMenu?source=biryanispecials">
           		 <img src="Images/foodcardnames/Biryani.jpg" alt="Biryani">
            </a>
            <h3>Biryani Specials</h3>
        </div>
        
        <div class="food-card">
	        <a href="ShowMenu?source=pizza">
	            <img src="Images/foodcardnames/pizza.jpg" alt="Pizza">
            </a>
            <h3>Pizza</h3>
        </div>
        
        <div class="food-card">
	        <a href="ShowMenu?source=burger">
	            <img src="Images/foodcardnames/bugger.jpg" alt="Burger">
            </a>
            <h3>Burger</h3>
        </div>
        
        <div class="food-card">
	        <a href="ShowMenu?source=delights">
	            <img src="Images/foodcardnames/southIndian.jpg" alt="delights">
            </a>
            <h3>South Indian Delights</h3>
        </div>
        
        <div class="food-card">
        	<a href="ShowMenu?source=desserts">
            	<img src="Images/foodcardnames/dessert.jpg" alt="Dessert">
            </a>
            <h3>Desserts</h3>
        </div>
       
    </section>

    <!-- About GrubHub -->
    <section id="about">
        <h2>About GrubHub</h2>
        <p>GrubHub is your ultimate food discovery app that allows you to browse a wide variety of delicious meals 
        from the best local restaurants. Whether you're craving a spicy non-veg dish, a cheesy pizza, or healthy vegetarian options, 
        we have it all! Explore, order, and get your favorite food delivered right to your doorstep with ease.</p>
        <div class="services-list">
            <h3>Services We Provide</h3>
            <ul>
                <li><strong>Variety of Cuisines:</strong> From local snacks to global dishes, we offer an extensive menu.</li>
                <li><strong>Fast Delivery:</strong> Enjoy quick deliveries with live tracking for your orders.</li>
                <li><strong>Customizable Orders:</strong> Personalize your meals with our easy-to-use customization options.</li>
                <li><strong>Exclusive Discounts:</strong> Get exclusive deals and discounts on your favorite dishes.</li>
            </ul>
        </div>
    </section>

    <!-- Footer -->
    <footer>
        &copy; 2024 GrubHub | All Rights Reserved
    </footer>
    
      <script>
    // Check URL parameters
    window.onload = function () {
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('orderSuccess') === 'true') {
            alert('Your order was placed successfully!');
        }
    };
</script>

</body>
</html>
