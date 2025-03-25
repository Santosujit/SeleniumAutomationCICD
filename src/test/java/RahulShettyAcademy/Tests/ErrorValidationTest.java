package RahulShettyAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.TestComponents.Retry;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.ProductCatalogue;

//Ask this to Trainer - After adding Zara Coat to Cart, Cart  Page is taking lots of time to be displayed even after spiiner 
//disappears in a second, It's taking time

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	// retryAnalyzer = Retry.class this line we have written above, so If this test
	// case fail, It will rerun
	public void LoginErrorValidation() throws IOException, InterruptedException {
		landingPage.loginApplication("santosh@gmail.com", "wrongPassword");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());// add the d explicitly
																							// removed it. Added
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("santosh@gmail.com", "IloveRahul$1956");
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		driver.quit();

	}

}
