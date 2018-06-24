package main;

import java.io.IOException;  
import java.io.PrintWriter;    
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="PartnerServlet", urlPatterns={"/PartnerServlet"})  
public class PartnerServlet extends HttpServlet {  
    
    @Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{    
        response.setContentType("text/html");  
    try (PrintWriter out = response.getWriter()) {
        String ac=request.getParameter("action");
        String n,ln,e,pr,op,np,hp;
//        al = request.getParameter("ali");
//        d = request.getParameter("division");
        e = request.getParameter("email");
        pr = request.getParameter("password");
        String p = Password.hashPassword(pr);
        String activ = "active";
        HttpSession session = request.getSession();
        System.out.println("mail in servletbegin is "+e);
        session.setAttribute("mail",e);
        
        if("Login".equals(ac))
        {   
        if(!PartnerLoginDao.existingmail(e)){
        request.setAttribute("message", "This email is not registered.");
        request.getRequestDispatcher("PartnersLogin.jsp").forward(request,response);
        }
        if(PartnerLoginDao.validate(e,pr)){
        if(PartnerLoginDao.validateStatus(e, activ))
        {
        //request.getRequestDispatcher("WelcomeServlet").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/partner.jsp");
        }
        else
        {
        request.setAttribute("message", "Account activation is pending.");
        request.getRequestDispatcher("PartnersLogin.jsp").forward(request,response);
        }
        }
        else
        {
        request.setAttribute("message", "Invalid password. Please try again.");
        request.getRequestDispatcher("PartnersLogin.jsp").forward(request,response);
        }
        }
        /*StatusPojo sp = new StatusPojo();
        try {
			UserPojo up = LoginDao.verifyLogin(e, pr);
			if(up != null) {
				if(up.getSTATUS().equals(GlobalConstants.ACTIVE) || up.getSTATUS().equals(GlobalConstants.IN_RESET_PASSWORD)) {
					request.getSession().setAttribute(GlobalConstants.USER, up.getUSER_ID());
					request.getSession().setAttribute(GlobalConstants.USER_NAME, up.getFIRST_NAME()+" "+up.getLAST_NAME());
					sp.setCode(0);
					sp.setMessage("Success");
                                        request.getRequestDispatcher("WelcomeServlet").forward(request,response);
				} else if(up.getSTATUS().equals(GlobalConstants.NEW)){
					sp.setCode(-1);
					request.setAttribute("message", "Account activation is in pending");
                                        request.getRequestDispatcher("Login.jsp").forward(request,response);
                                        sp.setMessage("Account activation is in pending");
				} else {
					sp.setCode(-1);
					sp.setMessage("Unknown error");
				}
				
			} else {
				sp.setCode(-1);
				sp.setMessage("Email or Password is not valid");
                                request.setAttribute("message", "Invalid username or password. Please try again.");
                                request.getRequestDispatcher("Login.jsp").forward(request,response);
			}
		} catch (DBException ex) {
			ex.printStackTrace();
			sp.setCode(-1);
			sp.setMessage(ex.getMessage());
		}
		PrintWriter pw = response.getWriter();
		pw.write(Utils.toJson(sp));
		pw.flush();
		pw.close();
	}
        else System.out.println("error in servlet");
        }*/          
        else if("Signup".equals(ac))
        {   
        if(PartnerLoginDao.existingmail(e)){
        request.setAttribute("message", "It looks like you're already a user. Kindly sign in to continue. ");
        request.getRequestDispatcher("PartnersLogin.jsp").forward(request,response);
        }
        else{
        n = request.getParameter("name");
        ln = request.getParameter("lastname");
        String has = Utils.prepareRandomString(30);
        String hash = BCrypt.hashpw(has, GlobalConstants.SALT);
        String id = PartnerLoginDao.getData(n,ln,e,hash,p);
        PartnerMailUtil.sendEmailRegistrationLink(id, e, has);
        request.setAttribute("message", "Registration Link Was Sent To Your Mail Successfully. Please Verify Your Email.");
        request.getRequestDispatcher("PartnersLogin.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/PartnersLogin.jsp");
        //request.getRequestDispatcher("WelcomeServlet").forward(request,response);
        }
        }
        else if("changepass".equals(ac))
        {
        String mail =(String)session.getAttribute("mailmail");
            
        op = request.getParameter("passwordold");
        np = request.getParameter("passwordnew");
        hp = Password.hashPassword(np);
        System.out.println("mail in servlet is "+mail);
        if(PartnerLoginDao.oldpass(mail,op,hp)){
        request.setAttribute("message", "Your new password has been updated. Kindly sign in to continue. ");
        //request.getRequestDispatcher("PartnersLogin.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/PartnersLogin.jsp");
        }
        else{
        request.setAttribute("message", "<html><head><style></style></head><body><br>The current password you entered is incorrect. Please try again.</body></html>");
        request.getRequestDispatcher("partnerchangepass.jsp").forward(request,response);
        
        }
        }
        else if("forgotpass".equals(ac))
        {
        UserPojo up=PartnerLoginDao.selectUSERbyEmail(e);
        if(up!=null) {
	String has = Utils.prepareRandomString(30);
        PartnerLoginDao.updateEmailVerificationHashForResetPassword(e, BCrypt.hashpw(has,GlobalConstants.SALT));
	PartnerMailUtil.sendResetPasswordLink(up.getUSER_ID()+"", e, has);
	request.setAttribute("message", "<html><head><style></style></head><body><br>We have sent reset password link to your email.</body></html>");
        request.getRequestDispatcher("partnerforgot.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/partnerforgot.jsp");
        }
        else {
	request.setAttribute("message", "<html><head><style></style></head><body><br>This email doesn't exist.</body></html>");
        request.getRequestDispatcher("partnerforgot.jsp").forward(request,response);
        }
        }
        /*else if("Projects".equals(ac))
        {
//        String m = (String)session.getAttribute("mail");
//        String userrr = (String)session.getAttribute("userrr");
//        String userr = (String)session.getAttribute("userr");
//        session.setAttribute("maill",m);
//        session.setAttribute("userrrrr",userrr);
//        session.setAttribute("userrrr",userr);
        //String members=" ";
        String all = request.getParameter("alliance");
        String nam = request.getParameter("Name");
        String desc = request.getParameter("Desc");
        //for(int i=0; i<all.length; i++){
        //members+=all[i]+" ";}
        LoginDao.addProject(nam, desc, all);
        request.getRequestDispatcher("Projects.jsp").forward(request,response);
        //response.sendRedirect(request.getContextPath() + "/Projects.jsp");
        }*/
    }
    catch(Exception ex){System.out.println(ex);}
    }
//@Override
//public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//doPost(request,response);
//}
}
