package com.firstcryapp.test;

	import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
	import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
	import java.net.URL;
	import java.time.Duration;
	import java.util.concurrent.TimeUnit;

	public class TC_UserLogin_3{
	    public static void main(String[] args) throws MalformedURLException {

	        // 1️⃣ Set Desired Capabilities
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


	        // 2️⃣ Initialize AndroidDriver
	        AndroidDriver driver = new AndroidDriver(
	                new URL("http://127.0.0.1:4723"), caps
	        );

	        // Implicit wait (Selenium 3 syntax)
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        // ✅ Example: perform a simple operation (2 + 3)
	     // Locate the TextView by text
	        driver.findElementByAndroidUIAutomator("new UiSelector().className(\"com.horcrux.svg.u\").instance(3)").click();
	      
	        System.out.println("The User Profile was clicked");
	        
	        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"a\")").click();
	        System.out.println("The Login button was clicked");
	        
	       driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"lemail\")").sendKeys("9363025780");
	       System.out.println("The login credential was entered");
	        
	        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"loginotp\")").click();
	        System.out.println("The Continue Button was Clicked");

	        
	      


	    }
	}