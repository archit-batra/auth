package main;

public class Setup {
	  public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	  public static final String DB_URL = "jdbc:mysql://localhost:3306/nar";
	  public static final String DB_USERNAME = "root";
	  public static final String DB_PASSWORD = "";
	  public static final String MAIL_TO = "rapchit93@gmail.com"; // like example@outlook.com
	  public static final String MAIL_USERNAME = "mohammadtester295@gmail.com"; // like example@outlook.com
	  public static final String MAIL_PASSWORD = "";  // your mail password here
	  public static final String MAIL_SMTP_HOST = "smtp.gmail.com"; // smtp.live.com
	  public static final String MAIL_REGISTRATION_SITE_LINK = "http://localhost:8084/Nar/VerifyRegisteredEmailHash";
          public static final String PARTNER_MAIL_REGISTRATION_SITE_LINK = "http://localhost:8084/Nar/PartnerVerifyRegisteredEmailHash";
          public static final String APPROVAL_LINK = "http://localhost:8084/Nar/getmailservlet";

}
