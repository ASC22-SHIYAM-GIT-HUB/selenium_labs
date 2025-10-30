package com.firstcry.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firstcry.base.BaseTesting;

import java.time.Duration;

public class LoginTest extends BaseTesting {

    @Test
    public void testLoginWithMobileNumberAndOTP() throws InterruptedException {
        // Log the start of the test
        test.info("Starting Login Test");

        // Step 1: Navigate to the FirstCry login page
        navigateurl("https://www.firstcry.com/");

        // Step 2: Wait for the 'Login' button to be visible and clickable using XPath or CSS Selector
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Login')]")));

        // Step 3: Click on the 'Login' button
        loginButton.click();

        // Step 4: Wait for the mobile number field to be visible and enter the mobile number
        WebElement mobileNumberField = driver.findElement(By.id("lemail"));
        mobileNumberField.sendKeys("9363025780");

        // Step 5: Wait for the 'CONTINUE' button to be clickable and click it
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
        continueButton.click();  // Click on CONTINUE

        // Step 6: Wait or auto-enter OTP based on environment
        if (System.getenv("JENKINS_HOME") != null) {
            test.info("Running in Jenkins environment - auto-entering predefined OTP");

            String otp = "123456"; // predefined OTP for Jenkins

            driver.findElement(By.id("notp0")).sendKeys(String.valueOf(otp.charAt(0)));
            driver.findElement(By.id("notp1")).sendKeys(String.valueOf(otp.charAt(1)));
            driver.findElement(By.id("notp2")).sendKeys(String.valueOf(otp.charAt(2)));
            driver.findElement(By.id("notp3")).sendKeys(String.valueOf(otp.charAt(3)));
            driver.findElement(By.id("notp4")).sendKeys(String.valueOf(otp.charAt(4)));
            driver.findElement(By.id("notp5")).sendKeys(String.valueOf(otp.charAt(5)));

            test.pass("OTP auto-filled successfully in Jenkins environment");
        } else {
            test.info("Waiting for manual OTP entry (local run)");
            Thread.sleep(30000); // Wait for manual OTP entry locally
        }

        // Step 7: Wait for the 'Submit' button to be clickable and submit the OTP
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'loginSignup_submitOtpBtn_block')]/span[text()='SUBMIT']")));
        submitButton.click();

        // Step 8: Verify if login is successful
        boolean isLoggedIn = driver.getCurrentUrl().contains("dashboard");
        Assert.assertTrue(isLoggedIn, "Login failed after entering OTP");

        // Log the success message
        test.pass("Login test passed successfully with OTP");
    }
}
