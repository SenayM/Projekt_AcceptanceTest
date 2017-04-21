package Projekt_AcceptanceTest.Projekt_AcceptanceTest;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class App 
{
	private static WebDriver driver;
	public static Logger LOG;
	private static WebDriverWait wait;
	private static String baseURL="https://www.br.se";

	
    public static void main( String[] args )
    {
        
    	
    	//Declaring Static variables for the test case
			
		
			
			//Defining firefoxDriver and Logger
			driver= new FirefoxDriver();
			wait = new WebDriverWait(driver, 3);
	    	
			
		//The actual test
		
		//loading the page
			driver.get(baseURL);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( ".//*[@id='js-site-search-input']")));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[4]/div/div/ul/li[2]/div/a")));
			WebElement searchBar = driver.findElement(By.xpath( ".//*[@id='js-site-search-input']"));
	    	searchBar.sendKeys("ATLANTA");
	    	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
				
		
		
		//After class will be used to close the driver
	
			driver.close();
			driver.quit();
    	 
    	
    	
    	
    }
}
