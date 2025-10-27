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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.firstcry.base.BaseTesting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.firstcry.utilities.ExtentManager;

public class spoofwislist extends BaseTesting {

    ExtentReports extent = ExtentManager.getinstance(); // singleton ExtentReports
    ExtentTest test; // one test per method

    @Test
    public void testAddingProductToWishlist() throws InterruptedException {
        // Create ExtentTest for this method
        test = extent.createTest("testAddingProductToWishlist");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        logStep("Starting test: testAddingProductToWishlist");

        // Step 4: Close any popups if present
        try {
            WebElement popupClose = driver.findElement(By.cssSelector(".modal-close, .close-button"));
            if (popupClose.isDisplayed()) {
                popupClose.click();
                logStep("Popup closed successfully.");
            }
        } catch (Exception e) {
            logStep("No popup appeared.");
        }

        // Step 5: Click the wishlist button
        WebElement wishlistButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='prodImgInfo']/section[1]/section/div[1]/label")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", wishlistButton);
        Thread.sleep(500); // ensure clickable
        js.executeScript("arguments[0].click();", wishlistButton);
        logStep("Clicked the wishlist button.");

        // Step 6: Verify the wishlist button activated
        WebElement activeHeart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='prodImgInfo']/section[1]/section/div[1]/label[contains(@class,'active')]")
        ));
        Assert.assertTrue(activeHeart.isDisplayed(), "Wishlist icon did not activate.");
        logStep("Wishlist button activated and verified successfully.");

        // Step 7: Click the Shortlist button (robust)
        WebElement shortlistButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sh")));
        js.executeScript("arguments[0].scrollIntoView(true);", shortlistButton);
        actions.moveToElement(shortlistButton).click().perform();
        logStep("Clicked the Shortlist button.");

        logStep("Test completed: testAddingProductToWishlist");
    }

    // Helper method to log steps
    private void logStep(String message) {
        System.out.println("[TESTINFO] " + message);
        if (test != null) {
            test.pass(message);
        }
    }

    // Flush report after test
    @AfterMethod
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
