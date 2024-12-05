import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            boolean firstPart = true, inOrder;
            ArrayList<ArrayList<Integer>> order = new ArrayList<>();
            order.add(new ArrayList<>());
            order.add(new ArrayList<>());
            int firstResult = 0, secondResult = 0;
            String[] tempTab;
            String tempString;
            while (sc.hasNextLine()) {
                inOrder = true;
                tempString = sc.nextLine();
                if (tempString.isEmpty()) {
                    firstPart = false;
                }
                if (firstPart) {
                    tempTab = tempString.split("\\|");
                    order.get(0).add(Integer.parseInt(tempTab[0]));
                    order.get(1).add(Integer.parseInt(tempTab[1]));
                } else if (!tempString.isEmpty()) {
                    tempTab = tempString.split(",");
                    int index1, index2;
                    for (int i = 0; i < order.get(0).size(); i++) {
                        index1 = -1;
                        index2 = -1;
                        for (int j = 0; j < tempTab.length; j++) {
                            if (Integer.parseInt(tempTab[j]) == order.get(0).get(i)) index1 = j;
                            if (Integer.parseInt(tempTab[j]) == order.get(1).get(i)) index2 = j;
                        }
                        if (index1 == -1 || index2 == -1) {
                            continue;
                        }
                        if (index1 > index2) {
                            inOrder = false;
                        }
                    }
                    if (inOrder) {
                        int middleIndex = (tempTab.length - 1) / 2;
                        firstResult += Integer.parseInt(tempTab[middleIndex]);
                    } else {
                    }
                }
            }

            System.out.println("First result: " + firstResult);
            System.out.println("Second result: " + secondResult);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}