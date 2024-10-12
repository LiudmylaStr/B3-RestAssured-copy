package io.loopcamp.test.day09_a_autorization;
import io.loopcamp.test.utils.DocuportApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class DocuportApiTest extends DocuportApiTestBase {

    /**
     Given accept type is json
     And header Authorization is access_token of employee
     When I send GET request to  /api/v1/identity/departments/all
     Then status code is 200
     And content type is application/json; charset=utf-8
     And departments info is presented in payload
     */

    @DisplayName("GET /api/v1/identity/departments/all")
    @Test
    public void getAllDepartmentsTest () {

        String accessToken = getAccessToken("employee");
        // System.out.println("ACCESS TOKEN:" + accessToken);

        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .when().get("/api/v1/identity/departments/all");

        //response.prettyPrint();
        //System.out.println(response.statusCode());

        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), is("application/json; charset=utf-8"));
        assertThat(response.path("displayText"), hasItems("Tax", "Bookkeeping"));
    }


    /**
     * Given accept type is Json
     * And header Authorization is access token of supervisor
     * When I send GET request to "/api/v1/company/states/all/"
     * Then status code is 200
     * And content type is json
     * And state "Washington D.C." is in the response body
     */
    @DisplayName("GET /api/v1/company/states/all/")
    @Test
    public void getAllStatesTest () {

        given().accept(ContentType.JSON)
                .and().header("Authorization", getAccessToken("supervisor"))
                .when().get("/api/v1/company/states/all/")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("name", hasItem("Washington D.C."));

    }

    /**
     Given accept type is Json
     And header Authorization is access token for advisor
     When I send GET request to /api/v1/document/clients/all
     Then status code is 200
     And content type is application/json; charset=utf-8
     body index 1 matches data:
     {
     "id": 31,
     "name": "3tseT",
     "clientType": 1,
     "isActive": true,
     "advisor": null
     }
     */

    @DisplayName("GET /api/v1/document/clients/all")
    @Test
    public void getAllClientsTest () {

//        System.out.println(getAccessToken("advisor"));
//        List<Map <String, Object>> clientMap = given().accept(ContentType.JSON)
//                .and().header("Authorization", getAccessToken("advisor"))
//                .when().get("/api/v1/document/clients/all")
//                .then().assertThat().statusCode(HttpStatus.SC_OK)
//                .and().contentType(ContentType.JSON)
//                .and().extract().body().as(List.class);

        // To be able to get specific Json Object I need pass the "path to the key" -- > [0]
        // So, instead of validating/asserting in one statement, I assigned all into Response
        // And, then used path method to get the response as Map
        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization", getAccessToken("advisor"))
                .when().get("/api/v1/document/clients/all");

        Map <String, Object> clientMap = response.path("[1]");

        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), is("application/json; charset=utf-8"));
        assertThat(clientMap.get("id"), equalTo(31));
        assertThat(clientMap.get("name"), equalTo("3tseT"));
        assertThat(clientMap.get("clientType"), equalTo(1));
        assertThat(clientMap.get("isActive"), equalTo(true));
        assertThat(clientMap.get("advisor"), equalTo(null));

        /*
            {
              "id": 31,
              "name": "3tseT",
              "clientType": 1,
              "isActive": true,
              "advisor": null
            }
         */
    }
}


