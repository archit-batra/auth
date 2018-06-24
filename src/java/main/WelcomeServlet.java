package main;

import java.io.IOException;  
import java.io.PrintWriter;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*; 

  
public class WelcomeServlet extends HttpServlet  { 
    
    @Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
        PrintWriter out = response.getWriter();
        Class.forName("com.mysql.jdbc.Driver");  
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nar","root","")) {
            HttpSession session = request.getSession();
            String e = (String)session.getAttribute("mail");
            session.setAttribute("mail",e);
            //request.getParameter("email");
            System.out.println("mail in WelcomeServlet is "+e);
            PreparedStatement ps=con.prepareStatement("select name from users where email=?");
            ps.setString(1, e);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next())
            {
                String d = rs.getString(1).toUpperCase();
                session.setAttribute("user",d);
                }
            else
                out.print("Exit");
            con.close();
        }
        
//        String all = request.getParameter("alliance");
//        String div = request.getParameter("division");
//        if("Employee".equals(all)&&"Technolabs".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Technolabs.jsp");
//        rd.include(request,response);}
//        else if("Employee".equals(all)&&"Megamart".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Megamart.jsp");
//        rd.include(request,response);}
//        else if("Employee".equals(all)&&"Sq-My".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Sq-My.jsp");
//        rd.include(request,response);}
//        else if("Partner".equals(all)&&"Technolabs".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Technolabs.jsp");
//        rd.include(request,response);}
//        else if("Partner".equals(all)&&"Megamart".equals(div)){
//        RequestDispatcher rd=request.getRequestDispatcher("Megamart.jsp");
//        rd.include(request,response);}
//        else if("Partner".equals(all)&&"Sq-My".equals(div)){
        //request.getRequestDispatcher("Home.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/Home.jsp");
    }
    catch(IOException | ClassNotFoundException | SQLException es)
    {System.out.println(es);}
} 
    @Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
}
    }  
  
