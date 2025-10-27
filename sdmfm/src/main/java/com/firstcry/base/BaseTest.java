package com.firstcry.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.firstcry.utilities.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupreport() {
        extent = ExtentManager.getinstance();  // Initialize ExtentReports
    }

    @AfterSuite
    public void flushreport() {
        extent.flush();  // Write the report after test execution
    }

    @BeforeMethod
    public void setup() {
        // Set up ChromeDriver with WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Implicit wait for elements

        // Create a new ExtentTest instance for each test (initialize before test starts)
        test = extent.createTest("Login Test");  // Replace with the actual test name
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();  // Close the browser after each test
        }
        // Log the test result to ExtentReports
        test.pass("Test passed successfully");
        // In case of failure, use: test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath("path_to_screenshot").build());
    }

    // Utility method to navigate to a URL
    public void navigateurl(String url) {
        driver.get(url);  // Open the specified URL
    }
}
