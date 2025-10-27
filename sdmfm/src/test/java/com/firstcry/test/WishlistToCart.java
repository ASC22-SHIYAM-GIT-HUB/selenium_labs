package com.firstcry.test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.firstcry.base.BaseTesting;

public class WishlistToCart extends BaseTesting {

    @Test
    public void removeFromCart() {
        try {
            test.info("🛒 Starting Cart Cleanup Test (Already on Cart Page)");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // ✅ Loop until cart is empty
            while (true) {
                // Find all 'REMOVE' buttons — the parent divs are clickable
                List<WebElement> removeButtons = driver.findElements(
                        By.xpath("//div[contains(@class,'shortcomm') and .//span[normalize-space(text())='REMOVE']]")
                );

                int count = removeButtons.size();
                test.info("Found " + count + " REMOVE buttons.");

                if (count == 0) {
                    test.pass("✅ Cart is empty. Nothing to remove.");
                    break;
                }

                // Click the first remove button
                WebElement removeBtn = removeButtons.get(0);

                try {
                    js.executeScript("arguments[0].scrollIntoView(true);", removeBtn);
                    js.executeScript("arguments[0].click();", removeBtn);
                    test.info("🗑️ Removed one item from cart.");

                    // Wait a bit for the DOM/cart to refresh
                    Thread.sleep(2000);

                } catch (Exception clickErr) {
                    test.warning("⚠️ Could not click remove button: " + clickErr.getMessage());
                }
            }

            test.pass("✅ Cart cleanup completed successfully.");

        } catch (Exception e) {
            test.fail("❌ Cart cleanup failed: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            // Optional: close browser if this is your last test
            if (driver != null) {
                driver.quit();
                test.info("Browser closed successfully.");
            }
        }
    }
}
