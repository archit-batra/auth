<!DOCTYPE HTML>
<html>
<head>
<title>File Upload Utility</title>
</head>
<body>
<%@ page import="java.sql.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%
	ServletContext sc = getServletContext();
//sc.log("File Upload Started");

	int maxFileSize = 5 * 1024 * 1024;  // 5MB max

	MultipartParser parser = 
     	new MultipartParser(request, maxFileSize);
	com.oreilly.servlet.multipart.Part p = null;
	long numBytes = 0;

	String files[] = {""};
	int index = 0;
	String email="";
	String fullName="";
        String mail =(String)session.getAttribute("mailmail");
	//sc.log("Uploading Files for Request-ID: "+reqID);
	while((p = parser.readNextPart()) != null)
	{
//sc.log("ARG: "+p.getName());
		if(p.isParam())
		{
			//Do something here with other form 
			//elements that are not files
			String pName = p.getName();
			if (pName.equals("fullName"))
			{
				ParamPart pp = (ParamPart)p;
				fullName = pp.getStringValue();
			}
			else if (pName.equals("email"))
			{
				ParamPart pp = (ParamPart)p;
				email = pp.getStringValue();
			}
		}
		else if(p.isFile())
		{
			FilePart fp = (FilePart)p;
			String fileName = fp.getFileName();
System.out.println("File: "+fp.getFileName());
            if (fileName != null) 
            {
		        String url = "jdbc:mysql://localhost:3306/nar";
		        String driver="com.mysql.jdbc.Driver";
		
		    	String sql =
					"INSERT INTO logindocuments (email, full_name, comments, file_bytes, file_size, file_name,received) VALUES (?,?,?,?,?,?,SYSDATE())";
		
		    	Connection conn = null;
		
		    	PreparedStatement prep = null;
		        try {
					java.io.ByteArrayOutputStream aBytes = new java.io.ByteArrayOutputStream();
					numBytes = fp.writeTo(aBytes);
		
			        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		            conn = DriverManager.getConnection(url, "root", null);

		            prep = conn.prepareStatement(sql);
		
					prep.setString (1, mail);
					prep.setString (2, fullName);
					prep.setString (3, fileName);
					prep.setBytes(4, aBytes.toByteArray());
					prep.setLong(5, numBytes);
					prep.setString(6, fileName);
		            prep.execute();
		            //conn.commit();
		        } catch (SQLException sqle) {
		            System.err.println("SQL Error: " + sqle);
		            //conn.rollback();  // ROLLBACK!!
		        } catch (Exception e) {
		            System.err.println("Error: " + e);
		        } finally {
		        	try {
		        		if (prep != null)
		        			prep.close();
		        		if (conn != null)
				            conn.close();
		        	} catch (SQLException sqle) {
		        	    sqle.printStackTrace();
		        	}
		        }

				request.getRequestDispatcher("loginfiles.jsp").forward(request,response);
        
                        }
		}
	}
%>
</body>
</html>