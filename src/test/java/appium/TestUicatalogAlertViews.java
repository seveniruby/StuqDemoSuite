package appium;

import appium.Util.Node;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestUicatalogAlertViews extends BaseClass{


    @Before
    public void setUp() throws MalformedURLException {
        startApp();
        findElementByText("Alert Views").click();
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
        findElementByText("CancelNOTFOUND").click();
        assertThat("mast have Okay", findElementsByText("Okay").size(), is(1));
    }

}
