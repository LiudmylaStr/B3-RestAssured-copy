package io.loopcamp.test.day09_b_specifications;

import io.loopcamp.test.utils.MinionSecureApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionSpecTest extends MinionSecureApiTestBase {

    RequestSpecification requestSpec =   given().accept(ContentType.JSON)
            .and().auth().basic("loopacademy", "loopacademy");


    ResponseSpecification responseSpec = expect().statusCode(HttpStatus.SC_OK)
            .and().contentType(ContentType.JSON);

    @DisplayName("GET all minions with Req & Res Specification")
    @Test
    public void allMinionTest (){

//        given().accept(ContentType.JSON)
//                .and().auth().basic("loopacademy", "loopacademy")
        given().spec(requestSpec)
                .when().get()
                .then().spec(responseSpec)
//                .then().assertThat().statusCode(HttpStatus.SC_OK)
//                .and().contentType(ContentType.JSON)
                .log().all();
    }


    @DisplayName("GET single minion with Req & Res Specification")
    @Test
    public void singleMinionTest () {

//        given().accept(ContentType.JSON)
//                .and().auth().basic("loopacademy", "loopacademy")
        given().spec(requestSpec)
                .and().pathParam("id", 10)
                .and().get("/{id}")
                .then().spec(responseSpec)
//                .then().assertThat().statusCode(HttpStatus.SC_OK)
//                .and().contentType(ContentType.JSON)
                .and().body("name", is(equalTo("Lorenza")));

    }

}