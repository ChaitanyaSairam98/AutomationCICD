package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
            WebDriver driver;
	
	  public ProductCatalog(WebDriver driver) {
		      super(driver);
		      this.driver = driver;
		    PageFactory.initElements(driver, this);
		 }
	  
	  @FindBy(css=".mb-3")
	  List<WebElement> products;
	  
	  @FindBy(css=".ng-animating")
	  WebElement spinner;
	
	  
	  By productsBy = By.cssSelector(".mb-3");
	  By addtoCart = By.cssSelector(".card-body button:last-of-type");
	  By toastMessage = By.cssSelector("#toast-container");
	  
	  public List<WebElement> getProductList() {
		  //waitForElementToDisappear();
		  waitForElementToAppear(productsBy);
		  return products;
		  
	  }
	  
	  public WebElement getEachProductName(String productName) {
		  WebElement prod = getProductList().stream().filter(product->
		  product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);	
		  return prod;
			
	  }
	  
	  public void addProductToCart(String productName) {
		  WebElement prod = getEachProductName(productName);
		  prod.findElement(addtoCart).click(); 
		  waitForElementToAppear(toastMessage);
		  waitForElementToDisappear(spinner);
	  }
	
	

}
