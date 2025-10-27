package com.firstcry.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firstcry.base.BaseTest;
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
        continueButton.click();  // This is where the "CONTINUE" button is clicked

        // Step 6: Wait for OTP input fields to appear
        test.info("Waiting for OTP to be entered manually");
        Thread.sleep(30000);  // Sleep for 5 minutes for manual OTP entry

        // Step 7: After the user manually enters the OTP, find and submit it
        // Predefined OTP
        String otp = "123456"; // Replace with the OTP you want to enter

        // Enter OTP digit by digit into the individual fields
        WebElement otpField0 = driver.findElement(By.id("notp0"));
        otpField0.sendKeys(String.valueOf(otp.charAt(0)));  // Enter first digit of OTP

        WebElement otpField1 = driver.findElement(By.id("notp1"));
        otpField1.sendKeys(String.valueOf(otp.charAt(1)));  // Enter second digit of OTP

        WebElement otpField2 = driver.findElement(By.id("notp2"));
        otpField2.sendKeys(String.valueOf(otp.charAt(2)));  // Enter third digit of OTP

        WebElement otpField3 = driver.findElement(By.id("notp3"));
        otpField3.sendKeys(String.valueOf(otp.charAt(3)));  // Enter fourth digit of OTP

        WebElement otpField4 = driver.findElement(By.id("notp4"));
        otpField4.sendKeys(String.valueOf(otp.charAt(4)));  // Enter fifth digit of OTP

        WebElement otpField5 = driver.findElement(By.id("notp5"));
        otpField5.sendKeys(String.valueOf(otp.charAt(5)));  // Enter sixth digit of OTP

        // Step 8: Wait for the 'Submit' button to be clickable and submit the OTP
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'loginSignup_submitOtpBtn_block')]/span[text()='SUBMIT']")));
        submitButton.click();

        // Step 9: Verify if login is successful
        boolean isLoggedIn = driver.getCurrentUrl().contains("dashboard");
        Assert.assertTrue(isLoggedIn, "Login failed after entering OTP");

        // Log the success message
        test.pass("Login test passed successfully with OTP");
    }
}
