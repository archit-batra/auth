<%@page import="main.LoginDao"%>
<%@page import="main.Setup"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/projects.css"> 
        <title>Projects</title>
        <script>
	function deleteFile (id)
	{       
		if (confirm("Delete Project id: "+id))
			window.location.replace("Projects.jsp?delProjectId="+id);
        }
        </script>
        </head>
        <body>
        <ul class="sidenav">
        <li><a  href="Home.jsp">Home</a></li>
        <li><a class="active" href="Projects.jsp">Projects</a></li>
        <li><a href="#Resources">Resources</a></li>
        <li><a href="loginfiles.jsp">Uploads</a></li>
        <li><a href="changepass.jsp">Change Password</a></li>
        </ul>
    
        <label id="label"><a href="Logout">Logout</a></label><style>h5{position: absolute; top: 2%; right: 2%;} h4{position: absolute; top: 5%; right: 2%;} #label{position: absolute; top: 9%; right: 2%;} @media screen and (max-width: 900px) {h5{top:9%;}h4{top:12%;}#label{top:16%;}}@media screen and (max-width: 490px) {h5{top:42%;}h4{top:45%;}#label{top:48%;}}</style>    
        <div class="wrapper">
	<div class="container">
        <%
        String delProjectId = request.getParameter("delProjectId");
        System.out.println("delete project name "+delProjectId);
        String e = (String)session.getAttribute("mail");
        String user = (String)session.getAttribute("user");
        String userrr = (String)session.getAttribute("userrr");
        String userr = (String)session.getAttribute("userr");
        out.println("<h5>Welcome</h5>");
        out.println("<h4>"+user+"</h4>");
        
        if("rapchit93@gmail.com".equals(e)){
        Class.forName(Setup.DB_DRIVER);  
        Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
        PreparedStatement ps=con.prepareStatement("select name,last_name from users");  
        ResultSet rs=ps.executeQuery();
        %>
        <script>
        function OnSubmitForm() {
        <%
        String all = request.getParameter("alliance");
        String nam = request.getParameter("Name");
        String desc = request.getParameter("Desc");
        String due = request.getParameter("date");
        System.out.println("name is "+nam);
        System.out.println("Member is "+all);
        System.out.println("Description is "+desc);
        LoginDao.addProject(nam, desc, all,due);
        %>
        }
        </script>
        <form onsubmit="OnSubmitForm()" method="post">
        <span class="all"><label>Select Member<!--(Hold down Ctrl(windows)/Command(Mac) button to select multiple options.)--></label></span>
        <div id="sel1"> <select style="VERTICAL-ALIGN: middle; WIDTH: 99px" name="alliance" required>
        <%
        while(rs.next()){ 
        String ali=rs.getString(1)+" "+rs.getString(2);
        %>
        <option><%= ali%></option>
        <% } %>
        </select>     
        </div>
        <span class="due"><label>Due Date</label></span>
        <div id="date"><input type="date" name="date" required></div>
        <input type="text" id="name" name="Name" placeholder="Project Name" required>
        <textarea rows="4" cols="50" id="desc" name="Desc" placeholder="Description" required/></textarea>
        <button type="submit">Submit</button></form>
        <%
        
        try{
        Class.forName(Setup.DB_DRIVER);  
        PreparedStatement ps1=con.prepareStatement("select * from projects");
        if (delProjectId != null)
        {
        System.out.println("THIS LINE EXECUTES");    
        PreparedStatement ps2=con.prepareStatement("DELETE FROM projects WHERE id = ?");
        ps2.setString(1, delProjectId);
        ps2.executeUpdate();
        }
        ResultSet rs1=ps1.executeQuery();
        System.out.println(userrr+ userr);
        while(rs1.next())
        {
        String has = main.Utils.prepareRandomString(30);
        String hasa = has;
        System.out.println(has+" "+hasa);
        out.println("<div class=\"wrap\"><nav class=\"vertical\"><ul><li><label for="+has+">"+rs1.getString(1)+"</label><input type=\"radio\"  name=\"verticalMenu\" id="+hasa+" /><div><p>Member: "+rs1.getString(3)+"</p><p>Alot Date: "+rs1.getString(5)+"</p><p>Due Date: "+rs1.getString(6)+"</p><p>Description: "+rs1.getString(2)+"</p></div></li></ul></nav></div><form method=\"post\"><input type=\"button\" value=\"Delete\" onclick='deleteFile(\""+rs1.getInt(4)+"\")'></form>");
        }
        con.close();
        }
        catch(Exception es)
        {System.out.println(es);}
        }
        else if(e != null && !e.isEmpty()){
        try{
        Class.forName(Setup.DB_DRIVER);  
        Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
        PreparedStatement ps=con.prepareStatement("select * from projects where members=?");
        ps.setString(1, userrr+userr);
        ResultSet rs=ps.executeQuery();
        System.out.println(userrr+ userr);
        while(rs.next())
        {
        String has = main.Utils.prepareRandomString(30);
        String hasa = has;
        System.out.println(has+" "+hasa);
        out.println("<div class=\"wrap\"><nav class=\"vertical\"><ul><li><label for="+has+">"+rs.getString(1)+"</label><input type=\"radio\"  name=\"verticalMenu\" id="+hasa+" /><div><p>Alot Date: "+rs.getString(5)+"</p><p>Due Date: "+rs.getString(6)+"</p><p>Description: "+rs.getString(2)+"</p></div></li></ul></nav></div>");
        }
        con.close();
        }
        catch(ClassNotFoundException | SQLException es)
        {System.out.println(es);}
        }
        else response.sendRedirect(request.getContextPath() + "/Logout.jsp");
        %>
        </div>
        </div>
        </body>
        </html>
