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

public class approval extends HttpServlet {

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String to=Setup.MAIL_TO;
        String Name=request.getParameter("Name");
        String Designation=request.getParameter("Designation");
        String Company=request.getParameter("Company");
        String Email=request.getParameter("Email");              //1
        HttpSession session1 = request.getSession();
        String acto =(String)session1.getAttribute("acti");      //2
        String link=Setup.APPROVAL_LINK+"?email="+Email+"&filename="+acto;
        
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
    message.setSubject("File download request");  
      
    //3) create MimeBodyPart object and set your message text 
StringBuilder bodyText = new StringBuilder(); 
			bodyText.append("The file in the attachment has been requested for download by:\n\n")
			     .append("  Name:"+"     \t"+Name+"\n")
			     .append("  Designation:"+"\t"+Designation+"\n")
			     .append("  Company:"+"\t"+Company+"\n")
			     .append("  Email:"+"     \t"+Email+"\n\n")
			     .append("To approve please click "+link+" or open the link in browser");
			     
    BodyPart messageBodyPart1 = new MimeBodyPart();  
    messageBodyPart1.setText(bodyText.toString());    
    //messageBodyPart1.setText("The file in the attachment has been requested for download by:"+"\n"+"Name:"+"\t\t"+Name+"\n"+"Designation:"+"\t"+Designation+"\n"+"Company:"+"\t"+Company+"\n"+"Email:"+"\t\t"+Email+"\n"+"To approve click below link:"+"\n"+link+" or open the link in browser");  
      
    //4) create new MimeBodyPart object and set DataHandler object to this object      
    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
    //messageBodyPart2.setText("Name:"+"\t\t"+Name+"\n"+"Designation:"+"\t"+Designation+"\n"+"Company:"+"\t"+Company+"\n"+"Email:"+"\t\t"+Email+"\n"+"To approve click below link:"+"\n"+link+" or open the link in browser");  
    System.out.println("value retrieved in approval is"+acto);
    if("Concept_of_Cellular_System_Design".equals(acto)){
        
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\Concept of Cellular System Design.pdf";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("Concept of Cellular System Design.pdf");}
    else if("Springer_Short_CCA_in_WSN".equals(acto)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\Springer_Short CCA in WSN.pdf";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("Springer_Short CCA in WSN.pdf");}
    else if("T1,2,T3".equals(acto)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\T1,2,T3.pdf";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("T1,2,T3.pdf");}
    else if("dmrcfiberopticstransmissionsystem-141015141003-conversion-gate01".equals(acto)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\dmrcfiberopticstransmissionsystem-141015141003-conversion-gate01.ppt";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("dmrcfiberopticstransmissionsystem-141015141003-conversion-gate01.ppt");}
    else if("dmrcpidspastraining-141015141022-conversion-gate01".equals(acto)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\dmrcpidspastraining-141015141022-conversion-gate01.ppt";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("dmrcpidspastraining-141015141022-conversion-gate01.ppt");}
    else if("dmrcsdhtheory-141015141044-conversion-gate02".equals(acto)){
    String filename = "C:\\Users\\Archit\\Documents\\NetBeansProjects\\Nar\\web\\docs\\dmrcsdhtheory-141015141044-conversion-gate02.ppt";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName("dmrcsdhtheory-141015141044-conversion-gate02.ppt");}
     
     
    //5) create Multipart object and add MimeBodyPart objects to this object      
    Multipart multipart = new MimeMultipart();  
    multipart.addBodyPart(messageBodyPart1);  
    multipart.addBodyPart(messageBodyPart2);  
  
    //6) set the multiplart object to the message object  
    message.setContent(multipart, "text/html; charset=utf-8" );  
     
    //7) send message  
    Transport.send(message);  
   
   System.out.println("message sent....");
   request.setAttribute("message", "<html><head><style></style></head><body><br>The file you requested has been sent for approval. It will be sent to your email after the process.</body></html>");
   response.sendRedirect(request.getContextPath() + "/userinfo.jsp");
   //request.getRequestDispatcher("userinfo.jsp").forward(request,response);
   //session1.removeAttribute("actii");
   }catch (MessagingException ex) {ex.printStackTrace();}  
 
       }
    catch(Exception es)
    {es.printStackTrace();}
    
}
   
 } 
