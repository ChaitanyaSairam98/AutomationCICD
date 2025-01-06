package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(id ="userEmail")
	WebElement username;
	
	@FindBy(id ="userPassword")
	WebElement userpassword;
	
	@FindBy(id ="login")
	WebElement submitButton;
	
	@FindBy (css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public  ProductCatalog loginApplication(String Email, String password) {
	
		username.sendKeys(Email);
		userpassword.sendKeys(password);
		submitButton.click();
		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
