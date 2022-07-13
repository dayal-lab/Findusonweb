package Findusonweb;


import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PageNotFound_By_Sir 
{
	//https://findusonweb.com/
	
	public static String url = "https://findusonweb.co.uk/view_companies";
	public static WebDriver driver;
	public static boolean validResponse = false;
	
	@BeforeMethod
	static void setup() {
		try {
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(url);
			
			}
			catch(Exception e) 
			{
				e.printStackTrace();
            	System.out.println(e.getMessage());
            	System.out.println(e.getStackTrace());
			}
	}
	
	static void findImgerror(){
		try{
		//get list of elements with anchor tag
	      List<WebElement> l = driver.findElements(By.tagName("img"));  //<====
	      //iterate links
	      for(int j=0; j<l.size(); j++) 
	      {
	         WebElement e = l.get(j);
	         //get URL of links with getAttribute()
	         String u = e.getAttribute("src");
	         
	         if(u==null)
	         	{
	        	 	String img = e.getAttribute("class");
	        	 	System.out.print("Get url: " + img);
	         	}
	         else
	         	{
		         	System.out.println("Image Url: "+u);
	         	
	         	}	
	         }
		}
	         catch (Exception e) 
			      {
			         e.printStackTrace();
			         System.out.println("error: " + e.getMessage());
			      }
	      
	   }  
	
	static  void find404error() throws MalformedURLException{
		
		//get list of elements with anchor tag
	      List<WebElement> l = driver.findElements(By.tagName("a"));
	      //repeat links
	      for(int j=0; j<l.size(); j++) {
	         WebElement e = l.get(j);
	         //get URL of links with getAttribute()
	         String u = e.getAttribute("href");
	         	//String href = "href";
				if(u == null){

	        	 String cls = e.getAttribute("class");
	        	 String id = e.getAttribute("id");
	        	 String title = e.getAttribute("title");
	        	 String datatype = e.getAttribute("data-toggle");
	        	 System.out.println("href is null: "+" className:"+cls+" title:"+title+" Id:"+id+" value:"+datatype);
	         }
	         else{
	        	 
	        	 try{
	        	 URL link = new URL(u);
	        	 HttpURLConnection c = (HttpURLConnection)link.openConnection();
	             //   c.setConnectTimeout(1000);
		            // connection began
		            c.connect();
		            //getResponseCode() to obtain response code
		            if(c.getResponseCode()== 401) {
		               System.out.println(u+" − "+" status_msg:"+ c.getResponseMessage() +" status_code:"+c.getResponseCode());
		            }
	            if(c.getResponseCode()== 200) {
		               System.out.println(u+" − "+" status_msg:"+ c.getResponseMessage() +" status_code:"+c.getResponseCode());
		            }	
		            if(c.getResponseCode()== 403) {
			               System.out.println(u+" − "+" status_msg: "+ c.getResponseMessage() +" status_code:"+c.getResponseCode());
			            }
	            
		         //   System.out.println("Print the url: "+u+" Status Msg: "+ c.getResponseMessage()+" Status Code: "+c.getResponseCode());
	        	 }  catch(Exception ex){
	        		 System.out.println(ex.getMessage());
	        		 ex.printStackTrace();
	        		 System.out.println(ex.getLocalizedMessage());
	        		 System.out.println(ex.getClass());
	        	 }
		      //      Thread.sleep(500);
	         
	         }
	         
	   }
}
	
	@AfterMethod
	public void closeApplication() throws InterruptedException
		{
			 Thread.sleep(5000);
		     driver.quit();
		} 

}
