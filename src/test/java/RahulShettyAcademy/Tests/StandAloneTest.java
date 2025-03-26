package RahulShettyAcademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RahulShettyAcademy.pageobjects.LandingPage;
//import RahulShettyAcademy.AbstractComponents.AbstarctComponents;
//Added a comment, changed the file to test webhook and automatic jenkins run
//Added a comment, changed the file to test webhook and automatic jenkins run again


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
//		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		Opening the URL,maximizing the window  and Logging in
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("santosh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("IloveRahul$1956");
		driver.findElement(By.id("login")).click();
		
		LandingPage pg = new LandingPage(driver);

//		Now we should wait till all Products are loading, so use Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//		Now after Logging in, I want to click on Add to Cart of ZARA COAT 3

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

//		Now Clicking on Add to cart a Toast Message "Added to cart successfully" appears for a fraction of second
//		And also a Loading Icon appears. Loading Icon should disappear, and then go to Cart
//		what we want is Toast message should appear, and Loading Icon should disappear after that click on Cart

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));// visibility of
																										// Toas Message
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-tns-c31-1.ng-star-inserted"))));// invisibility
																											// of
																											// Loading
																											// Icon
//		Now Click on the Cart Link below
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

//		Now Validate If ZARA COAT 3 is available in the cart section

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); // Capture all the
																								// products of the cart
//		Now validate If cartProducts contains ZARA COAT 3 or productName

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

//		As shown above Filter method returns a WebElemenmt and you can do anything upon it
//		However anyMatch() method only matches

//		Now click on checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();

//		Now in the final order page, type india in Select Country drop down using Action class, and click on India
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Place Order ']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Place Order ']")));

//		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
//		General click didn't work, so we tried using Javascript click

		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", submit);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String orderConfirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();

	}

}
