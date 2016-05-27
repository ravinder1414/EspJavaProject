package com.selenium.hybrid.util;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.apache.log4j.*; 

import com.selenium.hybrid.util.TestUtil;


public class Keywords {
	
	public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\selenium\\hybrid\\xls\\Test Suite2.xlsx");
	static Keywords keywords=null;
	public static Properties CONFIG=null;
	public Properties OR=null;
	public WebDriver driver= null;
	public Properties APPTEXT= null;
	public int get_count;
	public static String keyword=null;
	public static String description=null;
	//public static String stepDescription;
	public static String testStatus;
	public static String fileName;
	public static String fileName1;
	public static String result=null;
	public static String startTime=null;
	Logger log = Logger.getLogger(Keywords.class);
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	
	private Keywords(){
		//ReportUtil.startSuite("Test Suite");
		System.out.println("Initializing Keywords");
		// initialize properties files
		try {
			// config
			CONFIG = new Properties();
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\selenium\\hybrid\\config\\config.properties");
			CONFIG.load(fs);
			log.info("loading Config");
			// OR
			OR = new Properties();
			fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\selenium\\hybrid\\config\\OR.properties");
			OR.load(fs);
			log.info("loading OR");

			APPTEXT = new Properties();
			fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\selenium\\hybrid\\config\\app_text.properties");
			APPTEXT.load(fs);
			log.info("loading APPTEXT");
		} catch (Exception e) {
			// Error is found
			e.printStackTrace();
		}
	}
	
	
	public  void executeKeywords(String testName,Hashtable<String,String> data){
		System.out.println("Executing - "+testName);
		// find the keywords for the test
		
		String objectKey=null;
		String dataColVal=null;
		String locatorvalue=null;
		
		for(int rNum=2;rNum<=xls.getRowCount("Test Steps");rNum++){
			if(testName.equals(xls.getCellData("Test Steps", "TCID", rNum))){
			 keyword=xls.getCellData("Test Steps", "Keyword", rNum);
			 objectKey=xls.getCellData("Test Steps", "Object", rNum);
			 dataColVal=xls.getCellData("Test Steps", "Data", rNum);
			 locatorvalue=xls.getCellData("Test Steps", "Locator", rNum);
			 description=xls.getCellData("Test Steps", "Description", rNum);
			 log.info("Keyword"+ keyword + "objectkey" +  objectKey + "datacolval" + dataColVal);
			 System.out.println(objectKey);
			
			 System.out.println(keyword +" -- "+objectKey+" -- "+ dataColVal);
			 if(keyword.equals("openBrowser"))
				 result=openBrowser(dataColVal);
			 else if (keyword.equals("click"))
				 result=click(objectKey);
			 else if(keyword.equals("input"))
				 result=input(objectKey,data.get(dataColVal));
			 else if(keyword.equals("navigate"))
				 result=navigate(dataColVal);
			 else if(keyword.equals("verifyText"))
				 result=verifyText(objectKey);
			 else if(keyword.equals("checkMail"))
				 result= checkMail(data.get(dataColVal));
			 else if(keyword.equals("verifyLogin"))
				 result=verifyLogin(data.get(dataColVal));
			 else if(keyword.equals("close_browser"))
				result=close_browser();
			 else if(keyword.equals("switchframe"))
				 result=switchframe(objectKey);
			 else if(keyword.equals("ewait"))
				 result=ewait(objectKey);	 
			 else if(keyword.equals("Iwait"))
				 result=Iwait(objectKey);	 
			 else if(keyword.equals("isdisplayed"))
				 result=isdisplayed(objectKey);	
			 else if(keyword.equals("contains"))
				 result=contains(objectKey);	
			 else if(keyword.equals("store_text"))
				 result=store_text(objectKey);	
			 else if(keyword.equals("select"))
				 result=select(objectKey,data.get(dataColVal));	
			 else if(keyword.equals("submit"))
				 result=submit(objectKey);	
			 else if(keyword.equals("alert_ok"))
				 result=alert_ok();	
			 else if(keyword.equals("comparevalue"))
				 result=comparevalue(objectKey);	
			 else if(keyword.equals("clicklinkbytext"))
				 result=clicklinkbytext(objectKey);
			 else if(keyword.equals("slectradiocheckbox"))
				 result=slectradiocheckbox(objectKey);
			 else if(keyword.equals("Threadsleep"))
				 Threadsleep();
			 
			 try {
			    	
			    	fileName=result+testName+"_"+keyword+timeStamp+".jpg";
					BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				    ImageIO.write(image, "png", new File(System.getProperty("user.dir")+"//screenshots//"+fileName));
				    fileName1=System.getProperty("user.dir")+"//screenshots//"+fileName;
				    ReportUtil.addKeyword(description, keyword, result, fileName1);
					//FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+fileName));
				} 
			 
			 catch (Throwable t) {
					System.out.println("***ERR***");
					log.info("ERROR");
					// TODO Auto-generated catch block
					t.printStackTrace();
				}
			 
			 
			 System.out.println("Result is"+result+"********");
			 
			
			 if(result.contains("Fail")){
				 testStatus=result;
				 log.info("***********************************"+testName+" --- " +testStatus);
					
				 
				 Assert.assertTrue(false, result);
			 }
					
			 
			 
			 
			 else if(result == null){
							testStatus="Skip";
					ReportUtil.addTestCase(testName, 
									TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
									TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
									testStatus );
									
									
								
					 } 
			
						else
						
						{
							
					   // testStatus=result;
					    					    
						testStatus="Pass";
						
						
						// report skipped
						log.info("***********************************"+testName+" --- " +testStatus);
		
						
			/*
						ReportUtil.addTestCase(testName, 
								startTime, 
								TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
								testStatus );
		*/
						}
			}
					}
			ReportUtil.endSuite();	 
			 
	
	}

	public String openBrowser(String browserType){
		System.out.println("Executing openBrowser");
		log.info("Executing openBrowser");
		if(CONFIG.getProperty(browserType).equals("Mozilla")){
			driver= new FirefoxDriver();
		}
		else if(CONFIG.getProperty(browserType).equals("Chrome")){
			
			System.out.println("opening chrome");
			System.setProperty("webdriver.chrome.driver", "D:\\Workspace\\chromedriver.exe");
			 driver = new ChromeDriver();
		}
		else if ((CONFIG.getProperty(browserType).equals("IE"))){
			
		System.setProperty("webdriver.ie.driver", "D:\\Workspace\\IEDriverServer.exe" );
		driver= new InternetExplorerDriver();
		driver.get("javascript:document.getElementById('overridelink').click()");
		}
		return "Pass";
		
	}
	
	public String navigate(String URLKey){
		System.out.println("Executing navigate");
		log.info("Executing navigate");
		try{
		driver.get(CONFIG.getProperty(URLKey));
		
		driver.manage().window().maximize();

		
		}catch(Exception e){
			return "Fail - not able to navigate";
		}
		return "Pass";
	}
	
	public  String click(String xpathKey){
		System.out.println("Executing click");
		log.info("Executing click");
		try{
		driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
		
		//driver.findElement(By.name(OR.getProperty(xpathKey))).click();
		
		}catch(Exception e){
			return "Fail - not able to click - "+xpathKey;

		}
		return "Pass";	
	}
	
	
	public String submit(String xpathKey){
		System.out.println("Executing submit");
		log.info("Executing submit");
		try{
		driver.findElement(By.xpath(OR.getProperty(xpathKey))).submit();
		
		//driver.findElement(By.name(OR.getProperty(xpathKey))).click();
		
		}catch(Exception e){
			return "Fail - not able to submit - "+xpathKey;

		}
		return "Pass";	
	}
	
	
	
	public String switchframe(String frameKey){
		System.out.println("Executing switchframe");
		log.info("Executing switchframe");
		
		try{
			
			driver.switchTo().frame(OR.getProperty(frameKey));
		//driver.findElement(By.name(OR.getProperty(xpathKey))).click();
		
		}catch(Exception e){
			return "Fail - not able to switch to new frame - "+frameKey;

		}
		return "Pass";	
	}
	
	
	public String input(String xpathKey,String data){
		System.out.println("Executing input");
		log.info("Executing input");
		try{
		driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(data);
		}catch(Exception e){
			return "Fail - not able to enter data in input box -"+xpathKey;
		}
		return "Pass";			
	}
	
	
	
	public  String verifyText(String xpathKey){
		System.out.println("Executing verifyText");
		log.info("Executing verifyText");
	//APPICATION_LOGS.debug("Executing verifyText");
	String expected=APPTEXT.getProperty(xpathKey);
	String actual=driver.findElement(By.xpath(OR.getProperty(xpathKey))).getText();
	String newLine = System.getProperty("line.separator");
	System.out.println("actual value"+ actual + newLine +"Expected value" + expected);
	//APPICATION_LOGS.debug(expected);
	//APPICATION_LOGS.debug(actual);
	try{
		Assert.assertEquals(actual.trim() , expected.trim());
	}catch(Throwable t){
		// error
		//APPICATION_LOGS.debug("Error in text - "+object);
		//APPICATION_LOGS.debug("Actual - "+actual);
		//APPICATION_LOGS.debug("Expected -"+ expected);
		return "Fail -"+ t.getMessage(); 
		
	}
	
	return "Pass";
	
}
	
	public String ewait(String xpathKey){
		System.out.println("Executing ewait");
		log.info("Executing ewait");
		try{
			 WebDriverWait wait = new WebDriverWait(driver,30);
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathKey)));

		}catch(Exception e){
			return "Fail - not waiting -"+xpathKey;
		}
		return "Pass";			
	}
	
	
	public String Iwait(String xpathKey){
		System.out.println("Executing Iwait");
		log.info("Executing Iwait");

		try{
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}catch(Exception e){
			return "Fail - not Iwaiting -"+xpathKey;
		}
		return "Pass";			
	}
	
	public  void Threadsleep(){
		System.out.println("Executing Threadsleep");
		log.info("Executing Iwait");

		
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
	public String isdisplayed(String xpathKey){
		System.out.println("Executing isdisplayed");
		log.info("Executing isdisplayed");
		try{
			driver.findElement(By.xpath(OR.getProperty(xpathKey))).isDisplayed(); 
			
		}
		catch(Exception e){
			return "Fail - not able to locate element -"+xpathKey;
		}
		return "Pass";			
	}
	
	//To verify contains character only
	public String contains(String xpathKey){
		System.out.println("Executing contains");
		log.info("Executing contains");

		String expected=APPTEXT.getProperty(xpathKey);
		String actual=driver.findElement(By.xpath(OR.getProperty(xpathKey))).getText().replaceAll("[^A-Z]","");
		System.out.println(actual.replaceAll("[^A-Z]", ""));
		//String actual=actual1.replaceFirst(actual1, replacement)
		//CharSequence exp= expected;
		System.out.println("actual value"+ actual + "Expected value" + expected);
		
		try{
			
			Assert.assertEquals(actual.trim() , expected.trim());
		

		}catch(Exception e){
			return "Fail - not contains -"+xpathKey;
		}
		return "Pass";			
	}
	
	//To 
	
	
	
	public String store_text(String xpathKey){
		System.out.println("Executing store_text");
		log.info("Executing store_text");

		
		try{
			String get_text = driver.findElement(By.xpath(OR.getProperty(xpathKey))).getText();
			get_count=Integer.parseInt(get_text);
			System.out.println("storedvalue"+ get_text + get_count );
		}catch(Exception e){
			return "Fail - not able to enter data in get Text -"+xpathKey;
		}
		return "Pass" ;			
	}
	
	
	public String select(String xpathKey,String data){
		System.out.println("Executing select");
		log.info("Executing select");

		
		try{
			//new Select (driver.findElement(By.id("designation"))).selectByVisibleText("Programmer ");
			driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(data);
			//System.out.println("storedvalue"+ get_text );
		}catch(Exception e){
			return "Fail - not able to select -"+xpathKey;
		}
		return "Pass";			
	}
	
	public String clicklinkbytext(String linktextkey){
		System.out.println("Executing clicklinkbytext");
		log.info("Executing clicklinkbytext");

		
		try{
			//new Select (driver.findElement(By.id("designation"))).selectByVisibleText("Programmer ");
			driver.findElement(By.partialLinkText(OR.getProperty(linktextkey))).click();
			//System.out.println("storedvalue"+ get_text );
		}catch(Exception e){
			return "Fail - not able to enter data in get Text -"+linktextkey;
		}
		return "Pass";			
	}
	
	
	public String slectradiocheckbox(String cssSelectorkey){
		System.out.println("Executing slectradiocheck");
		log.info("Executing slectradiocheckbox");

		
		try{
			//new Select (driver.findElement(By.id("designation"))).selectByVisibleText("Programmer ");
			driver.findElement(By.cssSelector(OR.getProperty(cssSelectorkey))).click();
			//System.out.println("storedvalue"+ get_text );
		}catch(Exception e){
			return "Fail - not able to enter data in get Text -"+cssSelectorkey;
		}
		return "Pass";			
	}
	
	public String alert_ok(){
		System.out.println("Executing alert_ok");
		log.info("Executing Executing alert_ok");
		try{
			   Alert alert = driver.switchTo().alert();
			    alert.accept();

		
		//driver.findElement(By.name(OR.getProperty(xpathKey))).click();
		
		}catch(Exception e){
			return "Fail - not able to click ok - ";

		}
		return "Pass";	
	}
	
	
	public String close_browser(){
		System.out.println("Executing Browser_close");
		log.info("Executing Browser_close");
		//driver.findElement(By.xpath("//*[@id='submitActionLogOut']")).sendKeys(Keys.ENTER);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.close();
		driver.quit();
		return "Pass";
		
	}
	

	
	
	/**************************Application dependent****************************/
	
	public String comparevalue(String xpathKey){
		System.out.println("Executing comparevalue");
		int expected_count=get_count+1;
		String expected=Integer.toString(expected_count);
		String actual=driver.findElement(By.xpath(OR.getProperty(xpathKey))).getText();
		System.out.println("expected"+ expected);
		//String actual=actual1.replaceFirst(actual1, replacement)
		//CharSequence exp= expected;
		System.out.println("actual value"+ actual + "Expected value" + expected);
		
		try{
			
			Assert.assertEquals(actual.trim() , expected.trim());
		

		}catch(Exception e){
			return "Fail - not contains -"+xpathKey;
		}
		return "Pass";			
	}
	
	
	
	
	
	
	
	
	
	public String checkMail(String mailName){
		System.out.println("Executing checkMail");

		try{
		driver.findElement(By.linkText(mailName)).click();
		}catch(Exception e){
			return "Fail-Mail not found";
		}
		
		
		
		return "Pass";							
	}
	
	
	
	//******Singleton function*******//
	public static Keywords getKeywordsInstance(){
		if(keywords == null){
			keywords = new Keywords();
		}
		
		
		return keywords;
	}
	
	public String verifyLogin(String verificationText){
		
		int total= driver.findElements(By.xpath(OR.getProperty("login_err_msg"))).size();
		int inboxcount=driver.findElements(By.xpath(OR.getProperty("inbox"))).size();
		if(total!=0){
			// not logged in
			// verify err msg
			if(driver.findElement(By.xpath(OR.getProperty("login_err_msg"))).equals(verificationText))
				return "Pass";
			else 
				return "Fail - Not able to find the error message ";
		}
			
		else if(inboxcount!=0){
		 // logged in
			if(driver.findElement(By.xpath(OR.getProperty("inbox"))).equals(verificationText))
				return "Pass";
			else 
				return "Fail - Not able to find the Inbox link";
		}
		
		return "Fail";
	}
	
	

	
	
	

}
