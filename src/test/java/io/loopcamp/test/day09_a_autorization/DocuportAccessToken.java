package io.loopcamp.test.day09_a_autorization;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class DocuportAccessToken {

    /**
     Given accept type is Json
     And content type is Json
     And body is:
     {
     "usernameOrEmailAddress": "b1g1_client@gmail.com",
     "password": "Group1"
     }
     When I send POST request to
     Url: https://beta.docuport.app/api/v1/authentication/account/authenticate
     Then status code is 200
     And accessToken should be present and not empty
     */

    @DisplayName("POST /authentication/account/authenticate - request api authentication")
    @Test
    public void docuportLoginTest () {

        String reqJsonBody = "{\n" +
                "                 \"usernameOrEmailAddress\": \"b1g1_client@gmail.com\",\n" +
                "                 \"password\": \"Group1\"\n" +
                "             }";

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and().body(reqJsonBody)
                .when().post("https://beta.docuport.app/api/v1/authentication/account/authenticate");

        // response.prettyPrint();
        assertThat(response.statusCode(), is(equalTo(HttpStatus.SC_OK)));

        // System.out.println("Access Token: " + response.path("user.jwtToken.accessToken"));
        String accessToken = response.path("user.jwtToken.accessToken");
        assertTrue(accessToken != null && !accessToken.isEmpty());

    }

}







