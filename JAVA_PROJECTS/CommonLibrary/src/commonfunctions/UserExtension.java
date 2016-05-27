package commonfunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

public class UserExtension {
	
	public static List<WebElement> FindAllLinks(WebDriver driver){
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		return links;
		
		
		}
	
	public static boolean IsElementPresent(WebDriver driver, WebElement element)
	{
		boolean flag;
		WebDriverWait objWaitDriver  = new WebDriverWait(driver, 10);
		try
		{
			objWaitDriver.until(ExpectedConditions.visibilityOf(element));
			flag = true;
		}catch(org.openqa.selenium.TimeoutException e)
		{
			flag = false;
		}
		
			return flag;				
	}
	public static boolean IsElementNotPresent(WebDriver driver, WebElement element)
	{
		boolean flag;
		WebDriverWait objWaitDriver  = new WebDriverWait(driver, 100);
		try
		{
			objWaitDriver.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
			flag = true;
		}catch(org.openqa.selenium.TimeoutException e)
		{
			flag = false;
		}
		
			return flag;				
	} 
	
	public static void ClickSafe(WebDriver driver, By by)
	{
		try
		{
			driver.findElement(by).click();
		}
		catch(Exception e)
		{
			ReportExtn.Fail("Unable to Click. " +  e.getMessage());
		}
		
	}
	//Get Attribute with safe
	public static String GetAttributeSafe(WebDriver driver, By by, String attribute)
	{
		String sStr="null";
		try
		{
			sStr = driver.findElement(by).getAttribute(attribute);
		}
		catch(Exception e)
		{
			ReportExtn.Fail("Unable to get the Attribute. " +  e.getMessage());
		}
		return sStr;
	}
	
	//Mouse Over on a WebElement
	public static void MouseOver(WebDriver driver, WebElement element)
	{
		try
		{
			Actions objAction = new Actions(driver);
			objAction.moveToElement(element);
			objAction.build().perform();
			
		}
		catch(Exception e)
		{
			ReportExtn.Fail("Unable to find the Element. " + element.toString() + e.getMessage());
		}
		
	}
	
	//Attempting to recover from StaleElementReferenceException 
	public static WebElement GetStaleElement(WebDriver driver, By objBy)
	{
		try
	    	{
		        return driver.findElement(objBy);
		         
		    }
	    	catch (StaleElementReferenceException e)
	    	{
	    		System.out.println("Attempting to recover from StaleElementReferenceException ...");
	    		return GetStaleElement(driver, objBy);
		      
		    }		
	}
	//Wait Till the GetText of the Element is equal to Text  
	public static boolean WaitTillGetTextValueIs(WebDriver driver, WebElement element, String Text)
	{
		boolean flag;
		flag = false;
		WebDriverWait objWaitDriver  = new WebDriverWait(driver, 10);
		for(int i =0;i<10;)
		{
			try		
			{
				if(element.getText().trim().equalsIgnoreCase(Text))
				{
					flag = true;
					i = 11;
				}
				else
				{
					i++;
					objWaitDriver.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
					
				}
							
			}catch(org.openqa.selenium.TimeoutException e)
			{
				
			}
			
		}
		return flag;		
	}
	//Wait till the Attribute value is equal to a value
	public static boolean WaitTillAttributeValueIs(WebDriver driver, WebElement element, String Attribute, String value)
	{
		boolean flag;
		flag = false;
		WebDriverWait objWaitDriver  = new WebDriverWait(driver, 5);
		objWaitDriver.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		for(int i =0;i<30;i++)
		{
			try		
			{
				if(element.getAttribute(Attribute).trim().equalsIgnoreCase(value))
				{
					flag = true;
					i = 31;
				}
				else
				{
					//i++;
					objWaitDriver.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
					
				}
							
			}catch(org.openqa.selenium.TimeoutException e)
			{
				
			}
			
		}
		return flag;		
	}

	//Wait till the Attribute value is NOT equal to a value
	public static boolean WaitTillAttributeValueIsNot(WebDriver driver, WebElement element, String Attribute, String value)
	{
		boolean flag;
		flag = false;
		WebDriverWait objWaitDriver  = new WebDriverWait(driver, 2);
		for(int i =0;i<30;)
		{
			try		
			{
				if(element.getAttribute(Attribute).trim().equalsIgnoreCase(value))
				{
					i++;
					objWaitDriver.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));					
				}
				else
				{
					
					flag = true;
					i = 31;
				}
							
			}catch(org.openqa.selenium.TimeoutException e)
			{
				
			}
			
		}
		return flag;		
	}
	
	//Wait till the Attribute Exists
	public static boolean WaitTillAttributeExist(WebDriver driver, WebElement element, String Attribute)
	{
		boolean flag;
		flag = false;
		WebDriverWait objWaitDriver  = new WebDriverWait(driver, 2);
		for(int i =0;i<30;)
		{
			try		
			{
				if(element.getAttribute(Attribute).trim().equalsIgnoreCase("temp"))
				{
					i++;
					objWaitDriver.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));					
				}
				else
				{
					i++;
					objWaitDriver.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
				}
							
			}catch(java.lang.NullPointerException e)
			{
				return flag;
			}
			
		}
		return flag;		
	}

}

