package io.loopcamp.test.day04_a_jsonPath;

import io.loopcamp.test.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeWorkQ1 extends HRApiTestBase {

    /* Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2 */

    @DisplayName("GET /countries")
    @Test
    public void jsonPathValidateUSA() {


        Response response = given().accept(ContentType.JSON).
                and().pathParams("country_id", "US")
                .when().get("/countries/{country_id}");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());


        JsonPath jsonPath = response.jsonPath();
        assertEquals("US", jsonPath.getString("country_id"));
        assertEquals("United States of America", jsonPath.getString("country_name"));
        assertEquals(2, jsonPath.getInt("region_id"));
    }
}