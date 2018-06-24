package main;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

//import com.sl.dao.UserDAO;
//import com.sl.db.DBException;
//import com.sl.model.UserPojo;
//import com.sl.util.BCrypt;
//import com.sl.util.GlobalConstants;
//import com.sl.util.MailUtil;
//import com.sl.util.Utils;

/**
 * Servlet implementation class VerifyRegisteredEmailHash
 */
@WebServlet("/VerifyRegisteredEmailHash")
public class VerifyRegisteredEmailHash extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final Logger LOGGER = Logger.getLogger(VerifyRegisteredEmailHash.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyRegisteredEmailHash() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get user Id and email verification code Hash code  
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String hash = BCrypt.hashpw(request.getParameter("hash"), GlobalConstants.SALT);
		String scope = request.getParameter("scope");
		//String message = null;
		try {
			// verify with database
                        System.out.println("userId"+userId);
                        System.out.println("userId"+userId.toString());
                        System.out.println("hash"+hash);
                        System.out.println("scope"+scope);
                        System.out.println("activation"+GlobalConstants.ACTIVATION);
                        if(LoginDao.verifyEmailHash(userId.toString(), hash) && scope.equals(GlobalConstants.ACTIVATION)) {
			   //update status as active
			   System.out.println("if is true");
                           LoginDao.updateStaus(userId.toString(), "active");
			   LoginDao.updateEmailVerificationHash(userId.toString(), null);
			   request.setAttribute("message", "Email verified successfully. Account is activated.");
                           response.sendRedirect(request.getContextPath() + "/Login.jsp");
                           //request.getRequestDispatcher("Login.jsp").forward(request,response);
			} else if(LoginDao.verifyEmailHash(userId.toString(), hash) && scope.equals(GlobalConstants.RESET_PASSWORD)) {
			   //update status as active
			   LoginDao.updateStaus(userId.toString(), "active");
			   LoginDao.updateEmailVerificationHash(userId.toString(), null);
			   //put some session for user
			   request.getSession().setAttribute(GlobalConstants.USER, userId);
			   request.getSession().setAttribute(GlobalConstants.IS_RESET_PASSWORD_VERIFIED, GlobalConstants.YES);
			   response.sendRedirect(request.getContextPath() + "/resetpass.html");
                           //request.getRequestDispatcher("resetpass.html").forward(request,response);
			} else {
			   //now increment verification attempts 
			   int attempts = LoginDao.incrementVerificationAttempts(userId.toString());
			   if(attempts == 20) {
				   // reset verification code if attempts equal to 20 
				   String hashcode = Utils.prepareRandomString(30);
				   LoginDao.updateEmailVerificationHash(userId.toString(), BCrypt.hashpw(hashcode, GlobalConstants.SALT));
				   UserPojo up = LoginDao.selectUSER(userId.toString());
				   MailUtil.sendEmailRegistrationLink(userId.toString(), up.getEMAIL(), hashcode);
				   request.setAttribute("message", "20 times Wrong Email Validation Input Given. So we have sent new activation link to your Email");
                                   request.getRequestDispatcher("Login.jsp").forward(request,response);
			   } else {
                                   request.setAttribute("message", "Wrong Email Validation Input");
                                   request.getRequestDispatcher("Login.jsp").forward(request,response);
				   }
			}
		} catch (DBException e) {
			//LOGGER.debug(e.getMessage());
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
			//LOGGER.debug(e.getMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
			//LOGGER.debug(e.getMessage());
		}
		 
	}

}
