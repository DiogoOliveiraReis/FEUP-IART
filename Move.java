public class Move {
    public int x;
    public int y;
    public String direction;

    public Move(int posX, int posY, String d) {
        x = posX;
        y = posY;
        direction = d;
    }

    public Move(int posX, int posY, int d) {
        x = posX;
        y = posY;
        switch (d) {
            case 0:
                direction = "MoveDownLeftJump";
                break;
            case 1:
                direction = "MoveDownLeft";
                break;
            case 2:
                direction = "MoveDown";
                break;
            case 3:
                direction = "MoveLeft";
                break;
        }
    }
}