import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static boolean[][] visited;
    static ArrayList<ArrayList<Character>> map = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));

        HashMap<Character, Integer> prices = new HashMap<>();
        HashMap<Character, Integer> pricesBulk = new HashMap<>();
        while (sc.hasNext()) {
            map.add(new ArrayList<>());
            String line = sc.next();
            for (int i = 0; i < line.length(); i++) {
                map.getLast().add(line.charAt(i));
            }
        }

        visited = new boolean[map.size()][map.getFirst().size()];
        char previous = '.';
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                char c = map.get(i).get(j);
                if (previous != c && !visited[i][j]) {
                    int[] val = countPlots(i, j, c);
                    prices.put(c, prices.getOrDefault(c, 0) + val[0]*val[1]);
                    System.out.println(c + " - " + val[0] + ", " + val[2]);
                    pricesBulk.put(c, pricesBulk.getOrDefault(c, 0) + val[0]*val[2]);
                }
                previous = map.get(i).get(j);
            }
        }

        int firstAnswer = 0;
        int secondAnswer = 0;

        for (int i : prices.values()) {
            firstAnswer += i;
        }

        for (int i : pricesBulk.values()) {
            secondAnswer += i;
        }
        System.out.println("First Answer " + firstAnswer);
        System.out.println("Second Answer " + secondAnswer);
    }

    static int[] countPlots(int x, int y, char c) {
        int[] arr = {0, 0, 0}; // 0 - area, 1 - perimeter, 2 - sides
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        visited[x][y] = true;

        arr[0]++;

        for (int[] d : dirs) {
            int newX = x + d[0];
            int newY = y + d[1];

            if (newX >= 0 && newX < map.size() && newY >= 0 && newY < map.size()) {
                if (!visited[newX][newY] && map.get(newX).get(newY) == c) {
                    int[] temp = countPlots(newX, newY, c);
                    arr[0] += temp[0];
                    arr[1] += temp[1];
                    arr[2] += temp[2];
                } else if (map.get(newX).get(newY) != c) {
                    arr[1]++;
                }
            } else {
                arr[1]++;
            }
        }

        if (x - 1 < 0 && y - 1 < 0) arr[2]++;
        if (x + 1 >= map.size() && y - 1 < 0) arr[2]++;
        if (x + 1 >= map.size() && y + 1 >= map.size()) arr[2]++;
        if (x - 1 < 0 && y + 1 >= map.size()) arr[2]++;

        if (y - 1 >= 0 && x + 1 < map.size() && map.get(x).get(y - 1) != c && map.get(x + 1).get(y) != c) arr[2]++;
        if (x - 1 >= 0 && y - 1 >= 0 && map.get(x - 1).get(y) != c && map.get(x).get(y - 1) != c) arr[2]++;
        if (x - 1 >= 0 && y + 1 < map.size() && map.get(x - 1).get(y) != c && map.get(x).get(y + 1) != c) arr[2]++;
        if (x + 1 < map.size() && y + 1 < map.size() && map.get(x + 1).get(y) != c && map.get(x).get(y + 1) != c) arr[2]++;

        if (x + 1 < map.size() && y + 1 < map.size() && map.get(x + 1).get(y + 1) != c && map.get(x + 1).get(y) == c && map.get(x).get(y + 1) == c) arr[2]++;
        if (x - 1 >= 0 && y + 1 < map.size() && map.get(x - 1).get(y + 1) != c && map.get(x - 1).get(y) == c && map.get(x).get(y + 1) == c) arr[2]++;
        if (x + 1 < map.size() && y - 1 >= 0 && map.get(x + 1).get(y - 1) != c && map.get(x + 1).get(y) == c && map.get(x).get(y - 1) == c) arr[2]++;
        if (x - 1 >= 0 && y - 1 >= 0 && map.get(x - 1).get(y - 1) != c && map.get(x - 1).get(y) == c && map.get(x).get(y - 1) == c) arr[2]++;

        if (x + 1 == map.size() && y + 1 < map.size() && map.get(x).get(y + 1) != c) arr[2]++;
        if (x + 1 == map.size() && y - 1 >= 0 && map.get(x).get(y - 1) != c) arr[2]++;
        if (x - 1 < 0 && y + 1 < map.size() && map.get(x).get(y + 1) != c) arr[2]++;
        if (x - 1 < 0 && y - 1 >= 0 && map.get(x).get(y - 1) != c) arr[2]++;

        if (y + 1 == map.size() && x + 1 < map.size() && map.get(x + 1).get(y) != c) arr[2]++;
        if (y + 1 == map.size() && x - 1 >= 0 && map.get(x - 1).get(y) != c) arr[2]++;
        if (y - 1 < 0 && x + 1 < map.size() && map.get(x + 1).get(y) != c) arr[2]++;
        if (y - 1 < 0 && x - 1 >= 0 && map.get(x - 1).get(y) != c) arr[2]++;

        return arr;
    }
}