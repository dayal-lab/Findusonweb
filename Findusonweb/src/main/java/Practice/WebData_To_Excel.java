package Practice;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebData_To_Excel 
{
	WebDriver driver;
	
	
	@BeforeTest 
	public void Setup()
	{
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver (options);
		driver.get("https://www.worldometers.info/geography/alphabetical-list-of-countries/");
	}
	
	
	
	
	@Test
	public void test() throws InterruptedException, IOException
	{
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[contains(text(),'Got it!')]")).click();
		Thread.sleep(2000);
		
		//set path of excel file to write data into that
		String path = "e:\\Test.xlsx";

		//now create object of all method's "Demo" class.
		//Set "path" into class "Demo", which we have defined into that class's constructor "Demo (String path)", it will run those data.
		Demo obj = new Demo(path);
		
//***SETTING HEADERS INTO EXCEL- 		
		//setting header names in excel's header that is. setting at 1st row which is defined in defined site's rank, Country Region,...will add into excel's first row.
		//Here, it will call Demo's "setCellData" method.
		//in that method given, if sheet not exists then it will autmatically create new, but we have defined sheet name so it wont create new.
		//set sheetname , header name of website "Country" and it will set at 0 , 0 that in excel's 1st row and at 1st column.
		//Same will store web page's table's header into excel as per below-
		
		obj.setCellData("TestData", 0, 0, "Country");
		obj.setCellData("TestData", 0, 1, "Population");
		obj.setCellData("TestData", 0, 2, "Area");
		obj.setCellData("TestData", 0, 3, "Density");
		
		
		
//NOW CAPTURE ACTUAL DATA OF WEBPAGE TO STORE INTO EXCEL-
		
		//taking path of webpage's table
		WebElement table = driver.findElement(By.xpath("//body/div[3]/div[2]/div[1]/div[1]/div[2]"));
		
		
		//capture rows present in web table
		int rows =table.findElements(By.xpath("tr")).size();
		
		for (int r = 1 ; r<=rows ; r++)
		{
			String country = table.findElement(By.xpath("tr["+r+"]/td[1]")).getText();
			String population = table.findElement(By.xpath("tr["+r+"]/td[2]")).getText();
			String  area = table.findElement(By.xpath("tr["+r+"]/td[3]")).getText();
			String density = table.findElement(By.xpath("tr["+r+"]/td[4]")).getText();
		
		System.out.println(country+population+area+density);


		
//NOW WRITING ACTUAL DATA FROM WEB TO EXCEL SHEET -
		
		//writing data into excel sheet "TestData"
		obj.setCellData("TestData", r, 0, country);
		obj.setCellData("TestData", r, 1, population);
		obj.setCellData("TestData", r, 2, area);
		obj.setCellData("TestData", r, 3, density);
		
		}
		
		System.out.println("Added data successfully into excel sheet");
		
	}
	
	
	@AfterTest
	public void quit() throws InterruptedException
	{
		Thread.sleep(2000);	
		driver.quit();		
	}	

	
}


