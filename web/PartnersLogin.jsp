<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log in to Narnix</title>
        <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login.css">
        <!--<script>window.history.forward(1);</script>-->
    </head>
    <body>
        <div class="wrapper">
	<div class="container">
            ${message}
            <h1>Welcome</h1>
		
	<form class="form" action="PartnerServlet" method="post">
<!--            <span class="all"><label>Select alliance</label></span>
            <div id="sel1"> <select style="VERTICAL-ALIGN: middle; WIDTH: 99px" name="alliance">
                <option>Employee</option>
                <option>Partner</option></select>    
            </div>
            
            <span class="div"><label> Select division</label></span>
            <div id="sel2"><select style="VERTICAL-ALIGN: middle; WIDTH: 99px" name="division">
                <option>Technolabs</option>
                <option>Megamart</option>
                <option>Sq-My</option></select>
            </div>
            -->
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
            <span id="forg">Forgot <a href="partnerforgot.jsp">password?</a></span>
            <span id="forg">Don't have an account?<a href="PartnerSignup.html">Sign Up</a></span>
            <span id="forg">Return to <a href="index.html">Homepage</a></span>
            <input type="hidden" name="action" value="Login">
	</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
        </div>
    </body>
</html>