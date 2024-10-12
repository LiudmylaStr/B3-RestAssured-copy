package io.loopcamp.test.day11_methodsourcexel_mock_apis;

import io.loopcamp.test.utils.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ReadExcelFileDataTest {

    @Test
    public void readDocBetaUsersTest () {

        String excelFilePath = "src/test/resources/DocBeta.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(excelFilePath, "BETA2");

        // Get me all column names
        System.out.println("Column Names: " + excelUtil.getColumnsNames());

        List <Map<String, String>> data = excelUtil.getDataList();
        System.out.println("Data: " + data);

        // Get me all row counts
        System.out.println("Row Count: " + excelUtil.rowCount());


    }

}