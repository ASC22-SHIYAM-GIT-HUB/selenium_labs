package pack1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC002_FindElements {
	public static void main(String[] arg) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		List<WebElement> amazonlinks=driver.findElements(By.tagName("a"));
		System.out.println("Total Size :" + amazonlinks.size());
		
		for(WebElement links : amazonlinks) {
			System.out.println("Links are : " + links.getText());
			System.out.println("The particular links are : "+ links.getAttribute("href"));
			
			// System.out.println("Total Size :" + amazonlinks.size());
		}
		
		driver.quit();
		
		
				
	}

}
