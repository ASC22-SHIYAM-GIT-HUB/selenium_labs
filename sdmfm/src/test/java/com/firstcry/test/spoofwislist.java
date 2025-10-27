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
import com.firstcry.base.BaseTesting;

public class spoofwislist extends BaseTesting {

    @Test
    public void testAddingProductToWishlistAndShortlist() throws InterruptedException {
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        //navigateurl("https://www.firstcry.com/");

       
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
       
        
     
    }   
}
       