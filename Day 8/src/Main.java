import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        List<String> map = new ArrayList<>();
        while (scanner.hasNextLine()) {
            map.add(scanner.nextLine().strip());
        }
        int rows = map.size();
        int cols = map.getFirst().length();
        int firstAnswer = 0;
        int secondAnswer = 0;
        Map<Character, List<int[]>> antennas = new HashMap<>();
        for (int y = 0; y < rows; y++) {
            String row = map.get(y);
            for (int x = 0; x < cols; x++) {
                char ch = row.charAt(x);
                if (ch != '.') {
                    antennas.putIfAbsent(ch, new ArrayList<>());
                    antennas.get(ch).add(new int[]{y, x});
                }
            }
        }
        Set<String> antinodes = new HashSet<>();
        for (List<int[]> array : antennas.values()) {
            for (int i = 0; i < array.size(); i++) {
                for (int j = i + 1; j < array.size(); j++) {
                    int[] point1 = array.get(i);
                    int[] point2 = array.get(j);
                    int row1 = point1[0], col1 = point1[1];
                    int row2 = point2[0], col2 = point2[1];
                    antinodes.add((2 * row1 - row2) + "," + (2 * col1 - col2));
                    antinodes.add((2 * row2 - row1) + "," + (2 * col2 - col1));
                }
            }
        }
        for (String antinode : antinodes) {
            String[] parts = antinode.split(",");
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);
            if (r >= 0 && r < rows && c >= 0 && c < cols) {
                firstAnswer++;
            }
        }
        System.out.println("First answer: " + firstAnswer);
        System.out.println("Second answer: " + secondAnswer);
    }
}
