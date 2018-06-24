package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class getmailservlet extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0");
        PrintWriter out = response.getWriter();
        String to=request.getParameter("email");
        String actii=request.getParameter("filename");
        
  //change accordingly  
  final String user=Setup.MAIL_USERNAME;//change accordingly  
  final String password=Setup.MAIL_PASSWORD;//change accordingly  
   
  //1) get the session object     
  Properties props = System.getProperties();  
  props.put("mail.smtp.host", Setup.MAIL_SMTP_HOST);    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");  

  
  Session session = Session.getDefaultInstance(props,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,password);  
   }  
  });  
     
  //2) compose message     
  try{  
    MimeMessage message = new MimeMessage(session);  
    message.setFrom(new InternetAddress(user));  
    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    message.setSubject("Greetings from Narnix!");  
      
    //3) create MimeBodyPart object and set your message text     
    BodyPart messageBodyPart1 = new MimeBodyPart();  
    messageBodyPart1.setText("Please find the file you requested for download in the attachment.");  
      
    //4) create new MimeBodyPart object and set DataHandler object to this object      
    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
    System.out.println("value retrieved in getmailservlet is"+actii);
    if("Concept_of_Cellular_System_Design".equals(actii)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\Concept of Cellular System Design.pdf";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("Concept of Cellular System Design.pdf");
    }
    else if("Springer_Short_CCA_in_WSN".equals(actii)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\Springer_Short CCA in WSN.pdf";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("Springer_Short CCA in WSN.pdf");
    }
    else if("T1,2,T3".equals(actii)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\T1,2,T3.pdf";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("T1,2,T3.pdf");
    }
    else if("dmrcfiberopticstransmissionsystem-141015141003-conversion-gate01".equals(actii)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\dmrcfiberopticstransmissionsystem-141015141003-conversion-gate01.ppt";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("dmrcfiberopticstransmissionsystem-141015141003-conversion-gate01.ppt");
    }
    else if("dmrcpidspastraining-141015141022-conversion-gate01".equals(actii)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\dmrcpidspastraining-141015141022-conversion-gate01.ppt";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("dmrcpidspastraining-141015141022-conversion-gate01.ppt");
    }
    else if("dmrcsdhtheory-141015141044-conversion-gate02".equals(actii)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\dmrcsdhtheory-141015141044-conversion-gate02.ppt";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("dmrcsdhtheory-141015141044-conversion-gate02.ppt");
    }  
    else throw new Exception("could not get file"); 
     
    //5) create Multipart object and add MimeBodyPart objects to this object      
    Multipart multipart = new MimeMultipart();  
    multipart.addBodyPart(messageBodyPart1);  
    multipart.addBodyPart(messageBodyPart2);  
  
    //6) set the multiplart object to the message object  
    message.setContent(multipart );  
     
    //7) send message  
    Transport.send(message);  
   
   System.out.println("message sent....");
   //out.println("We have sent the file you requested to your email");
   response.sendRedirect(request.getContextPath() + "/afterappoval.html");
   //request.getRequestDispatcher("afterappoval.html").forward(request,response);
   //session1.removeAttribute("actii");
   //session1.invalidate();
   }catch (MessagingException ex) {ex.printStackTrace();}  
 
       }
    catch(Exception es)
    {es.printStackTrace();}
}
   
 } 
