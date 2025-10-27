package com.firstcry.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.firstcry.base.BaseTest;

import java.time.Duration;

public class SearchTest extends BaseTest {

    @Test
    public void testProductSearchFunctionality() {
        // Test Case: Verify product search functionality (TC_SEARCH_001)
    	navigateurl("https://www.firstcry.com/");
        // Step 1: Wait for the search bar to be visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_box"))); // Corrected the id to "search_box"

        // Step 2: Enter product name in search bar
        searchBar.sendKeys("Baby Stroller");

        // Step 3: Click on the search button (search icon)
        WebElement searchButton = driver.findElement(By.cssSelector(".search-button"));
        searchButton.click();

        // Step 4: Wait for the product link (Babyhug Cosy Cosmo Stroller) to appear and click on it
        WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[contains(@title, 'Babyhug Cosy Cosmo Stroller')]"))
        );

        // Scroll the element into view and click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);
        productLink.click();

        // Step 5: Wait for an element on the product page to be visible (indicating page has fully loaded)
        WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(text(), 'Cosy Cosmo Stroller')]") // Ensure that the product title is on the product page
        ));

        // Step 6: Verify if the correct product page is opened (You can check the URL or product name here)
        Assert.assertTrue(driver.getCurrentUrl().contains("babyhug-cosy-cosmo-stroller"), "Product page is not opened correctly.");

        // Additional Assertion (optional) to verify the product title
        Assert.assertTrue(productTitle.isDisplayed(), "Product title is not displayed.");
    }
}
