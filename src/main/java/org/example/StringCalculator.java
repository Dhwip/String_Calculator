package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = "[,\n]";
        String numberSection = numbers;

        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf('\n');
            String delimiterDefinition = numbers.substring(2, delimiterEndIndex);

            if (delimiterDefinition.startsWith("[") && delimiterDefinition.endsWith("]")) {
                List<String> delimiters = new ArrayList<>();
                Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterDefinition);
                while (matcher.find()) {
                    delimiters.add(Pattern.quote(matcher.group(1)));
                }
                delimiter = String.join("|", delimiters);
            } else {
                delimiter = Pattern.quote(delimiterDefinition);
            }

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
            else if(num <= 1000){
                sum += num;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new NegativeNumberException("negative numbers not allowed " + negativeNumbers);
        }

        return sum;
    }
}

