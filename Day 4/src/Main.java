import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            ArrayList<ArrayList<Character>> input = new ArrayList<>(' ');
            String tempString, target = "XMAS";
            boolean found, matches;
            int k = 0, firstAnswer = 0, secondAnswer = 0;
            while (sc.hasNextLine()) {
                tempString = sc.nextLine();
                input.add(new ArrayList<>());
                for (int j = 0; j < tempString.length(); j++) {
                    input.get(k).add(tempString.charAt(j));
                }
                k++;
            }
            //part1
            for (int y = 0; y < input.size(); y++) {
                for (int x = 0; x < input.get(y).size(); x++) {
                    if (x + target.length() <= input.get(y).size()) {
                        found = true;
                        for (int i = 0; i < target.length(); i++) {
                            if (input.get(y).get(x + i) != target.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            firstAnswer++;
                        }
                        found = true;
                        for (int i = 0; i < target.length(); i++) {
                            if (input.get(y).get(x + target.length() - 1 - i) != target.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            firstAnswer++;
                        }
                    }
                    if (y + target.length() <= input.size()) {
                        found = true;
                        for (int i = 0; i < target.length(); i++) {
                            if (input.get(y + i).get(x) != target.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            firstAnswer++;
                        }
                        found = true;
                        for (int i = 0; i < target.length(); i++) {
                            if (input.get(y + target.length() - 1 - i).get(x) != target.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            firstAnswer++;
                        }
                    }
                    if (x + target.length() <= input.get(y).size() && y + target.length() <= input.size()) {
                        found = true;
                        for (int i = 0; i < target.length(); i++) {
                            if (input.get(y + i).get(x + i) != target.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            firstAnswer++;
                        }
                        found = true;
                        for (int i = 0; i < target.length(); i++) {
                            if (input.get(y + target.length() - 1 - i).get(x + target.length() - 1 - i) != target.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            firstAnswer++;
                        }
                    }
                    if (x - target.length() >= -1 && y + target.length() <= input.size()) {
                        found = true;
                        for (int i = 0; i < target.length(); i++) {
                            if (input.get(y + i).get(x - i) != target.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            firstAnswer++;
                        }
                        found = true;
                        for (int i = 0; i < target.length(); i++) {
                            if (input.get(y + target.length() - 1 - i).get(x - target.length() + 1 + i) != target.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            firstAnswer++;
                        }
                    }
                }
            }
            //part 2
            for (int y = 0; y < input.size()-2; y++) {
                for (int x = 0; x < input.get(y).size()-2; x++) {
                    if (input.get(y).get(x) == 'M' && input.get(y+1).get(x+1) == 'A' && input.get(y).get(x+2) == 'S' && input.get(y+2).get(x) == 'M' && input.get(y+2).get(x+2) == 'S') secondAnswer++;
                    if (input.get(y).get(x) == 'M' && input.get(y+1).get(x+1) == 'A' && input.get(y).get(x+2) == 'M' && input.get(y+2).get(x) == 'S' && input.get(y+2).get(x+2) == 'S') secondAnswer++;
                    if (input.get(y).get(x) == 'S' && input.get(y+1).get(x+1) == 'A' && input.get(y).get(x+2) == 'S' && input.get(y+2).get(x) == 'M' && input.get(y+2).get(x+2) == 'M') secondAnswer++;
                    if (input.get(y).get(x) == 'S' && input.get(y+1).get(x+1) == 'A' && input.get(y).get(x+2) == 'M' && input.get(y+2).get(x) == 'S' && input.get(y+2).get(x+2) == 'M') secondAnswer++;
                }
            }
            System.out.println("First answer: " + firstAnswer);
            System.out.println("Second answer: " + secondAnswer);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}