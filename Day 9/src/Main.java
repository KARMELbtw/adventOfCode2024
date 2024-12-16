import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        String input = sc.nextLine();
        ArrayList<Integer> diskMap = new ArrayList<>();
        long firstAnswer = 0;
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j <  input.charAt(i)-'0'; j++) {
                if (i%2 == 0) {
                    diskMap.add(i/2);
                } else {
                    diskMap.add(-1);
                }
            }
        }
        for (int i = 0; i < diskMap.size(); i++) {
            if (diskMap.get(i) == -1) {
                diskMap.set(i, diskMap.getLast());
                diskMap.removeLast();
            }
            while (diskMap.getLast() == -1) {
                diskMap.removeLast();
            }
            firstAnswer+=diskMap.get(i)*i;
        }
        System.out.println("First answer: " + firstAnswer);
    }
}