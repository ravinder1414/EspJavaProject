package commonfunctions;



import java.io.File;



import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ScreenShotOnTestFailure implements ITestListener{

	private static RemoteWebDriver driver;
	private static String sEnv;
	private static String sApp;
	  
	  public static void init(RemoteWebDriver d, String Environment, String Application) {
	      driver = d;
	      sEnv = Environment;
	      sApp =  Application;
	    		  
	  }
	  
	  public static void takeSnapShot(String sEnvironment, String sApplication, String methodName)
	  {
		  String sFileLocation = "c:\\Webdriver2.0\\Screenshots\\";
		  sFileLocation = sFileLocation.concat(sApplication).concat("\\").concat(sEnvironment).concat("\\").concat(methodName).concat(".png");
		  System.out.println(sFileLocation);
		  WebDriver augmentedDriver = new Augmenter().augment(driver);
	      File srcFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
	      try 
	      {
			FileUtils.copyFile(srcFile, new File(sFileLocation));
			
	      }
	      catch (IOException e)
	      {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }

	  }
	  
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {				
		// TODO Auto-generated method stub
		takeSnapShot(sEnv, sApp, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
