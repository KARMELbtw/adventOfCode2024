import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static int GridX = 101;
    public static int GridY = 103;
    public static int[][] Grid = new int[GridY][GridX];


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        ArrayList<Robot> robots = new ArrayList<>();

        Pattern pat = Pattern.compile("..(?<x>\\d+).(?<y>\\d+)...(?<velX>-?\\d+).(?<velY>-?\\d+)");

        while (sc.hasNextLine()) {
            Matcher mat = pat.matcher(sc.nextLine());
            if (mat.find()) {
                robots.add(new Robot(Integer.parseInt(mat.group("x")), Integer.parseInt(mat.group("y")), Integer.parseInt(mat.group("velX")), Integer.parseInt(mat.group("velY"))));
                Grid[robots.getLast().posY][robots.getLast().posX] += 1;
            }
        }


        for (int i = 0; i < 100; i++) {
            for (Robot robot : robots) {
                robot.Move();
            }
        }

        int quadrant1 = 0;
        int quadrant2 = 0;
        int quadrant3 = 0;
        int quadrant4 = 0;

        for (int i = 0; i < 103; i++) {
            for (int j = 0; j < 101; j++) {
                if (i <= 50 && j <= 49) quadrant1 += Grid[i][j]; // Top-left
                else if (i <= 50 && j >= 51) quadrant2 += Grid[i][j]; // Top-right
                else if (i >= 52 && j <= 49) quadrant3 += Grid[i][j]; // Bottom-left
                else if (i >= 52 && j >= 51) quadrant4 += Grid[i][j]; // Bottom-right
            }
        }

        int firstAnswer = quadrant1*quadrant2*quadrant3*quadrant4;

        System.out.println("First Answer: " + firstAnswer);

        for (int i = 0; i < 10000; i++) {
            boolean tree = true;
            for (Robot robot : robots) {
                robot.Move();
            }
            for (int j = 0; j < GridY; j++) {
                for (int k = 0; k < GridX; k++) {
                    if (Grid[j][k] > 1) tree = false;
                }
            }
            if (tree) {
                System.out.println("Second Answer: " + (i+101));
                break;
            }
        }

        sc.close();
    }
}