package com.luv2code.tdd;

public class FizzBuzz {

    public static String competer(int value){
        StringBuilder result = new StringBuilder();
        if(value % 3 == 0) result.append("Fizz");
        if(value % 5 == 0) result.append("Buzz");
        if(result.length() == 0) result.append(value);
        return result.toString();
    }


}
