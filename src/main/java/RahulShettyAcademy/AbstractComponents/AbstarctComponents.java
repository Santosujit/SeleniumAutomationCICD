package RahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.OrderPage;


public class AbstarctComponents {

//child class sent driver to parent class, but to catch the driver we have to have a constructor in parent class
	WebDriver driver;

	public AbstarctComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	driver.findElement(By.cssSelector("[routerlink*='cart']")) -> Go to cart
	@FindBy(css = ".btn.btn-custom[routerlink='/dashboard/cart']")
//	
//	[routerlink*='cart']
//	
	WebElement cartLink;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	

	@FindBy(css = ".ng-tns-c31-1.ng-star-inserted")
	WebElement spinner;
	
	

//	Now we should wait till all Products are loading, so use Explicit wait
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//	Use above code to design an utility

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForElementToDisppaear(WebElement ele) {// spinner is disappearing, still It's waiting for hidden
															// spinner to disappear. so It's taking 4 seconds of time.
															// So you can add Thread.sleep(), and remove webdriver wait
															// here
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}



	public CartPage goToCartPage() {
		waitForElementToDisppaear(spinner);
		waitForWebElementToAppear(cartLink);
		cartLink.click();// It will take you to next page which is cart page
//		CartPage cartPage = new CartPage(driver);
		return new CartPage(driver);// Create CartPage cartPage in test class to catch the object of CartPage
	}

	public OrderPage goToOrdersPage() {
		orderHeader.click();// It will take you to next page which is cart page
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;

		
	}
}
