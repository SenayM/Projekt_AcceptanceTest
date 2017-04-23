package Projekt_AcceptanceTest.Projekt_AcceptanceTest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.grid.web.servlet.handler.SeleniumBasedRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FunctionalTest {

	//Declaring Static variables for the test case
			private static WebDriver driver;
			public static Logger LOG;
			private static WebDriverWait wait;
			private static WebDriverWait longwait;
			private static String baseURL="https://www.br.se";
			
			
			//Before class will be executed before the actual test 
			@BeforeClass
			public static void setupOnce(){
				
				//Defining firefoxDriver and Logger
				driver= new FirefoxDriver();
				wait = new WebDriverWait(driver, 3);
				longwait=new WebDriverWait(driver,6);
		    	
				//LOG = Logger.getLogger(FunctionalTest.class.getName());
			}
			//The actual test
			@Test 
			public void testLoadTime() throws InterruptedException{
				
				//loading the page
				driver.get(baseURL);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[4]/div/div/ul/li[2]/div/a")));
				WebElement product = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div/div/ul/li[2]/div/a"));
		    	product.click();
				
				
				longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[4]/div/div/ul/li[2]/div/div/a/img")));
				WebElement product1 = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div/div/ul/li[2]/div/div/a/img"));
		    	product1.click();
	    	
		    	Thread.sleep(3000);
		    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/div[3]")));
		    	WebElement button = driver.findElement(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/div[3]"));
		    	
		    	
		    	longwait.until(ExpectedConditions.elementToBeClickable(By.tagName("a")));
		    	WebElement buttons = button.findElement(By.tagName("a"));
		    	
		    	System.out.println(buttons.getText());
		    		
		    	buttons.click();
		    	
		    	//click cart
		    	
	    	
	    	
		    	
				Thread.sleep(3000);
		    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iconbar")));
				WebElement chart=driver.findElement(By.id("iconbar"));
				
		    	Thread.sleep(3000);
		       	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='iconbar']/div[4]/a/div")));
				WebElement shopingCart = driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/div"));
		    	shopingCart.click();
				
				Thread.sleep(10000);
				
				System.out.println(chart.getText());
		    	try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
					
			}
			
			//After class will be used to close the driver
			@AfterClass
			public static void closeBrowser(){
				driver.close();
				driver.quit();
	}

}
