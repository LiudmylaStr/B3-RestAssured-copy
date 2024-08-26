package io.loopcamp.test.day04_a_jsonPath;

import io.loopcamp.test.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeWorkQ2 extends HRApiTestBase {

    /* - Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
            - And Content - Type is Json
- And all job_ids start with 'SA'
            - And all department_ids are 80
            - Count is 25 */
    @DisplayName("GET /employees?q={department_id:80}")
    @Test
    public void jsonQueryValidateJobs() {


        Response response = given().accept(ContentType.JSON).
                and().queryParams("q", "{\"department_id\":80}")
                .when().get("/employees");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        List<String> jobsId = jsonPath.getList("items.job_id");
        for (String each : jobsId) {
            assertTrue(each.startsWith("SA"));
        }

        List<Integer> departmentId = jsonPath.getList("items.department_id");
        for (Integer each : departmentId) {
            assertTrue(each == 80);
        }
        assertEquals(jobsId.size(), departmentId.size(), 25);

    }
}
