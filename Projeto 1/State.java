public class State {

    public int[][] board6x6 = {
        { 0, 0, 0, 0, 3, 0 },
        { 0, 1, 0, 0, 3, 3 },
        { 0, 0, 1, 0, 0, 0 },
        { 0, 0, 0, 1, 0, 0 },
        { 2, 2, 0, 0, 1, 0 },
        { 0, 2, 0, 0, 0, 0 } };

    public int[][] board4x4 = {
        { 0, 0, 3, 0 },
        { 0, 1, 3, 3 },
        { 2, 2, 1, 0 },
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

    public boolean checkPlayerScored(int[][] board) {
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

    public boolean checkScoreTop(int[][] board) {
        if (board[board.length - 1][0] == 3) {
            board[board.length - 1][0] = 0;
            return true;
        } else
            return false;
    }

    public boolean checkScoreBottom(int[][] board) {
        if (board[0][board.length - 1] == 2) {
            board[0][board.length - 1] = 0;
            return true;
        } else
            return false;
    }

    public double updateScore(State state, int[][] board, int player) {
        double distTopPlayer = 0;
        double distBottomPlayer = 0;
        int player2;

        if (player == 3)
            player2 = 2;
        else
            player2 = 3;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == player) {
                    distTopPlayer += Math.sqrt(Math.pow(i - (board.length - 1), 2) + Math.pow(j, 2));
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == player2) {
                    distBottomPlayer += Math.sqrt(Math.pow(i, 2) + Math.pow(j - (board.length - 1), 2));
                }
            }
        }
        if (player == 3)
            return distBottomPlayer - distTopPlayer;
        else
            return distTopPlayer - distBottomPlayer;
    }
}