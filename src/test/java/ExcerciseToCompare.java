import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ExcerciseToCompare {

    private String username = "ck_8da07afd3503a4242e95a2d5bad99cc2fb55c552";
    private String password = "cs_e57e8cadc42ca42064c44239853dda9aa2e77474";
    private String url = "http://localhost/fakestore/wp-json/wc/v3/";
    private String productsEndpoint = "products";
    @Test
    public void testMe() {
        //Tworzymy nowy obiekt
        Response response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .contentType("application/json")
                .body("{\"name\":\"Test Me 123\"}")
                .when()
                .post(url + productsEndpoint);
        //sprawdzamy kod odpowiedzi (to jest POST - oczekujemy 201!!)
        Assertions.assertEquals(201, response.statusCode());
        //pobieramy do zmiennej ID nowo utworzonego obiektu
        String createdID = response.jsonPath().get("id").toString();
        //sprawdzamy, czy w odpowiedzi po utworzeniu nazwa jest prawidlowa
        Assertions.assertEquals("Test Me 123", response.jsonPath().get("name"));
        //pobieramy obiekt po ID
        response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .when()
                .get(url + productsEndpoint + "/" + createdID);
        //sprawdzamy kod odpowiedzi (tu jest GET - tu oczekujemy 200)
        Assertions.assertEquals(200, response.statusCode());
        //sprawdzamy czy nazwa jest taka, jakiej oczekujemy
        Assertions.assertEquals("Test Me 123", response.jsonPath().get("name"));
        //pobieramy list produktow - zwracam uwage na parametr - musimy pobrac wiecej niz domyslne 10!
        response = given()
                .port(80)
                .auth()
                .oauth(username, password, "", "")
                .queryParam("per_page", 100)
                .when()
                .get(url + productsEndpoint);
        //sprawdzamy kod odpowiedzi
        Assertions.assertEquals(200, response.statusCode());
        //sprawdzamy, ze ID naszego obiektu w ogole istnieje w tej liscie
        Assertions.assertTrue(response.prettyPrint().contains(createdID));
        //sprawdzamy, ze nazwa naszego obiektu w ogole wystepuje w tej liscie
        Assertions.assertTrue(response.prettyPrint().contains("Test Me 123"));
    }
}

