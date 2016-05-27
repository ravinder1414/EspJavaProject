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
public class Testcase2 {
	
	
	private static final String startTime = null;
	public static String testStatus;
		public static String fileName1;
	public static WebDriver driver;
	Logger log = Logger.getLogger(Testcase2.class);
	
	@BeforeTest
	
	public static void BeforeTesting(){
	ReportUtil.startSuite("Testcases2");
	
	}
	
	@Test(dataProvider="getLoginData")
	public void Home(Hashtable<String,String> data){
		log.info("Home");
		if(!TestUtil.isTestCaseExecutable("Home", Keywords.xls))
		  throw new SkipException("Skipping the test as Runmode is NO");
		if(!data.get("RunMode").equals("Y"))
			  throw new SkipException("Skipping the test as data set Runmode is NO");

		System.out.println("***Home***");
		Keywords k = Keywords.getKeywordsInstance();
		k.executeKeywords("Home",data);
		
	}
	
	@Test(dataProvider="getLoginData")
	public void Menu(Hashtable<String,String> data){
		log.info("Menu");
		if(!TestUtil.isTestCaseExecutable("Menu", Keywords.xls))
		  throw new SkipException("Skipping the test as Runmode is NO");
		if(!data.get("RunMode").equals("Y"))
			  throw new SkipException("Skipping the test as data set Runmode is NO");

		System.out.println("***Menu***");
		Keywords k = Keywords.getKeywordsInstance();
		k.executeKeywords("Menu",data);

		
	}

		
	@Test(dataProvider="getLoginData")
	public void support(Hashtable<String,String> data){
		log.info("support");
		if(!TestUtil.isTestCaseExecutable("support", Keywords.xls))
		  throw new SkipException("Skipping the test as Runmode is NO");
		if(!data.get("RunMode").equals("Y"))
			  throw new SkipException("Skipping the test as data set Runmode is NO");

		System.out.println("***support***");
		Keywords k = Keywords.getKeywordsInstance();
		k.executeKeywords("support",data);
		
	}
	
	
	@Test(dataProvider="getLoginData")
	public void Panels(Hashtable<String,String> data){
		log.info("Panels");
		if(!TestUtil.isTestCaseExecutable("Panels", Keywords.xls))
		  throw new SkipException("Skipping the test as Runmode is NO");
		if(!data.get("RunMode").equals("Y"))
			  throw new SkipException("Skipping the test as data set Runmode is NO");

		System.out.println("***Panels***");
		Keywords k = Keywords.getKeywordsInstance();
		k.executeKeywords("Panels",data);
		
	}
	
	@Test(dataProvider="ToDo")
public void ToDo(Hashtable<String,String> data){
		log.info("ToDo");
		if(!TestUtil.isTestCaseExecutable("ToDo", Keywords.xls))
		  throw new SkipException("Skipping the test as Runmode is NO");
		if(!data.get("RunMode").equals("Y"))
			  throw new SkipException("Skipping the test as data set Runmode is NO");

		System.out.println("***ToDo***");
		Keywords k = Keywords.getKeywordsInstance();
		k.executeKeywords("ToDo",data);
		
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
		
		   System.out.println(Keywords.testStatus+"Writing in report util teststatus testcase2 skip status");
	       
		

		
	}  
	
	

	@AfterSuite
	public static void endScript() throws Exception{
			
				TestUtil.zip(Keywords.CONFIG.getProperty("zippath"));
				//Email.execute(Keywords.CONFIG.getProperty("mailfile"));

		}
	
}
