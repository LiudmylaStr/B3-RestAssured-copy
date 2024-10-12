package io.loopcamp.test.day08_a_jsonchema_validation;

import io.loopcamp.pojo.Minion;
import io.loopcamp.test.utils.MinionApiTestBase;
import io.loopcamp.test.utils.MinionRestUtil;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionPostJsonSchemaValidationTest extends MinionApiTestBase {

    /**
     given accept is json
     and content type is json
     and body is:
     {
     "gender": "Male",
     "name": "TestPost1"
     "phone": 1234560000
     }
     When I send POST request to /minions
     Then status code is 201
     And body should match MinionPostSchema.json schema
     */

    @DisplayName("POST - validate JSON SCHEMA")
    @Test
    public void minionPostJsonSchemaValidationTest () {

        // Option 1 - we put it just to review
        String reqBody = "             {\n" +
                "                 \"gender\": \"Male\",\n" +
                "                 \"name\": \"TestPost1\"\n" +
                "                 \"phone\": 1234560000\n" +
                "             }";

        // Option 2 - used this
        Map<String, Object> reqBody2 = new HashMap<>();
        reqBody2.put("gender", "Male");
        reqBody2.put("name", "TestPost2");
        reqBody2.put("phone", 1234560000);



        // Option 3 - we put it just to review
        Minion reqBody3 = new Minion();
        reqBody3.setGender("Male");
        reqBody3.setName("TestPost1");
        reqBody3.setPhone("1234560000");


        int newMinionId = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(reqBody2)
                .when().post()
                .then().assertThat().statusCode(HttpStatus.SC_CREATED)
                .and().body(
                        JsonSchemaValidator.matchesJsonSchema(
                                new File("src/test/resources/jsonschemas/MinionPostSchema.json")))
                .log().all()
                .and().extract().jsonPath().getInt("data.id");


        System.out.println("Newly generate Minion ID: " + newMinionId);
        MinionRestUtil.deleteMinionById(newMinionId);



    }



}
