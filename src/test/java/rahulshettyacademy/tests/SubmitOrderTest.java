package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.Checkoutpage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	String productName ="QWERTY";
	@Test(dataProvider="getData",groups="purchase")
	public void  submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		
		//String countryName ="INDIA";
		ProductCatalog productcatalog = landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(input.get("product"));
		CartPage cartpage = productcatalog.goToCart();
		Boolean match = cartpage.verifyProductDisplay(input.get("product"));
		 Assert.assertTrue(match);
		 System.out.println(match);
		 Checkoutpage checkoutpage = cartpage.goToCheckoutPage();
		 checkoutpage.selectCountry("INDIA");
		 ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		 String messasge = confirmationpage.getConfirmationMessage();
		 Boolean match1 = messasge.equalsIgnoreCase("Thankyou for the order.");
		 Assert.assertTrue(match1);
		 System.out.println(match1);
	  }
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistory() throws InterruptedException {
		ProductCatalog productcatalog = landingpage.loginApplication("chaitanya@gmail.com","Sairam123");
		
		OrderPage orderpage = productcatalog.goToOrderPage();
		Thread.sleep(3000);
		Assert.assertTrue(orderpage.verifyProductDisplayInOrder(productName));
		}
	
	@DataProvider   
	public Object[][] getData() throws IOException {
		
	List<HashMap<String, String>> data = getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\purchaseOrder.json");
		
	return new Object[][] {{data.get(0)},{data.get(1)}};
	
	}
}

//@DataProvider
//public Object[][] getdata() {
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "chaitanya@gmail.com");
//	map.put("password","Sairam123");
//	map.put("product", "ZARA COAT 3");
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "chaitanyaB@gmail.com");
//	map1.put("password","Sairam12");
//	map1.put("product", "ADIDAS ORIGINAL");
//	return new Object[][] {{map},{map1} };
//}
//@DataProvider
//public Object[][] getdata() {

//	return new Object[][] {{"chaitanya@gmail.com","Sairam123","ZARA COAT 3"},{"chaitanyaB@gmail.com","Sairam12","ADIDAS ORIGINAL"} };
//}
