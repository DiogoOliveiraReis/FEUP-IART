class Parquet {

    /*
     * 0 - Empty Space, 1 - Void, 2 - Player 1 Piece, 3 - Player 2 Piece
     */
    static int[][] board = { { 0, 0, 0, 3, 3, 0 }, { 0, 1, 0, 0, 3, 3 }, { 0, 0, 1, 0, 0, 3 }, { 2, 0, 0, 1, 0, 0 },
            { 2, 2, 0, 0, 1, 0 }, { 0, 2, 2, 0, 0, 0 } };

    public static void main(String args[]) {
        Board GameBoard = new Board();
        GameBoard.displayBoard(board);
    }
}