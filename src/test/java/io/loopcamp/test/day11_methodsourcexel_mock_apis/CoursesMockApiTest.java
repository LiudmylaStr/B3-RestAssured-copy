package io.loopcamp.test.day11_methodsourcexel_mock_apis;

import io.loopcamp.test.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoursesMockApiTest {

    /**
     Given accept type is json
     When I send GET request to /courses
     Then status code is 200
     And content type is json
     And body courseIds contain 1,2,3
     And courseNames are "Java SDET", "RPA Developer", "Salesforce Automation"
     */


    @BeforeAll
    public static void setUp (){
        baseURI = ConfigurationReader.getProperty("mock_server_url");
    }


    @DisplayName("GET /courses")
    @Test
    public void coursesMockApiTest () {

        given().accept(ContentType.JSON)
                .when().get("/courses")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("courseIds", hasItems(1, 2, 3),
                        "courseNames", hasItems("Java SDET", "RPA Developer", "Salesforce Automation") )
                .log().all();

    }






}