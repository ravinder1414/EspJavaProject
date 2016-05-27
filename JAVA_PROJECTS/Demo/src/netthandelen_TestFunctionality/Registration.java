package netthandelen_TestFunctionality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.hybrid.util.ReportUtil;
import com.selenium.hybrid.util.TestUtil;

import java.sql.SQLException;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import netthandelen_Variables.EnvironmentVariables;
import uiMap_Netthandelen.Registration_PageObject;
import utils.BaseClass;
import utils.JyperionListener;
import org.openqa.selenium.By;



@Listeners(JyperionListener.class)

public class Registration extends BaseClass {
	//public class Registration {
		
				//Remote Web driver for remote execution
				public RemoteWebDriver driver = null;
				
				//BrowseManagement to set the browser capabilities
				public BrowserManagement objBrowserMgr = null;
				
				//Registration page object
				
				public Registration_PageObject uiRegistation_PageObject;
				

			
			
				
				//Method which will executed before the class loads
				//Browser Parameter received from TestNg.xml
				@Parameters({"Browser"})
				@BeforeClass
				public void BeforeNavigation(String sBrowser) throws MalformedURLException
				{
					try

					{

					//Read the application properties file
					//Load environment variable from properties file
					String sPath_AppProperties="";
					FileInputStream objFileInputStream = null;
					Properties objProperties = new Properties();
					
					//Set file path as per environment
					if (EnvironmentVariables.sEnv.equalsIgnoreCase("dev"))
					{
						sPath_AppProperties = ".//Resources//ApplicationProperties/DevApplication.properties";
					}
					else if (EnvironmentVariables.sEnv.equalsIgnoreCase("stage"))
					{
						sPath_AppProperties = ".//Resources//ApplicationProperties/StageApplication.properties";			
					}
					else if (EnvironmentVariables.sEnv.equalsIgnoreCase("lt"))
					{
						sPath_AppProperties = ".//Resources//ApplicationProperties/LtApplication.properties";			
					}
					else
					{
						sPath_AppProperties = ".//Resources//ApplicationProperties/TestApplication.properties";			
					}
					
					
					
					
					


					
					//Load the Application variable file into File Input Stream.
					File objFileApplication = new File(sPath_AppProperties);
					try
					{
						objFileInputStream = new FileInputStream(objFileApplication);
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
					
					
					//Edit Browser Capabilities as per project
					//Fire fox Profile		
					FirefoxProfile profile = new FirefoxProfile();
					
					profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sTrusted_Uris);
					//Capability
					objBrowserMgr = new BrowserManagement(sBrowser);
					objBrowserMgr.capability.setCapability(FirefoxDriver.PROFILE, profile);		
						
					//Create the Remote Driver Instance
					try
					{					
						driver = new RemoteWebDriver(new URL("http://".concat(EnvironmentVariables.sHub).concat(":").concat(EnvironmentVariables.sHubPort).concat("/wd/hub")), objBrowserMgr.capability);
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
					}
					catch(Exception ex)
					{	
						ReportExtn.Fail("Unable to create the Remotedriver" +  ex.getMessage());			
					}
					driver.get(EnvironmentVariables.sTestnetthandelen_Url);
					driver.manage().window().maximize();
					
					uiRegistation_PageObject =new Registration_PageObject(driver) ;
					
					}
					catch (Exception e)
											
					{
					Reporter.log(e.getMessage());
					System.out.println(e.getMessage());
					System.out.println(e.getStackTrace());
					
					
					}

					
				}
				
				@AfterClass
				public void AfterNavigation()
				{
					try

					{
						

						// Writing to Result Property File

						System.out.println("Inside After class");
						
						//System.out.println("Before method writing to Result Property file");
						
						//SRM_ReusableMethods.writeToPropertyFile(sPath_ResultProperties, "sEmailAddressNameFromAddInfoCall_Lead_Creation", sEmailAddress);
						
						//System.out.println("After method writing to Result Property file");


						
					//Quit the test after test class execution
					if(driver != null)
					{
						driver.quit();			
					}
				}
				catch (Exception e)
										
				{
				Reporter.log(e.getMessage());
				System.out.println(e.getMessage());
				//System.out.println(e.getStackTrace());
				}

				}
				
				
				
				@Test
				public void RegistrationPage(Method objMethod) throws InterruptedException, ClassNotFoundException, SQLException
				{
					
                    

					uiRegistation_PageObject =new Registration_PageObject(driver) ;
					Thread.sleep(10000);
					
					
                   
					
					uiRegistation_PageObject.popMessage.click();
					
					uiRegistation_PageObject.lnkRegister.click();
					
					
					//uiRegistation_PageObject.txtFirstName.sendKeys("Test1");
					
					uiRegistation_PageObject.txtFirstName.sendKeys("test1");
					
					
					uiRegistation_PageObject.txtEmailAddress.clear();
					uiRegistation_PageObject.txtEmailAddress.sendKeys("ravinder.kumar145@espire.com");
					
					uiRegistation_PageObject.txtNewPassword.sendKeys("asdf1234");
					
					uiRegistation_PageObject.txtAddress.sendKeys("test123");
					
					uiRegistation_PageObject.txtHomeZipcode.sendKeys("1201");
					
					uiRegistation_PageObject.txtMobileNumber.sendKeys("99156114");
					uiRegistation_PageObject.btnRegister.click();
					
					Thread.sleep(10000);
					
					
					
					
				String RegistrationMessage=	uiRegistation_PageObject.txtRegistrationMessage.getText().trim();
				
				System.out.println(RegistrationMessage);
				//Assert.assertEquals(RegistrationMessage, "SKRIV INN DIN KODE");
				
				Assert.assertEquals("SKRIV INN DIN KODE", RegistrationMessage, "Registration successful");
				
				Database data = new Database();
				String smsValue =data.verification();
				
				System.out.println("smsValue");

				Thread.sleep(10000);
				
				driver.findElement(By.id("txt-code")).sendKeys(smsValue);
				
				//Log.info("Filling Varification code from Databse");
				
				
					}
	}
				
					
					
					
						
	
				
				
	

	

