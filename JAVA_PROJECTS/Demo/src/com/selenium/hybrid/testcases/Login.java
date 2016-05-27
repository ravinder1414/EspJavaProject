package com.selenium.hybrid.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.log4j.*; 

import com.selenium.hybrid.util.Email;
import com.selenium.hybrid.util.JyperionListener;
import com.selenium.hybrid.util.Keywords;
import com.selenium.hybrid.util.ReportUtil;
import com.selenium.hybrid.util.TestUtil;
@Listeners(JyperionListener.class)
public class Login {
	
	
	private static final String startTime = null;
	public static String testStatus;
		public static String fileName1;
	public static WebDriver driver;
	Logger log = Logger.getLogger(Login.class);
	
	
	//@BeforeSuite
	public static void startTesting(){
		//ReportUtil.startTesting("D://workspace//ImpellamAuto_Rnd//report//index.html C://JAVA_PROJECTS//Demo//test-output//index.html",
		
		ReportUtil.startTesting("C://JAVA_PROJECTS//Demo//test-output//index.html",
                TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
                "Prod",
                "1.1.3");
		//driver=new FirefoxDriver();
		//driver.get("https://test.netthandelen.no:7014/register");
	}
	
	
	@BeforeTest
	
	public static void BeforeTesting(){
	ReportUtil.startSuite("Testcases1");
	//driver.get("https://test.netthandelen.no:7014/register");
	
	}
	
	@Test(dataProvider="getLoginData")
	public void LoginTest(Hashtable<String,String> data){
		log.info("LoginTest");
		if(!TestUtil.isTestCaseExecutable("Successful Login", Keywords.xls))
		  throw new SkipException("Skipping the test as Runmode is NO");
		if(!data.get("RunMode").equals("Y"))
			  throw new SkipException("Skipping the test as data set Runmode is NO");

		System.out.println("***Successful Login***");
		Keywords k = Keywords.getKeywordsInstance();
		k.executeKeywords("Successful Login",data);
		
	}
	
	@Test(dataProvider="Incorrectlogindata")
	public void Incorrectlogin(Hashtable<String,String> data){
		log.info("Incorrectlogindata");
		if(!TestUtil.isTestCaseExecutable("Incorrect Login", Keywords.xls))
		  throw new SkipException("Skipping the test as Runmode is NO");
		if(!data.get("RunMode").equals("Y"))
			  throw new SkipException("Skipping the test as data set Runmode is NO");

		System.out.println("***Incorrect Login***");
		Keywords k = Keywords.getKeywordsInstance();
		k.executeKeywords("Incorrect Login",data);

		
	}

	
	@Test(dataProvider="getLoginData")
	public void Logout(Hashtable<String,String> data){
			log.info("Logout");
			if(!TestUtil.isTestCaseExecutable("Logout", Keywords.xls))
			  throw new SkipException("Skipping the test as Runmode is NO");
			if(!data.get("RunMode").equals("Y"))
				  throw new SkipException("Skipping the test as data set Runmode is NO");

			System.out.println("***Logout***");
			Keywords k = Keywords.getKeywordsInstance();
			k.executeKeywords("Logout",data);
			
		}
	
	@DataProvider
	public Object[][] Incorrectlogindata(){
		return TestUtil.getData("Incorrect Login", Keywords.xls);
	}
	
		
	@DataProvider
	public Object[][] getLoginData(){
		return TestUtil.getData("Successful Login", Keywords.xls);
	}

	@DataProvider
	public Object[][] ToDo(){
		return TestUtil.getData("ToDo", Keywords.xls);
	}
	
	
	@AfterMethod
	public static void afterTest(ITestResult result){
		
		if( Keywords.testStatus.equals("Pass")){
			 System.out.println("method name:" + result.getMethod().getMethodName());
		
	       ReportUtil.addTestCase(result.getMethod().getMethodName(), 
					startTime, 
					TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
					Keywords.testStatus);
	       
	       System.out.println(Keywords.testStatus+"Writing in report util teststatus testcase2 pass status");
	       
	       
		}
		
		else if
	       
	       (Keywords.testStatus.contains("Fail")){
	    	   //fileName1=System.getProperty("user.dir")+"//screenshots//"+Keywords.fileName;
	    	   ReportUtil.addTestCase(result.getMethod().getMethodName(), 
						startTime, 
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
						Keywords.testStatus );
	    	   System.out.println(Keywords.testStatus+"Writing in report util teststatus testcase2 fail status");
	       //ReportUtil.addKeyword(Keywords.description,Keywords.keyword,Keywords.result,fileName1);
	        }
		
		
	       else 
	    	   System.out.println("***SKIP***");
		
		ReportUtil.addTestCase(result.getMethod().getMethodName(), 
				startTime, 
				TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
				Keywords.testStatus );
		
		   System.out.println(Keywords.testStatus+"Writing in report util teststatus testcase2 skip status");}
	       
		

		
	
	
	

	@AfterSuite
	public static void endScript() throws Exception{
			
				TestUtil.zip(Keywords.CONFIG.getProperty("zippath"));
				//Email.execute(Keywords.CONFIG.getProperty("mailfile"));

		}
	
}
