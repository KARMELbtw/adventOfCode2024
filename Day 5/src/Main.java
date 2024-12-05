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
                    if (check(tempTab, order)) {
                        int middleIndex = (tempTab.length - 1) / 2;
                        firstResult += Integer.parseInt(tempTab[middleIndex]);
                    } else {
                        for (int i = 0; i < tempTab.length; i++) {
                            System.out.print(tempTab[i]+",");
                        }
                        System.out.println();
                        while (!check(tempTab, order)) {
                            sort(tempTab, order);
                        }
                        for (int i = 0; i < tempTab.length; i++) {
                            System.out.print(tempTab[i]+",");
                        }
                        System.out.println();
                        int middleIndex = (tempTab.length - 1) / 2;
                        secondResult += Integer.parseInt(tempTab[middleIndex]);
                    }
                }
            }
            System.out.println("First result: " + firstResult);
            System.out.println("Second result: " + secondResult);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public static boolean check(String[] tab, ArrayList<ArrayList<Integer>> order) {
        int index1 = 0, index2 = 0;
        for (int i = 0; i < order.get(0).size(); i++) {
            index1 = -1;
            index2 = -1;
            for (int j = 0; j < tab.length; j++) {
                if (Integer.parseInt(tab[j]) == order.get(0).get(i)) index1 = j;
                if (Integer.parseInt(tab[j]) == order.get(1).get(i)) index2 = j;
            }
            if (index1 == -1 || index2 == -1) {
                continue;
            }
            if (index1 > index2) {
                return false;
            }
        }
        return true;
    }
    public static String[] sort(String[] tab, ArrayList<ArrayList<Integer>> order) {
        boolean inOrder = false;
        int index1, index2;
        while (!inOrder) {
            for (int i = 0; i < order.get(0).size(); i++) {
                index1 = -1;
                index2 = -1;
                for (int j = 0; j < tab.length; j++) {
                    if (Integer.parseInt(tab[j]) == order.get(0).get(i)) index1 = j;
                    if (Integer.parseInt(tab[j]) == order.get(1).get(i)) index2 = j;
                }
                if (index1 == -1 || index2 == -1) {
                    continue;
                }
                if (index1 > index2) {
                    tab[index1] = String.valueOf(order.get(1).get(i));
                    tab[index2] = String.valueOf(order.get(0).get(i));
                }
                if (index1 < index2) {
                    inOrder = true;
                }
            }
        }
        return tab;
    }
}