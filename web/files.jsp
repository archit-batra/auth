<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
<link href="css/files.css" rel="stylesheet" type="text/css">
<title>Uploads</title>
<script language="JavaScript">
	function download (id)
	{
		window.open ("Download?fileId="+id, "hiddenFrame");
                //request.getRequestDispatcher("Download"+"?fileId="+id).forward(request,response);
	}
	function deleteFile (id)
	{
		if (confirm("Delete File ID: "+id))
			window.location.replace ("files.jsp?delFileId="+id);
	}

</script>
<style>body{text-align: center;} table{align:center; margin-top: 10px; width:100%;font-family: 'Vollkorn', serif;font-size: 18px;} td{font-weight: bold; padding: 25px;} @media (max-width: 500px){ td{padding: 5px;} }</style>
</head>
<body>
<h5>Welcome</h5>
<label id="label"><a href="Logout">Logout</a></label><style>a{color: #4CAF50;} h5{position: absolute; top: 2%; right: 2%;} h4{position: absolute; top: 5%; right: 2%;} #label{position: absolute; top: 9%; right: 2%;} @media screen and (max-width: 900px) {h5{top:9%;}h4{top:12%;}#label{top:16%;}}@media screen and (max-width: 490px) {h5{top:43%;}h4{top:46%;}#label{top:49%;}}</style>    
        <ul class="sidenav">
  <li><a href="partner.jsp">Home</a></li>
  <li><a href="#Resources">Resources</a></li>
  <li><a class="active" href="files.jsp">Uploads</a></li>
  <li><a href="partnerchangepass.jsp">Change Password</a></li>
</ul>
<div class="wrapper">
<div class="container">
<h1>Files</h1>
<div id="box">
<table class="table table-fixed">
<thead><tr><th></th><th>File Name</th><th>Size</th><th>Received</th><th>Delete</th></tr></thead>
<%      String mail =(String)session.getAttribute("mail");
        String user =(String)session.getAttribute("user");
        out.println("<h4>"+user+"</h4>");
        System.out.println("mail in files is "+mail);
        session.setAttribute("mailmail",mail);
        String url = "jdbc:mysql://localhost:3306/nar";
        String driver="com.mysql.jdbc.Driver";

    	String sql = "SELECT email, file_id, file_name, file_size, received FROM documents WHERE email=?";

    	java.sql.Connection conn = null;
		String delFileId = request.getParameter("delFileId");
        try {
			java.sql.DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			conn = java.sql.DriverManager.getConnection(url, "root", null);
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1,mail);

			if (delFileId != null)
			{java.sql.Statement stmtt = conn.createStatement();
				stmtt.executeUpdate("DELETE FROM documents WHERE file_id = "+delFileId);
			}
			java.sql.ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				out.println (
					"<tbody><tr><td></td>" +
					"<td><a href='javascript:download("+rs.getString(2)+")'> "
						+rs.getString(3)+"</a></td>"+
					"<td align='right'>"+rs.getString(4)+"</td>"+
					"<td>"+rs.getString(5)+"</td><td><a href='javascript:deleteFile("+rs.getString(2)+")'>[X]</a></tr></tbody>");
			}
        } catch (java.sql.SQLException sqle) {
            System.err.println("SQL Error: " + sqle);
            //conn.rollback();  // ROLLBACK!!
        } catch (Exception e) {
            System.err.println("Error: " + e);
        } finally {
        	try {
        		if (conn != null)
		            conn.close();
        	} catch (java.sql.SQLException sqle) {
        	    sqle.printStackTrace();
        	}
        }
if(mail == null && mail.isEmpty()) response.sendRedirect(request.getContextPath() + "/Logout.jsp");
%>
</table>
</div>
    <form enctype="multipart/form-data" action="upload_file.jsp" method="post">
      <input type="text" style="display: none;" placeholder="Full Name" name="fullName" required></p>
      <input type="email" style="display: none;" placeholder="Email" name="email" required></p>
      <label class="custom-file-upload">
      <input type="file" name="fileName"/>Select File</label>
      <button type="submit">Upload</button>
    </form></div>

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
<iframe src="about:blank" name="hiddenFrame" width=0 height=0 frameborder=0></iframe>
</html>
