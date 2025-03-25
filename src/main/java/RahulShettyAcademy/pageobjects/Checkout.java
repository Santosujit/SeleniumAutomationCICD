package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstarctComponents;



public class Checkout extends AbstarctComponents {

	WebDriver driver;

	public Checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By results = By.cssSelector(".ta-results");

//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////	Now in the final order or checkout page, type india in Select Country drop down using Action class, and click on India
//	Actions act = new Actions(driver);
//	act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

	public void selectCountry(String countryName) {// countryName comes from Test Case
		Actions act = new Actions(driver);
		act.sendKeys(country, "india").build().perform();
		waitForElementToAppear(results);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);// Clicking the subnmit button, It will take the user to
															// confirmation page
		return new ConfirmationPage(driver);// so create the object of confirmation page and pass the driver from this
											// page to confirmation page
	}
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Place Order ']")));
//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Place Order ']")));
//
////	driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
////	General click didn't work, so we tried using Javascript click
//
//	WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
//
//	JavascriptExecutor js = (JavascriptExecutor) driver;
//
//	js.executeScript("arguments[0].click();", submit);
//
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
//	String orderConfirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//	Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));
}
