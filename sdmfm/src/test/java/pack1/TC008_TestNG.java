package pack1;
 
import org.testng.annotations.Test;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
 
public class TC008_TestNG {
	WebDriver driver;
  @Test(dataProvider = "logindata")
  public void f(String uname, String pword) {
	  
	  System.out.println("This is the Test");
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  	
		driver.findElement(By.name("username")).sendKeys(uname);
		
		driver.findElement(By.name("password")).sendKeys(pword);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	  
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("This is before method");
	  WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
  }
  
 
  @AfterMethod
  public void afterMethod() {
	  System.out.println("This is after method");
	  driver.quit();
  }
  
 
 
  @DataProvider
  public Object[][] logindata() {
    return new Object[][] {
      new Object[] { "Admin", "admin123" },
      new Object[] { "pooja", "welcome" },
    };
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("This is before class");
  }
 
  @AfterClass
  public void afterClass() {
	  System.out.println("This is after class");
  }
 
  @BeforeTest
  public void beforeTest() {
	  System.out.println("This is before test");
  }
 
  @AfterTest
  public void afterTest() {
	  System.out.println("This is after test");
  }
 
  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("This is before suite");
  }
 
  @AfterSuite
  public void afterSuite() {
	  System.out.println("This is after suite");
  }
 
}