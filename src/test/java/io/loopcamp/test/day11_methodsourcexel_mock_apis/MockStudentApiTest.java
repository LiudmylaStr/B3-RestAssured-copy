package io.loopcamp.test.day11_methodsourcexel_mock_apis;

import io.loopcamp.test.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MockStudentApiTest {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("mock_server_url");
    }


    @Test
    public void studentTest() {

        given().accept(ContentType.JSON)
                .when().get("/student/1")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("studentId", is(equalTo(1)));

    }
}