package Projekt_AcceptanceTest.Projekt_AcceptanceTest;

import static org.junit.Assert.*;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

public class SecurityTestSuit {
	//Declaring Static variables for the test case
		private static WebDriver driver;
		public static Logger LOG;
		public static WebDriverWait wait;
		private static String baseURL="https://www.br.se";
		private static WebDriverWait longwait;
		public static FileHandler fileHandler;
		public static String myEmail="birr5@yahoo.com";
		
		
		//Before class will be executed before the actual test 
		@BeforeClass
		public static void setupOnce(){
			
			//Defining firefoxDriver and Logger
			driver= new FirefoxDriver();
			LOG = Logger.getLogger(PerformanceTestSuit.class.getName());
			wait = new WebDriverWait(driver,3) ;
			longwait = new WebDriverWait(driver,12) ;
			
			try {
				fileHandler= new FileHandler("securityTestlog.log",true);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			LOG.addHandler(fileHandler);
			fileHandler.setFormatter(new SimpleFormatter());
			
		}
		
		// Test Case TC007
		@Test
		public void testLoginTC007(){
		try{
			//loading the page and clicking on login
			driver.get(baseURL);
			longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='iconbar']/div[1]/a[1]/div")));
	    	WebElement loginIcon = driver.findElement(By.xpath(".//*[@id='iconbar']/div[1]/a[1]/div"));
	    	loginIcon.click();
	    		
	    	//Typing username and password
	    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='j_username']")));
	    	WebElement txtUser = driver.findElement(By.xpath(".//*[@id='j_username']"));
	    	
	    	WebElement txtPass = driver.findElement(By.xpath(".//*[@id='j_password']"));
	    	longwait.until(ExpectedConditions.elementToBeClickable(txtPass));
	    	
	    	txtUser.sendKeys(myEmail);
	    	txtPass.sendKeys("test101");
	    	Thread.sleep(2000);
	    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='loginForm']/div[3]/button")));
	    	WebElement btnLogin = driver.findElement(By.xpath(".//*[@id='loginForm']/div[3]/button"));
	    	btnLogin.click();
	    	Thread.sleep(8000);
	    	
	    	//verifying login
	    	WebElement logedinEmail = driver.findElement(By.xpath(".//*[@id='content']/div/table/tbody/tr[3]"));
	    	longwait.until(ExpectedConditions.elementToBeClickable(logedinEmail));
	    	System.out.println("Loged in with email : "+ logedinEmail.getText());
	    	LOG.info("Loged in with email : "+ logedinEmail.getText());
	    	
	    	//Actual test to Check if the page loads in less than 12 Seconds
			Assert.assertTrue("Check if the user is loged in ", logedinEmail.getText().toLowerCase().contains(myEmail));
			LOG.info(logedinEmail.getText().toLowerCase().contains(myEmail) ? "Test Case TC007 PASS": "Test Case TC007 FAIL");
		
	    	
		}catch (InterruptedException e){
			System.out.println(e.getStackTrace());
		}
		
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
