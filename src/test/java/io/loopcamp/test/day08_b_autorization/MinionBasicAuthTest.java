package io.loopcamp.test.day08_b_autorization;

import io.loopcamp.test.utils.MinionSecureApiTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionBasicAuthTest extends MinionSecureApiTestBase {

    /**
     [POSITIVE TEST - SUNNY TEST]
     given accept type is json
     and basic auth credentials are loopacademy , loopacademy
     When user sends GET request to /minions
     Then status code is 200
     And content type is json
     And print response
     */


    @DisplayName("GET - with Auth")
    @Test
    public void getMinionWithAuthTest () {

        given().accept(ContentType.JSON)
                .and().auth().basic("loopacademy", "loopacademy")
                .when().get()
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .log().all();

    }


    /**
     [NEGATIVE TEST - RAINY TEST]
     given accept type is json
     When user sends GET request to /minions
     Then status code is 401
     And content type is json
     And error is "Unauthorized"
     And log response
     */

    @Test
    public void getAllMinionsAuthNegTest () {

        List<Map<String, String>> allCombination = new ArrayList<>();
        Map <String, String> opt1 = new HashMap<>();
        opt1.put("username", "");
        opt1.put("password", "");


        Map <String, String> opt2 = new HashMap<>();
        opt2.put("username", "Invalid");
        opt2.put("password", "Invalid");

        allCombination.add(opt1);
        allCombination.add(opt2);



        // To avoid stopping on the first fail we can apply the softassertions
        for (Map<String, String> each : allCombination) {

            given().accept(ContentType.JSON)
                    .and().auth().basic(each.get("username"), each.get( "password"))// "", ""  | "Invalid", "Invalid"
                    .when().get()
                    .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                    .and().contentType(ContentType.JSON)
                    .log().all();
            System.out.println();
        }



    }
}






