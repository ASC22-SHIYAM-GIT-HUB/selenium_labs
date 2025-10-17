package pack1;


import org.openqa.selenium.By;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
 
import java.time.Duration;
 
import static org.testng.Assert.assertTrue;
 
public class Lab11 {
    WebDriver driver;
 
    
    By usernamePOM = By.name("username");
    By passwordPOM = By.name("password");
    By loginButtonPOM = By.xpath("//button[@type='submit']");
    By dashboardPOM = By.xpath("//h6[text()='Dashboard']");
 
   
    @FindBy(name = "username")
    WebElement usernamePF;
 
    @FindBy(name = "password")
    WebElement passwordPF;
 
    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButtonPF;
 
    @FindBy(xpath = "//h6[text()='Dashboard']")
    WebElement dashboardPF;
 
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        PageFactory.initElements(driver, this);
    }
 
    @Test(dataProvider = "logindata")
    public void loginTestUsingPOM(String uname, String pword) {
        System.out.println("🔹 Test using Page Object Model");
        driver.findElement(usernamePOM).sendKeys(uname);
        driver.findElement(passwordPOM).sendKeys(pword);
        driver.findElement(loginButtonPOM).click();
 
        boolean isDashboardVisible = driver.findElement(dashboardPOM).isDisplayed();
        assertTrue(isDashboardVisible, "Dashboard should be visible after login");
    }
 
    @Test(dataProvider = "logindata")
    public void loginTestUsingPageFactory(String uname, String pword) {
        System.out.println("🔹 Test using Page Factory");
        usernamePF.sendKeys(uname);
        passwordPF.sendKeys(pword);
        loginButtonPF.click();
 
        boolean isDashboardVisible = dashboardPF.isDisplayed();
        assertTrue(isDashboardVisible, "Dashboard should be visible after login");
    }
 
    @DataProvider
    public Object[][] logindata() {
        return new Object[][] {
            { "Admin", "admin123" },
            { "pooja", "welcome" }
        };
    }
 
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
 
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Starting Test Suite");
    }
 
    @AfterSuite
    public void afterSuite() {
        System.out.println("Test Suite Completed");
    }
 
    @BeforeClass
    public void beforeClass() {
        System.out.println("Starting Test Class");
    }
 
    @AfterClass
    public void afterClass() {
        System.out.println("Test Class Completed");
    }
 
    @BeforeTest
    public void beforeTest() {
        System.out.println("Starting Test");
    }
 
    @AfterTest
    public void afterTest() {
        System.out.println("Test Completed");
    }
}
 