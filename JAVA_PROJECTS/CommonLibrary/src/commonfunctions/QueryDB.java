package commonfunctions;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.testng.annotations.Test;


//This test is designed to get the Query Result Sets. You can call  Function "getDBQueryResult" for that
//To use this we need to declare & assign  Connection string in Env variables properties file and  read the same in EnvironmentVariables.java
//Query String we need to pass from the script itself
public  class QueryDB {
	public static Connection conn=null;	
	public static Statement stmt=null;
	
	//the following commented function is a sample that can be used to call the "getDBQueryResult" function
//@Test public void doQuery(){
//String qString= "select mkleadid, firstname, Lastname from polaris..mkleads where firstname='TestLeadSEPcLTfhgwUrD'";
//String ConnString= "jdbc:sqlserver://KUCY2TSQLC02\\TSTORION;databaseName=c2000;user=kcctech;password=24giveis24get";
//try {
	//ResultSet rs= getDBQueryResult(qString,ConnString );
	//while (rs.next()) {
		//System.out.println(rs.getString(1));
		//System.out.println(rs.getString(2));
	    //}
//} //catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	//e.printStackTrace();
//} //catch (SQLException e) {
	// TODO Auto-generated catch block
	//e.printStackTrace();
//}

//}
	
  @Test
  public static ResultSet getDBQueryResult(String queryString , String ConString) throws ClassNotFoundException, SQLException  {
	  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  conn= DriverManager.getConnection(ConString);
	  stmt=(Statement) conn.createStatement();
	  
	  ResultSet rs = stmt.executeQuery(queryString);
	  return rs;
	  
  }
  
  
}
