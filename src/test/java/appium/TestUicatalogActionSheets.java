package appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TestUicatalogActionSheets extends BaseClass{

    @BeforeClass
    public static void before() throws MalformedURLException {
        startApp();
        findElementByText("AAPLActionSheetViewController").click();
        /*        for(MobileElement x : driver.findElementsByXPath("//*")){
            System.out.println(x.getId());
            System.out.println(x.getTagName());
            System.out.println(x.getAttribute("label"));
        }*/
    }

    @Test
    public void OKayTest() {
        findElementByText("Okay").click();
        findElementByText("OK").click();
        assertThat("mast have Okay", findElementsByText("Okay").size(), is(1));
    }

    @Test
    public void CancelTest() {
        findElementByText("Okay").click();
        findElementByText("Cancel").click();
        assertThat("mast have Okay", findElementsByText("Okay").size(), is(1));
    }


}
