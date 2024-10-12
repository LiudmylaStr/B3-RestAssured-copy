package io.loopcamp.test.day09_b_specifications;

import io.loopcamp.test.utils.DocuportApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class DocuportSpecTest extends DocuportApiTestBase {


    /**
     Given accept type is json
     And header Authorization is access_token of employee title
     When I send GET request to /api/v1/company/organizer-titles/all
     Then status code is 200
     And content type is json
     And employee title info is presented in payload
     */

//    RequestSpecification reqSpec = given().accept(ContentType.JSON)
//            .and().header("Authorization", getAccessToken("employee"));
//
//
//    ResponseSpecification resSpec = expect().statusCode(HttpStatus.SC_OK)
//                .and().contentType(ContentType.JSON);

    @DisplayName("GET /api/v1/company/organizer-titles/all")
    @Test
    public void employeeTitleTest () {

//        List <Map<String, ?>> list = given().accept(ContentType.JSON)
//                .and().header("Authorization", getAccessToken("employee"))
        List <Map<String, ?>> list = given().spec(reqSpec)
                .when().get("/api/v1/company/organizer-titles/all")
//                .then().assertThat().statusCode(HttpStatus.SC_OK)
//                .and().contentType(ContentType.JSON)
                .then().spec(resSpec)
                .and().body("displayText", hasItems("President", "Vice President"))
                .and().extract().body().as(List.class); // DESERIALIZATION


        System.out.println(list);
        assertEquals("1", list.get(0).get("value"));
        assertEquals("President", list.get(0).get("displayText"));


        //assert that second object in response payload by using hamcrest matchers
        given().spec(reqSpec)
                .when().get("/api/v1/company/organizer-titles/all")
                .then().spec(resSpec)
                .and().body("[1].value", is("2"),
                        "[1].displayText", equalTo("Vice President"));



        //assert that all objects in response payload with using hamcrest matchers
        given().spec(reqSpec)
                .when().get("/api/v1/company/organizer-titles/all")
                .then().spec(resSpec)
                .and().body("value", hasItems("1", "2", "3", "4"),
                        "displayText", hasItems("President", "Vice President", "Treasurer", "Secretary"),
                        "displayText", hasSize(4))
                .log().all();
    }


}

