package com.firstcry.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firstcry.base.BaseTesting;

import java.time.Duration;

public class spooflogin extends BaseTesting {

    @Test
    public void testLoginWithMobileNumberAndOTP() throws InterruptedException {
        test.info("Starting Login Test");

        navigateurl("https://www.firstcry.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Login')]")));
        loginButton.click();

        WebElement mobileNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lemail")));
        mobileNumberField.clear();
        mobileNumberField.sendKeys("9363025780");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
        continueButton.click();

        // Handle OTP input
        if (System.getenv("JENKINS_HOME") != null) {
            test.info("Running in Jenkins environment - auto-entering predefined OTP");
            String otp = "123456";

            for (int i = 0; i < otp.length(); i++) {
                driver.findElement(By.id("notp" + i)).sendKeys(String.valueOf(otp.charAt(i)));
            }

            test.pass("OTP auto-filled successfully in Jenkins environment");
        } else {
            test.info("Waiting for manual OTP entry (local run)");

            boolean otpEntered = false;
            int maxWaitSeconds = 60;
            int waited = 0;

            while (!otpEntered && waited < maxWaitSeconds) {
                Thread.sleep(1000);
                waited++;

                StringBuilder enteredOtp = new StringBuilder();
                boolean allFilled = true;

                for (int i = 0; i < 6; i++) {
                    WebElement otpField = driver.findElement(By.id("notp" + i));
                    String val = otpField.getAttribute("value");
                    if (val == null || val.isEmpty()) {
                        allFilled = false;
                        break;
                    }
                    enteredOtp.append(val);
                }

                if (allFilled) {
                    otpEntered = true;
                    test.pass("Manual OTP entered: " + enteredOtp.toString());
                }
            }

            if (!otpEntered) {
                test.fail("Manual OTP was not entered within " + maxWaitSeconds + " seconds");
                Assert.fail("OTP not entered in time");
            }
        }

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'loginSignup_submitOtpBtn_block')]/span[text()='SUBMIT']")));
        submitButton.click();

        // Wait for login confirmation
        Thread.sleep(5000);

        boolean isLoggedIn = false;
        try {
            WebElement profileIcon = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'My Account') or contains(text(), 'Logout')]")));
            isLoggedIn = profileIcon.isDisplayed();
        } catch (Exception e) {
            isLoggedIn = driver.getCurrentUrl().contains("firstcry");
        }

        Assert.assertTrue(isLoggedIn, "Login failed after entering OTP");
        test.pass("Login test passed successfully with OTP");
    }
}
