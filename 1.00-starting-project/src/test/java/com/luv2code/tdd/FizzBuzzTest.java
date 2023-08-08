package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {

    //If number is divisible by 3, print Fizz
    @Test
    @Order(1)
    @DisplayName("Divisible by Three")
    void testForDivisibleByThree(){

        String expetcted = "Fizz";
        assertEquals(expetcted, FizzBuzz.competer(3), "Shoudl return Fizz");

    }

    //If number is divisible by 5, print Buzz
    @Test
    @Order(2)
    @DisplayName("Divisible by Five")
    void testForDivisibleByFive(){

        String expetcted = "Buzz";
        assertEquals(expetcted, FizzBuzz.competer(5), "Shoudl return Buzz");

    }

    //If number is divisible by 3 and 5, print FizzBuzz
    @Test
    @Order(3)
    @DisplayName("Divisible by Three and Five")
    void testForDivisibleByThreeAndFive(){

        String expetcted = "FizzBuzz";
        assertEquals(expetcted, FizzBuzz.competer(15), "Should return FizzBuzz");

    }

    //If number is NOT divisible by 3 or 5, then print the number
    @Test
    @Order(4)
    @DisplayName("Not divisible by Three or Five")
    void testForDivisibleByThreeOrFive(){

        String expetcted = "1";
        assertEquals(expetcted, FizzBuzz.competer(1), "Should return 1");

    }

    //60001230804534083855


    @Order(5)
    @DisplayName("Testing with Small data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    void testSmallDataFile(int value, String expected){

        assertEquals(expected, FizzBuzz.competer(value), "Should return "+expected);

    }


}