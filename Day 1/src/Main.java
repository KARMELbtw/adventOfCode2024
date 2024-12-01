import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {
    
    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) {
        int i, j, temp;
        int n = arr.size();

        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n-1; j++) {
                if (arr.get(j) > arr.get(j+1)) {
                    temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }
        }
        return arr;
    }

    public static int abs(int n) {
        if (n < 0) {
            return n*(-1);
        } else {
            return n;
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner in = new Scanner(file);

            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();
            String[] tempHalfs;
            int sum1 = 0;
            int sum2 = 0;
            int multiplier;

            while (in.hasNextLine()) {
                String line = in.nextLine();
                tempHalfs = line.split("   ");
//                System.out.println(tempHalfs[0] + " " + getMinNumber(tempHalfs[0]));
//                System.out.println(tempHalfs[1] + " " + getMinNumber(tempHalfs[1]));
//                System.out.println();
                left.add(Integer.parseInt(tempHalfs[0]));
                right.add(Integer.parseInt(tempHalfs[1]));
            }

            ArrayList<Integer> leftSorted = bubbleSort(left);
            ArrayList<Integer> rightSorted = bubbleSort(right);

            for (int i = 0; i < left.size(); i++) {
                sum1 += abs(leftSorted.get(i) - rightSorted.get(i));
//                System.out.println(left.get(i) + " " + right.get(i) + ", " + (left.get(i) - right.get(i)));
                multiplier = 0;
                for (int j = 0; j < right.size(); j++) {
                    if (left.get(i).equals(right.get(j))) {
                        multiplier++;
                    }
                }
                sum2 += left.get(i)*multiplier;
            }
            System.out.println("First answer: " + sum1);
            System.out.println("Second answer: " + sum2);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}