package commonfunctions;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserManagement {
	
	public DesiredCapabilities capability = null; 
	
	public BrowserManagement(String sBrowser)
	{
		capability = new DesiredCapabilities();
		//Browser Capability				
		if(sBrowser.equalsIgnoreCase("ie"))
		{
			capability = DesiredCapabilities.internetExplorer();			
	    	capability.setBrowserName("iexplore");	
	        capability.setPlatform(Platform.WINDOWS);
	    	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);	    
	    	capability.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);	    	
		    capability.setJavascriptEnabled(true);		    
		}
		else if(sBrowser.equalsIgnoreCase("chrome"))
		{
			capability = DesiredCapabilities.chrome();			
		}		
		else
		{
			capability = DesiredCapabilities.firefox();
			FirefoxProfile profile = new FirefoxProfile();
		}		
	}
}
