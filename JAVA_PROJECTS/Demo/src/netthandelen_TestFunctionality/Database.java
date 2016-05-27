package netthandelen_TestFunctionality;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class Database {
		
		
		public static String verification() throws ClassNotFoundException, SQLException{

		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			String dbUrl = "jdbc:sqlserver://KRS01DB09"; 
			String sms1=null;
			String username = "tespire";   
		   
		    String password = "Espire123";             

		 
		    String query = "SELECT TOP 1  body FROM [NHCustomersales].[dbo].[SMSmessage] order by smsmessageid desc;";  
		    
		    Connection con = DriverManager.getConnection(dbUrl,username,password);
		     
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(query);

		    while (rs.next()) {
		        String sms = rs.getString("Body");
		        
		         sms1 =  sms.substring(12, 18);
		    
		        System.out.println(sms1);
		        
		        
		        
		        }

		       rs.close();
		       stmt.close();
		       
		       return sms1;
			
	     
	      }
	}


