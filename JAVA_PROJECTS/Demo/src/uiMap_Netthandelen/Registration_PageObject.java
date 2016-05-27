package uiMap_Netthandelen;

//Import files
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Registration_PageObject {
		
public Registration_PageObject(WebDriver driver) {
			
			//Initialize A RegistrationPageObjects.
			PageFactory.initElements(driver, this);
		}
		
	
		//Pop up message
		@FindBy(how=How.XPATH, using="//img[@src='//s3.amazonaws.com/static.barilliance.com/img/common/dialog_close.png']")
		public WebElement popMessage;
		
		//Register link English
		
		
				@FindBy(how=How.LINK_TEXT, using="Register")
				public WebElement lnkRegister;
		
		//@FindBy(how=How.LINK_TEXT, using="Ny kunde")
		//public WebElement lnkRegister;
	                                               

		//First Name
		@FindBy(how=How.ID, using="txt-first-name")
		public WebElement txtFirstName;
		

		//Last Name
		@FindBy(how=How.ID, using= "txt-last-name")
		public WebElement txtLastName;
		
		//Email Address Text Box
		@FindBy(how=How.ID, using="txt-email")
		public WebElement txtEmailAddress;
				
		//Password
		@FindBy(how=How.ID, using="txt-password")
		public WebElement txtNewPassword;
		
		//Address
		@FindBy(how=How.ID, using="txt-address")
		public WebElement txtAddress;
		
		//Home ZIP Code Text
		@FindBy(how=How.ID, using="txt-post-number")
		public WebElement txtHomeZipcode;
		
		
		//Mobile number Text
		@FindBy(how=How.ID, using="txt-mobile-number")
		public WebElement txtMobileNumber;
		

		//Register button
		@FindBy(how=How.ID, using= "btn-register")
		public WebElement btnRegister;
		
		//Registation message
				@FindBy(how=How.XPATH, using= ".//*[@id='validationWrapper']/div[3]/div[1]/label")
				public WebElement txtRegistrationMessage;
		
		
		
		
		
		
		
		
		
		
		//Confirm Email Address Text box
		@FindBy(how=How.ID, using= "ConfirmEmail")
		public WebElement txtConfirmEmail;

		//Spouse Radio Button Yes
		@FindBy(how=How.ID, using="MilitaryYes")
		public WebElement rbtnSpouse_Yes;
		
		//Spouse Radio Button No
		@FindBy(how=How.ID, using= "MilitaryNo")
		public WebElement rbtnSpouse_No;
		
		//Degree Radio Button
		@FindBy(how=How.ID, using="Graduate")
		public WebElement rbtnDegreeGraduate;


		//Undergraduate Degree Radio Button
		@FindBy(how=How.ID, using= "Undergraduate")
		public WebElement rbtnDegreeUndergraduate;
		
		//Nursing Radio Button Yes
		@FindBy(how=How.ID, using="NursingYes")
		public WebElement rbtnNurshing_Yes;
		

		//Nursing Radio Button No
		@FindBy(how=How.ID, using= "NursingNo")
		public WebElement rbtnNurshing_No;
		
		


		//Confirm Password
		@FindBy(how=How.ID, using= "txtConfirmPassword")
		public WebElement txtConfirmPassword;
		
		//TCPA CheckBox
		@FindBy(how=How.ID, using="TCPA")
		public WebElement checkBoxTCPA;
		

		//Create Account Button
		@FindBy(how=How.ID, using= "create-account")
		public WebElement btnCreateAccount;
		
		//Account Created Successfully message
		
		@FindBy(how=How.XPATH, using= ".//*[@id='account-creation-modal']/div[1]")
		public WebElement txtAccountCreationMessage;
		
		//Click on Ok button
		
		@FindBy(how=How.ID, using= "ok-mini")
		public WebElement btnOK;
		
		
		
		
		

	}

