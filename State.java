public class State {

    public int[][] board = { { 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 }, { 0, 0, 1, 3, 0, 0 }, { 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 1, 0 }, { 2, 0, 0, 0, 0, 0 } };

    public int player;
    public String name;
    public boolean gameover = false;
    public int score;

    public State(String n, int p) {
        name = n;
        score = 0;
        player = p;
    }

    public boolean checkGameOver() {
        if (board[5][0] == 3 || board[5][1] == 3 || board[4][0] == 3) {
            score = 1;
            gameover = true;
            return true;
        }
        if (board[0][5] == 2 || board[1][5] == 2 || board[0][4] == 2) {
            score = -1;
            gameover = true;
            return true;
        }
        return false;
    }
}