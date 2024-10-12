package io.loopcamp.test.day10_data_driven_test;

import io.loopcamp.test.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CsvFileSourceTest {

    @BeforeAll
    public static void setUp (){
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/ZipCodes.csv", numLinesToSkip = 1)
    public void zipCodCsvFileTest (String state, String city) {

        Map<String, String> data = new HashMap<>();
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
