package org.example;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = "[,\n]";
        String numberSection = numbers;

        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf('\n');
            String customDelimiter = numbers.substring(2, delimiterEndIndex);
            customDelimiter = java.util.regex.Pattern.quote(customDelimiter);
            delimiter = customDelimiter;
            numberSection = numbers.substring(delimiterEndIndex + 1);
        }

        String[] parts = numberSection.split(delimiter);
        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String part : parts) {
            int num = Integer.parseInt(part.trim());
            if (num < 0) {
                negativeNumbers.add(num);
            }
            else {
                sum += num;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new NegativeNumberException("negative numbers not allowed " + negativeNumbers);
        }

        return sum;
    }
}

