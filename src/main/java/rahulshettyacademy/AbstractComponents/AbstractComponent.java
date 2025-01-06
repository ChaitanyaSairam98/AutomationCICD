package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement myOrders;
	
	
	By cartSection = By.cssSelector(".cartSection h3");
	
	public CartPage goToCart() {
		cart.click();
		waitForElementToAppear(cartSection);
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	public OrderPage goToOrderPage() {
		waitForWebElementToAppear(myOrders);
		myOrders.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	

	public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}

	public void waitForElementToDisappear(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf( ele));
	}
		
}

