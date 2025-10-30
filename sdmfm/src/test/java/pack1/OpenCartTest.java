package pack1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OpenCartTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            // 1. Login with credentials created in Lab 1 (replace with your actual credentials)
            driver.get("http://tutorialsninja.com/demo/");
            driver.findElement(By.xpath("//span[text()='My Account']")).click();
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.id("input-email")).sendKeys("testuser@example.com");  // Replace email
            driver.findElement(By.id("input-password")).sendKeys("Test@1234");          // Replace password
            driver.findElement(By.xpath("//input[@value='Login']")).click();

            // 2. Go to 'Components' tab and click
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Components"))).click();

            // 3. Select 'Monitors'
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Monitors (2)"))).click();

            // 4. Select 25 from 'Show' dropdown
            Select showDropdown = new Select(driver.findElement(By.id("input-limit")));
            showDropdown.selectByVisibleText("25");

            // 5. Click on 'Add to cart' for the first item
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-original-title='Add to Cart'])[1]"))).click();

            // 6. Click on 'Specification' tab
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Specification']"))).click();

            // 7. Verify details present on the page
            List<WebElement> specs = driver.findElements(By.xpath("//div[@id='tab-specification']//td"));
            if (specs.isEmpty()) throw new Exception("Specification details are not present.");

            // 8. Click on 'Add to Wish list' button
            driver.findElement(By.xpath("//button[@data-original-title='Add to Wish List']")).click();

            // 9. Verify message 'Success: You have added Apple Cinema 30" to your wish list!'
            WebElement wishlistMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
            if (!wishlistMsg.getText().contains("Success: You have added Apple Cinema 30")) {
                throw new Exception("Wishlist success message not found or incorrect.");
            }

            // 10. Enter 'Mobile' in 'Search' text box
            WebElement searchBox = driver.findElement(By.name("search"));
            searchBox.clear();
            searchBox.sendKeys("Mobile");

            // 11. Click on 'Search' button
            driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

            // 12. Click on 'Search in product descriptions' checkbox
            WebElement descriptionCheckbox = driver.findElement(By.name("description"));
            if (!descriptionCheckbox.isSelected()) descriptionCheckbox.click();

            // 13. Click on link 'HTC Touch HD' for the mobile 'HTC Touch HD'
            driver.findElement(By.linkText("HTC Touch HD")).click();

            // 14. Clear '1' from 'Qty' and enter '3'
            WebElement qtyInput = driver.findElement(By.id("input-quantity"));
            qtyInput.clear();
            qtyInput.sendKeys("3");

            // 15. Click on 'Add to Cart' button
            driver.findElement(By.id("button-cart")).click();

            // 16. Verify success message 'Success: You have added HTC Touch HD to your shopping cart!'
            WebElement cartMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
            if (!cartMsg.getText().contains("Success: You have added HTC Touch HD")) {
                throw new Exception("Add to cart success message not found or incorrect.");
            }

            // 17. Click on 'View cart' button adjacent to search button
            driver.findElement(By.xpath("//a[contains(@href,'route=checkout/cart')]")).click();

            // 18. Verify Mobile name added to the cart
            List<WebElement> cartItems = driver.findElements(By.xpath("//table//a[contains(text(), 'HTC Touch HD')]"));
            if (cartItems.isEmpty()) throw new Exception("HTC Touch HD not found in cart.");

            // 19. Click on 'Checkout' button
            driver.findElement(By.linkText("Checkout")).click();

            // 20. Click on 'My Account' dropdown
            driver.findElement(By.xpath("//span[text()='My Account']")).click();

            // 21. Select 'Logout' from dropdown
            driver.findElement(By.linkText("Logout")).click();

            // 22. Verify 'Account Logout' heading
            WebElement logoutHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            if (!logoutHeading.getText().equals("Account Logout")) {
                throw new Exception("'Account Logout' heading not found.");
            }

            // 23. Click on 'Continue'
            driver.findElement(By.linkText("Continue")).click();

            System.out.println("Test completed successfully.");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
