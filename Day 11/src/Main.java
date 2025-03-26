import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        ArrayList<Long> stones = new ArrayList<>();
        while (sc.hasNextInt()) {
            stones.add((long)sc.nextInt());
        }
        sc.close();

        for (int i = 0; i < 40; i++) {
            ArrayList<Long> nextStones = new ArrayList<>();
            for (Long stone : stones) {
                if (stone.equals(0L)) {
                    nextStones.add(1L);
                } else {
                    int digitCount = (int) Math.log10(stone) + 1;
                    if (digitCount%2 == 0) {
                        int half = digitCount/2;
                        nextStones.add(stone/(long)Math.pow(10, half));
                        nextStones.add(stone%(long)Math.pow(10, half));
                    } else {
                        nextStones.add(stone * 2024L);
                    }
                }
            }
            stones = nextStones;
        }

        System.out.println(stones.size());
    }
}