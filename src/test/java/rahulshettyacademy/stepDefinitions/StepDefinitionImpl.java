 package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.Checkoutpage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest{
	LandingPage landingpage;
	ProductCatalog productcatalog;
	ConfirmationPage confirmationpage;
	 @Given("I Landed on Ecommerce Page") 
	 public void i_landed_on_ecommerce_page()throws IOException {
		 landingpage = launchApplication();
	  }
	@Given("^Login with username (.+) and password (.+)$")
	public void login_with_username_and_password(String username, String password) {
	    productcatalog = landingpage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_Product_to_cart(String productName){
		List<WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submitOrder(String productName) {
		CartPage cartpage = productcatalog.goToCart();
		Boolean match = cartpage.verifyProductDisplay(productName);
		 Assert.assertTrue(match);
		 Checkoutpage checkoutpage = cartpage.goToCheckoutPage();
		 checkoutpage.selectCountry("INDIA");
		 confirmationpage = checkoutpage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_on_confirmationPage(String string) {
		String messasge = confirmationpage.getConfirmationMessage();
		 Assert.assertTrue(messasge.equalsIgnoreCase(string));
		 driver.close();
	}
	@Then("{string} message is displayed")
	public void Error_message_displayed(String string) {
		landingpage.getErrorMessage();
	     Assert.assertEquals(string,landingpage.getErrorMessage());
	}

}