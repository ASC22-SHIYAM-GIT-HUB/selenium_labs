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

import com.firstcry.base.BaseTesting;

public class spoofa2d extends BaseTesting {
	
    @Test
    public void testAddingProductToWishlistAndShortlist() throws InterruptedException {
    	 // Step 8: Verify Shortlist count incremented
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
       
