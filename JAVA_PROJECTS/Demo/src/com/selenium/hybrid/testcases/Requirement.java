package com.selenium.hybrid.testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
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
public class Requirement {
	private static final String startTime = null;
	public static String testStatus;
	public static WebDriver driver;
	public static String fileName1;
	Logger log = Logger.getLogger(Requirement.class);
	
	@BeforeSuite
	public static void startTesting(){
		//ReportUtil.startTesting("D://workspace//ImpellamAuto//report//index.html", 
		ReportUtil.startTesting("C://JAVA_PROJECTS//Demo//test-output//index.html",
                TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
                "Prod",
                "1.1.3");
		
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
	
/*
	@AfterMethod
	public static void afterTest(){
		if( testStatus.equals("Pass"))
			fileName1=System.getProperty("user.dir")+"//screenshots//"+Keywords.fileName;
	       ReportUtil.addKeyword(Keywords.description,Keywords.keyword,Keywords.result,fileName1);
	       ReportUtil.addTestCase(Keywords.keyword, 
					startTime, 
					TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
					testStatus );
			
	       
	        if ( testStatus.equals("Fail"))
	    	   fileName1=System.getProperty("user.dir")+"//screenshots//"+Keywords.fileName;
	       ReportUtil.addKeyword(Keywords.description,Keywords.keyword,Keywords.result,fileName1);
	}
	
*/
	@AfterSuite
	public static void endScript() throws Exception{
			
				TestUtil.zip(Keywords.CONFIG.getProperty("zippath"));
				Email.execute(Keywords.CONFIG.getProperty("mailfile"));

		}
	
}
