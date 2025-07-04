package org.example;

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

        for (String part : parts) {
            sum += Integer.parseInt(part.trim());
        }

        return sum;
    }
}

