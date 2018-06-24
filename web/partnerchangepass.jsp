<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Change Password for Narnix</title>
        <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/changepass.css"> 
        <script>
            var check = function() {
  if (document.getElementById('password').value ==
    document.getElementById('confirm_password').value) {
    document.getElementById('message').innerHTML = '';
   } else {
    document.getElementById('message').innerHTML = 'New passwords should match.';
      }}
        </script>
    </head>
    <body>
        <h5>Welcome</h5>
<label id="label"><a href="Logout">Logout</a></label><style>a{color: white;} h5{position: absolute; top: 2%; right: 2%;} h4{position: absolute; top: 5%; right: 2%;} #label{position: absolute; top: 9%; right: 2%;} @media screen and (max-width: 900px) {h5{top:9%;}h4{top:12%;}#label{top:16%;}}@media screen and (max-width: 490px) {h5{top:43%;}h4{top:46%;}#label{top:49%;}}</style>    
        <ul class="sidenav">
  <li><a href="partner.jsp">Home</a></li>
  <li><a href="#Resources">Resources</a></li>
  <li><a href="files.jsp">Uploads</a></li>
  <li><a class="active" href="partnerchangepass.jsp">Change Password</a></li>
</ul>

        <%
        String mail =(String)session.getAttribute("mail");
        String user =(String)session.getAttribute("user");
        out.println("<h4>"+user+"</h4>");
        session.setAttribute("mailmail",mail);
        System.out.println("mail in changepass is "+mail);
        if(mail == null && mail.isEmpty()) response.sendRedirect(request.getContextPath() + "/Logout.jsp");
        %>
        <div class="wrapper">
	<div class="container">
            
        <form class="form" action="PartnerServlet" method="post">
        
            <h1>Change Password</h1>   
        
             <input type="password" name="passwordold" placeholder="Current Password" required>
         
             <input type="password" name="passwordnew" id="password" onkeyup='check();' placeholder="New Password" required>
            <input type="password" id="confirm_password" onkeyup='check();' placeholder="New Password Again" required>
            <button type="submit">Submit</button><br><br>
         <span id='message'></span><br>
            ${message}
            
    
        
        
        <input type="hidden" name="action" value="changepass">
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
