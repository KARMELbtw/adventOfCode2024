import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Pattern pat = Pattern.compile("X=(?<posX>\\d+),\\sY=(?<posY>\\d+)|X\\+(?<buttonX>\\d+),\\s*Y\\+(?<buttonY>\\d+)");
        ArrayList<ClawMachine> machines = new ArrayList<>();
        machines.add(new ClawMachine());
        while (sc.hasNextLine()) {
            Matcher mat = pat.matcher(sc.nextLine());
            if (mat.find()) {
                if (mat.group("buttonX") != null) {
                    if (machines.getLast().aX < 0) {
                        machines.getLast().aX = Integer.parseInt(mat.group("buttonX"));
                        machines.getLast().aY = Integer.parseInt(mat.group("buttonY"));
                    } else {
                        machines.getLast().bX = Integer.parseInt(mat.group("buttonX"));
                        machines.getLast().bY = Integer.parseInt(mat.group("buttonY"));
                    }
                } else if (mat.group("posX") != null) {
                    machines.getLast().prizeX = Integer.parseInt(mat.group("posX"));
                    machines.getLast().prizeY = Integer.parseInt(mat.group("posY"));
                    machines.add(new ClawMachine());
                }
            }
        }
        machines.removeLast();

        int firstAnswer = 0;
        long secondAnswer = 0;
        for (int i = 0; i < machines.size(); i++) {
            firstAnswer += machines.get(i).GetMinAmountOfTokens();
//            secondAnswer += machines.get(i).GetMinAmountOfTokensPart2();
        }
        System.out.println("First Answer: " + firstAnswer);
//        System.out.println("Second Answer: " + secondAnswer);
        sc.close();
    }
}