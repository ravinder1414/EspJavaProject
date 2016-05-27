package netthandelen_Variables;
//Import files
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commonfunctions.ReportExtn;


public class EnvironmentVariables {
	
	//Application Environment Variables
	public static String sUrl_Orion3 = "";
	public static String sEnv = "";
	public static String sApp = "Orion3";
	public static String sOrion3_B2B_Url="";
	public static String sTestnetthandelen_Url="";
	public static String sTrusted_Uris="";
	public static String sSEP_Url="";
	public static String sSRM_OnlyUrl="";
	public static String sB2B_LeadCreationUrl="";
	public static String sLead_ImportURL="";
	public static String sConnString= "";
	public static String sOrion1_URL;
	public static String sIWD_URL;
	public static String sIWD_UName;
	public static String sIWD_PWD;
	

	//Execution Environment 
	//Grid Hub and Port
	public static String sHub=null;
	public static String sHubPort=null;	
	
	//Wait for Page\Element to Load
	public static int iTimeout=30;
	
	//Before Execution of the suite
	//Set the variables from TestNG.xml
	//Set the variables from properties file
	@Parameters({ "Environment", "sHub", "sHubPort"})
	@BeforeSuite
	public void BeforeSuite_Orion3(String Environment, @Optional("localhost") String Hub, @Optional("4444") String HubPort, ITestContext context)
	{
		//Load environment variable from properties file
		String sPath_EnvProperties="";
		FileInputStream objFileInputStream = null;
		Properties objProperties = new Properties();
		
		//Set file path as per environment
		if (Environment.equalsIgnoreCase("dev"))
		{
			sPath_EnvProperties = ".//Resources//EnvironmentProperties/DevEnvironment.properties";
		}
		else if (Environment.equalsIgnoreCase("stage"))
		{
			sPath_EnvProperties = ".//Resources//EnvironmentProperties/StageEnvironment.properties";			
		}
		else if (Environment.equalsIgnoreCase("lt"))
		{
			sPath_EnvProperties = ".//Resources//EnvironmentProperties/LtEnvironment.properties";			
		}
		else
		{
			sPath_EnvProperties = ".//Resources//EnvironmentProperties/TestEnvironment.properties";			
		}
		
		//Load the Environment file into File Input Stream.
		File objFileEnvironment = new File(sPath_EnvProperties);
		try
		{
			objFileInputStream = new FileInputStream(objFileEnvironment);
		}catch (FileNotFoundException ex)
		{
			ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
		}
		
		//Load the File Input Stream into the Properties file
		try
		{
			objProperties.load(objFileInputStream);
			
		} catch (IOException ex) {

			ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
		}
		
		//Set the variables from TestNG.xml
		sHub = Hub;
		sHubPort = HubPort;
		sEnv =  Environment;
		
		
		//Set the Environment Variables
		
	    sTestnetthandelen_Url = (String)objProperties.getProperty("Testnetthandelen_Url");
	    /*sTrusted_Uris = (String)objProperties.getProperty("Trusted_Uris");
	    sSEP_Url =(String)objProperties.getProperty("SEP_Url");
	    sSRM_OnlyUrl =(String)objProperties.getProperty("SRM_OnlyUrl");
	    sB2B_LeadCreationUrl=(String)objProperties.getProperty("B2B_LeadCreationUrl");
	    sLead_ImportURL=(String)objProperties.getProperty("Lead_ImportURL");
	    sOrion1_URL=(String)objProperties.getProperty("Orion1_URL");
	    sConnString=(String)objProperties.getProperty("ConnString");
		iTimeout = Integer.parseInt(objProperties.getProperty("Timeout"));
		sIWD_URL = (String)(objProperties.getProperty("IWD_Url"));
		sIWD_UName = (String)(objProperties.getProperty("IWD_UserName"));
		sIWD_PWD = (String)(objProperties.getProperty("IWD_Password"));*/
	}
}
