public class Minimax extends Parquet {

    public static Move getPCBestMove(State state, int[][] board, int depthLimit) {
        int depth = 0;
        Move bestMove = new Move(0, 0, 0);
        double bestScore = -99999999;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                for (int d = 0; d < 4; d++) {
                    Move move = new Move(x, y, d);
                    if (validMove(state, board, move, 3)) {
                        makeEnemyMove(state, board, move);
                        double score = minimax(state, board, depth, depthLimit, false);
                        revertEnemyMove(state, board, move);
                        
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = move;
                        }
                    }
                }
            }
        }
        System.out.println("getPCBestMove X = " + bestMove.x + ", Y = " + bestMove.y + ", D = " + bestMove.direction);
        System.out.println("Best Score = " + bestScore);
        return bestMove;
    }

    public static double minimax(State state, int[][] board, int depth, int depthLimit, boolean isMax) {
        
        Move lol = new Move(0, 0, 0);
        if (state.checkGameOver(board)) {
            return state.updateScore(state, board);
        }
        if (depthLimit == 0) {
            return state.updateScore(state, board);
        }
        //score = 0;
        if (isMax) {
            double bestScore = -99999999;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    for (int d = 0; d < 4; d++) {
                        Move move = new Move(x, y, d);
                        if (validMove(state, board, move, 3)) {
                            makeEnemyMove(state, board, move);
                            double score = minimax(state, board, depth + 1, depthLimit - 1, false);
                            revertEnemyMove(state, board, move);
                            if (score > bestScore) {
                                bestScore = score;
                                lol = move;
                            }
                        }
                    }
                }
            }
            return bestScore;
        } 
        else {
            double bestScore = 99999999;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    for (int d = 0; d < 4; d++) {
                        Move move = new Move(x, y, d);
                        if (d == 0) {
                            move.direction = "MoveUpRightJump";
                        } else if (d == 1) {
                            move.direction = "MoveUpRight";
                        } else if (d == 2) {
                            move.direction = "MoveUp";
                        } else if (d == 3) {
                            move.direction = "MoveRight";
                        }
                        if (validMove(state, board, move, 2)) {
                            makeMove(state, board, move);
                            double score = minimax(state, board, depth + 1, depthLimit - 1, true);
                            revertMakeMove(state, board, move);
                            if (score < bestScore) {
                                bestScore = score;
                                lol = move;
                            }
                        }
                    }
                }
            }
            return bestScore;
        }
    }
}