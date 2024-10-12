package io.loopcamp.test.day10_data_driven_test;
import io.loopcamp.test.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CsvSourceDDTTest {


    @ParameterizedTest
    @CsvSource({"7, 34, 32",
            "3, 56, 34",
            "4, 76, 77",
            "4, 87, 87"})
    public void basicAddTest (int num1, int num2, int num3) {

        System.out.println("Num 1: " + num1);
        System.out.println("\tNum 2: " + num2);
        System.out.println("\t\tNum 3: " + num3);

    }
    // If method parameter count is less than CSV count [3 -> "7, 34, 32"], it will take from beginning and assing to paramters
    // If method parameter count is less than CSV count [3 -> "7, 34, 32"], it will throw error
    // SO, make sure the count in each "" is matching through out all in the CsvSource and same number of parameter in the method




    @BeforeAll
    public static void setUp (){
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @CsvSource({"NC, Raleigh",
            "PA, Philadelphia",
            "NJ, Jersey City",
            "Fl, Saint Augustine"})
    public void stateAndCityZipcodeTest (String state, String city) {

        Map <String, String> data = new HashMap<>();
        data.put("stateName", state);
        data.put("cityName", city);

        given().accept(ContentType.JSON)
                .and().pathParams(data)  // Option 1 - Another option you can do
                // .and().pathParams("stateName", state,"cityName", city ) // Option 2- Another option you can do
                //.and().pathParam("stateName", state)  // Option 3 - Another option you can do
                //.and().pathParam("cityName", city)  // Option 3 - Another option you can do
                .when().get("/US/{stateName}/{cityName}")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("places.'place name'", everyItem(is(containsString(city))))
                .log().all();
    }
}
