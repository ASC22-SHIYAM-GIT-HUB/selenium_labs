package com.firstcry.test;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.firstcry.base.BaseTesting;

public class spoofsearchtest extends BaseTesting {

    @Test
    public void testAddingProductToWishlistAndShortlist() throws InterruptedException {
        System.out.println("Starting test: testAddingProductToWishlistAndShortlist");

        navigateurl("https://www.firstcry.com/");
        System.out.println("Navigated to FirstCry homepage");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Step 1: Search for the product
        System.out.println("Locating search box...");
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_box")));
        System.out.println("Entering product name in search box...");
        searchBox.sendKeys("Babyhug Cosy Cosmo Stroller");
        System.out.println("Clicking search button...");
        driver.findElement(By.cssSelector(".search-button")).click();

        // Step 2: Click on the product link
        System.out.println("Waiting for product link to be clickable...");
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@title,'Babyhug Cosy Cosmo Stroller')]")
        ));
        System.out.println("Scrolling to product link...");
        js.executeScript("arguments[0].scrollIntoView(true);", product);
        System.out.println("Clicking on product link...");
        product.click();

        // Step 2a: Switch to new tab if opened
        String originalTab = driver.getWindowHandle();
        System.out.println("Original tab handle: " + originalTab);
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                System.out.println("Switched to new tab: " + tab);
                break;
            }
        }

        // Step 3: Wait for the product page to load
        System.out.println("Waiting for product page to load...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("prodImgInfo")));
        System.out.println("✅ Navigated to product page: " + driver.getCurrentUrl());

        System.out.println("Test completed: testAddingProductToWishlistAndShortlist");
    }
}
