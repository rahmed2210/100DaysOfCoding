package seleniumMethods;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class L05_02_use_of_slider_action {
	WebDriver driver;

	@BeforeTest
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\rubal\\eclipse-workspace\\SeleniumMethods\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("https://demoqa.com/slider");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));	
	}
	
	
	@Test (enabled = false, priority = 1)
	public void sliderActionTest() throws InterruptedException {
		WebElement slider = driver.findElement(By.xpath("//input[@class='range-slider range-slider--primary']"));
		Actions actions = new Actions(driver);
		Thread.sleep(5000);
        // dragAndDrop (int xoffset, int yoffset)
		actions.dragAndDropBy(slider, 70, 70).build().perform();
		Thread.sleep(5000);
	}
	
	
	@Test (enabled = true, priority = 1)
	public void sliderActionTest2() throws InterruptedException {
		WebElement slider = driver.findElement(By.xpath("//input[@class='range-slider range-slider--primary']"));
		Actions actions = new Actions(driver);
		Thread.sleep(5000);
		actions.clickAndHold(slider);
		actions.moveByOffset(65, 0).build().perform();
		Thread.sleep(5000);	
	}
	
	
	@AfterTest
	public void tearUp() {
		driver.quit();
	}
}
