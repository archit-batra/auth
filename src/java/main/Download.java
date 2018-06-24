package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import timlin.util.Logger;

@WebServlet(name = "Download", urlPatterns = {"/Download"})
public class Download extends HttpServlet
{
    /* Handle the HTTP GET method by building a simple web page.
    */

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost (HttpServletRequest request,
                	   HttpServletResponse response)
    throws ServletException, IOException
    {
    	int id = Integer.parseInt(request.getParameter("fileId"));

        String url = "jdbc:mysql://localhost:3306/nar";
        String sql = "SELECT file_size, file_name, file_bytes FROM documents WHERE file_id = ?";

    	Connection conn = null;

    	PreparedStatement prep = null;
        try {
	        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            conn = DriverManager.getConnection(url, "root", null);
	    //conn.setAutoCommit(false);    // SET AUTOCOMMIT OFF!!
            prep = conn.prepareStatement(sql);

			prep.setInt (1, id);
            ResultSet rs = prep.executeQuery();
//Logger.debug(String.format("File-ID: %d", id));

            if (!rs.next()) return;

			int fileSize = rs.getInt(1);
			String fileName = rs.getString(2);
    		int iByte;
    		InputStream is = rs.getBinaryStream(3);
    		if (is == null)	//return;
    		
			response.setContentType ("application/txt");
			response.setHeader ("Content-Disposition", "attachment;filename=\""+fileName+"\"");

    		java.io.OutputStream fos = response.getOutputStream();
    		
    		while ((iByte = is.read()) != -1) fos.write(iByte);
    		
    		conn.close();
    	} catch(Exception ex) {
    		//return "Error: "+ex.getMessage();
    	} finally {
    		
    	}

    }

    public void doGet (HttpServletRequest request,
                	   HttpServletResponse response)
    throws ServletException, IOException
    {
	   doPost(request, response);
    }

}