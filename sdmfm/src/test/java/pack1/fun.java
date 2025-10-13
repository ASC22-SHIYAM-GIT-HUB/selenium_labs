package pack1;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
 
import java.time.Duration;
 
public class fun {
 
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 
        try {
            driver.get("http://tutorialsninja.com/demo/");
            driver.manage().window().maximize();
 
            // Navigate to Register Page
            driver.findElement(By.xpath("//span[text()='My Account']")).click();
            driver.findElement(By.linkText("Register")).click();
 
            // Enter First Name
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));
            firstName.clear();
            firstName.sendKeys("John");
            System.out.println("Entered First Name: John");
 
            // Enter Last Name
            WebElement lastName = driver.findElement(By.id("input-lastname"));
            lastName.clear();
            lastName.sendKeys("Doe");
            System.out.println("Entered Last Name: Doe");
 
            // Enter Email (unique)
            WebElement email = driver.findElement(By.id("input-email"));
            email.clear();
            email.sendKeys("johndoe" + System.currentTimeMillis() + "@mail.com");
            System.out.println("Entered Email");
 
            // Enter Telephone
            WebElement phone = driver.findElement(By.id("input-telephone"));
            phone.clear();
            phone.sendKeys("1234567890");
            System.out.println("Entered Telephone");
 
            // Enter Password and Confirm Password
            WebElement passwordField = driver.findElement(By.id("input-password"));
            WebElement confirmPasswordField = driver.findElement(By.id("input-confirm"));
            passwordField.clear();
            passwordField.sendKeys("test1234");
            confirmPasswordField.clear();
            confirmPasswordField.sendKeys("test1234");
            System.out.println("Entered Password");
 
            // Select Newsletter 'No'
            WebElement newsletterNo = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
            newsletterNo.click();
 
            // Accept Privacy Policy
            WebElement privacyCheckbox = driver.findElement(By.name("agree"));
            privacyCheckbox.click();
 
            // Click Continue button
            WebElement continueBtn = driver.findElement(By.cssSelector("input[type='submit']"));
            continueBtn.click();
            System.out.println(" Account creation successful.");
 
            // Wait for success message
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Your Account Has Been Created!')]")));
 
            if (successMsg.isDisplayed()) {
                System.out.println(" Account creation successful.");
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}