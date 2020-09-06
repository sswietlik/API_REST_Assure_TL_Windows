import io.restassured.path.xml.exception.XmlPathException;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class AssertionPlay extends BaseAPITest {
    @Test
    public void testGet() {
        given()
                .port(80)
                .when()
                .get(productsEndpoint + "/393")

                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .body("name",equalTo("Fuerteventura - Sotavento"));

    }
}
