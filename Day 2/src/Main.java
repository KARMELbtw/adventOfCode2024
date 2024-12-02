import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static boolean desc1(String input) {
        String[] strings = input.split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        for (String s : strings) {
            nums.add(Integer.parseInt(s));
        }

        for (int i = 0; i < nums.size() - 1; i++) {
            int p = nums.get(i);
            int n = nums.get(i + 1);
            if ((p - n) < 1 || (p - n) > 3) {
                return false;
            }
        }
        return true;
    }

    static boolean asc1(String input) {
        String[] strings = input.split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        for (String s : strings) {
            nums.add(Integer.parseInt(s));
        }

        for (int i = 0; i < nums.size() - 1; i++) {
            int p = nums.get(i);
            int n = nums.get(i + 1);
            if ((n - p) < 1 || (n - p) > 3) {
                return false;
            }
        }
        return true;
    }

    static boolean desc2(String input) {
        String[] strings = input.split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        boolean isSafe;
        for (String s : strings) {
            nums.add(Integer.parseInt(s));
        }
        for (int i = 0; i < nums.size(); i++) {
            ArrayList<Integer> dampedNums = new ArrayList<>(nums);
            dampedNums.remove(i);
            isSafe = true;
            for (int j = 0; j < dampedNums.size() - 1; j++) {
                int p = dampedNums.get(j);
                int n = dampedNums.get(j + 1);
                if ((p - n) < 1 || (p - n) > 3) {
                    isSafe = false;
                    break;
                }
            }
            if (isSafe) {
                return true;
            }
        }
        return false;
    }

    static boolean asc2(String input) {
        String[] strings = input.split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        boolean isSafe;
        for (String s : strings) {
            nums.add(Integer.parseInt(s));
        }
        for (int i = 0; i < nums.size(); i++) {
            ArrayList<Integer> newReport = new ArrayList<>(nums);
            newReport.remove(i);
            isSafe = true;
            for (int j = 0; j < newReport.size() - 1; j++) {
                int p = newReport.get(j);
                int n = newReport.get(j + 1);
                if ((n - p) < 1 || (n - p) > 3) {
                    isSafe = false;
                    break;
                }
            }
            if (isSafe) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int firstResult = 0, secondResult = 0;
        String input;
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                if (desc1(input)) {firstResult++;}
                if (asc1((input))) {firstResult++;}
                if (desc2((input))) {secondResult++;}
                if (asc2((input))) {secondResult++;}
            }
            System.out.println("First answer: " + firstResult);
            System.out.println("Second answer: " + secondResult);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}