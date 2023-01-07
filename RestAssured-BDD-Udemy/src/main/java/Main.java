import files.payload;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Main {
    public static void main(String[] args) {
        /*
        There are 3 methods that gonna we use
        - given -> all input details
        - when -> submit the API
        - then -> validate response
         */

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type","application/json")
                .body(payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server","Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);
    }
}