package org.example;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
    @Test
    public void testCustomDelimiterSemicolon() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("//;\n1;2;3"));
    }

    @Test
    public void testCustomDelimiterPipe() {
        StringCalculator calc = new StringCalculator();
        assertEquals(15, calc.add("//|\n4|5|6"));
    }

    @Test
    public void testCustomDelimiterSpecialChar() {
        StringCalculator calc = new StringCalculator();
        assertEquals(10, calc.add("//.\n2.3.5"));
    }

    @Test
    public void testNegativeNumberThrowsException() {
        StringCalculator calc = new StringCalculator();
        NegativeNumberException ex = assertThrows(NegativeNumberException.class, () -> calc.add("1,-2,3"));
        assertEquals("negative numbers not allowed [-2]", ex.getMessage());
    }

    @Test
    public void testMultipleNegativeNumbersThrowException() {
        StringCalculator calc = new StringCalculator();
        NegativeNumberException ex = assertThrows(NegativeNumberException.class, () -> calc.add("1,-2,-4,3"));
        assertEquals("negative numbers not allowed [-2, -4]", ex.getMessage());
    }

    @Test
    public void testNegativeZeroIsTreatedAsZero() {
        StringCalculator calc = new StringCalculator();
        assertEquals(0, calc.add("-0"));
    }

    @Test
    public void testNumbersGreaterThanThousandAreIgnored() {
        StringCalculator calc = new StringCalculator();
        assertEquals(2, calc.add("2,1001"));
        assertEquals(1002, calc.add("2,1000"));  // 1000 is allowed
    }

    @Test
    public void testNumbersWithNewlineAndGreaterThanThousand() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("1\n2\n1001,3"));
    }

    @Test
    public void testCustomDelimiterWithLargeNumberIgnored() {
        StringCalculator calc = new StringCalculator();
        assertEquals(5, calc.add("//;\n2;1001;3"));
    }

    @Test
    public void testOnlyLargeNumbersReturnZero() {
        StringCalculator calc = new StringCalculator();
        assertEquals(0, calc.add("1001,2000,3001"));
    }

    @Test
    public void testDelimiterOfAnyLength() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("//[***]\n1***2***3"));
    }

    @Test
    public void testDelimiterOfLengthOneUsingBrackets() {
        StringCalculator calc = new StringCalculator();
        assertEquals(3, calc.add("//[*]\n1*2"));
    }

    @Test
    public void testMultipleCustomDelimitersOfLengthOne() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleCustomDelimitersOfDifferentLengths() {
        StringCalculator calc = new StringCalculator();
        assertEquals(10, calc.add("//[***][%%]\n1***2%%3***4"));
    }

    @Test
    public void testCustomDelimiterAndNumbersAboveThousand() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("//[***]\n1***2***1001***3"));
    }

    @Test
    public void testMultipleDelimitersOfLengthOne() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleDelimitersOfDifferentLengths() {
        StringCalculator calc = new StringCalculator();
        assertEquals(10, calc.add("//[***][%%]\n2***3%%5"));
    }

    @Test
    public void testMultipleDelimitersWithSpecialChars() {
        StringCalculator calc = new StringCalculator();
        assertEquals(15, calc.add("//[$$][@!]\n5$$5@!5"));
    }

    @Test
    public void testMultipleDelimitersWithNegativeNumbers() {
        StringCalculator calc = new StringCalculator();
        NegativeNumberException ex = assertThrows(NegativeNumberException.class, () -> calc.add("//[*][#]\n1*-2#3"));
        assertEquals("negative numbers not allowed [-2]", ex.getMessage());
    }

    @Test
    public void testMultipleDelimitersWithNumberGreaterThan1000() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add("//[--][++]\n1--1001++5"));
    }

}
