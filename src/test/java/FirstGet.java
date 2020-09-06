import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FirstGet {
    private String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private String url = "http://localhost/fakestore/wp-json/wc/v3/";
    private String productsEndpoint = "products";
    private String productFuerta = "393";
    private String productCreated = "979";
    @Test
    public void firstGetTest() {
        Response response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .when()
                .get(url + productsEndpoint + "/" + productCreated);
//        System.out.println(response.prettyPeek());
//        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.jsonPath().getString("name"));
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void firstPOSTTest() {
        Response response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body("{\"name\": \"Created in RestAssure\"}")


                .when()
                .post(url+productsEndpoint);
        Assertions.assertEquals(201,response.statusCode());
        System.out.println(response.jsonPath().getString("id"));

 }
}
