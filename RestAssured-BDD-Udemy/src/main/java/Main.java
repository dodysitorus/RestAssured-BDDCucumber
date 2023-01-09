import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

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
        // POST PLACE API
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type","application/json")
                .body(payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server","Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String place_id = js.getString("place_id");
        System.out.println(place_id);

        // PUT/PACTH PLACE API

        String newAddress = "Summer Walk, Africa";

        given().log().all().queryParam("key", "qaclikc123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("/maps/api/place/get/json")
                .then().assertThat().statusCode(200)
                .log().all()
//                .body("msg", equalTo("Address successfully updated"))
                .extract().response().asString();

//        GET PLACE API

        String getPlace = given().log().all().queryParam("key", "qaclikc123")
                .queryParam("place_id", place_id)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        JsonPath json = new JsonPath(getPlace);
        String actualAddress = json.getString("address");
        System.out.println(actualAddress);
    }
}