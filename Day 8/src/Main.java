import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        List<String> grid = new ArrayList<>();
        int firstAnswer = 0;
        while (scanner.hasNextLine()) {
            grid.add(scanner.nextLine().strip());
        }
        int rows = grid.size();
        int cols = grid.getFirst().length();
        Map<Character, List<int[]>> antennas = new HashMap<>();
        for (int row = 0; row < rows; row++) {
            String rowString = grid.get(row);
            for (int col = 0; col < cols; col++) {
                char ch = rowString.charAt(col);
                if (ch != '.') {
                    antennas.computeIfAbsent(ch, _ -> new ArrayList<>()).add(new int[]{row, col});
                }
            }
        }
        Set<String> antinodes1 = new HashSet<>();
        Set<String> antinodes2 = new HashSet<>();
        for (List<int[]> array : antennas.values()) {
            for (int i = 0; i < array.size(); i++) {
                for (int j = 0; j < array.size(); j++) {
                    if (i == j) continue;
                    int[] antenna1 = array.get(i);
                    int[] antenna2 = array.get(j);
                    int row1 = antenna1[0], col1 = antenna1[1];
                    int row2 = antenna2[0], col2 = antenna2[1];
                    antinodes1.add((2 * row1 - row2) + "," + (2 * col1 - col2));
                    antinodes1.add((2 * row2 - row1) + "," + (2 * col2 - col1));
                    int drow = row2 - row1;
                    int dcol = col2 - col1;
                    int row = row1;
                    int col = col1;
                    while (row >= 0 && row < rows && col >= 0 && col < cols) {
                        antinodes2.add(row + "," + col);
                        row += drow;
                        col += dcol;
                    }
                }
            }
        }
        for (String antinode : antinodes1) {
            String[] parts = antinode.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            if (row >= 0 && row < rows && col >= 0 && col < cols) {
                firstAnswer++;
            }
        }
        System.out.println("First answer: " + firstAnswer);
        System.out.println("Second answer: " + antinodes2.size());
    }
}