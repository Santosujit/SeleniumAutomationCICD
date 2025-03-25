package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstarctComponents;

public class LandingPage extends AbstarctComponents {

//	driver.findElement(By.id("userEmail")).sendKeys("santosh@gmail.com");this driver is not known in this class
//	so we have to bring this driver from StandAloneTest or SubmnitOrder class
//	for initialization we use constructor, in a class, before anything gets executed, 1st constructor gets executed

	WebDriver driver; // This driver has no life in this line

	public LandingPage(WebDriver driver) {// To catch the driver we are creating WebDriver variable to catch the driver
		super(driver); // thats coming from other class
		this.driver = driver;// Now our class driver got life from Test class driver
		PageFactory.initElements(driver, this);// It will do driver.findElement() in FindBy. For PageFactory to work,
												// this line is must. It initiates PageFactory
	}

//	Now use PageFactory methods instead of using driver.findElement()
//	driver.findElement(By.id("userEmail"))

	@FindBy(id = "userEmail") // It will create WebElement in run time
	WebElement userEmailEle;

//	driver.findElement(By.id("userPassword"))
	@FindBy(id = "userPassword") // It will create WebElement in run time
	WebElement passwordEle;

//	driver.findElement(By.id("login")).click();
	@FindBy(id = "login") // It will create WebElement in run time
	WebElement submit;

//	@FindBy(css = "[class*=toast-message]") // It will create WebElement in run time
//	WebElement errorMessage;
////	ng-tns-c4-3 toast-message ng-star-inserted - Error so commented this line

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

//	Now create Page actions, 1st Log in
//	In Page Class By Locators, WebElements and Action methods are allowed only, no Assertion in Page class Pls.
//	Assertion should be only be in Test class

	public ProductCatalogue loginApplication(String email, String password) {
		userEmailEle.sendKeys(email);// No hardcoding of userEmail or password
		passwordEle.sendKeys(password);
		submit.click();// after you click submit or Login, you will land into product catalogue page
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);you can write this one or below line
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
//		return new ProductCatalogue(driver);// Create the Object of the next page and return it, pass the driver so that
//		                                        Next Page can use the driver

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();

	}

}
