import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            String input = new Scanner(new File("input.txt")).useDelimiter("\\Z").next();
            String regex = "mul\\((?<n1>\\d+),(?<n2>\\d+)\\)|(?<enable>do\\(\\))+|(?<disable>don't\\(\\))+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            boolean isEnabled = true;
            int sum1 = 0, sum2 = 0;
            while (matcher.find()) {
                if (matcher.group("enable") != null && !isEnabled) isEnabled = true;
                if (matcher.group("disable") != null && isEnabled) isEnabled = false;
                if (matcher.group("n1") != null && matcher.group("n2") != null) {
                    if (isEnabled) sum2 += Integer.parseInt(matcher.group("n1")) * Integer.parseInt(matcher.group("n2"));
                    sum1 += Integer.parseInt(matcher.group("n1")) * Integer.parseInt(matcher.group("n2"));
                }
            }
            System.out.println("First answer: " + sum1);
            System.out.println("Second answer: " + sum2);
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading file found");
        }
    }
}