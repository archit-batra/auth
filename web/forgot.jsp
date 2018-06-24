<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Forgot Narnix Password</title>
        <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/forgot.css"> 
    </head>
    <body>
        <div class="wrapper">
	<div class="container">
            
        <form class="form" action="Servlet" method="post">
        <h1>Forgot Password</h1>   
        <input type="email" name="email" placeholder="Email" required>
            <button type="submit">Submit</button>
            <span id="forg">Return to <a href="index.html">Homepage</a></span>
            ${message}
        <input type="hidden" name="action" value="forgotpass">
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
