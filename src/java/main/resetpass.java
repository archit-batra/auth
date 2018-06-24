package main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "resetpass", urlPatterns = {"/resetpass"})
public class resetpass extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute(GlobalConstants.USER);
    String isResetPasswordVerified = (String) request.getSession().getAttribute(GlobalConstants.IS_RESET_PASSWORD_VERIFIED);
    
    try{
    if(userId!=null && isResetPasswordVerified != null) {
    String n = request.getParameter("password");
    String p = Password.hashPassword(n);
    LoginDao.updatePassword(userId.toString(), p);
    request.setAttribute("message", "Password changed successfully");
    response.sendRedirect(request.getContextPath() + "/Login.jsp");
    //request.getRequestDispatcher("Login.jsp").forward(request,response);
    }
    else{
    request.setAttribute("message", "Invalid input");
    request.getRequestDispatcher("Login.jsp").forward(request,response);
    } 
    }
    catch (DBException e) {
    e.printStackTrace();
    }
    
    
    }

}