package org.example;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    @Test
    public void testEmptyStringReturnsZero() {
        StringCalculator calc = new StringCalculator();
        assertEquals(0, calc.add(""));
    }

    @Test
    public void testSingleNumberReturnsValue() {
        StringCalculator calc = new StringCalculator();
        assertEquals(1, calc.add("1"));
        assertEquals(42, calc.add("42"));
    }

    @Test
    public void testTwoNumbersReturnSum() {
        StringCalculator calc = new StringCalculator();
        assertEquals(3, calc.add("1,2"));
        assertEquals(10, calc.add("4,6"));
    }

    @Test
    public void testSpacesAroundNumbersAreHandled() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add(" 1 , 5 "));
    }

    @Test
    public void testMultipleNumbersReturnSum() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("1,2,3"));
        assertEquals(10, calc.add("1,2,3,4"));
        assertEquals(21, calc.add("1,2,3,4,5,6"));
    }

    @Test
    public void testLargeInput() {
        StringCalculator calc = new StringCalculator();
        String input = "1,2,3,4,5,6,7,8,9,10";
        assertEquals(55, calc.add(input));
    }
    @Test
    public void testNumbersWithOnlyNewline() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("1\n2\n3"));
    }

    @Test
    public void testNewlinesAndCommas() {
        StringCalculator calc = new StringCalculator();
        assertEquals(10, calc.add("1\n2\n3,4"));
    }
}
