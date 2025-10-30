package pack1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
public class tutorial_pageObject {
	WebDriver driver;
	By myAccount=By.linkText("My Account");
	
	public tutorial_pageObject(WebDriver driver2) {
		this.driver=driver2;
	}
	
	
	public void clickOnMyAccount() {
		driver.findElement(myAccount).click();
		
	}
 
}
 