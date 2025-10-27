package com.firstcry.test;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firstcry.base.BaseTest;

public class A2CardTest extends BaseTest {

    @Test
    public void testAddingProductToWishlistAndShortlist() throws InterruptedException {
        navigateurl("https://www.firstcry.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Step 1: Search for the product
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_box")));
        searchBox.sendKeys("Babyhug Cosy Cosmo Stroller");
        driver.findElement(By.cssSelector(".search-button")).click();

        // Step 2: Click on the product link
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@title,'Babyhug Cosy Cosmo Stroller')]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", product);
        product.click();

        // Step 2a: Switch to new tab if opened
        String originalTab = driver.getWindowHandle();
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        // Step 3: Wait for the product page to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("prodImgInfo")));
        System.out.println("✅ Navigated to product page: " + driver.getCurrentUrl());

        // Step 4: Close any popups if present
        try {
            WebElement popupClose = driver.findElement(By.cssSelector(".modal-close, .close-button"));
            if (popupClose.isDisplayed()) {
                popupClose.click();
            }
        } catch (Exception e) {
            // No popup appeared
        }

        // Step 5: Click the wishlist button
        WebElement wishlistButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='prodImgInfo']/section[1]/section/div[1]/label")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", wishlistButton);
        Thread.sleep(500); // ensure clickable
        js.executeScript("arguments[0].click();", wishlistButton);

        // Step 6: Verify the wishlist button activated
        WebElement activeHeart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='prodImgInfo']/section[1]/section/div[1]/label[contains(@class,'active')]")
        ));
        Assert.assertTrue(activeHeart.isDisplayed(), "Wishlist icon did not activate.");
        System.out.println("✅ Wishlist button clicked and verified successfully.");

        // Step 7: Click the Shortlist button (robust)
        WebElement shortlistButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sh")));
        js.executeScript("arguments[0].scrollIntoView(true);", shortlistButton);
        actions.moveToElement(shortlistButton).click().perform();

        // Step 8: Verify Shortlist count incremented
        WebElement shortlistCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sh span")));
        wait.until(ExpectedConditions.textToBePresentInElement(shortlistCount, "(1)"));
        Assert.assertEquals(shortlistCount.getText(), "(1)", "Shortlist count did not increment!");
        System.out.println("✅ Shortlist button clicked and count verified.");
        
        WebElement checkbox = driver.findElement(By.cssSelector("input[data-id='700000']"));
        checkbox.click();
        
        WebElement addToCartBtn = driver.findElement(By.xpath("//div[text()='Add to cart']"));
        addToCartBtn.click();
        
     // Wait until the cart count updates
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartCount = wait1.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#cart_TotalCount .prodQuant")
        ));

        // Wait for the text to become "1"
        wait1.until(ExpectedConditions.textToBePresentInElement(cartCount, "1"));

        // Now assert
        Assert.assertEquals(cartCount.getText(), "1", "Cart count did not update!");
        System.out.println("✅ Cart count updated successfully.");
        
     // Click the cart icon to go to the cart page
        WebElement cartIcon = driver.findElement(By.id("cart_TotalCount"));
        cartIcon.click();
        System.out.println("✅The product in the Cart updated successfully.");
        
     
    }   
}
       