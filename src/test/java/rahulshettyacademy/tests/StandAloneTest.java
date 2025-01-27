package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
                // Comment 
		String productName ="ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("chaitanya@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sairam123");
		driver.findElement(By.id("login")).click();
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		 Assert.assertTrue(match);
		 System.out.println(match);
		 
		 driver.findElement(By.cssSelector(".totalRow button")).click();
		 
		 Actions a= new Actions(driver);
		 a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		 //driver.findElement(By.xpath(("//button[contains(@class, 'ta-item')])[2]"))).click();
		 driver.findElement(By.cssSelector(".ta-item:last-child")).click();
		 driver.findElement(By.cssSelector(".btnn")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		Boolean match1 = driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order.");
		
		Assert.assertTrue(match1);
		System.out.println(match1);
		 
		 
		
		

	  }
	         
	}
