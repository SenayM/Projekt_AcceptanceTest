package Projekt_AcceptanceTest.Projekt_AcceptanceTest;

import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//This test case will be used to test if the Web site will be loaded within 12 seconds
public class PerformanceTestSuit  {
	//Declaring Static variables for the test case
	private static WebDriver driver;
	public static Logger LOG;
	public static WebDriverWait wait;
	private static String baseURL="https://www.br.se";
	
	
	//Before class will be executed before the actual test 
	@BeforeClass
	public static void setupOnce(){
		
		//Defining firefoxDriver and Logger
		driver= new FirefoxDriver();
		LOG = Logger.getLogger(PerformanceTestSuit.class.getName());
		wait = new WebDriverWait(driver,12) ;
		
		
	}
	//The actual test
	@Test (timeout = 12000)
	public void testLoadTime_TC001(){
		
		//Declaring long variable to hold the start time in Milliseconds
		long start = System.currentTimeMillis();
		//actual test to load the page
		driver.get(baseURL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("frontpage-banner-img")));
		
		//System.out.println(result.getText());
		//Declaring long variable to have the finish time
		long finish = System.currentTimeMillis();
		
		long totalTime = finish - start; 
		
		//Printing Start and finish time in Milliseconds
		System.out.println("Total Time for page load Start  : "+start);
		System.out.println("Total Time for page load finish : "+finish);
		
		//Calculating elapsed time, and converting Milliseconds to seconds
		double inSecond= totalTime/1000.0;
				
		//Printing and Logging total time in Seconds
		System.out.println("Total Time for page load : "+inSecond+ "Seconds");
		LOG.info("Total Time for page load : "+inSecond+ " Seconds");
		//Actual test to Check if the page loads in less than 12 Seconds
		Assert.assertTrue("Check if load time is less than 10 sec ", inSecond<12);
		
	}
	
	//After class will be used to close the driver
	@AfterClass
	public static void closeBrowser(){
		driver.close();
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			System.out.println(e.getStackTrace());
		}
		driver.quit();
		
		
		
	}


}
