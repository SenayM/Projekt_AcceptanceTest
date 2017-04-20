package Projekt_AcceptanceTest.Projekt_AcceptanceTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App 
{
	public static WebDriver driver;
	
    public static void main( String[] args )
    {
        
    	
    	driver =new FirefoxDriver();
		String result=null;
    	
		driver.get("http://the-internet.herokuapp.com/challenging_dom");
		WebElement table= driver.findElement(By.className("large-10"));
		
		
		WebElement tbody=table.findElement(By.tagName("tbody"));
		List<WebElement> tr=tbody.findElements(By.tagName("tr"));
		for(int i =0;i<tr.size();i++){
			List<WebElement> td=tr.get(i).findElements(By.tagName("td"));
			System.out.println("Row no "+ i +" Is : ");
			
				for (int j=0;j<td.size();j++){
					result=td.get(j).getText();
					result=result.replace("edit delete","") ;
				
				System.out.print(result + "  ");
				
				}
			System.out.print("\n");
			
			
			//result=tr.get(i).getText();
			//concatResult = result.replace("edit delete","") ;
        	//System.out.println("Row no "+i+" is "+  concatResult);
		}
		
	/*
	 List<WebElement> td=tr.get(i).findElements(By.tagName("tr"));
			for (int j=0;j<td.size();j++){
			//System.out.println("Row no "+i+" is"+	td.get(j).getText());
	*/
		driver.close();
			driver.quit();
    	 
    	
    	
    	
    }
}
