package commonfunctions;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Reporter;

public class ReportExtn {

	
	
	public static void Pass(Method objMethod, String msg)
	{
		Date currDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("'['yyyy.MM.dd']''['hh:mm:ss']'");	
		
		Reporter.log("["+ objMethod.getName()+"]" + ft.format(currDate) + "     Pass: " +  msg);
		
	}
	
	public static void Fail(String msg)
	{
		Date currDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("'['yyyy.MM.dd']''['hh:mm:ss']'");	
		
		Reporter.log("[UserExtension]" + ft.format(currDate) + "     Fail: " +  msg);
	}
	
	public static void Fail(Method objMethod, String msg)
	{
		Date currDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("'['yyyy.MM.dd']''['hh:mm:ss']'");	
		
		Reporter.log("["+ objMethod.getName()+"]" + ft.format(currDate) + "     Fail: " +  msg);
	}
	
	public static void Fail(Method objMethod, String Expected, String Actual, String msg)
	{
		Date currDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("'['yyyy.MM.dd']''['hh:mm:ss']'");	
		
		Reporter.log("["+ objMethod.getName()+"]" + ft.format(currDate) + "     Fail: Expected: " + Expected +  " Acutal: " + Actual + " " + "Message: " +  msg);
	}
	
	public static void Data(Method objMethod, String msg)
	{
		Date currDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("'['yyyy.MM.dd']''['hh:mm:ss']'");	
		
		Reporter.log("["+ objMethod.getName()+"]" + ft.format(currDate) + "     Data: " +  msg);
	}
	
	public static void MethodReportingLayout(String MethodName)
	{
		Date currDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("'['yyyy.MM.dd']''['hh:mm:ss']'");	
		Reporter.log("["+ MethodName +"]" + ft.format(currDate) + "     Starts");
	}
	
}
