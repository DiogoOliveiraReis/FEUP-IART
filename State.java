public class State {

    public int[][] board6x6 = {
        { 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 3, 0, 0 },
        { 0, 0, 1, 0, 3, 0 },
        { 0, 2, 0, 1, 0, 0 },
        { 0, 0, 2, 0, 1, 0 },
        { 0, 0, 0, 0, 0, 0 } };

    public int[][] board4x4 = {
        { 0, 0, 3, 0 },
        { 0, 1, 0, 3 },
        { 2, 0, 1, 0 },
        { 0, 2, 0, 0 } };

    public int player;
    public String name;
    public boolean gameover = false;
    public double score;

    public State(String n, int p) {
        name = n;
        score = 0;
        player = p;
    }

    public boolean checkGameOver(int[][] board) {
        if (board[board.length - 1][0] == 3) {
            gameover = true;
            return true;
        }
        if (board[0][board.length - 1] == 2) {
            gameover = true;
            return true;
        }
        return false;
    }

    public void updateScore(State state, int[][] board) {
        double distBot = 0;
        double distPlayer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 3) {
                    distBot += Math.sqrt(Math.pow(i - (board.length - 1), 2) + Math.pow(j, 2));
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 2) {
                    distPlayer += Math.sqrt(Math.pow(i, 2) + Math.pow(j - (board.length - 1), 2));
                }
            }
        }
        score += distPlayer - distBot;
    }
}