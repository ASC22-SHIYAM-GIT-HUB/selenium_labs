package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab4 {
	public static void main(String[] arg) {
		WebDriverManager.chromedriver().setup();
		WebDriver drive = new ChromeDriver();
		drive.get("https://tutorialsninja.com/demo/index.php?");
		drive.manage().window().maximize();
		String title = drive.getTitle();
		if(title.equals("Your Store"))
		{
			System.out.println("Title is Matched");
		}
		
		else
		{
			System.out.println("Title is not Matched");
		}
		
		drive.findElement(By.linkText("My Account")).click();
		drive.findElement(By.linkText("Register")).click();
		drive.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]")).click();
		
		
		String warningText = drive.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
		System.out.println(warningText);
		if (warningText.contains("Warning: You must agree to the Privacy Policy!")) {
		    System.out.println("Warning message verified.");
		}
		
		drive.findElement(By.id("input-firstname")).sendKeys("John");
		// To test 33 characters:
		String longFirstName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"; // 33 A's
		//drive.findElement(By.id("input-firstname")).clear();
		drive.findElement(By.id("input-firstname")).sendKeys(longFirstName);
		drive.findElement(By.xpath("//input[@value='Continue']")).click();
		// Capture error message if any, e.g. 
		// drive.findElement(By.cssSelector("selector_for_error")).getText()
		
		/*drive.findElement(By.id("input-address-1")).sendKeys("123 Main St");
		drive.findElement(By.id("input-city")).sendKeys("Mumbai");
		drive.findElement(By.id("input-postcode")).sendKeys("400001");

		Select country = new Select(drive.findElement(By.id("input-country")));
		country.selectByVisibleText("India");

		Select region = new Select(drive.findElement(By.id("input-zone")));
		region.selectByIndex(1);*/




			
	}

}
