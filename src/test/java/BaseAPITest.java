import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.oauth;

public class BaseAPITest {
    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String url = "http://localhost/fakestore/wp-json/wc/v3/";
    private static String uri = "http://localhost";
    private static String basePath = "fakestore/wp-json/wc/v3";
    protected static String productsEndpoint = "products";
    private static int port = 80;

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = uri;
        RestAssured.port = port;
        RestAssured.basePath = basePath;
        RestAssured.authentication = oauth(username,password,"","");
    }
}
