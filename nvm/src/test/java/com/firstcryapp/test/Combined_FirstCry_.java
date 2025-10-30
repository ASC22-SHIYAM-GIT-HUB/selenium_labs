
package com.firstcryapp.test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.firstcryapp.utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Combined_FirstCry_ {

    AndroidDriver driver;
    ExtentReports extent;
    ExtentTest test1, test2, test3, test4, test5;

    @BeforeTest
    public void setup() throws MalformedURLException {
        extent = ExtentManager.getinstance();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "OPPO F19s");
        caps.setCapability(MobileCapabilityType.UDID, "7c84fea"); // Device UDID
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        // App details
        caps.setCapability("appPackage", "com.coloros.calculator");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");

        // Optional flags
        caps.setCapability("noReset", true);
        caps.setCapability("newCommandTimeout", 300);
        caps.setCapability("appium:disableHiddenApiPolicyManagement", true);
        caps.setCapability("appium:ignoreHiddenApiPolicyError", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("✅ App launched successfully!");
    }

    // -------------------- Test 1: Notifications --------------------
    @Test(priority = 1)
    public void TC_notification_1() {
        test1 = extent.createTest("FirstCry App - Notification Permission Test");
        test1.log(Status.INFO, "Starting notification permission handling...");

        driver.findElementById("com.android.permissioncontroller:id/permission_deny_button").click();
        test1.log(Status.INFO, "Clicked on notification deny button");
        System.out.println("The notification deny button was clicked");

        driver.findElementById("fc.admin.fcexpressadmin:id/ivCloseDialog").click();
        test1.log(Status.INFO, "Closed notification enable dialog");
        System.out.println("The Enable notification deny button was clicked");

        test1.log(Status.PASS, "Notification permission test completed successfully");
    }

    // -------------------- Test 2: Login Skip Permissions --------------------
    @Test(priority = 2)
    public void TC_Login_2() {
    	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        test2 = extent.createTest("FirstCry App - Login Skip Permissions Test");
        test2.log(Status.INFO, "Handling skip login and location permissions...");
        driver.findElementByAndroidUIAutomator("new UiSelector().className(\"com.horcrux.svg.u\").instance(9)").click();
        System.out.println("Skip Button was clicked");
        test2.log(Status.INFO, "Skip button clicked");

        driver.findElementById("fc.admin.fcexpressadmin:id/ivClosePermisionDialog").click();
        test2.log(Status.INFO, "Location permission deny button clicked");
        System.out.println("The permission for location service deny button was clicked");

        test2.log(Status.PASS, "Login skip permission test completed successfully");
    }

    // -------------------- Test 3: User Login --------------------
    @Test(priority = 3)
    public void TC_UserLogin_3() {
    	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        test3 = extent.createTest("FirstCry App - User Login Test");
        test3.log(Status.INFO, "Starting user login flow...");

        driver.findElementByAndroidUIAutomator("new UiSelector().className(\"com.horcrux.svg.u\").instance(3)").click();
        test3.log(Status.INFO, "User Profile clicked");
        System.out.println("The User Profile was clicked");

        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"a\")").click();
        test3.log(Status.INFO, "Login button clicked");
        System.out.println("The Login button was clicked");

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"lemail\")").sendKeys("9363025780");
        test3.log(Status.INFO, "Entered login credentials");
        System.out.println("The login credential was entered");

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"loginotp\")").click();
        test3.log(Status.INFO, "Continue button clicked");
        System.out.println("The Continue Button was Clicked");

        test3.log(Status.PASS, "User login test completed successfully");
    }

    // -------------------- Test 4: Search Product --------------------
    @Test(priority = 4)
    public void TC_Search_4() {
    	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        test4 = extent.createTest("FirstCry App - Product Search Test");
        test4.log(Status.INFO, "Starting product search flow...");

        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"fc.admin.fcexpressadmin:id/ivSearchMenu\")").click();
        test4.log(Status.INFO, "Search bar clicked");
        System.out.println("The SearchBar was Clicked");

        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Search for products or brands\")").sendKeys("Baby Stroller");
        test4.log(Status.INFO, "Entered 'Baby Stroller' in search bar");
        System.out.println("The Product name was entered in search Bar");

        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Baby Strollers and Prams\")").click();
        test4.log(Status.INFO, "Clicked on searched product");
        System.out.println("The Product name was clicked");

        test4.log(Status.PASS, "Product search test completed successfully");
    }

    // -------------------- Test 5: Wishlist & Add to Cart --------------------
    @Test(priority = 5)
    public void TC_Whislist_5() {
    	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        test5 = extent.createTest("FirstCry App - Wishlist and Add to Cart Test");
        test5.log(Status.INFO, "Starting wishlist and add to cart flow...");

        driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(21)").click();
        test5.log(Status.INFO, "Clicked on product");
        System.out.println("The product was Clicked");

        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"y\")").click();
        test5.log(Status.INFO, "Wishlist button clicked");
        System.out.println("The Wishlist button was clicked");

        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"ADD TO CART\")").click();
        test5.log(Status.INFO, "Product added to cart");
        System.out.println("The Product was added to cart");

        test5.log(Status.PASS, "Wishlist and add to cart test completed successfully");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ App closed successfully!");
        }
        extent.flush();
    }
}
