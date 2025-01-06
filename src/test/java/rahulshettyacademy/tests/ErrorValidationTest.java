package rahulshettyacademy.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest {

	
	@Test(groups= {"Errorhandling"},retryAnalyzer=Retry.class)
	public void  loginErrorValidation() {
		// TODO Auto-generated method stub
		//String productName ="ZARA COAT 3";
		landingpage.loginApplication("chaitanyaB@gmail.com","Sairaam123");
		landingpage.getErrorMessage();
	     Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());

	  }
	@Test
	public void productErrorValidation() {
		String productName ="QWERTY";
		ProductCatalog productcatalog = landingpage.loginApplication("chaitanyaB@gmail.com","Sairam12");
		List<WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(productName);
		CartPage cartpage = productcatalog.goToCart();
		Boolean match = cartpage.verifyProductDisplay("QWERTY");
		 Assert.assertTrue(match);
		 System.out.println(match);
	}
	         
	}
