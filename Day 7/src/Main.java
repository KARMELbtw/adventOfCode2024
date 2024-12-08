import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean possible1(long target, ArrayList<Long> arr) {
        if (arr.size() == 1) return target == arr.getFirst();
        if (target % arr.getLast() == 0 && possible1(target / arr.getLast(), removeLast(arr))) return true;
        if (target > arr.getLast() && possible1(target-arr.getLast(), removeLast(arr))) return true;
        return false;
    }

    public static boolean possible2(long target, ArrayList<Long> arr) {
        if (arr.size() == 1) return target == arr.getFirst();
        if (target % arr.getLast() == 0 && possible2(target / arr.getLast(), removeLast(arr))) return true;
        if (target > arr.getLast() && possible2(target-arr.getLast(), removeLast(arr))) return true;
        String targetString = String.valueOf(target);
        String lastString = String.valueOf(arr.getLast());
        if (targetString.length() > lastString.length() && targetString.endsWith(lastString) && possible2(Long.parseLong(targetString.substring(0, targetString.length()-lastString.length())), removeLast(arr))) return true;
        return false;
    }

    public static ArrayList<Long> removeLast(ArrayList<Long> arr) {
        ArrayList<Long> removed = new ArrayList<>(arr);
        removed.removeLast();
        return removed;
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            String line;
            String[] tab, numsTab;
            long firstAnswer = 0, secondAnswer = 0, target;
            ArrayList<Long> nums;
            while (sc.hasNextLine()) {
                nums = new ArrayList<>();
                line = sc.nextLine();
                tab = line.split(": ");
                target = Long.parseLong((tab[0]));
                numsTab = tab[1].split(" ");
                for (String num : numsTab) {
                    nums.add(Long.parseLong(num));
                }
                if (possible1(target, nums)) {
                    firstAnswer+=target;
                }
                if (possible2(target, nums)) {
                    secondAnswer+=target;
                }
                nums.clear();
            }
            System.out.println("First answer " + firstAnswer);
            System.out.println("Second answer " + secondAnswer);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}