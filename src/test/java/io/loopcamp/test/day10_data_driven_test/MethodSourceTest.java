package io.loopcamp.test.day10_data_driven_test;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodSourceTest {


    public static List <String> getCountries () {
        List<String> countries = Arrays.asList("Canada", "USA", "Azerbaijan");
        return countries;
    }



    @ParameterizedTest
    @MethodSource("getCountries")
    public void countriesTest (String eachCountry) {

        System.out.println("Country Name: " + eachCountry);


    }
}
