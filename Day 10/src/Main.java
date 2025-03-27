import java.io.*;
import java.util.*;

public class Main {
    static int rows, cols;
    static int[][] map;
    static int[] directionsX = {-1, 1, 0, 0};
    static int[] directionsY = {0, 0, -1, 1};
    static int secondAnswer = 0;

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNext()) {
            String line = sc.next();
            lines.add(line);
        }
        rows = lines.size();
        cols = lines.getFirst().length();
        map = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        int firstAnswer = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 0) {
                    firstAnswer += CalculateTrailheadScore(i, j);
                    CalculateAmountOfTrails(i, j);
                }
            }
        }
        System.out.println("First Answer: " + firstAnswer);
        System.out.println("Second Answer: " + secondAnswer);
    }

    static int CalculateTrailheadScore(int startX, int startY) {
        HashSet<String> visitedNines = new HashSet<>();
        DepthFirstSearch(startX, startY, visitedNines);
        return visitedNines.size();
    }

    static void CalculateAmountOfTrails(int x, int y) {
        if (map[x][y] >= 9) {
            secondAnswer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int offsetX = x + directionsX[i];
            int offsetY = y + directionsY[i];

            if (isValid(offsetX, offsetY) && map[offsetX][offsetY] == map[x][y] + 1) {
                CalculateAmountOfTrails(offsetX, offsetY);
            }
        }
    }

    static void DepthFirstSearch(int x, int y, Set<String> visitedNines) {

        if (map[x][y] >= 9) {
            visitedNines.add(x + "," + y);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int offsetX = x + directionsX[i];
            int offsetY = y + directionsY[i];

            if (isValid(offsetX, offsetY) && map[offsetX][offsetY] == map[x][y] + 1) {
                DepthFirstSearch(offsetX, offsetY, visitedNines);
            }
        }
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}