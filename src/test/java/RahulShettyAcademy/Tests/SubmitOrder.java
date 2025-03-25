package RahulShettyAcademy.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.Checkout;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.OrderPage;
import RahulShettyAcademy.pageobjects.ProductCatalogue;

//In Test method driver is there, through constructor we have to bring this driver to life in each action methods

public class SubmitOrder extends BaseTest {

//	This is a copy of StandAloneTest, we will modify here

//	public static void main(String[] args) throws InterruptedException {// Now remove main() method and use TestNG
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })

//	public void submitOrder(String email, String password, String productName) throws IOException--->It was earlier we did
//	In this above line scenario, If DataProvider sends us 15 values, we have declare 15 String variables to receive them
//	which is ugly, so we will use HashMap concept
	public void submitOrder(HashMap<String, String> input) throws IOException {// to catrch the data
																				// provided by
																				// dataprovider we
																				// create theree
																				// string variables
																				// to catch the data

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();// send this driver to all actions methods from this Test method
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		Opening the URL,maximizing the window  and Logging in
//		driver.get("https://rahulshettyacademy.com/client");
//		driver.manage().window().maximize();
//		driver.findElement(By.id("userEmail")).sendKeys("santosh@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("IloveRahul$1956");
//		driver.findElement(By.id("login")).click();

//		LandingPage landingPage = launchApplication();

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();// chld class object can access parent class methods. you
															// know that you are
		// going to cart page. so create the object of cart page inside this method
//		CartPage cartPage = new CartPage(driver); not needed
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		Checkout checkOut = cartPage.goTocheckOut();// you know that this step leads to checkout page
		checkOut.selectCountry("india");
		ConfirmationPage confirmationPage = checkOut.submitOrder();// go to submitOrder(), when you go there you see its
																	// returning return new ConfirmationPage()
//		                          so create an object variable ConfirmationPage confirmationPage to hold this value

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Ordering a Product Tese Case successfully passed");
//		driver.close(); THis will be executed from AfterMthod in BaseTest

//		Now we should wait till all Products are loading, so use Explicit wait
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
////		Now after Logging in, I want to click on Add to Cart of ZARA COAT 3
//
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		WebElement prod = products.stream()
//				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
//				.orElse(null);
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

//		Now Clicking on Add to cart a Toast Message "Added to cart successfully" appears for a fraction of second
//		And also a Loading Icon appears. Loading Icon should disappear, and then go to Cart
//		what we want is Toast message should appear, and Loading Icon should disappear after that click on Cart

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));// visibility of
		// Toast Message
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));// invisibility
//																											// of
//																											// Loading
//																											// Icon
//		Now Click on the Cart Link below
//		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

//		Now Validate If ZARA COAT 3 is available in the cart section

//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); // Capture all the
//																								// products of the cart
////		Now validate If cartProducts contains ZARA COAT 3 or productName
//
//		Boolean match = cartProducts.stream()
//				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
//
//		Assert.assertTrue(match);

//		As shown above Filter method returns a WebElemenmt and you can do anything upon it
//		However anyMatch() method only matches

////		Now click on checkout
//		driver.findElement(By.cssSelector(".totalRow button")).click();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		Now in the final order page, type india in Select Country drop down using Action class, and click on India
//		Actions act = new Actions(driver);
//		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Place Order ']")));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Place Order ']")));

//		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
//		General click didn't work, so we tried using Javascript click

//		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		js.executeScript("arguments[0].click();", submit);

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
//		String orderConfirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	}

//	In below I want to write another Test method, to check whether added product name is displayed the order history or not
//	After submit Order, Product checking in Order History 'll be executed, so depednsOnMethod use

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("santosh@gmail.com", "IloveRahul$1956");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

	}

	// Order is common in the Header, so write the method in AbstractComponents
	// class or Utility

//	Now through DataProvider drive Data needed for test case
//	Through this method, one test case can be run multiple times
//	DataProvider provies the data to the test case, so we need to get the data first, that's why create 
//	a getTestData() mthod

//	@DataProvider
//	public Object[][] getData() {//It's a multi dimensional array
//		return new Object[][] { { "santosh@gmail.com", "IloveRahul$1956", "ZARA COAT 3" },
//				{ "santoshsujit@gmail.com", "IlovemyMom$1956", "ADIDAS ORIGINAL" } };
//	}

//	Convert the above DataProvider to a HashMap way
//	String email, String password, String productName
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "santosh@gmail.com");
//		map.put("password", "IloveRahul$1956");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "santoshsujit@gmail.com");
//		map1.put("password", "IlovemyMom$1956");
//		map1.put("product", "ADIDAS ORIGINAL");

//		Now above HashMaps we created manually, Now we will build an Utility which can tread Json file,
//		convert it to String, then from String to HashMaps
//		My Test should not have any data, Test should look clean, external data should come from Jason
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\RahulShettyAcademy\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
