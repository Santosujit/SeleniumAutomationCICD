package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstarctComponents;



public class CartPage extends AbstarctComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); // Capture all the
//	// products of the cart
////Now validate If cartProducts contains ZARA COAT 3 or productName
//
//Boolean match = cartProducts.stream()
//.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
//
//Assert.assertTrue(match);

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

//	Now click on checkout
//	driver.findElement(By.cssSelector(".totalRow button")).click();

	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public Checkout goTocheckOut() {
		checkOutEle.click();
		return new Checkout(driver); // This method will return the object Checkout so catch in the Test class
	}
}
