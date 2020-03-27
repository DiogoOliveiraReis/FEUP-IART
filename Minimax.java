import java.lang.Math;

public class Minimax extends Parquet {

    public static Move[] getPCBestMove(State state, int[][] board, int depthLimit, boolean alfaBeta) {
        System.out.println("AlphaBetaPruning = " + alfaBeta);
        double alfa = -99999999;
        double beta = 99999999;
        double score;
        int depth = 0;
        Move bestMove = new Move(0, 0, 0);
        Move bestVoidMove = new Move(0, 0, 0);
        double bestScore = -99999999;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                for (int d = 0; d < 4; d++) {
                    Move move = new Move(x, y, d);
                    if (validMove(state, board, move, 3)) {
                        makeEnemyMove(state, board, move);
                        for (int xv = 0; xv < board.length; xv++) {
                            for (int yv = 0; yv < board.length; yv++) {
                                for(int voidDir = 0; voidDir < 4; voidDir++) {
                                    Move moveVoid = new Move(xv, yv, voidDir);
                                    if (voidDir == 0) {
                                        moveVoid.direction = "MoveUp";
                                    } else if (voidDir == 1) {
                                        moveVoid.direction = "MoveLeft";
                                    } else if (voidDir == 2) {
                                        moveVoid.direction = "MoveDown";
                                    } else if (voidDir == 3) {
                                        moveVoid.direction = "MoveRight";
                                    }
                                    if(validVoidMove(state, board, moveVoid)) {
                                        makeVoidMove(state, board, moveVoid);


                                        if (alfaBeta)
                                            score = minimax(state, board, depth, depthLimit, false, alfa, beta);
                                        else
                                            score = minimax(state, board, depth, depthLimit, false);
                                                                                    
                                        revertVoidMove(state, board, moveVoid);


                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = move;
                                            bestVoidMove = moveVoid;
                                        }
                                    }
                                }
                            }
                        }
                        revertEnemyMove(state, board, move);
                    }
                }
            }
        }
        System.out.println("getPCBestMove X = " + bestMove.x + ", Y = " + bestMove.y + ", D = " + bestMove.direction);
        System.out.println("Best Score = " + bestScore);
        
        Move[] bestmoves = { bestMove, bestVoidMove };
        
        return bestmoves;
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
                            for (int xv = 0; xv < board.length; xv++) {
                                for (int yv = 0; yv < board.length; yv++) {
                                    for(int voidDir = 0; voidDir < 4; voidDir++) {
                                        Move moveVoid = new Move(xv, yv, voidDir);
                                        if (voidDir == 0) {
                                            moveVoid.direction = "MoveUp";
                                        } else if (voidDir == 1) {
                                            moveVoid.direction = "MoveLeft";
                                        } else if (voidDir == 2) {
                                            moveVoid.direction = "MoveDown";
                                        } else if (voidDir == 3) {
                                            moveVoid.direction = "MoveRight";
                                        }
                                        if(validVoidMove(state, board, moveVoid)) {
                                            makeVoidMove(state, board, moveVoid);
                                            
                                            score = minimax(state, board, depth + 1, depthLimit - 1, false, alfa, beta);

                                            revertVoidMove(state, board, moveVoid);

                                            if (score > bestScore) {
                                                bestScore = score;
                                            }
                                            alfa = Math.max(alfa, bestScore);
                                            if (beta <= alfa)
                                                break;
                                        }
                                    }
                                }
                            }
                            revertEnemyMove(state, board, move);
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
                            for (int xv = 0; xv < board.length; xv++) {
                                for (int yv = 0; yv < board.length; yv++) {
                                    for(int voidDir = 0; voidDir < 4; voidDir++) {
                                        Move moveVoid = new Move(xv, yv, voidDir);
                                        if (voidDir == 0) {
                                            moveVoid.direction = "MoveUp";
                                        } else if (voidDir == 1) {
                                            moveVoid.direction = "MoveLeft";
                                        } else if (voidDir == 2) {
                                            moveVoid.direction = "MoveDown";
                                        } else if (voidDir == 3) {
                                            moveVoid.direction = "MoveRight";
                                        }
                                        if(validVoidMove(state, board, moveVoid)) {
                                            makeVoidMove(state, board, moveVoid);
                                            
                                            score = minimax(state, board, depth + 1, depthLimit - 1, true, alfa, beta);

                                            revertVoidMove(state, board, moveVoid);

                                            if (score < bestScore) {
                                                bestScore = score;
                                            }
                                            beta = Math.min(beta, bestScore);
                                            if (beta <= alfa)
                                                break;
                                        }
                                    }
                                }
                            }
                            revertMakeMove(state, board, move);
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
                            for (int xv = 0; xv < board.length; xv++) {
                                for (int yv = 0; yv < board.length; yv++) {
                                    for(int voidDir = 0; voidDir < 4; voidDir++) {
                                        Move moveVoid = new Move(xv, yv, voidDir);
                                        if (voidDir == 0) {
                                            moveVoid.direction = "MoveUp";
                                        } else if (voidDir == 1) {
                                            moveVoid.direction = "MoveLeft";
                                        } else if (voidDir == 2) {
                                            moveVoid.direction = "MoveDown";
                                        } else if (voidDir == 3) {
                                            moveVoid.direction = "MoveRight";
                                        }
                                        if(validVoidMove(state, board, moveVoid)) {
                                            makeVoidMove(state, board, moveVoid);
            
                                            score = minimax(state, board, depth + 1, depthLimit - 1, false);
                                            
                                            revertVoidMove(state, board, moveVoid);
            
                                            if (score > bestScore) {
                                                bestScore = score;
                                            }
                                        }
                                    }
                                }
                            }
                            revertEnemyMove(state, board, move);
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
                            for (int xv = 0; xv < board.length; xv++) {
                                for (int yv = 0; yv < board.length; yv++) {
                                    for(int voidDir = 0; voidDir < 4; voidDir++) {
                                        Move moveVoid = new Move(xv, yv, voidDir);
                                        if (voidDir == 0) {
                                            moveVoid.direction = "MoveUp";
                                        } else if (voidDir == 1) {
                                            moveVoid.direction = "MoveLeft";
                                        } else if (voidDir == 2) {
                                            moveVoid.direction = "MoveDown";
                                        } else if (voidDir == 3) {
                                            moveVoid.direction = "MoveRight";
                                        }
                                        if(validVoidMove(state, board, moveVoid)) {
                                            makeVoidMove(state, board, moveVoid);
                                            
                                            score = minimax(state, board, depth + 1, depthLimit - 1, true);

                                            revertVoidMove(state, board, moveVoid);

                                            if (score < bestScore) {
                                                bestScore = score;
                                            }
                                        }
                                    }
                                }
                            }
                            revertMakeMove(state, board, move);
                        }
                    }
                }
            }
            return bestScore;
        }
    }
}