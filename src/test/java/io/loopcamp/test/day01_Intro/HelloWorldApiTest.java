package io.loopcamp.test.day01_Intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class HelloWorldApiTest {
    String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";

    @DisplayName("Hello World GET request")
    @Test
    public void helloWorldGetRequestTest(){
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        response.prettyPrint();
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        // assert/validate/verify that RESPONSE STATUS CODE is 200
        Assertions.assertEquals(200, response.statusCode(), "Did not match");

        // We can also assign the RESPONSE STATUS CODE into a variable and use it.
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200, actualStatusCode, "Did not match");


        // asString(); method will return the RESPONSE BODY as a String
        String actualResponseBody = response.asString();

        // Here we validated that the RESPONSE BODY contains our expected text (Hello World!)
        Assertions.assertTrue(actualResponseBody.contains("Hello World!"));
    }

}