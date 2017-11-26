package appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.AfterClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    private static IOSDriver<MobileElement> driver;

    public static void installApp() throws MalformedURLException {
        System.out.println("Class rule before");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("app", "/Users/seveniruby/projects/ios-uicatalog/build/Debug-iphonesimulator/UICatalog.app");
        desiredCapabilities.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("platformName", "ios");
        desiredCapabilities.setCapability("platformVersion", "10.2");
        desiredCapabilities.setCapability("deviceName", "iPhone 7");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new IOSDriver<MobileElement>(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.installApp();

    }
    public static void startApp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //desiredCapabilities.setCapability("app", "/Users/seveniruby/projects/ios-uicatalog/build/Debug-iphonesimulator/UICatalog.app");
        desiredCapabilities.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("platformName", "ios");
        desiredCapabilities.setCapability("platformVersion", "10.2");
        desiredCapabilities.setCapability("deviceName", "iPhone 7");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver= new IOSDriver<MobileElement>(remoteUrl, desiredCapabilities);
        //driver.launchApp();
    }

    public static MobileElement findElementByText(String text) {
        String xpath="//*[contains(@label, '" + text + "')]";
        List<MobileElement> elements = driver.findElementsByXPath(xpath);
        if (elements.size() > 0) {
            return elements.get(0);
        } else {
            System.out.println("not found "+ xpath);
            for (MobileElement x : driver.findElementsByXPath("//*")) {
                System.out.println(x.getTagName());
                System.out.println(x.getAttribute("label"));
            }
            return driver.findElementByXPath(xpath);
        }

    }

    public static List<MobileElement> findElementsByText(String text) {
        return driver.findElementsByXPath("//*[contains(@label, '" + text + "')]");
    }

/*    public static MobileElement findElementByRegex(String regex="abc.*d"){
        String source=driver.getPageSource();
        String locator=XPath.find(source, regex);
        driver.findElementByAccessibilityId(locator);
    }*/

    @AfterClass
    public static void tearDown() {
        System.out.println("AfterClass driver.quit");
        driver.quit();
    }
}
