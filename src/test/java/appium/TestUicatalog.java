import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestUicatalog {

    private IOSDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("app", "/Users/seveniruby/projects/ios-uicatalog/build/Debug-iphonesimulator/UICatalog.app");
        desiredCapabilities.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("platformName", "ios");
        desiredCapabilities.setCapability("platformVersion", "10.2");
        desiredCapabilities.setCapability("deviceName", "iPhone 7");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new IOSDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        MobileElement el1 = (MobileElement) driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"AX error -25205\"])[3]");
        el1.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
