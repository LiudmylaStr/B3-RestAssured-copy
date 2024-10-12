package io.loopcamp.test.day08_a_jsonchema_validation;
import io.loopcamp.test.utils.ConfigurationReader;
import io.loopcamp.test.utils.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionGetJsonSchemaValidationTest extends MinionApiTestBase {
    /**
     given accept type is json
     and path param id is 15
     when I send GET request to /minions/{id}
     Then status code is 200
     And json payload/body matches SingleMinionSchema.json
     */

    @DisplayName("GET /{id} - validate JSON SCHEMA")
    @Test
    public void singleMinionSchemaValidationTest () {

        given().accept(ContentType.JSON)
                .and().pathParam("minionId", 15)
                //.when().get(ConfigurationReader.getProperty("minion.api.url")+ "/{minionID}"); // Since I read from baseUri from parent class, I do the line below.
                .when().get("/{minionId}")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                //.and().body("id", is(15))
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SingleMinionSchema.json")))
                .log().all();
    }
    /**
     given accept type is json
     when I send GET request to /minions
     Then status code is 200
     And json payload/body matches AllMinionsSchema.json
     */

    @DisplayName("GET all minions - validate JSON SCHEMA")
    @Test
    public void allMinionSchemaValidationTest () {

        Response response = given().accept(ContentType.JSON)
                .when().get();

        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/AllMinionSchema.json"))).log().all();

        assertThat(response.path("[0].id"), is(equalTo(1)));


    }

    /**
     given accept type is json
     And query params: nameContains "e" and gender "Female"
     when I send GET request to /Minions/search
     Then status code is 200
     And json payload/body matches SearchMinionsSchema.json
     */

    @DisplayName("GET /search?K-V - - validate JSON SCHEMA")
    @Test
    public void searchMinionSchemaValidationTest () {

        given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/search")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/jsonschemas/SearchMinionsSchema.json")))
                .log().all();
    }




}



