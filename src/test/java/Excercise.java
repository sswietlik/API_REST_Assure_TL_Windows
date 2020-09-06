import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class Excercise {

    private String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private String url = "http://localhost/fakestore/wp-json/wc/v3/";
    private String productsEndpoint = "products";

    @Test
    public void  testMe(){
        Response response = given()
                    .port(80)
                    .auth()
                    .oauth(username,password,"","")
                    .contentType("application/json")
                    .body("{\"name\": \"Test Me 123\"}")


                    .when()
                    .post(url+productsEndpoint);
        Assertions.assertEquals(201,response.statusCode());
        String createdID = response.jsonPath().getString("id").toString();
        Assertions.assertEquals("Test Me 123",response.jsonPath().getString("name"));

        response = given()
                    .port(80)
                    .auth()
                    .oauth(username,password,"","")

                    .when()
                    .get(url+productsEndpoint+ "/"+createdID);
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("Test Me 123",response.jsonPath().getString("name"));


        response = given()
                    .port(80)
                    .auth()
                    .oauth(username,password,"","")
                    .queryParam("per_page",100)

                    .when()
                    .get(url+productsEndpoint);
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertTrue(response.prettyPrint().contains(createdID));
        Assertions.assertTrue(response.prettyPrint().contains("Test Me 123"));

    }
}
