package Findusonweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;



public class GlobalHR 
{

	WebDriver driver;
	
	
	@BeforeTest 
	public void Setup()
	{
		//after "WebDriverManager" when write  dot (.), then will show multiple web browsers.
		// this will load automatically latest version, latest updated driver
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("incognito");
		
		driver = new ChromeDriver ();
		driver.get("http://www.mydevsystems.com/dev/gap_v2/index.php/login/login");
	}
	
	
	
	
	@Test
	public void test() throws InterruptedException
	{
	
	}
	
	
	@AfterTest
	public void quit() throws InterruptedException
	{
		Thread.sleep(2000);	
		driver.quit();		
	}	

	
}



