public class ClawMachine {
    public int aX = -1;
    public int aY = -1;
    public int bX = -1;
    public int bY = -1;
    public int prizeX = -1;
    public int prizeY = -1;

    public ClawMachine() {
    }

    public int GetMinAmountOfTokens() {
        int min = 401;

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (aX*i+bX*j == prizeX && aY*i+bY*j == prizeY) {
                    if (i*3+j < min) min = i*3+j;
                }
            }
        }

        if (min == 401) return 0;
        return min;
    }

//    public long GetMinAmountOfTokensPart2() {
//        long posX = prizeX+10000000000000L;
//        long posY = prizeY+10000000000000L;
//
//    }
}
