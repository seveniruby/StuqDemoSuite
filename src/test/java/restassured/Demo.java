package restassured;

import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Demo {
    @Test
    public void baidu(){
        given()
            .log().all()
            .queryParam("wd", "mp3")
        .when()
            .get("http://www.baidu.com/s")
        .then()
            .log().all()
            .statusCode(200)
            .body("html.head.title", equalTo("mp3_百度搜索"));
    }

    @Test
    public void xueqiu(){
        useRelaxedHTTPSValidation();
        given()
            .log().all()
            .header("X-Requested-With", "XMLHttpRequest")
            .formParam("username", "15600534760")
            .formParam("password", "xxxxxx")
        .when()
            .post("https://xueqiu.com/snowman/login")
        .then()
            .log().all()
            .statusCode(200)
            .body("error_description", equalTo("用户名或密码错误"));
//            .body("href", response -> equalTo("http://localhost:8080/" + response.path("userId")));
        //.body("error_uri",  res-> {equalTo(res.path("error_code")+"xxxxx");});
    }

    @Test
    public void xueqiu2(){


        useRelaxedHTTPSValidation();
        given()
            .log().all()
            .header("X-Requested-With", "XMLHttpRequest")
            .body("remember_me=true&username=15600534760&password=xxx&captcha=")
        .when()
            .post("/snowman/login")
        .then()
            .log().all()
            .statusCode(200)
            .body("error_description", equalTo("用户名或密码错误"));
    }

    @Test
    public void timeout(){

        useRelaxedHTTPSValidation();

        System.out.println(config.getHttpClientConfig().params());
        int timeout=5000;
        config=config().httpClient(
                config().getHttpClientConfig()
                .setParam(ClientPNames.CONN_MANAGER_TIMEOUT, Long.valueOf(timeout))
                .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
                .setParam(CoreConnectionPNames.SO_TIMEOUT, timeout)
        );

        System.out.println(config.getHttpClientConfig().params());



        System.out.println(System.currentTimeMillis());
        given().log().all().get("https://testerhome.com/xxxx/xxxxxxx").then().log().all().statusCode(404);
        System.out.println(System.currentTimeMillis());
    }
    @Before
    public void before() {

    }
    @BeforeClass
    public static void beforeClass(){
        baseURI="https://xueqiu.com/";
    }

}
