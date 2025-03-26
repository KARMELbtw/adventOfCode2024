import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        HashMap<Long, Long> stones = new HashMap<>();
        while (sc.hasNextInt()) {
            stones.put((long)sc.nextInt(), 1L);
        }
        sc.close();

        Long firstAnswer = 0L;
        Long secondAnswer = 0L;


        for (int i = 0; i < 75; i++) {
            HashMap<Long, Long> nextStones = new HashMap<>();
            for (Long stone : stones.keySet()) {
                Long num = stones.get(stone);
                if (stone.equals(0L)) {
                    nextStones.put(1L, num);
                } else {
                    int digitCount = String.valueOf(stone).length();
                    if (digitCount % 2 == 0) {
                        int half = digitCount / 2;
                        long left = stone/(long) Math.pow(10, half);
                        long right = stone%(long) Math.pow(10, half);
                        nextStones.put(left, nextStones.getOrDefault(left, 0L) + num);
                        nextStones.put(right, nextStones.getOrDefault(right, 0L) + num);
                    } else {
                        nextStones.put(stone * 2024L, num);
                    }
                }
            }
            stones = nextStones;
            if (i == 24) {
                for (Long value : stones.values()) {
                    firstAnswer += value;
                }
            }
        }

        for (Long value : stones.values()) {
            secondAnswer += value;
        }

        System.out.println("First Answer: " + firstAnswer);
        System.out.println("Second Answer: " + secondAnswer);
    }
}