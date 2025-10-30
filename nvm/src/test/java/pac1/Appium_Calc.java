package pac1;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Appium_Calc {
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
        driver.findElementById("com.coloros.calculator:id/digit_2").click();
        driver.findElementById("com.coloros.calculator:id/op_add").click();
        driver.findElementById("com.coloros.calculator:id/digit_3").click();
        driver.findElementById("com.coloros.calculator:id/eq").click();

        // Get result
        String result = driver.findElementById("com.coloros.calculator:id/result").getText();
        System.out.println("Calculation Result: " + result);

        // 4️⃣ Quit driver
        driver.quit();
    }
}
