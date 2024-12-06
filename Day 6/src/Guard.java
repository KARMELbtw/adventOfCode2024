import java.util.ArrayList;

public class Guard {
    int posx;
    int posy;
    int distinctPositions;
    ArrayList<ArrayList<Character>> map;
    ArrayList<ArrayList<Character>> mapCopy;

    public Guard(int posx, int posy, ArrayList<ArrayList<Character>> map) {
        this.posx = posx;
        this.posy = posy;
        this.map = map;
        this.mapCopy = new ArrayList<>();
        this.mapCopy.addAll(map);

    }

    public int getDistinctPositions() {
        return distinctPositions+1;
    }

    public void move(int x, int y) {
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
}
