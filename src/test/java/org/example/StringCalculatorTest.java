package org.example;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StringCalculatorTest {

    StringCalculator calc = new StringCalculator();

    @Test
    public void testEmptyStringReturnsZero() {
        assertEquals(0, calc.add(""));
    }

    @Test
    public void testSingleNumberReturnsValue() {
        assertEquals(1, calc.add("1"));
        assertEquals(42, calc.add("42"));
    }

    @Test
    public void testTwoNumbersReturnSum() {
        assertEquals(3, calc.add("1,2"));
        assertEquals(10, calc.add("4,6"));
    }

    @Test
    public void testSpacesAroundNumbersAreHandled() {
        assertEquals(6, calc.add(" 1 , 5 "));
    }

    @Test
    public void testMultipleNumbersReturnSum() {
        assertEquals(6, calc.add("1,2,3"));
        assertEquals(10, calc.add("1,2,3,4"));
        assertEquals(21, calc.add("1,2,3,4,5,6"));
    }

    @Test
    public void testLargeInput() {
        String input = "1,2,3,4,5,6,7,8,9,10";
        assertEquals(55, calc.add(input));
    }
    @Test
    public void testNumbersWithOnlyNewline() {
        assertEquals(6, calc.add("1\n2\n3"));
    }

    @Test
    public void testNewlinesAndCommas() {
        assertEquals(10, calc.add("1\n2\n3,4"));
    }
    @Test
    public void testCustomDelimiterSemicolon() {
        assertEquals(6, calc.add("//;\n1;2;3"));
    }

    @Test
    public void testCustomDelimiterPipe() {
        assertEquals(15, calc.add("//|\n4|5|6"));
    }

    @Test
    public void testCustomDelimiterSpecialChar() {
        assertEquals(10, calc.add("//.\n2.3.5"));
    }

    @Test
    public void testNegativeNumberThrowsException() {
        NegativeNumberException ex = assertThrows(NegativeNumberException.class, () -> calc.add("1,-2,3"));
        assertEquals("negative numbers not allowed [-2]", ex.getMessage());
    }

    @Test
    public void testMultipleNegativeNumbersThrowException() {
        NegativeNumberException ex = assertThrows(NegativeNumberException.class, () -> calc.add("1,-2,-4,3"));
        assertEquals("negative numbers not allowed [-2, -4]", ex.getMessage());
    }

    @Test
    public void testNegativeZeroIsTreatedAsZero() {
        assertEquals(0, calc.add("-0"));
    }

    @Test
    public void testNumbersGreaterThanThousandAreIgnored() {
        assertEquals(2, calc.add("2,1001"));
        assertEquals(1002, calc.add("2,1000"));
    }

    @Test
    public void testNumbersWithNewlineAndGreaterThanThousand() {
        assertEquals(6, calc.add("1\n2\n1001,3"));
    }

    @Test
    public void testCustomDelimiterWithLargeNumberIgnored() {
        assertEquals(5, calc.add("//;\n2;1001;3"));
    }

    @Test
    public void testOnlyLargeNumbersReturnZero() {
        assertEquals(0, calc.add("1001,2000,3001"));
    }

    @Test
    public void testDelimiterOfAnyLength() {
        assertEquals(6, calc.add("//[***]\n1***2***3"));
    }

    @Test
    public void testDelimiterOfLengthOneUsingBrackets() {
        assertEquals(3, calc.add("//[*]\n1*2"));
    }

    @Test
    public void testMultipleCustomDelimitersOfLengthOne() {
        assertEquals(6, calc.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleCustomDelimitersOfDifferentLengths() {
        assertEquals(10, calc.add("//[***][%%]\n1***2%%3***4"));
    }

    @Test
    public void testCustomDelimiterAndNumbersAboveThousand() {
        assertEquals(6, calc.add("//[***]\n1***2***1001***3"));
    }

    @Test
    public void testMultipleDelimitersOfLengthOne() {
        assertEquals(6, calc.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleDelimitersOfDifferentLengths() {
        assertEquals(10, calc.add("//[***][%%]\n2***3%%5"));
    }

    @Test
    public void testMultipleDelimitersWithSpecialChars() {
        assertEquals(15, calc.add("//[$$][@!]\n5$$5@!5"));
    }

    @Test
    public void testMultipleDelimitersWithNegativeNumbers() {
        NegativeNumberException ex = assertThrows(NegativeNumberException.class, () -> calc.add("//[*][#]\n1*-2#3"));
        assertEquals("negative numbers not allowed [-2]", ex.getMessage());
    }

    @Test
    public void testMultipleDelimitersWithNumberGreaterThan1000() {
        assertEquals(6, calc.add("//[--][++]\n1--1001++5"));
    }

    @Test
    public void testMultipleSingleCharDelimiters() {
        assertEquals(6, calc.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleDelimitersOfLengthGreaterThanOne() {
        assertEquals(6, calc.add("//[***][%%%]\n1***2%%%3"));
    }

    @Test
    public void testMultipleDelimitersIncludingSpecialCharacters() {
        assertEquals(10, calc.add("//[***][^][.]\n1***2^3.4"));
    }

    @Test
    public void testIgnoreNumbersGreaterThan1000WithMultiDelimiters() {
        assertEquals(3, calc.add("//[***][%%]\n1***1001%%2"));
    }
}
