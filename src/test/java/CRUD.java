import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class CRUD {
    private static String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private static String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private static String url = "http://localhost/fakestore/wp-json/wc/v3/";
    private static String uri = "http://localhost";
    private static String basePath = "fakestore/wp-json/wc/v3";
    private static String productsEndpoint = "products";
    private static int port = 80;

    @BeforeAll
    //Metoda jest jako STATIC ponieważ będzie wykonywana ZANIM obiekt tej klasy testowej ZOSTANIE STWORZONY
    public static void setUp(){
        RestAssured.baseURI = uri;
        RestAssured.port = port;
        RestAssured.basePath = basePath;
        RestAssured.authentication = oauth(username,password,"","");
    }
//-------R--------
    @Test
    public void testGetAgain(){
        Response response = when()

                .get(productsEndpoint+ "/393");
        Assertions.assertEquals(200,response.statusCode());
    }
//-------C--------
    @Test
    public void  testPostAgain(){
        Response response = given()

                .contentType("application/json")
                .body("{\"name\": \"Krzysiu jem zupę\"}")
                    .when()
                    .post(productsEndpoint);
        Assertions.assertEquals(201,response.statusCode());

    }
//-------U--------

    @Test
    public void testUpdate(){
        Response response = given()
                .contentType("application/json")
                .body("{\"name\": \"Krzysiu jem drugie, zupe i deser\"}")
                .when()
                .put(productsEndpoint + "/989");
        Assertions.assertEquals(200,response.statusCode());
    }
//-------D--------

    @Test
    public void testDelete(){
        Response response = when()
                .delete(productsEndpoint + "/989");
        Assertions.assertEquals(200,response.statusCode());
    }

}
