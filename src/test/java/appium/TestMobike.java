package appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestMobike {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appPackage", "com.mobike.mobikeapp");
        desiredCapabilities.setCapability("appActivity", ".SplashActivity");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "ddd");
        desiredCapabilities.setCapability("noReset", "true");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("com.mobike.mobikeapp:id/text");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.mobike.mobikeapp:id/qrcode_input_btn_image");
        el2.click();
        System.out.println(driver.getPageSource());
        MobileElement el3 = (MobileElement) driver.findElementById("com.mobike.mobikeapp:id/inputView");
        el3.sendKeys("0123456789");
        MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
        el4.click();
        MobileElement el5 = (MobileElement) driver.findElementById("com.mobike.mobikeapp:id/buttonClose");
        el5.click();
    }

    @Test
    public void testSwipe() throws InterruptedException {
        driver.findElement(By.id("home")).click();
        (new TouchAction(driver)).press(534, 1061).moveTo(-27, 511).release().perform();
        Thread.sleep(5000);
    }
    @Test
    public void testBackground(){
        driver.runAppInBackground(java.time.Duration.ofSeconds(3));

    }
    @After
    public void tearDown() {
        driver.quit();
    }
}

