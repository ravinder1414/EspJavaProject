package reusableMethods_PageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import commonfunctions.ReportExtn;

public class SRM_ReusableMethods {
	
	
	
	/*// Variables to be used by method writeToPropertyFile
	public FileOutputStream objFileOutputStream=null;
	public static FileInputStream objFileInputStream = null;
	public static Properties objProperties = new Properties();*/
	
	
	
	
	
	
	
	
	
  //This method is used to wait for 2 to appear in SRM
// Prerquisite is that in SRM you have to make the first search	
  public static void WaitSearchOpportunity(WebDriver driver, int timeout) throws InterruptedException {
	  int count =0;
		
		for(int i=0;i<5;i++)
		{ try {	
			//refreshiing /reloading the page
			driver.findElement(By.xpath(".//*[@id='secondSearchButton']")).click();
		  	Thread.sleep(1000);
		  	//verifiation
		  	if(driver.findElement(By.xpath("//h3/span[contains(text(),'Opportunities')]")).isDisplayed())
		  	{
		  		System.out.println("PASS");
		  		//driver.findElement(By.xpath(".//*[@id='showFiltersId-Opportunity-006']")).click();
		  		i=21;
		  	}
		  	
		  }
			catch(Exception e)
			{
				System.out.println("FAIL");
				count=count+1;
				System.out.println("No of Iterations"+" "+count);
				Thread.sleep(timeout);
		
			}

		}
  }
  
  //This method is used to wait for Inquiry to appear in SRM
  public static void WaitSearchInquiry(WebDriver driver, int timeout) throws InterruptedException {
	  int count =0;
		
		for(int i=0;i<5;i++)
		{ try {	
			driver.findElement(By.xpath(".//*[@id='secondSearchButton']")).click();
		  	Thread.sleep(1000);
		  	if(driver.findElement(By.xpath("//h3/span[contains(text(),'Inquiries')]")).isDisplayed())
		  	{
		  		System.out.println("PASS");
		  		//driver.findElement(By.xpath(".//*[@id='showFiltersId-Opportunity-006']")).click();
		  		i=21;
		  	}
		  	
		  		
		  }
			catch(Exception e)
			{
				System.out.println("FAIL");
				count=count+1;
				System.out.println("No of Iterations"+" "+count);
				Thread.sleep(timeout);
		
			}
		}
  }
		
  
  //This metod is used for to wait program groups to appear in SRM
  public static void WaitSearchProgramGroups(WebDriver driver, int timeout) throws InterruptedException {
	  int count =0;
		
		for(int i=0;i<5;i++)
		{ try {	
			driver.findElement(By.xpath(".//*[@id='secondSearchButton']")).click();
		  	Thread.sleep(1000);
		  	if(driver.findElement(By.xpath("//h3/span[contains(text(),'Program Groups')]")).isDisplayed())
		  	{
		  		System.out.println("PASS");
		  		//driver.findElement(By.xpath(".//*[@id='showFiltersId-Opportunity-006']")).click();
		  		i=21;
		  	}
		  	
		  		
		  }
			catch(Exception e)
			{
				System.out.println("FAIL");
				count=count+1;
				System.out.println("No of Iterations"+" "+count);
				Thread.sleep(timeout);
		
			}
		}
  }
		
		//This method is used to wait for College/University to appear in SRM
		  public static void WaitCollegeUniversity(WebDriver driver, int timeout) {
			  int count =0;
				
				for(int i=0;i<5;i++)
				{ try {	
					Thread.sleep(timeout);
				  	//driver.findElement(By.xpath(".//*[@id='secondSearchButton']")).click();
					driver.navigate().refresh();
				  	Thread.sleep(1000);
				  	if(driver.findElement(By.xpath(".//*[@class='pbBody']/table/tbody/tr[2]/th/a")).isDisplayed())
				  	{
				  		System.out.println("PASS");
				  		//driver.findElement(By.xpath(".//*[@id='showFiltersId-Opportunity-006']")).click();
				  		i=21;
				  	}
				  }
					catch(Exception e)
					{
						System.out.println("FAIL");
						count=count+1;
						System.out.println("No of Iterations"+" "+count);
						
				
					}

		}
		  }
		  
		  

			//This method is used to wait for College/University to appear in SRM
			  public static void WaitStartDate(WebDriver driver, int timeout) {
				  int count =0;
					
					for(int i=0;i<5;i++)
					{ try {	
						Thread.sleep(timeout);
					  	//driver.findElement(By.xpath(".//*[@id='secondSearchButton']")).click();
						driver.navigate().refresh();
					  	Thread.sleep(1000);
					  	if(driver.findElement(By.xpath(".//*[@id='00Ni000000BVIrJ_ileinner']")).isDisplayed())
					  	{
					  		System.out.println("PASS");
					  		//driver.findElement(By.xpath(".//*[@id='showFiltersId-Opportunity-006']")).click();
					  		i=21;
					  	}
					  }
						catch(Exception e)
						{
							System.out.println("FAIL");
							count=count+1;
							System.out.println("No of Iterations"+" "+count);
							
					
						}
					}
			  }
			  
			  
			  
			  // This method is for writing to Property File
			  
			  public static void writeToPropertyFile(String Path_AppProperties,String propertyTabName,String textToBeWritten)
			  {
				  try
				  {
					  	
						FileInputStream objFileInputStream = null;
						Properties objProperties = new Properties();
					  
					  	
					  System.out.println("Starting writing to Property file");
					  
						File objFileApplication = new File(Path_AppProperties);
						try
						{
							objFileInputStream = new FileInputStream(objFileApplication);
						}catch (FileNotFoundException ex)
						{
							ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
						}

						
						try
						{
							objProperties.load(objFileInputStream);
							
						} catch (IOException ex) {

							ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
						}
						
						// Writing to The property File
						
						objProperties.setProperty(propertyTabName, textToBeWritten);
						
						
						
						// Store the Value in Property File
						
								
						objProperties.store(new FileOutputStream(Path_AppProperties), null);	
					  
						System.out.println("Finish writing to Property file");
					  
					  
				  }
				  catch(Exception e)
				  {
					  System.out.println(e.getMessage());
					  System.out.println(e.getStackTrace());
				  }
			  }
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
}