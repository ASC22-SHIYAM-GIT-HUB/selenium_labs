package com.firstcry.test;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.firstcry.base.BaseTesting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.firstcry.utilities.ExtentManager;

public class spoofsearchtest extends BaseTesting {

    ExtentReports extent = ExtentManager.getinstance(); // singleton ExtentReports
    ExtentTest test; // one test per method

    @Test
    public void searchbutton() throws InterruptedException {
        // Create ExtentTest for this test method
        test = extent.createTest("Search Bar Button");

        logStep("Starting test: Search Bar Button");

        navigateurl("https://www.firstcry.com/");
        logStep("Navigated to FirstCry homepage");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Step 1: Search for the product
        logStep("Locating search box...");
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_box")));
        logStep("Entering product name in search box...");
        searchBox.sendKeys("Babyhug Cosy Cosmo Stroller");
        logStep("Clicking search button...");
        driver.findElement(By.cssSelector(".search-button")).click();

        // Step 2: Click on the product link
        logStep("Waiting for product link to be clickable...");
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@title,'Babyhug Cosy Cosmo Stroller')]")
        ));
        logStep("Scrolling to product link...");
        js.executeScript("arguments[0].scrollIntoView(true);", product);
        logStep("Clicking on product link...");
        product.click();

        // Step 2a: Switch to new tab if opened
        String originalTab = driver.getWindowHandle();
        logStep("Original tab handle: " + originalTab);
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                logStep("Switched to new tab: " + tab);
                break;
            }
        }

        // Step 3: Wait for the product page to load
        logStep("Waiting for product page to load...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("prodImgInfo")));
        logStep("✅ Navigated to product page: " + driver.getCurrentUrl());

        logStep("Test completed: testAddingProductToWishlistAndShortlist");
    }

    // Helper method to log steps both in console and ExtentReports
    private void logStep(String message) {
        System.out.println("[TESTINFO] " + message);
        if (test != null) {
            test.pass(message); // logs the step in report
        }
    }

    // Flush ExtentReports after each test
    @AfterMethod
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
