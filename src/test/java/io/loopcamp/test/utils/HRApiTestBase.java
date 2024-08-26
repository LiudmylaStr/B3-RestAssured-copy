package io.loopcamp.test.utils;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class HRApiTestBase {

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("hr.api.url");
    }

}