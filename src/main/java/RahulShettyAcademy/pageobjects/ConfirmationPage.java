package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstarctComponents;



public class ConfirmationPage extends AbstarctComponents {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement ConfirmationMessage;
	
	By ConfirmationMessageLocator = By.cssSelector(".hero-primary");
	
	public  String getConfirmationMessage() {
		return ConfirmationMessage.getText();
	}
	
	

}
