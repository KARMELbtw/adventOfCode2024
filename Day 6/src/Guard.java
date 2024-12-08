import java.util.ArrayList;

public class Guard {
    int posx;
    int posy;
    int distinctPositions = 0;
    int obstaclePositions = 0;
    ArrayList<ArrayList<Character>> map;
    ArrayList<ArrayList<Character>> mapCopy;

    public Guard(int posx, int posy, ArrayList<ArrayList<Character>> map) {
        this.posx = posx;
        this.posy = posy;
        this.map = map;
        this.mapCopy = new ArrayList<>();
        for (ArrayList<Character> row : map) {
            this.mapCopy.add(new ArrayList<>(row));
        }
    }

    public int getDistinctPositions() {
        return distinctPositions+1;
    }

    public int getObstaclePositions() {
        return obstaclePositions;
    }

    public void move() {
        int x = posx;
        int y = posy;
        while (x < map.getFirst().size() && x >= 0 && y < map.size() && y >= 0) {
//            System.out.println(x + "," + y);
//            for (int i = 0; i < map.size(); i++) {
//                for (int j = 0; j < map.getFirst().size(); j++) {
//                    System.out.print(map.get(i).get(j));
//                }
//                System.out.println();
//            }
            switch (map.get(y).get(x)) {
                case '^':
                    if (y - 1 < 0) return;
                    if (map.get(y - 1).get(x) == '#') {
                        map.get(y).set(x, '>');
                        break;
                    }
                    if (map.get(y - 1).get(x) != 'X') distinctPositions++;
                    map.get(y - 1).set(x, '^');
                    map.get(y).set(x, 'X');
                    y--;
                    break;
                case '>':
                    if (x + 1 > map.get(y).size() - 1) return;
                    if (map.get(y).get(x + 1) == '#') {
                        map.get(y).set(x, 'v');
                        break;
                    }
                    if (map.get(y).get(x + 1) != 'X') distinctPositions++;
                    map.get(y).set(x + 1, '>');
                    map.get(y).set(x, 'X');
                    x++;
                    break;
                case '<':
                    if (x - 1 < 0) return;
                    if (map.get(y).get(x - 1) == '#') {
                        map.get(y).set(x, '^');
                        break;
                    }
                    if (map.get(y).get(x - 1) != 'X') distinctPositions++;
                    map.get(y).set(x - 1, '<');
                    map.get(y).set(x, 'X');
                    x--;
                    break;
                case 'v':
                    if (y + 1 > map.size() - 1) return;
                    if (map.get(y + 1).get(x) == '#') {
                        map.get(y).set(x, '<');
                        break;
                    }
                    if (map.get(y + 1).get(x) != 'X') distinctPositions++;
                    map.get(y + 1).set(x, 'v');
                    map.get(y).set(x, 'X');
                    y++;
                    break;
            }
        }
    }

    public boolean checkForLoop() {
        int moves = 0;
        int x = posx;
        int y = posy;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                if (mapCopy.get(i).get(j) == 'U' || mapCopy.get(i).get(j) == 'L' || mapCopy.get(i).get(j) == 'R' || mapCopy.get(i).get(j) == 'D') {
                    mapCopy.get(i).set(j, '.');
                }
            }
        }
        while (x >= 0 && x < map.getFirst().size() && y >= 0 && y < map.size()) {
            moves++;
            if (moves > 100000) return true;
            switch (map.get(y).get(x)) {
                case '^':
                    if (y - 1 < 0) return false;
                    if (mapCopy.get(y-1).get(x) == 'U') return true;
                    if (map.get(y - 1).get(x) == '#') {
                        map.get(y).set(x, '>');
                        break;
                    }
                    map.get(y - 1).set(x, '^');
                    mapCopy.get(y).set(x, 'U');
                    y--;
                    break;

                case '>':
                    if (x + 1 >= map.get(y).size()) return false;
                    if (mapCopy.get(y).get(x+1) == 'R') return true;
                    if (map.get(y).get(x + 1) == '#') {
                        map.get(y).set(x, 'v');
                        break;
                    }
                    map.get(y).set(x + 1, '>');
                    mapCopy.get(y).set(x, 'R');
                    x++;
                    break;

                case '<':
                    if (x - 1 < 0) return false;
                    if (mapCopy.get(y).get(x-1) == 'L') return true;
                    if (map.get(y).get(x - 1) == '#') {
                        map.get(y).set(x, '^');
                        break;
                    }
                    map.get(y).set(x - 1, '<');
                    mapCopy.get(y).set(x, 'L');
                    x--;
                    break;

                case 'v':
                    if (y + 1 >= map.size()) return false;
                    if (mapCopy.get(y+1).get(x) == 'D') return true;
                    if (map.get(y + 1).get(x) == '#') {
                        map.get(y).set(x, '<');
                        break;
                    }
                    map.get(y + 1).set(x, 'v');
                    mapCopy.get(y).set(x, 'D');
                    y++;
                    break;
            }
        }
        return false;
    }

    public void placeObstacles() {
        char tempChar;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                map.get(posy).set(posx, '^');
                if (i != posy || j != posx) {
                    tempChar = map.get(i).get(j);
                    map.get(i).set(j, '#');
                    mapCopy.get(i).set(j, '#');
                    if (checkForLoop()) {
                        obstaclePositions++;
//                        System.out.println("Loop detected");
                    } else {
//                        System.out.println("no loop");
                    }
                    map.get(i).set(j, tempChar);
                    mapCopy.get(i).set(j, tempChar);
                }
            }
        }
    }
}
