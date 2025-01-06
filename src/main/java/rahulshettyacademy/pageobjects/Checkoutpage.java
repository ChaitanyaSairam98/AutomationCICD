package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class Checkoutpage extends AbstractComponent {
	WebDriver driver;

	public Checkoutpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:last-child")
	WebElement countryEle;
	
	@FindBy(css=".btnn")
	WebElement submitButton;
	
	By popup = By.cssSelector(".ta-results");
	

	public void selectCountry(String countryName) {
		 Actions a= new Actions(driver);
		 a.sendKeys(country, countryName).build().perform();
		 waitForElementToAppear(popup);
		 countryEle.click();
		 	 
	}
	
	public ConfirmationPage submitOrder() {
		
		submitButton.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
		
	}
	
	
	

}
