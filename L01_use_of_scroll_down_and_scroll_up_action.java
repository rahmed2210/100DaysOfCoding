package seleniumMethods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class L01_use_of_scroll_down_and_scroll_up_action {
	WebDriver driver;
	JavascriptExecutor js;

	@BeforeTest
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\rubal\\eclipse-workspace\\SeleniumMethods\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("https://www.mountsinai.org/"); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		
	}
	
	// 1st way: Scroll by Actions class
	@Test (enabled = true, priority = 1)
	public void scrollDownAndScrollUpTest01() throws InterruptedException {
		Actions actions = new Actions(driver);
		// for Scroll Down using Actions class
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(5000);
		// for Scroll Up using Actions class
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
		Thread.sleep(5000);
		//Keys.UP or Keys.Down doesn't change much but the test case passes
	}
	
	// 2nd way: Scroll by JavascriptExecutor
	@Test(enabled = true, priority = 2)
	public void scrollingAmazonSiteTest() throws InterruptedException{
		Thread.sleep(2000);
		driver.navigate().to("https://www.amazon.com/");
		// This will scroll down the page by 3000 pixel vertical
		Thread.sleep(4000); // used to see the scroll
		js.executeScript("window.scrollBy(0,5000)", ""); // scroll down
		// This will scroll up the page by 3000 pixel vertical
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,-3000)", ""); // scroll-up
		Thread.sleep(4000);
		driver.navigate().back(); // Back to Mountsinai
		Thread.sleep(4000);
		// not related with this test
		// How to refresh, getTitle by Javascript, 
		js.executeScript("history.go(0)"); // To do refresh by Javascript
		String sText = js.executeScript("return document.title;").toString(); // fetching page title by javascript
		System.out.println(sText);
	}
	
	//3rd way: Scroll by Robot class
	@Test(enabled = true, priority = 3)
	public void scrollingGoogleSiteTest() throws InterruptedException, AWTException{
		Thread.sleep(2000);
		driver.navigate().to("https://www.ebay.com/");
		Thread.sleep(5000);
		
		Robot robot = new Robot();
        // Scroll Down using Robot class
        robot.keyPress(KeyEvent.VK_PAGE_DOWN); // Constant for the PAGE_DOWN virtual key. 
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
        Thread.sleep(5000);
        
        // Scroll Up using Robot class
        robot.keyPress(KeyEvent.VK_PAGE_UP); // Constant for the PAGE_UP virtual key. 
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
        Thread.sleep(5000);
        driver.navigate().back();        
	}

	@AfterTest
	public void tearUp() {
		driver.quit();
	}
	

}
