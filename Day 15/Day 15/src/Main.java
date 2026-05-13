import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<String, Character> map = new HashMap<>();
    static int posX = -1;
    static int posY = -1;

    static int canMoveLeft(int x, int y, int count) {
        if (map.containsKey(x-1 + "," + y) && map.get(x-1 + "," + y).equals('.')) {
            return count + 1;
        } else if (map.get(x-1 + "," + y).equals('O')) {
            return canMoveLeft(x-1, y, count+1);
        } else {
            return 0;
        }
    }

    static int canMoveRight(int x, int y, int count) {
        if (map.containsKey(x+1 + "," + y) && map.get(x+1 + "," + y).equals('.')) {
            return count + 1;
        } else if (map.get(x+1 + "," + y).equals('O')) {
            return canMoveRight(x+1, y, count+1);
        } else {
            return 0;
        }
    }

    static int canMoveUp(int x, int y, int count) {
        if (map.containsKey(x + "," + (y-1)) && map.get(x + "," + (y-1)).equals('.')) {
            return count + 1;
        } else if (map.get(x + "," + (y-1)).equals('O')) {
            return canMoveUp(x, y-1, count+1);
        } else {
            return 0;
        }
    }

    static int canMoveDown(int x, int y, int count) {
        if (map.containsKey(x + "," + (y+1)) && map.get(x + "," + (y+1)).equals('.')) {
            return count + 1;
        } else if (map.get(x + "," + (y+1)).equals('O')) {
            return canMoveDown(x, y+1, count+1);
        } else {
            return 0;
        }
    }

    static void moveLeft(int x, int y) {
        int distance = canMoveLeft(x, y, 0);
        if (distance > 0) {
            map.replace(x+","+y, '.');
            posX = x-1;
            map.replace(posX+","+y, '@');
            for (int i = 1; i < distance; i++) {
                map.replace((posX-i)+","+y, 'O');
            }
        }
    }

    static void moveRight(int x, int y) {
        int distance = canMoveRight(x, y, 0);
        if (distance > 0) {
            map.replace(x+","+y, '.');
            posX = x+1;
            map.replace(posX+","+y, '@');
            for (int i = 1; i < distance; i++) {
                map.replace((posX+i)+","+y, 'O');
            }
        }
    }

    static void moveUp(int x, int y) {
        int distance = canMoveUp(x, y, 0);
        if (distance > 0) {
            map.replace(x+","+y, '.');
            posY = y-1;
            map.replace(x+","+posY, '@');
            for (int i = 1; i < distance; i++) {
                map.replace(x+","+(posY-i), 'O');
            }
        }
    }

    static void moveDown(int x, int y) {
        int distance = canMoveDown(x, y, 0);
        if (distance > 0) {
            map.replace(x+","+y, '.');
            posY = y+1;
            map.replace(x+","+posY, '@');
            for (int i = 1; i < distance; i++) {
                map.replace(x+","+(posY+i), 'O');
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);


        String moves = "";

        boolean mapLoaded = false;
        int i = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                mapLoaded = true;
            }
            if (!mapLoaded) {
                for(int j = 0; j < line.length(); j++) {
                    if(line.charAt(j) == '@') {
                        posX = j;
                        posY = i;
                    }
                    map.put(j + "," + i, line.charAt(j));
                }
            } else {
                moves += line;
            }

            i++;
        }

        for (char c : moves.toCharArray()) {
            switch (c) {
                case '<':
                    moveLeft(posX, posY);
                    break;
                case '^':
                    moveUp(posX, posY);
                    break;
                case '>':
                    moveRight(posX, posY);
                    break;
                case 'v':
                    moveDown(posX, posY);
                    break;
                default:
                    break;
            }
//            for (int j = 0; j < 8; j++) {
//                for (int k = 0; k < 8; k++) {
//                    System.out.print(map.get(k+","+j));
//                }
//                System.out.println();
//            }
        }

        int firstAnswer = 0;
        for (String key : map.keySet()) {
//            System.out.println(map.get(key));
            if (map.get(key).equals('O')) {
                String[] cords = key.split(",");
                firstAnswer += Integer.parseInt(cords[0]) + Integer.parseInt(cords[1])*100;
            }
        }

        System.out.println("First Answer: " + firstAnswer);

        sc.close();
    }
}