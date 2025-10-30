package pack1;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC005_Windowhandling {
		public static void main(String[] args) {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			
			driver.get("https://www.letcode.in/window");
			System.out.println("parent window:"+ driver.getWindowHandle());
			driver.findElement(By.id("multi")).click(); 
			
			//Set<String> windows = driver.getWindowHandles();
			    
			
		}

}
