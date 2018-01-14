package restassured;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.*;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import static org.junit.Assert.assertThat;

public class Demo {
    @BeforeClass
    public static void beforeClass(){
        System.out.println("beforeClass");
    }
    @Test
    public void baidu(){
        given().log().all().proxy("172.16.20.40", 7777)
            .queryParam("wd", "mp3")
        .when()
            .get("http://www.baidu.com/s")
        .then()
            .log().all()
            .statusCode(200)
            .body("html.head.title", equalTo("mp3_百度搜索"));
    }

    @Test
    public void testPut(){
        given().log().all().proxy("172.16.20.40", 7777)
                .queryParam("wd", "mp3")
                .when()
                //.put()
                .request("PATCH", "http://www.baidu.com")
                .then()
                .log().all()
                .statusCode(200)
                .body("html.head.title", equalTo("mp3_百度搜索"));
    }


    @Test
    public void baiduExtract(){
        Response response=
            given().proxy("172.16.20.40", 7777)
                .log().all()
                .queryParam("wd", "mp3")
            .when()
                .get("http://www.baidu.com/s")
            .then()
                .log().all()
                .statusCode(200)
            .extract()
                    .response();

        String title=response.htmlPath().getString("html.head.title");
        assertThat(title, equalTo("mp3_百度搜索"));

                given().proxy("172.16.20.40",7777)
                        .log().all()
                        .queryParam("wd", title)
                        .when()
                        .get("http://www.baidu.com/s")
                        .then()
                        .log().all()
                        .statusCode(200);


    }

    @Test
    public void testJson(){
        useRelaxedHTTPSValidation();
        HashMap<String, Object> hash=new HashMap<String, Object>();
        hash.put("first name", "testerhome");
        hash.put("last name", "霍格沃兹测试学院");

        given().proxy("172.16.20.40", 7777)
                .contentType(ContentType.JSON).body(hash)
        .when()
                .post("https://www.baidu.com/s")
        .then()
                .statusCode(200);
    }
    @Test
    public void testXML(){
        useRelaxedHTTPSValidation();
        HashMap<String, Object> hash=new HashMap<String, Object>();
        hash.put("first name", "testerhome");
        hash.put("last name", "霍格沃兹测试学院");

        given().proxy("172.16.20.40", 7777)
                .contentType(ContentType.XML).body(hash)
                .when()
                .post("https://www.baidu.com/s")
                .then()
                .statusCode(200);
    }

    @Test
    public void testResponse2(){
        given().log().all().proxy("172.16.20.40", 7777)
                .queryParam("wd", "mp3")
                .when()
                .get("http://www.baidu.com/s")
                .then()
                .log().all()
                .statusCode(200)
                .body("html.head.title", equalTo("mp3_百度搜索"))
                .body("html.head.title", response->equalTo(response.htmlPath().getString("html.head.title")+"xxxx"));
    }

    @Test
    public void testFilter(){
        given().filter((FilterableRequestSpecification req, FilterableResponseSpecification res , FilterContext ctx)->{
            System.out.println(req);
            Response resNew=ctx.next(req, res);
            System.out.println("filter demo");
            System.out.println(resNew.prettyPrint());
            return resNew;
        }).queryParam("wd", "mp3")
                .when()
                .get("http://www.baidu.com/s")
                .then()
                .statusCode(200)
                .body("html.head.title", equalTo("mp3_百度搜索"));
    }


    @Test
    public void testXMLDemo(){
      given()
              .get("http://127.0.0.1:8088/demo.xml")
          .then().body("**.find { it.name == 'Coffee' }.price", equalTo("20"));
    }
    @Test
    public void testerhome(){
        given().log().all()
                .formParam("on" ,"true")
                .formParam("x", "1.99")
                .header("User-Agent", "Xueqiu Android 10.0")
                .header("Cookie", "xq_a_token=6d94ee90543d9f6ad2f812f4a278acc9857d87c3; u=7041783755")
            .when().log().all()
                .post("https://api.xueqiu.com/etc/mobile/state.json")
            .then().log().all()
                .body("success", equalTo(true));

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
        System.out.println("before");
    }

}
