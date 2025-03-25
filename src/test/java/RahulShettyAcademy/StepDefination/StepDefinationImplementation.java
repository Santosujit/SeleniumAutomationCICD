package RahulShettyAcademy.StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.Checkout;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.LandingPage;
import RahulShettyAcademy.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page.")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^I log in with username (.+) and password (.+)$")
	public void I_log_in_with_username_and__password(String userName, String password) {
		productCatalogue = landingPage.loginApplication(userName, password);
	}

	@When("^When I add product (.+) to cart.$")
	public void When_I_add_product_to_cart(String productName)throws IOException {
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addToCart(productName);
	}

	@And("^checkout (.+) and submit the order.$")
	public void checkOut_And_Submit_Order(String productName) throws IOException {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		Checkout checkOut = cartPage.goTocheckOut();
		checkOut.selectCountry("india");
		confirmationPage = checkOut.submitOrder();
	}

	@Then("{string} message is displayed.")
	public void message_displayed_confirmationPage(String string) throws IOException{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}

}
