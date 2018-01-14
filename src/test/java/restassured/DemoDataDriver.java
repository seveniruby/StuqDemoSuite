package restassured;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


@RunWith(Parameterized.class)
public class DemoDataDriver {
    @Parameterized.Parameters(name = "{index}: baidu search wd={0}  expect={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                //read from yaml xml db csv
                { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 }
        });
    }

    @Parameterized.Parameter // first data value (0) is default
    public /* NOT private */ int fInput;

    @Parameterized.Parameter(1)
    public /* NOT private */ int fExpected;

    @Test
    public void baidu(){
        given()
            .log().all()
            .queryParam("wd", fInput)
        .when()
            .get("http://www.baidu.com/s")
        .then()
            .log().all()
            .statusCode(200)
            .body("html.head.title", equalTo(fExpected+"_百度搜索"))
            .time(lessThan(1L), SECONDS);
    }

    @Before
    public void before() {

    }
    @BeforeClass
    public static void beforeClass(){
        baseURI="https://xueqiu.com/";
    }

}
