<%@page import="java.sql.SQLException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Narnix</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
<style>
body {margin: 0;}
h2{text-align: center;}
ul.sidenav {
    list-style-type: none;
    margin: 0;
    margin-top: -50px;
    padding: 0;
    width: 25%;
    background-color: #f1f1f1;
    position: fixed;
    height: 100%;
    overflow: auto;
}

ul.sidenav li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}
 
ul.sidenav li a.active {
    background-color: #4CAF50;
    color: white;
}

ul.sidenav li a:hover:not(.active) {
    background-color: #555;
    color: white;
}

div.content {
    margin-left: 25%;
    margin-top: 50px;
    padding: 1px 16px;
    height: 1000px;
}

@media screen and (max-width: 900px) {
    ul.sidenav {
        width: 100%;
        margin-top: 0;
        height: auto;
        position: relative;
    }
    ul.sidenav li a {
        float: left;
        padding: 15px;
    }
    div.content {margin-left: 0;}
}

@media screen and (max-width: 490px) {
    ul.sidenav li a {
        text-align: center;
        float: none;
    }
}
</style>
<!--<script>window.history.forward(1);</script>-->
</head>
<body>
    <!--<h1 style="text-align: center">Narnix Technolabs</h1>-->
    <% try{
        Class.forName("com.mysql.jdbc.Driver");  
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nar","root","")) {
            String e = (String)session.getAttribute("mail");
            session.setAttribute("mail",e);
            //request.getParameter("email");
            System.out.println("mail in Home is "+e);
            PreparedStatement ps=con.prepareStatement("select name, last_name from users where email=?");
            ps.setString(1, e);
            ResultSet rs=ps.executeQuery();
            String user,userr,userrr;
            
            if(rs.next())
            {
                user = rs.getString(1).toUpperCase();
                out.println("<h4>"+user+"</h4>");
                session.setAttribute("user",user);
                userrr = rs.getString(1)+" ";
                userr = rs.getString(2);
                session.setAttribute("userrr",userrr);
                session.setAttribute("userr",userr);
                //session.setAttribute("user",d);
                System.out.println("userrr in Home is "+ userrr);
                System.out.println("userr in Home is "+ userr);
                }
            else
                response.sendRedirect(request.getContextPath() + "/Logout.jsp");
            con.close();
        }
        
//        String all = request.getParameter("alliance");
//        String div = request.getParameter("division");
//        if("Employee".equals(all)&&"Technolabs".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Technolabs.jsp");
//        rd.include(request,response);}
//        else if("Employee".equals(all)&&"Negawatt".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Megamart.jsp");
//        rd.include(request,response);}
//        else if("Employee".equals(all)&&"Sq-My".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Sq-My.jsp");
//        rd.include(request,response);}
//        else if("Partner".equals(all)&&"Technolabs".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Technolabs.jsp");
//        rd.include(request,response);}
//        else if("Partner".equals(all)&&"Negawatt".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Megamart.jsp");
//        rd.include(request,response);}
//        else if("Partner".equals(all)&&"Sq-My".equals(div)){
        //request.getRequestDispatcher("Home.jsp").forward(request,response);
        }
    catch(IOException | ClassNotFoundException | SQLException es)
    {System.out.println(es);}

        //String user =(String)session.getAttribute("user"); %>
    <h5>Welcome</h5>
    <!--<h4><%//=user%></h4>-->
    <%//String e = (String)session.getAttribute("mail");
            //request.getParameter("email");%>
    <%%>
    
    <% //System.out.println("mail in home is "+e);
        %>
    <label><a href="Logout">Logout</a></label><style>h5{position: absolute; top: -2%; right: 2%;} h4{position: absolute; top: 1%; right: 2%;} label{position: absolute; top: 7%; right: 2%;} @media screen and (max-width: 900px) {h5{top:5%;}h4{top:8%;}label{top:14%;}}@media screen and (max-width: 490px) {h5{top:37%;}h4{top:40%;}label{top:46%;}}</style>
<ul class="sidenav">
  <li><a class="active" href="Home.jsp">Home</a></li>
  <li><a href="Projects.jsp">Projects</a></li>
  <li><a href="#Resources">Resources</a></li>
  <li><a href="loginfiles.jsp">Uploads</a></li>
  <li><a href="changepass.jsp">Change Password</a></li>
</ul>

<div class="content">
  
  <h2>Team Portal</h2>
</div>

</body>
</html>
