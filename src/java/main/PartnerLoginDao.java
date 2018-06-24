package main;

import java.sql.*;
  
public class PartnerLoginDao {
public static boolean existingmail(String email){  
boolean status=false;
try{  
Class.forName(Setup.DB_DRIVER);  
Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
PreparedStatement ps=con.prepareStatement("select email from partners where email=?");  
//ps.setString(1,alliance);  
//ps.setString(2,division);  
ps.setString(1,email);  
//ps.setString(2,password);

ResultSet rs=ps.executeQuery();  
status=rs.next();
}catch(Exception e){e.printStackTrace();}  
return status; 
}    
public static boolean validate(String email, String password){  
boolean status=false;
try{  
Class.forName(Setup.DB_DRIVER);  
Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
PreparedStatement ps=con.prepareStatement("select * from partners where email=?");  
//ps.setString(1,alliance);  
//ps.setString(2,division);  
ps.setString(1,email);  
//ps.setString(2,password);

ResultSet rs=ps.executeQuery();  
status=rs.next();
String p = rs.getString("password");
if(Password.checkPassword(password,p)){
System.out.println("The password matches.");
return status;
}
else
{System.out.println("The password does not match.");
return false;}
}catch(Exception e){e.printStackTrace();}  
return status; 
}    
/*public static boolean validate1(String email, String password){  
boolean status=false;
try{  
Class.forName(Setup.DB_DRIVER);  
Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
PreparedStatement ps=con.prepareStatement("select * from users where email=? and password=?");  
//ps.setString(1,alliance);  
//ps.setString(2,division);  
ps.setString(1,email);  
ps.setString(2,password);

      
ResultSet rs=ps.executeQuery();  
status=rs.next();
System.out.println("This line executes.");
}catch(Exception e){System.out.println(e);}  
return status; 
}*/

public static boolean validateStatus(String email, String activ){  
boolean status=false;
try{  
Class.forName(Setup.DB_DRIVER);  
Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
PreparedStatement ps=con.prepareStatement("select * from partners where email=? and status=?");  
ps.setString(1,email);  
ps.setString(2,activ);  
ResultSet rs=ps.executeQuery();  

status=rs.next();
    System.out.println("status is active");
}
catch(Exception e){System.out.println(e);}  
return status; 
}
public static String getData(String name,String lastname, String email, String hash, String password){  
String id = null;
try{  
Class.forName(Setup.DB_DRIVER);  
Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
PreparedStatement ps=con.prepareStatement("insert into partners(name,last_name,email,email_verification_hash,password) values(?,?,?,?,?)");  
//ps.setString(1,alliance);  
//ps.setString(2,division);  
ps.setString(1,name);  
ps.setString(2,lastname);  
ps.setString(3,email); 
ps.setString(4,hash);  
ps.setString(5,password);  


ps.executeUpdate();  
PreparedStatement ps1 = con.prepareStatement("SELECT LAST_INSERT_ID()");
ResultSet rs = ps1.executeQuery();
if (rs != null) {
while (rs.next()) {
id = rs.getString(1);
}
}}
catch(Exception e)
{
    e.printStackTrace();
}  
return id; 
}
public static boolean oldpass(String email, String passwordold, String passwordnew){  
boolean status=false;
try{  
Class.forName(Setup.DB_DRIVER);  
Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
PreparedStatement ps=con.prepareStatement("select password from partners where email=?");  
//ps.setString(1,alliance);  
//ps.setString(2,division);  
ps.setString(1,email);  
//ps.setString(2,password);

ResultSet rs=ps.executeQuery();  
status=rs.next();
String p = rs.getString("password");
if(Password.checkPassword(passwordold,p)){
System.out.println("The password matches.");
newpass(email,passwordnew);}
else
{System.out.println("The password does not match.");
return false;}
}catch(Exception e){e.printStackTrace();}  
return status; 
}    
public static void newpass(String email, String password){  
try{  
Class.forName(Setup.DB_DRIVER);  
Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
PreparedStatement ps=con.prepareStatement("update partners SET password=? where email=?");  
//ps.setString(1,alliance);  
//ps.setString(2,division);  
ps.setString(1,password);  
ps.setString(2,email);

int a=ps.executeUpdate();  
if(a!=0)
    System.out.println("Password updated successfully");
else
    System.out.println("Password not updated. Please try again.");
}
catch(Exception e)
{
    e.printStackTrace();
}  
  
}
public static boolean verifyEmailHash(String user_id, String hash) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean verified = false;
		ResultSet res=null;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
                        System.out.println("line 1 executes");
			ps = conn.prepareStatement("select 1 from partners where USER_ID = ? and EMAIL_VERIFICATION_HASH = ?");
			System.out.println("line 2 executes");
                        ps.setString(1, user_id);
			ps.setString(2, hash);
			res = ps.executeQuery();
			System.out.println("line 3 executes");
                        if (res != null) {
                        System.out.println("line 4 executes");
                        	System.out.println(user_id);
                        System.out.println(hash);
                        while (res.next()) {
					System.out.println("line 5 executes");
                                verified = true;
                                        System.out.println("EmailHash verified");
                
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Exception while accessing database");
		}
                //System.out.println("EmailHash verified");
                return verified;
	}
public static void updateStaus(String user_id, String status) throws DBException{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
			ps = conn.prepareStatement("update partners set STATUS = ? where USER_ID = ?");
			ps.setString(1,status);
			ps.setString(2,user_id);
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Exception while accessing database");
		}
	}

	public static void updateEmailVerificationHash(String user_id, String hash) throws DBException{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
			ps = conn.prepareStatement("update partners set EMAIL_VERIFICATION_HASH = ?, EMAIL_VERIFICATION_ATTEMPTS = ? where USER_ID = ?");
			ps.setString(1,hash);
			ps.setInt(2,0);
			ps.setString(3,user_id);
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Exception while accessing database");
		}
	}

	public static int incrementVerificationAttempts(String user_id) throws DBException{
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet res = null;
		int attempts = 0;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
			ps = conn.prepareStatement("update partners set EMAIL_VERIFICATION_ATTEMPTS = EMAIL_VERIFICATION_ATTEMPTS + 1 where USER_ID = ?");
			ps.setString(1,user_id);
			ps.executeUpdate();
			
			ps2 = conn.prepareStatement("SELECT EMAIL_VERIFICATION_ATTEMPTS from users");
			res = ps2.executeQuery();
			
			if (res != null) {
				while (res.next()) {
					attempts = res.getInt(1);
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Exception while accessing database");
		}
		return attempts;
	}
        public static UserPojo selectUSER(String userId) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res=null;
		UserPojo pojo = null;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
			ps = conn.prepareStatement("select USER_ID, EMAIL, FIRST_NAME, LAST_NAME, EMAIL_VERIFICATION_HASH, EMAIL_VERIFICATION_ATTEMPTS, STATUS, CREATED_TIME from partners where USER_ID = ?");
			ps.setString(1, userId);
			res = ps.executeQuery();
			if (res != null) {
				while (res.next()) {
					pojo = new UserPojo();
					pojo.setUSER_ID(res.getInt(1));
					pojo.setEMAIL(res.getString(2));
					pojo.setFIRST_NAME(res.getString(3));
					pojo.setLAST_NAME(res.getString(4));
					pojo.setEMAIL_VERIFICATION_HASH(res.getString(5));
					pojo.setEMAIL_VERIFICATION_ATTEMPTS(res.getInt(6));
					pojo.setSTATUS(res.getString(7));
					pojo.setCREATED_TIME(res.getString(8));
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			 throw new DBException("Exception while accessing database");
		}
		return pojo;
	}
        public static UserPojo verifyLogin(String inputEmail, String inputPassword) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		UserPojo pojo = null;
		ResultSet res=null;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
			ps = conn.prepareStatement("select USER_ID, EMAIL, FIRST_NAME, LAST_NAME, STATUS, CREATED_TIME from partners where EMAIL = ? and PASSWORD = ?");
			ps.setString(1, inputEmail);
			ps.setString(2, inputPassword);
			res = ps.executeQuery();
			if (res != null) {
				while (res.next()) {
					pojo = new UserPojo();
					pojo.setUSER_ID(res.getInt(1));
					pojo.setEMAIL(res.getString(2));
					pojo.setFIRST_NAME(res.getString(3));
					pojo.setLAST_NAME(res.getString(4));
					pojo.setSTATUS(res.getString(5));
					pojo.setCREATED_TIME(res.getString(6));
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Exception while accessing database");
		}
		return pojo;
	}
        public static UserPojo selectUSERbyEmail(String inputEmail) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res=null;
		UserPojo pojo = null;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
			ps = conn.prepareStatement("select USER_ID, EMAIL, NAME, LAST_NAME, EMAIL_VERIFICATION_HASH, EMAIL_VERIFICATION_ATTEMPTS, STATUS, CREATED_TIME from partners where EMAIL = ?");
			ps.setString(1, inputEmail);
			res = ps.executeQuery();
			if (res != null) {
				while (res.next()) {
					pojo = new UserPojo();
					pojo.setUSER_ID(res.getInt(1));
					pojo.setEMAIL(res.getString(2));
					pojo.setFIRST_NAME(res.getString(3));
					pojo.setLAST_NAME(res.getString(4));
					pojo.setEMAIL_VERIFICATION_HASH(res.getString(5));
					pojo.setEMAIL_VERIFICATION_ATTEMPTS(res.getInt(6));
					pojo.setSTATUS(res.getString(7));
					pojo.setCREATED_TIME(res.getString(8));
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Exception while accessing database");
		}
		return pojo;
	}
        public static void updateEmailVerificationHashForResetPassword(String inputEmail,
			String hash) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
			ps = conn.prepareStatement("update partners set EMAIL_VERIFICATION_HASH = ?, EMAIL_VERIFICATION_ATTEMPTS = ?, STATUS = ? where EMAIL = ?");
			ps.setString(1,hash);
			ps.setInt(2,0);
			ps.setString(3,GlobalConstants.IN_RESET_PASSWORD);
			ps.setString(4,inputEmail);
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new DBException("Exception while accessing database");
		}
	}
        public static void updatePassword(String userId, String inputPassword) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Setup.DB_DRIVER);  
                        conn=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);
			ps = conn.prepareStatement("update partners set PASSWORD = ? where USER_ID = ?");
			ps.setString(1,inputPassword);
			ps.setString(2,userId);
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Exception while accessing database");
		}
	}
       public static int addProject(String Name,String Desc, String Members, String Due){  
int status=0; 
try{  
Class.forName(Setup.DB_DRIVER);  
Connection con=DriverManager.getConnection(Setup.DB_URL,Setup.DB_USERNAME,Setup.DB_PASSWORD);  
PreparedStatement ps=con.prepareStatement("insert into projects(name,description,members,due_date) values(?,?,?,?)");  
//ps.setString(1,alliance);  
//ps.setString(2,division);  
ps.setString(1,Name);  
ps.setString(2,Desc);  
ps.setString(3,Members); 
ps.setString(4,Due); 

status = ps.executeUpdate();  
}
catch(Exception e)
{
    e.printStackTrace();
}  
 return status;
}
     
     
} 
