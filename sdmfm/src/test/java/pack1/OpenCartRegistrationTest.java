package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class OpenCartRegistrationTest {
    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Navigate to registration page
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");

        // Verify page heading
        WebElement heading = driver.findElement(By.cssSelector("#content h1"));
        if (!heading.getText().equals("Register Account")) {
            System.out.println("Failed to open Register Account page");
            driver.quit();
            return;
        }

        // Part 1: Personal Details (Indian-style data)
        driver.findElement(By.id("input-firstname")).sendKeys("Amit");
        driver.findElement(By.id("input-lastname")).sendKeys("Sharma");
        String uniqueEmail = "amit.sharma" + System.currentTimeMillis() + "@mail.com";
        System.out.println(uniqueEmail);
        driver.findElement(By.id("input-email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("9876543210");

        // Part 2: Password
        driver.findElement(By.id("input-password")).sendKeys("India@123");
        driver.findElement(By.id("input-confirm")).sendKeys("India@123");

        // Part 3: Newsletter Subscription - select "Yes"
        driver.findElement(By.cssSelector("input[name='newsletter'][value='1']")).click();

        // Part 4: Privacy Policy agreement checkbox
        driver.findElement(By.name("agree")).click();

        // Part 5: Submit the registration form
        driver.findElement(By.cssSelector("input[type='submit'][value='Continue']")).click();

        // Verify account creation success message
        WebElement successHeading = driver.findElement(By.cssSelector("#content h1"));
        if (successHeading.getText().contains("Your Account Has Been Created!")) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed.");
        }

        // Click Continue to navigate forward
        driver.findElement(By.linkText("Continue")).click();

        // Close browser
        driver.quit();
    }
}
