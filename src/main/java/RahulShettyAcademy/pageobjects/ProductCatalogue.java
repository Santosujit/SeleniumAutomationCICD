package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstarctComponents;



public class ProductCatalogue extends AbstarctComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);// This constructor passes the driver to parent class constructor so that
						// parenty calss driver gets Life
		this.driver = driver;// Now our class driver got life from Test class driver
		PageFactory.initElements(driver, this);// It will do driver.findElement() in FindBy
	}

//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//	using above below method is constructed

	@FindBy(css = ".mb-3")
	List<WebElement> products;// PageFactory is only meant for driver.findElement() type not for By locator

	By addToCartButton = By.cssSelector(".card-body button:last-of-type");

	@FindBy(css = ".ng-animating")
	WebElement spinner;// PageFactory is only meant for driver.findElement() type not for By locator

	By productsLocator = By.cssSelector(".mb-3");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsLocator);
		return products;// Always try to return something in a method, so it can be used elsewhere or
						// else IT's only sitting inside the method
	}

//    WebElement prod = products.stream()
//			.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
//			.orElse(null);
//	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

//    Use above code to design to two utility or methods
//    one for selecting the particular product(ZARA Coat), and another method to add the product to the cart , let's see below

	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartButton).click();
		waitForElementToAppear(toastMessage);// called the utility method here
		waitForElementToDisppaear(spinner);

	}

//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));// visibility of
//	// Toast Message
//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));// invisibility
	// of
	// Loading
	// Icon

//	For visibility we have a utility
//	For invisibility create a utility

}
