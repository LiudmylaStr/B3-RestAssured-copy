package io.loopcamp.test.day11_methodsourcexel_mock_apis;

import io.loopcamp.test.utils.DocuportApiTestBase;
import io.loopcamp.test.utils.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class DocuportApiMethodSourceExcelTest extends DocuportApiTestBase {


    //create a method specifically for userCredentials that reads the data from file and stores into a collection, and return it
    public static List<Map<String, String>> getUserCredentials() {
        String filePath = "src/test/resources/DocBeta.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(filePath, "BETA2");
        List<Map<String, String>> data = excelUtil.getDataList();
        return data;
    }


    @ParameterizedTest
    @MethodSource("getUserCredentials")
    public void docuportUserTokenTest(Map<String, String> data) { // {"email:client","password:Group1"}
        System.out.println(data.get("email") + " token: " + getAccessToken(data.get("email")));
        System.out.println("#####################################################################");
    }


    // IN the following, we just did the similar testing but with the help for loop instead of @ParameterizedTest
    @Test
    public void loginTestUsingLoop() {
        List<Map<String, String>> data = getUserCredentials();

        for (Map<String, String> eachMap : data) {

            System.out.println(eachMap.get("email") + " token: " + getAccessToken(eachMap.get("email")));
            System.out.println("#####################################################################");

        }

    }


}