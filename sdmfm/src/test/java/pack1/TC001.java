package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		
		driver.get("https://www.google.com/");
		System.out.println("The title is :"+driver.getTitle());
		System.out.println("The url is :"+driver.getCurrentUrl());
		System.out.println("The page source is :"+driver.getPageSource());
		WebElement search=driver.findElement(By.id("APjFqb"));
		search.sendKeys("Software testing tools");
		search.submit();
		
		
		driver.navigate().to("https://www.amazon.com/");
		System.out.println("The title is :"+driver.getTitle());
		driver.navigate().back();
		System.out.println("The title is :"+driver.getTitle());
		driver.navigate().forward();
		System.out.println("The title is :"+driver.getTitle());
		
		

	}

}
