import java.lang.Math;

public class Minimax extends Parquet {

    public static Move getPCBestMove(State state, int[][] board, int depthLimit, boolean alfaBeta) {
        System.out.println("AlphaBetaPruning = " + alfaBeta);
        double alfa = -99999999;
        double beta = 99999999;
        double score;
        int depth = 0;
        Move bestMove = new Move(0, 0, 0);
        double bestScore = -99999999;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                for (int d = 0; d < 4; d++) {
                    Move move = new Move(x, y, d);
                    if (validMove(state, board, move, 3)) {
                        makeEnemyMove(state, board, move);
                        score = state.updateScore(state, board);
                        if (alfaBeta)
                            score = minimax(state, board, depth, depthLimit, false, alfa, beta);
                        else
                            score = minimax(state, board, depth, depthLimit, false);
                        revertEnemyMove(state, board, move);
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = move;
                        }
                        score = state.updateScore(state, board);
                    }
                }
            }
        }
        System.out.println("getPCBestMove X = " + bestMove.x + ", Y = " + bestMove.y + ", D = " + bestMove.direction);
        System.out.println("Best Score = " + bestScore);
        return bestMove;
    }

    public static double minimax(State state, int[][] board, int depth, int depthLimit, boolean isMax, double alfa,
            double beta) {
        double score;
        if (depthLimit == 0) {
            return state.updateScore(state, board);
        }
        if (state.checkGameOver(board)) {
            return state.updateScore(state, board);
        }
        if (isMax) {
            double bestScore = -99999999;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    for (int d = 0; d < 4; d++) {
                        Move move = new Move(x, y, d);
                        if (validMove(state, board, move, 3)) {
                            makeEnemyMove(state, board, move);
                            score = state.updateScore(state, board);
                            score = minimax(state, board, depth + 1, depthLimit - 1, false, alfa, beta);
                            revertEnemyMove(state, board, move);
                            if (score > bestScore) {
                                bestScore = score;
                            }
                            alfa = Math.max(alfa, bestScore);
                            if (beta <= alfa)
                                break;
                            score = state.updateScore(state, board);
                        }
                    }
                }
            }
            return bestScore;
        } else {
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
                            score = state.updateScore(state, board);
                            score = minimax(state, board, depth + 1, depthLimit - 1, true, alfa, beta);
                            revertMakeMove(state, board, move);
                            if (score < bestScore) {
                                bestScore = score;
                            }
                            beta = Math.min(beta, bestScore);
                            if (beta <= alfa)
                                break;
                            score = state.updateScore(state, board);
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public static double minimax(State state, int[][] board, int depth, int depthLimit, boolean isMax) {
        double score;
        if (depthLimit == 0) {
            return state.updateScore(state, board);
        }
        if (state.checkGameOver(board)) {
            return state.updateScore(state, board);
        }
        if (isMax) {
            double bestScore = -99999999;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    for (int d = 0; d < 4; d++) {
                        Move move = new Move(x, y, d);
                        if (validMove(state, board, move, 3)) {
                            makeEnemyMove(state, board, move);
                            score = state.updateScore(state, board);
                            score = minimax(state, board, depth + 1, depthLimit - 1, false);
                            revertEnemyMove(state, board, move);
                            if (score > bestScore) {
                                bestScore = score;
                            }
                            score = state.updateScore(state, board);
                        }
                    }
                }
            }
            return bestScore;
        } else {
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
                            score = state.updateScore(state, board);
                            score = minimax(state, board, depth + 1, depthLimit - 1, true);
                            revertMakeMove(state, board, move);
                            if (score < bestScore) {
                                bestScore = score;
                            }
                            score = state.updateScore(state, board);
                        }
                    }
                }
            }
            return bestScore;
        }
    }
}