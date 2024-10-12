package io.loopcamp.test.day10_data_driven_test;

import io.loopcamp.test.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class JUniteValueSourseTest {
    @ParameterizedTest
    //runs the test repeatedly for all sets of param inputs
    @ValueSource(ints = {15,34,4,5,23,45,786,234,23})
    public void numberTest(int num){
        System.out.println("num: "+ num);
        assertThat(num, is(greaterThan(10)));
    }

    @ParameterizedTest
    @ValueSource(strings ={"Ayaz", "OlexM", "Java","Tom","API"})
    public void nameTest (String str) {
        System.out.println("Hello"+ str);
        assertThat(str, containsString("a"));
    }

    @BeforeAll
    public static void setUp (){
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }


    @ParameterizedTest
    @ValueSource(ints = {22192, 22033, 19075, 27798, 12494})
    public void zipcodeTest (int zipcode) {

        given().accept(ContentType.JSON)
                .and().pathParam("zipCode", zipcode)
                .when().get("/US/{zipCode}")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .log().all();
    }

}
