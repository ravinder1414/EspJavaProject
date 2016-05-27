package reusableMethods_PageObject;
//Import files
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



public class ReusableMethods_PageObjects {
	public RemoteWebDriver driver = null;
public ReusableMethods_PageObjects(WebDriver driver) {
		
		//Initialize A AddALeadReferralPageObjects.
		PageFactory.initElements(driver, this);
	}
	
		



@FindBy(how=How.XPATH, using="//span[text()='Add An Inquiry']")
	 public WebElement lnkAddInquiry;
		
	
	@FindBy(how=How.ID, using="setupLink")
    public WebElement lnkSetup;
	
	
	@FindBy(how=How.NAME, using="studentInformationXML")
	public WebElement txtStudentInformationXML;
	
	
	@FindBy(how=How.ID, using="tsidLabel")
	public WebElement lnkDropDown;
	
	
	//Kaplan SRM DropDown
	
	@FindBy(how=How.LINK_TEXT, using="Kaplan SRM")
	public WebElement lnkKaplanSRM;
	
	@FindBy(how=How.ID, using="ext-gen60")
	public WebElement dropDownInquiry;
	
	//Admission Console
	
	@FindBy(how=How.LINK_TEXT, using="Admissions Console")
	public WebElement lnkAdmissionConsole;
	
	//Add an Inquiry dropdown
	
	
	@FindBy(how=How.XPATH, using="//button[@id='ext-gen33']/span")
	public WebElement lnkInquiryDropDown;
	
	
	
	@FindBy(how=How.XPATH, using="//div[4]/div/div/table/tbody/tr[2]/td[2]/em")
	public WebElement lnkAddInquiryDropDown;
	
	
	
	//Add an Inquiry
	
	@FindBy(how=How.XPATH, using=".//*[@id='ext-gen75']")
	public WebElement lnkAddInquiryClick;
	
	

	public void NavigateAdmissionConsoleSTAGE(WebDriver driver) throws InterruptedException{
		
		
		
		 String AppMenudriver = lnkDropDown.getText();
		 
		 if (!AppMenudriver.trim().equalsIgnoreCase("")){
		
		 lnkDropDown.click();
		 lnkAdmissionConsole.click();
		 }
		 
		 Thread.sleep(50000);
		 String Lstoptions=lnkInquiryDropDown.getText();
		 
		 if (!Lstoptions.trim().equalsIgnoreCase("Add An Inquiry")){
			 
			 Thread.sleep(50000);
		 Actions act = new Actions(driver); 
		 act.moveToElement(lnkAddInquiryDropDown, 480, 60).click().build().perform();
		 
		 lnkAddInquiry.click();

	
	
	}
	}
	
	public void BackToKaplanSRM(WebDriver driver) throws InterruptedException{
		
		
		
		 //String AppMenudriver = lnkSetup.getText();
		
		String AppMenudriver = lnkDropDown.getText();
		
		 
		 if (!AppMenudriver.trim().equalsIgnoreCase("Kaplan SRM")){
			 Thread.sleep(10000);
			 lnkSetup.click();
			 lnkKaplanSRM.click(); 
		 }
		// else{
			// Thread.sleep(30000);
			 //lnkDropDown.click();
			// lnkKaplanSRM.click();
		 }
	
	
	public void SEPSignin(WebDriver driver) throws InterruptedException{
		
		
		
		
		String AppMenudriver = lnkDropDown.getText();
		
		 
		 if (!AppMenudriver.trim().equalsIgnoreCase("Kaplan SRM")){
			 Thread.sleep(10000);
			 lnkSetup.click();
			 lnkKaplanSRM.click(); 
		 }
		// else{
			// Thread.sleep(30000);
			 //lnkDropDown.click();
			// lnkKaplanSRM.click();
		 }
	
	
	
	
	
	

		
	
public void NavigateAdmissionConsole_TEST(){
		
		
		
	}public void NavigateAdmissionConsole_DEV(){
		
		
		
	
	}
}
	
