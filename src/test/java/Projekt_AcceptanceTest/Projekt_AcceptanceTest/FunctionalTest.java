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
			private static String productName= "";
			public static String productsOnShopingList="";
			
			
			
			//Before class will be executed before the actual test 
			@BeforeClass
			public static void setupOnce(){
				
				//Defining firefoxDriver and Logger
				driver= new FirefoxDriver();
				wait = new WebDriverWait(driver, 3);
				longwait=new WebDriverWait(driver,6);
		    	
				//LOG = Logger.getLogger(FunctionalTest.class.getName());
			}
			//The actual test for testCase TC002
			@Test 
			public void testAddProductsToCartTC002() throws InterruptedException{
				
				try {
				//loading the page
				driver.get(baseURL);
				 
				//Wait and click on the big banner picturelink
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/section/div/a/img")));
				WebElement bannerPic = driver.findElement(By.xpath(".//*[@id='content']/section/div/a/img"));
		    	bannerPic.click();
			
		    	//Click on product img
				
				longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[5]/div[2]/div/ul/li[1]/div/div/a/img")));
				WebElement productImg = driver.findElement(By.xpath(".//*[@id='content']/div[5]/div[2]/div/ul/li[1]/div/div/a/img"));
				//System.out.println(productImg.getText());
				productImg.click();
		    
		    	
				//Waiting for element to Click add to cart
				Thread.sleep(5000);
		    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/div[3]")));
		    	WebElement button = driver.findElement(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/div[3]"));
		    		
		    	
		    	//Clicking add to cart
		    	longwait.until(ExpectedConditions.elementToBeClickable(By.id("content_0_productwrapper_0_basketandwishlist_0_basketIcon")));
		    	WebElement addToCart = button.findElement(By.id("content_0_productwrapper_0_basketandwishlist_0_basketIcon"));
		    	
		    	addToCart.click();
		    	
		    	//Copying name of product to a vriable
		    	
		    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div")));
		    	WebElement productDetail = driver.findElement(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div"));
		    	WebElement productInfo = productDetail.findElement(By.tagName("h1"));
		    	productName=productInfo.getText();
		    	
		    	System.out.println("1st Product Name : "+ productName.toLowerCase());
		    
		       	Thread.sleep(3000);
		       	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='iconbar']/div[4]/a/div")));
				WebElement shopingCart = driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/div"));
		    	shopingCart.click();
				
				Thread.sleep(5000);
				//Check if item is added to the shopping list
				longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[2]/div/table/tbody")));
				WebElement itemOnShopingList = driver.findElement(By.xpath(".//*[@id='basket']/div[2]/div/table/tbody"));
		    	productsOnShopingList=itemOnShopingList.getText();
				//System.out.println(productsOnShopingList.toLowerCase());
				
				
				Assert.assertTrue("Checking if the product is Added to the list",productsOnShopingList.toLowerCase().contains (productName.toLowerCase()) );
				
				
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
					
			}
			//Test case TC003 Remove product form cart
			@Test
			public void testRemoveProductFromCartTC003() throws InterruptedException {
			try{
				
				//Removing product from list
				longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[2]/div/table/tbody/tr/td[4]/div[2]/ul/li/a")));
				driver.findElement(By.xpath(".//*[@id='basket']/div[2]/div/table/tbody/tr/td[4]/div[2]/ul/li/a")).click(); ;
		    	Thread.sleep(5000);
		    	
				//Check if item is added to the shoping list by compaing with the whole table content
				longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='basket']/div[2]/div/table")));
				WebElement itemOnShopingList = driver.findElement(By.xpath(".//*[@id='basket']/div[2]/div/table"));
		    	productsOnShopingList=itemOnShopingList.getText();
			System.out.println("productsOnShopingList : "+ productsOnShopingList);
			System.out.println("product Name : "+ productName);
				
				Assert.assertFalse("Check if the product is not in the list", productsOnShopingList.toLowerCase().contains (productName.toLowerCase()));
				
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			}

			//Test case TC004 Remove order for amount less than 1
			@Test
			public void testRemoveOrderForAmountlessThanOneTC004() throws InterruptedException{
				
				
			try {
				//Calling method to add product to cart
				testAddProductsToCartTC002();
				
				//Entering amoount value less than 1 eg 0
				//*[@id='basket']/div[2]/div/table/tbody/tr/td[2]/span[1]
				longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[2]/div/table/tbody/tr/td[2]/span[1]")));
				WebElement txtAntal= driver.findElement(By.xpath(".//*[@id='basket']/div[2]/div/table/tbody/tr/td[2]/span[1]"));;
				txtAntal.findElement(By.xpath("//*[@id='b_quantity_1']")).sendKeys(Keys.DOWN); 
				Thread.sleep(5000);
				
				
				//Check if item is added to the shoping list by compaing with the whole table content
				longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='basket']/div[2]/div/table")));
				WebElement itemOnShopingList = driver.findElement(By.xpath(".//*[@id='basket']/div[2]/div/table"));
		    	productsOnShopingList=itemOnShopingList.getText();
			System.out.println("productsOnShopingList : "+ productsOnShopingList);
			System.out.println("product Name : "+ productName);
				
				Assert.assertFalse("Check if the product is not in the list", productsOnShopingList.toLowerCase().contains (productName.toLowerCase()));
		
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
