
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

  public class BetalningsättTc {
       private static WebDriver driver;
       public static WebDriverWait wait;
       public static String baseUrl="https://www.br.se/";

  @BeforeClass
  public static void setupClassOnce()  {
       driver = new FirefoxDriver();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   }


  @Test
  public void testBetalning()  {
	    driver.get(baseUrl + "/"); 
	  //Click on login button
	    driver.findElement(By.cssSelector("div.clubbr__icon")).click();
	 // Write username
	    try
        {
	    driver.findElement(By.id("j_username")).sendKeys("mndana.rose@gmail.com");
	    System.out.println("---------Email exists --------------\n-----------------------");
     // Write password
	    driver.findElement(By.id("j_password")).sendKeys("koftkoft");
	    System.out.println("---------pass exists --------------\n-----------------------");
        }
	   catch(Throwable e)
         {
	  //  System.out.println("---------kontot hittates inte ----------\n------------------");
        }
	    
	 //Click on Submitt knappen
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
     // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //click on basket button
	    driver.findElement(By.cssSelector("div.basket.active > a > span.text")).click();
	    //Click on "Gå till kassan" button
	    driver.findElement(By.xpath("(//a[contains(text(),'Gå till kassan')])[2]")).click();
	    //Check out information
	    try {
	    driver.findElement(By.id("address.firstName")).clear();
	    driver.findElement(By.id("address.firstName")).sendKeys("Mandy");
	    driver.findElement(By.id("address.lastName")).clear();
	    driver.findElement(By.id("address.lastName")).sendKeys("Zadeh");
	    driver.findElement(By.id("address.line1")).clear();
	    driver.findElement(By.id("address.line1")).sendKeys("blom34");
	    driver.findElement(By.id("address.postcode")).click();
	    driver.findElement(By.id("address.postcode")).clear();
	    driver.findElement(By.id("address.postcode")).sendKeys("16960");
	    driver.findElement(By.id("address.phone")).clear();
	    driver.findElement(By.id("address.phone")).sendKeys("07656987678");
	    System.out.println("Faktureringsuppgifter information is accepted");
	    //Write information about delivery
	    driver.findElement(By.name("deliveryHomeAddress.firstName")).clear();
	    driver.findElement(By.name("deliveryHomeAddress.firstName")).sendKeys("Mandy");
	    driver.findElement(By.name("deliveryHomeAddress.lastName")).clear();
	    driver.findElement(By.name("deliveryHomeAddress.lastName")).sendKeys("zadeh");
	    driver.findElement(By.name("deliveryHomeAddress.line1")).clear();
	    driver.findElement(By.name("deliveryHomeAddress.line1")).sendKeys("blom34");
	    driver.findElement(By.name("deliveryHomeAddress.phone")).clear();
	    driver.findElement(By.name("deliveryHomeAddress.phone")).sendKeys("07656987678");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    driver.findElement(By.id("terms")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
 	    System.out.println(" Delivery information is accepted");
	    }
	    catch(Throwable e)
        {
	    System.out.println("information is not complete");
        }
	    
  }
	    
	  
  @After
  public void aftertest()
  {
  System.out.println("test is over");
  }


  @AfterClass
  public static void closeBrowser(){
  driver.close();
  try{
  Thread.sleep(3000);
  }catch(InterruptedException e){
  System.out.println(e.getStackTrace());
  }
  driver.quit();
  }
  
  }
 
