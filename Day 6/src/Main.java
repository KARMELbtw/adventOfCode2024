import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            ArrayList<ArrayList<Character>> map = new ArrayList<>();
            String tempString;
            int posX = 0, posY = 0;
            int y = 0;
            while (sc.hasNextLine()) {
                tempString = sc.nextLine();
                map.add(new ArrayList<>());
                for (int x = 0; x < tempString.length(); x++) {
                    map.get(y).add(tempString.charAt(x));
                    if (map.get(y).get(x) == '^') {
                        posY = y;
                        posX = x;
                    }
                }
                y++;
            }
            Guard guard = new Guard(posX, posY, map);
            guard.move();
            guard.placeObstacles();
            System.out.println("First answer: " + guard.getDistinctPositions());
            System.out.println("Second answer: " + guard.getObstaclePositions());
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }
    }
}