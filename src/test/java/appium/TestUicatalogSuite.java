package appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestUicatalogActionSheets.class,
        TestUicatalogAlertViews.class
})

public class TestUicatalogSuite extends BaseClass{
    @ClassRule
    public static final ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            installApp();
        };

        @Override
        protected void after() {
            System.out.println("class rule after ");
        };
    };
}
