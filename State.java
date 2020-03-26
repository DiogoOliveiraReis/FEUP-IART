public class State {

    public int[][] board6x6 = { { 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 3, 0, 0 }, { 0, 0, 1, 0, 3, 0 }, { 0, 2, 0, 1, 0, 0 },
            { 0, 0, 2, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0 } };

    public int[][] board4x4 = { { 0, 0, 3, 0 }, { 0, 1, 0, 3 }, { 2, 0, 1, 0 }, { 0, 2, 0, 0 } };

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

    public void updateScore(State state, int[][] board, Move move) {
        if (state.player == 2)
            score--;
        else
            score++;
    }
}