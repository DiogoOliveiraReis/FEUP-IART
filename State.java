public class State {

    public int[][] board = {
        { 0, 0, 0, 3, 3, 0 }, 
        { 0, 1, 0, 0, 3, 3 }, 
        { 0, 0, 1, 0, 0, 3 }, 
        { 2, 0, 0, 1, 0, 0 },   
        { 2, 2, 0, 0, 1, 0 }, 
        { 0, 2, 2, 0, 0, 0 }
    };
    public int player;
    public boolean gameover = false;

    public State(int p) {
        player = p;
    }

    public void checkGameOver() {
        if (board[5][5] == 2)
            gameover = true;
    }
}