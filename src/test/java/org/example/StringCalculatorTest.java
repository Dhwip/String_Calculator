package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void testEmptyStringReturnsZero() {
        StringCalculator calc = new StringCalculator();
        assertEquals(0, calc.add(""), "Empty string should return 0");
    }

    @Test
    public void testSingleNumberReturnsValue() {
        StringCalculator calc = new StringCalculator();
        assertEquals(1, calc.add("1"), "Single number should return the number itself");
        assertEquals(42, calc.add("42"), "Single number should return the number itself");
    }

    @Test
    public void testTwoNumbersReturnSum() {
        StringCalculator calc = new StringCalculator();
        assertEquals(3, calc.add("1,2"), "1 + 2 should return 3");
        assertEquals(10, calc.add("4,6"), "4 + 6 should return 10");
    }

    @Test
    public void testSpacesAroundNumbersAreHandled() {
        StringCalculator calc = new StringCalculator();
        assertEquals(6, calc.add(" 1 , 5 "), "Spaces around numbers should be ignored");
    }
}
