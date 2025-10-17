package pack1;



import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.TestCase;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.*;
 
import java.time.Duration;
 
public class Lab9 extends TestCase {
 
    private WebDriver driver;
    private WebDriverWait wait;
 
    private String browser = "chrome";
 
    protected void setUp() throws Exception {
        super.setUp();
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("Launching Chrome browser...");
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            System.out.println("Launching Internet Explorer...");
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
 
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Browser opened and navigated to TutorialsNinja site.");
    }
    protected void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
        super.tearDown();
    }
 
    public void testLab3BasicFlow() throws Exception {
        driver.findElement(By.linkText("Desktops")).click();
        System.out.println("Clicked on 'Desktops' tab.");
 
        WebElement macLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Mac (1)")));
        macLink.click();
        System.out.println("Clicked on 'Mac' option.");
 
        Select sortBy = new Select(driver.findElement(By.id("input-sort")));
        sortBy.selectByVisibleText("Name (A - Z)");
        System.out.println("Selected 'Name (A - Z)' from Sort dropdown.");
 
        driver.findElement(By.xpath(
                "//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//button[1]"
        )).click();
        System.out.println("Clicked 'Add to Cart' for iMac.");
 
        WebElement successAlert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success"))
        );
 
        assertTrue(
                "Success message for iMac was not displayed.",
                successAlert.getText().contains("Success: You have added iMac to your shopping cart!")
        );
        System.out.println("Verified success message for iMac.");
    }
 
    public void testLab4ValidationFlow() throws Exception {
        String expectedTitle = "Your Store";
        String actualTitle = driver.getTitle();
        assertEquals("Page title does not match!", expectedTitle, actualTitle);
        System.out.println("Page title verified: " + actualTitle);
 
        driver.findElement(By.linkText("Desktops")).click();
        WebElement macLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Mac (1)")));
        macLink.click();
 
        String macHeading = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Page heading is not 'Mac'.", "Mac", macHeading);
        System.out.println("Verified page heading as 'Mac'.");
 
        Select sortBy = new Select(driver.findElement(By.id("input-sort")));
        sortBy.selectByVisibleText("Name (A - Z)");
 
        driver.findElement(By.xpath(
                "//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']//button[1]"
        )).click();
 
        WebElement successAlert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success"))
        );
        assertTrue(
                "Success message not displayed for iMac.",
                successAlert.getText().contains("Success: You have added iMac to your shopping cart!")
        );
        System.out.println("iMac added to cart verified.");
 
        driver.findElement(By.name("search")).sendKeys("Mobile");
        driver.findElement(By.cssSelector("#search button")).click();
        System.out.println("Searched for 'Mobile'.");
 
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Search - Mobile')]")));
        WebElement searchBox = driver.findElement(By.id("input-search"));
        searchBox.clear();
        searchBox.sendKeys("Monitors");
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("button-search")).click();
 
        WebElement newSearchHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Search - Monitors')]"))
        );
        assertTrue("Search results for 'Monitors' not displayed.", newSearchHeading.isDisplayed());
        System.out.println("Verified search results for 'Monitors'.");
    }
}
 