package io.loopcamp.test.day05_deserialization;

import io.loopcamp.pojo.Minion;
import io.loopcamp.pojo.MinionSearch;
import io.loopcamp.test.utils.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionSearchPojoTest extends MinionApiTestBase {

    /**
     Given accept type is json
     And query param nameContains value is "e"
     And query param gender value is "Female"
     When I send get request to /minions/search
     --------
     Then status code is 200
     And content type is Json
     And json response data is as expected
     */
    @DisplayName("")
    @Test
    public void minionSearchPojoTest () {

        Response response = given().accept(ContentType.JSON).
                and().queryParam("nameContains", "e").
                and().queryParam("gender", "Female").
                when().get("/search");

        // response.prettyPrint();
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());



        // DESERIALIZATION - JSON RESPONSE BODY to MINIONSEARCH pojo class (java custom class)
        MinionSearch minionSearch = response.body().as(MinionSearch.class);


        //How can you get me the total elements
        System.out.println("Total Elements: " + minionSearch.getTotalElement());


        // How can I get all minions that was part of the response body
        System.out.println("All minions with name contains 'e' and gender Female: \n" + minionSearch.getAllMinions());


        // get me the first minion
        System.out.println("First minion: " + minionSearch.getAllMinions().get(0));


        // Get me only the names from response body
        for (Minion eachMinion : minionSearch.getAllMinions() ) {
            System.out.println(eachMinion.getName());


        }


    }

}
