public class Move {
    public int x;
    public int y;
    public String direction;

    public Move(int posX, int posY, String d) {
        x = posX;
        y = posY;
        direction = d;
    }

    public Move(int posX, int posY, int d, int player) {
        x = posX;
        y = posY;
        switch (d) {
            case 0:
                if (player == 3) direction = "MoveDownLeftJump";
                else direction = "MoveUpRightJump";
                break;
            case 1:
                if (player == 3) direction = "MoveDownLeft";
                else direction = "MoveUpRight";
                break;
            case 2:
                if (player == 3) direction = "MoveDown";
                else direction = "MoveUp";
                break;
            case 3:
                if (player == 3) direction = "MoveLeft";
                else direction = "MoveRight";
                break;
        }
    }
}