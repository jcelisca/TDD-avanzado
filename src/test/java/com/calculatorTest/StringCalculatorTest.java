package com.calculatorTest;

import com.calculator.StringCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.rmi.UnexpectedException;

public class StringCalculatorTest {
    public StringCalculator stringCalculatorInstance = new StringCalculator();

    //An empty string returns zero
    @Test
    public void emptyStringTest() throws Exception {
        Assertions.assertEquals(0,stringCalculatorInstance.add(""));
    }

    // A single number returns the value
    @Test
    public void singleNumberReturnItsValueTest() throws Exception {
        Assertions.assertEquals(2,stringCalculatorInstance.add("2"));
        Assertions.assertEquals(1,stringCalculatorInstance.add("1"));
    }

    //Two numbers, comma delimited, returns the sum
    @Test
    public void twoNumbersCommaDelimitedReturnsTheSumTest() throws Exception {
        Assertions.assertEquals(2,stringCalculatorInstance.add("0,2"));
        Assertions.assertEquals(2,stringCalculatorInstance.add("1,1"));
        Assertions.assertEquals(3,stringCalculatorInstance.add("1,2"));
    }

    // Two numbers, newline delimited, returns the sum
    @Test
    public void sameThatBeforeButNewLineDelimitedTest() throws Exception {
        Assertions.assertEquals(2,stringCalculatorInstance.add("0\n2"));
        Assertions.assertEquals(2,stringCalculatorInstance.add("1\n1"));
        Assertions.assertEquals(3,stringCalculatorInstance.add("1\n2"));
    }

    // Three numbers, delimited either way, returns the sum
    @Test
    public void sameThatBeforeButAllDelimitersWorksTest() throws Exception {
        Assertions.assertEquals(6,stringCalculatorInstance.add("1\n2,3"));
        Assertions.assertEquals(7,stringCalculatorInstance.add("1,2\n4"));
    }

    // Negative numbers throw an exception

    @Test()
    public  void negativeNumbersThrowsAnExceptionTest() throws Exception {
        Assertions.assertThrows(Exception.class,()->stringCalculatorInstance.add("-1,-1"));
    }

    // Numbers greater than 1000 are ignored
    @Test
    public void greaterThan1000AreIgnoredTest() throws Exception {
        Assertions.assertEquals(1002,stringCalculatorInstance.add("2,1000"));
        Assertions.assertEquals(2,stringCalculatorInstance.add("2,1001"));
    }

    // A single char delimiter can be defined on the first line
    @Test
    public void singleCharDelimiterTest() throws Exception {
        Assertions.assertEquals(1002,stringCalculatorInstance.add("#2#1000"));
    }

    @Test
    public void severalCharDelimiterTest() throws Exception {
        Assertions.assertEquals(1000,stringCalculatorInstance.add("[###]200[###]800"));
        Assertions.assertEquals(650,stringCalculatorInstance.add("[##]50[##]600"));
    }
}
