import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.oauth;
import static io.restassured.RestAssured.when;

public class JsonPlay {
    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String url = "http://localhost/fakestore/wp-json/wc/v3/";
    private static String uri = "http://localhost";
    private static String basePath = "fakestore/wp-json/wc/v3";
    private static String productsEndpoint = "products";
    private static int port = 80;

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = uri;
        RestAssured.port = port;
        RestAssured.basePath = basePath;
        RestAssured.authentication = oauth(username,password,"","");
    }

    @Test
    public void jsonMe(){
        Response response = when()
                .get( productsEndpoint+"/393");
//        System.out.println(response.jsonPath().getString("related_ids"));
//        List<String> lista = response.jsonPath().getList("related_ids");
//        List<Integer>lista = response.jsonPath().getList("categories.id");
//        System.out.println(lista.get(0));
        System.out.println(response.jsonPath().getString("categories[0].id"));

//        response.xmlPath().get("path.to.our.xml[1].@parameter_name" );
    }
}
