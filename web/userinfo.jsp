<%@page import="main.LoginDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Narnix docs</title>
        <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/userinfo.css">
	</head>
    <body><%String ac=request.getParameter("action");
            session.setAttribute("acti",ac);
            System.out.println("value retrieved in userinfo is"+ac);
          %>
        
            <h1>narnix technolabs</h1>
        <div class="wrapper">
		<div class="container">
		
        <h1>Kindly enter your details to download</h1>   
        
		<form action="approval" method="post">
		
        <input type="text" name="Name" placeholder="Full Name" required/>
		<input type="text" name="Designation" placeholder="Designation" required/>
		<input type="text" name="Company" placeholder="Company/Organisation" required/>
        <input type="email" name="Email" placeholder="Email" required/>
        <button type="submit">Submit</button>
        <span id="forg">Return to <a href="index.html">Homepage</a></span>
            ${message}
        
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
