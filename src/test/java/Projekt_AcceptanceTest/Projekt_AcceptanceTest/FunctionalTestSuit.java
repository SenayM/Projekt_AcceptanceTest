package Projekt_AcceptanceTest.Projekt_AcceptanceTest;



import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FunctionalTestSuit {

	//Declaring Static variables for the test case
			private static WebDriver driver;
			public static Logger LOG;
			private static WebDriverWait wait;
			private static WebDriverWait longwait;
			private static WebDriverWait veryLongWait;
			private static String baseURL="https://www.br.se";
			private static String productName= "";
			public static String productsOnShopingList="";
			public static String productsOnWishList="";
			public static FileHandler fileHandler;
			public String keyword="atlanta";
		
			
			//Before class will be executed before the actual test 
			@BeforeClass
			public static void setupOnce(){
				
				//Defining firefoxDriver and Logger
				//driver= new FirefoxDriver();
				driver=new ChromeDriver();
				
				wait = new WebDriverWait(driver, 3);
				longwait=new WebDriverWait(driver,6);
				veryLongWait= new WebDriverWait (driver,9);
		    	LOG = Logger.getLogger(FunctionalTestSuit.class.getName());
		    	
		    	try {
					fileHandler= new FileHandler("functionalTestlog.log",true );
				} catch (Exception e) {
					e.printStackTrace();
				} 
				LOG.addHandler(fileHandler);
				fileHandler.setFormatter(new SimpleFormatter());
				
		    	
			}
			
			
			
			//The actual test for testCase TC002
			@Test 
			public void testAddProductsToCartTC002() throws InterruptedException{
				
				try {
				//loading the page
				driver.get(baseURL);
				 
				navigateAndSelectProduct();
		    
		    	
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
				LOG.info(productsOnShopingList.toLowerCase().contains (productName.toLowerCase()) ? "Test TC002 PASS" : "Test TC002 FAIL");
		
				
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
			//System.out.println("productsOnShopingList : "+ productsOnShopingList);
			System.out.println("product Name : "+ productName);
				
				Assert.assertFalse("Check if the product is not in the list", productsOnShopingList.toLowerCase().contains (productName.toLowerCase()));
				LOG.info(productsOnShopingList.toLowerCase().contains (productName.toLowerCase()) ? "Test TC003 FAIL" : "Test TC003 PASS");
		
			
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
			//System.out.println("productsOnShopingList : "+ productsOnShopingList);
			System.out.println("product Name : "+ productName);
				
				Assert.assertFalse("Check if the product is not in the list", productsOnShopingList.toLowerCase().contains (productName.toLowerCase()));
		LOG.info(productsOnShopingList.toLowerCase().contains (productName.toLowerCase()) ? "Test TC004 FAIL" : "Test TC004 PASS");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			}
			
		
			
			//Test Add products to wish list for a registered customers
			@Test
			public void addProductToWishlistTC005() throws InterruptedException{
				
				driver.get(baseURL);
					//Calling login function
					login();
					//navigate and select a product to add to cart or add to wish list
					
					navigateAndSelectProduct();
					
					//Copying name of product to a variable
			    	
			    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div")));
			    	WebElement productDetail = driver.findElement(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div"));
			    	WebElement productInfo = productDetail.findElement(By.tagName("h1"));
			    	productName=productInfo.getText();
			    	
			    	//System.out.println("1st Product Name : "+ productName.toLowerCase());
			    
			       	Thread.sleep(5000);
			    
					
					//Adding product to wishlist
					
			    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/div[3]/a[2]")));
			    	WebElement addToWishList = driver.findElement(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/div[3]/a[2]"));
			    	addToWishList.click();
			    	
			     	
			    	//Choosing wishlist to add
			    	Thread.sleep(2000);
			    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='wishlist-overlay']/div[1]/div[3]/div/div/div[2]/span")));
			    	WebElement chooseWishList = driver.findElement(By.xpath(".//*[@id='wishlist-overlay']/div[1]/div[3]/div/div/div[2]/span"));
			    	chooseWishList.click();
			    	
			    	  
			    	//navigate to wish list
			    	longwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iconbar")));
			    	WebElement wishList = driver.findElement(By.id("iconbar"));
			    	WebElement wishListDiv= wishList.findElement(By.className("wishlist")); ;
			    	Thread.sleep(2000);
			    	wishListDiv.click();
			    	
			    	WebElement wishListDivDiv=wishListDiv.findElement(By.tagName("span"));
			    	
			    	wishListDivDiv.click();
			    	Thread.sleep(3000);
			    	
			    	
			    	//navigate to Mylist
			    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mini-wishlist']/div[2]/div/div/div[2]/span")));
			    	WebElement myList = driver.findElement(By.xpath(".//*[@id='mini-wishlist']/div[2]/div/div/div[2]/span"));
			    	System.out.println("mini wishlist : "+myList.getText());
			    	myList.click();
			    
			    	//Check product on the wishlist
			    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='mini-wishlist']/div[2]/div/section/div")));
			    	WebElement myListItems = driver.findElement(By.xpath(".//*[@id='mini-wishlist']/div[2]/div/section/div"));
			    	productsOnWishList=myListItems.getText();
			    	//System.out.println("mini wishlist : "+myListItems.getText());
			    	
			    	Assert.assertTrue("Checking if the product is Added to the list",productsOnWishList.toLowerCase().contains (productName.toLowerCase()) );
					LOG.info(productsOnWishList.toLowerCase().contains (productName.toLowerCase()) ? "Test TC005 PASS" : "Test TC005 FAIL");
				
			    	driver.navigate().to(baseURL+"/logout");
			    	
			    	try{
			    	Thread.sleep(5000);
			    
			    	
					
				}catch (Exception e){
					System.out.println(e.getStackTrace());
				}
			}
			public void navigateAndSelectProduct(){
				
					driver.get(baseURL);
				//Wait and click on the big banner picturelink
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/section/div/a/img")));
				WebElement bannerPic = driver.findElement(By.xpath(".//*[@id='content']/section/div/a/img"));
		    	bannerPic.click();
			
		    	//Click on product img
				
				longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[5]/div[2]/div/ul/li[2]/div/div/a/img")));
				WebElement productImg = driver.findElement(By.xpath(".//*[@id='content']/div[5]/div[2]/div/ul/li[2]/div/div/a/img"));
				//System.out.println(productImg.getText());
				productImg.click();
		    
		    	
				
		    
		    	
				
		    	
			}
			
			public void login(){
				try{
					//loading the page and clicking on login
					driver.get(baseURL);
					longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='iconbar']/div[1]/a[1]/div")));
			    	WebElement loginIcon = driver.findElement(By.xpath(".//*[@id='iconbar']/div[1]/a[1]/div"));
			    	loginIcon.click();
			    		
			    	//Typing username and password
			    	Thread.sleep(3000);
			    	veryLongWait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='j_username']")));
			    	WebElement txtUser = driver.findElement(By.xpath(".//*[@id='j_username']"));
			    	
			    	WebElement txtPass = driver.findElement(By.xpath(".//*[@id='j_password']"));
			    	longwait.until(ExpectedConditions.elementToBeClickable(txtPass));
			    	
			    	txtUser.sendKeys("birr5@yahoo.com");
			    	txtPass.sendKeys("test101");
			    	Thread.sleep(2000);
			    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='loginForm']/div[3]/button")));
			    	WebElement btnLogin = driver.findElement(By.xpath(".//*[@id='loginForm']/div[3]/button"));
			    	btnLogin.click();
			    	Thread.sleep(12000);
			    	
					
				}catch (InterruptedException e){
					System.out.println(e.getStackTrace());
				}
				
			}
			
			
			
			//Check for availability of contact information/email TC006
			@Test
			public void checkAvailabilityOfContactEmailTC006() throws InterruptedException{
				driver.get(baseURL);
				//Click on kundservice
				WebElement kundservice = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[6]/a"));
		    	longwait.until(ExpectedConditions.elementToBeClickable(kundservice));
				kundservice.click();
				Thread.sleep(3000);
				
				//Copy content of Contact Information to a string variable for future assertion
				WebElement contactInfo = driver.findElement(By.xpath(".//*[@id='content']/div[4]"));
		    	longwait.until(ExpectedConditions.elementToBeClickable(contactInfo));
				//System.out.println("contactKundService :" + contactInfo.getText());
				//LOG.info("contactKundService :" + contactInfo.getText());
				
				Assert.assertTrue("Check if contact email is provided", contactInfo.getText().toLowerCase().contains("kundservice@br-leksaker.se"));
				LOG.info(contactInfo.getText().toLowerCase().contains("kundservice@br-leksaker.se") ? "Test TC006 PASS" : "Test TC006 FAIL");
				
				
			}
			
			//Test Case TC008 VerifySearchBox
			@Test
			public void testverifySearchboxTC08() throws InterruptedException{
				driver.get(baseURL);
				
				//Type on search box
				WebElement searchBox = driver.findElement(By.xpath(".//*[@id='js-site-search-input']"));
		    	wait.until(ExpectedConditions.elementToBeClickable(searchBox));
		    	searchBox.clear();
		    	searchBox.sendKeys(keyword);
		    	searchBox.sendKeys(Keys.ENTER);
		    	Thread.sleep(5000);
		    	
		    	
		    	//Click on the search result
		    	WebElement searchResult = driver.findElement(By.xpath(".//*[@id='result1-products']/div[2]/div/ul/li[1]/div/div/a/img"));
		    	wait.until(ExpectedConditions.elementToBeClickable(searchResult));
		    	searchResult.click();
		    	Thread.sleep(3000);
		    	
		    	//Copy the product name to a string variable
		    	WebElement productOnSearchResult = driver.findElement(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/h1"));
		    	wait.until(ExpectedConditions.elementToBeClickable(productOnSearchResult));
		    	System.out.println("Product on product search result"+productOnSearchResult.getText());
		    	Thread.sleep(3000);
		    	
		    	//Actual test to Check if the page loads in less than 12 Seconds
		    			Assert.assertTrue("Check if the result maches the search keyword ", productOnSearchResult.getText().toLowerCase().contains(keyword));
		    			LOG.info(productOnSearchResult.getText().toLowerCase().contains(keyword) ? "Test Case TC008 PASS": "Test Case TC008 FAIL");
		    		
			   	
			}
			
			
			
			//Test Hitta ButikTC10
			@Test
			public void testHittaButikTC10() throws InterruptedException{
				String addressDenmark="Copenhagen";
				String addressSweden="Sveavägen 44";
				driver.get(baseURL+"/hitta-butik");
				Thread.sleep(3000);
			
		//Check address in Sweden		
		    	//Type on searchBox
		    	WebElement hittaSearchBoxs  = driver.findElement(By.xpath(".//*[@id='address-search']  "));
		    	wait.until(ExpectedConditions.elementToBeClickable(hittaSearchBoxs));
		    	hittaSearchBoxs.sendKeys(addressSweden);
		    	hittaSearchBoxs.sendKeys(Keys.ENTER);
		    	Thread.sleep(4000);
		    	
		    	//Check search results
		    	WebElement hittaSearchResultss  = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/h1"));
		    	wait.until(ExpectedConditions.elementToBeClickable(hittaSearchResultss));
		    	System.out.println("Hitta Search Result " + hittaSearchResultss.getText());
		    	
		    	
		//Check address in Denmark		
		    	//Type on searchBox
		    	WebElement hittaSearchBox  = driver.findElement(By.xpath(".//*[@id='address-search']  "));
		    	wait.until(ExpectedConditions.elementToBeClickable(hittaSearchBox));
		    	hittaSearchBox.clear();
		    	hittaSearchBox.sendKeys(addressDenmark);
		    	hittaSearchBox.sendKeys(Keys.ENTER);
		    	Thread.sleep(4000);
		    	
		    	//Check search results
		    	WebElement hittaSearchResults  = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[4]/div[1]/div/div[2]"));
		    	wait.until(ExpectedConditions.elementToBeClickable(hittaSearchResults));
		    	//System.out.println("Hitta Search Result " + hittaSearchResults.getText());
		    	
		    	LOG.info(hittaSearchResults.getText().toLowerCase().contains("24642") ? "Test Case TC010_1 PASS": "Test Case TC010_1 FAIL");
		    	Assert.assertTrue("Check Address In denmark ",hittaSearchResults.getText().toLowerCase().contains("24642"));
				
				LOG.info(hittaSearchResultss.getText().toLowerCase().contains("24642") ? "Test Case TC010_2 PASS": "Test Case TC010_2 FAIL");
				Assert.assertTrue("Check Address in Sweden ",hittaSearchResultss.getText().toLowerCase().contains("11134"));
				
		    	}
			
			//Test Payment options
			@Test
			public void testPaymentOptionsTC009() throws InterruptedException{
			
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
		    	
		    	txtUser.sendKeys("birr5@yahoo.com");
		    	txtPass.sendKeys("test101");
		    	Thread.sleep(2000);
		    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='loginForm']/div[3]/button")));
		    	WebElement btnLogin = driver.findElement(By.xpath(".//*[@id='loginForm']/div[3]/button"));
		    	btnLogin.click();
		    	Thread.sleep(8000);
				
		    	
		    	//Type on search box
				WebElement searchBox = driver.findElement(By.xpath(".//*[@id='js-site-search-input']"));
		    	wait.until(ExpectedConditions.elementToBeClickable(searchBox));
		    	searchBox.clear();
		    	searchBox.sendKeys("atlanta");
		    	searchBox.sendKeys(Keys.ENTER);
		    	Thread.sleep(5000);
		    	
		    	
		    	//Click on the search result
		    	WebElement searchResult = driver.findElement(By.xpath(".//*[@id='result1-products']/div[2]/div/ul/li[1]/div/div/a/img"));
		    	wait.until(ExpectedConditions.elementToBeClickable(searchResult));
		    	searchResult.click();
		    	Thread.sleep(3000);
				
		    

				//Waiting for element to Click add to cart
				Thread.sleep(5000);
		    	longwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/div[3]")));
		    	WebElement button = driver.findElement(By.xpath(".//*[@id='product-details-wrapper']/div[1]/div/div[3]"));
		    		
		    	
		    	//Clicking add to cart
		    	longwait.until(ExpectedConditions.elementToBeClickable(By.id("content_0_productwrapper_0_basketandwishlist_0_basketIcon")));
		    	WebElement addToCart = button.findElement(By.id("content_0_productwrapper_0_basketandwishlist_0_basketIcon"));
		    	
		    	addToCart.click();
		    
		    	driver.navigate().to(baseURL+"/cart");
		    	Thread.sleep(4000);
		    	
		    	
		    	//Click on Gå till kassan
		    	WebElement goTillKassan = driver.findElement(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a"));
		    	wait.until(ExpectedConditions.elementToBeClickable(goTillKassan));
		    	goTillKassan.click();
		    	Thread.sleep(3000);
			
		    	//*[@id='yourInformationForm']/div[2]
		    	//Copy payment informations from the available payment options
		    	WebElement paymentMethods = driver.findElement(By.xpath(".//*[@id='yourInformationForm']/div[2]"));
		    	wait.until(ExpectedConditions.elementToBeClickable(paymentMethods));
		    	String payMethods=paymentMethods.getText().toLowerCase();
		    	System.out.println("Payment methods : "+payMethods);
		    	Thread.sleep(3000);
			
		    	Assert.assertTrue("Checking if the Faktura betaling is available",payMethods.contains("faktura") );
				LOG.info(payMethods.contains("faktura") ? "TC009_1 Faktura Betalning Available PASS" : "Tc009 Faktura Betalning not available FAIL");
				
				Assert.assertTrue("Checking if the Card Payment is available",payMethods.contains("betalkort") );
				LOG.info(payMethods.contains("betalkort") ? "TC009_2 Card Payment is Available PASS" : "Tc009 Faktura Card Payment not available FAIL");

				Assert.assertTrue("Checking if BankTransfer is available",payMethods.contains("banköverföring") );
				LOG.info(payMethods.contains("banköverföring") ? "TC009_3 BankTransfer Available PASS" : "Tc009 BankTransfer not available FAIL");

				//Logout account not to affect the other testcases
				driver.get("https://br.se/logout");
				Thread.sleep(3000);
		    	
			}
			//After class will be used to close the driver
			@AfterClass
			public static void closeBrowser(){
				driver.close();
				driver.quit();
	}

}
