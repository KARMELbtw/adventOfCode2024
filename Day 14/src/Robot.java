public class Robot {
    int posX;
    int posY;
    int velX;
    int velY;

    public Robot(int posX, int posY, int velX, int velY) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
    }

    public void Move() {
        int previousPosX = posX;
        int previousPosY = posY;

        posX += velX;
        if (posX >= Main.GridX) posX -= Main.GridX;
        if (posX < 0) posX += Main.GridX;
        posY += velY;
        if (posY >= Main.GridY) posY -= Main.GridY;
        if (posY < 0) posY += Main.GridY;

        Main.Grid[previousPosY][previousPosX]--;
        Main.Grid[posY][posX]++;
    }

}
