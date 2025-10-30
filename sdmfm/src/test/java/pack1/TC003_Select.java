package pack1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC003_Select {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get(" https://tutorialsninja.com/demo/index.php?");
		driver.findElement(By.linkText("Desktops")).click();
		driver.findElement(By.linkText("Mac (1)")).click();
		
		WebElement sorted = driver.findElement(By.id("input-sort"));
		
		Select sle =new Select(sorted);
		List<WebElement> alloptions =sle.getOptions();
		
		for(WebElement option : alloptions)
		{
			System.out.println("sort by :"+ option.getText());
		}
		
		
	    WebElement show = driver.findElement(By.id("input-limit"));
	    Select sle1 = new Select(show);
	    List<WebElement> showalloptions = sle1.getOptions();
	    
	    for(WebElement option1: showalloptions)
	    {
	    	System.out.println("show options :"+option1.getText());
	    }
		
		
		
		
	}

}
