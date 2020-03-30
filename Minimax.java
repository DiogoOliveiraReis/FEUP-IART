import java.lang.Math;

public class Minimax extends Parquet {

    public static Move[] getPCBestMove(State state, int[][] board, int depthLimit, boolean alfaBeta, int player) {
        System.out.println("AlphaBetaPruning = " + alfaBeta);
        double alfa = -99999999;
        double beta = 99999999;
        double score;
        int depth = 0;
        Move bestMove = new Move(0, 0, 0, player);
        Move bestVoidMove = new Move(0, 0, 0, player);
        double bestScore = -99999999;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                for (int d = 0; d < 4; d++) {
                    Move move = new Move(x, y, d, player);
                    if (validMove(state, board, move, player)) {
                        if(player == 3) makeMoveTopPlayer(state, board, move);
                        else makeMoveBottomPlayer(state, board, move);
                        for (int xv = 0; xv < board.length; xv++) {
                            for (int yv = 0; yv < board.length; yv++) {
                                for(int voidDir = 0; voidDir < 4; voidDir++) {
                                    Move moveVoid = new Move(xv, yv, voidDir, player);
                                    if (voidDir == 0) {
                                        moveVoid.direction = "MoveUp";
                                    } else if (voidDir == 1) {
                                        moveVoid.direction = "MoveLeft";
                                    } else if (voidDir == 2) {
                                        moveVoid.direction = "MoveDown";
                                    } else if (voidDir == 3) {
                                        moveVoid.direction = "MoveRight";
                                    }
                                    if (validVoidMove(state, board, moveVoid)) {
                                        makeVoidMove(state, board, moveVoid);

                                        if (alfaBeta)
                                            score = minimax(state, board, depth, depthLimit, false, alfa, beta, player);
                                        else
                                            score = minimax(state, board, depth, depthLimit, false, player);
                                                                                    
                                        revertVoidMove(state, board, moveVoid);

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
                        if(player == 3) revertMoveTopPlayer(state, board, move);
                        else revertMakeMoveBottomPlayer(state, board, move);
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
            double beta, int player) {
        double score;
        if (depthLimit == 0) {
            return state.updateScore(state, board, player);
        }
        if (state.checkGameOver(board)) {
            return state.updateScore(state, board, player);
        }
        if (isMax) {
            double bestScore = -99999999;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    for (int d = 0; d < 4; d++) {
                        Move move = new Move(x, y, d, player);
                        if (validMove(state, board, move, player)) {
                            if(player == 3) makeMoveTopPlayer(state, board, move);
                            else makeMoveBottomPlayer(state, board, move);
                            for (int xv = 0; xv < board.length; xv++) {
                                for (int yv = 0; yv < board.length; yv++) {
                                    for(int voidDir = 0; voidDir < 4; voidDir++) {
                                        Move moveVoid = new Move(xv, yv, voidDir, player);
                                        if (voidDir == 0) {
                                            moveVoid.direction = "MoveUp";
                                        } else if (voidDir == 1) {
                                            moveVoid.direction = "MoveLeft";
                                        } else if (voidDir == 2) {
                                            moveVoid.direction = "MoveDown";
                                        } else if (voidDir == 3) {
                                            moveVoid.direction = "MoveRight";
                                        }
                                        if (validVoidMove(state, board, moveVoid)) {
                                            makeVoidMove(state, board, moveVoid);
                                            
                                            score = minimax(state, board, depth + 1, depthLimit - 1, false, alfa, beta, player);

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
                            if (player == 3) revertMoveTopPlayer(state, board, move);
                            else revertMakeMoveBottomPlayer(state, board, move);
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
                        Move move;
                        int p;
                        if(player == 3) {
                            move = new Move(x, y, d, 2);
                            p = 2;
                        }
                        else {
                            move = new Move(x, y, d, 3);
                            p = 3;
                        }
                        
                        if (validMove(state, board, move, p)) {
                            if(player == 3) makeMoveBottomPlayer(state, board, move);
                            else makeMoveTopPlayer(state, board, move);
                            for (int xv = 0; xv < board.length; xv++) {
                                for (int yv = 0; yv < board.length; yv++) {
                                    for(int voidDir = 0; voidDir < 4; voidDir++) {
                                        Move moveVoid = new Move(xv, yv, voidDir, player);
                                        if (voidDir == 0) {
                                            moveVoid.direction = "MoveUp";
                                        } else if (voidDir == 1) {
                                            moveVoid.direction = "MoveLeft";
                                        } else if (voidDir == 2) {
                                            moveVoid.direction = "MoveDown";
                                        } else if (voidDir == 3) {
                                            moveVoid.direction = "MoveRight";
                                        }
                                        if (validVoidMove(state, board, moveVoid)) {
                                            makeVoidMove(state, board, moveVoid);
                                            
                                            score = minimax(state, board, depth + 1, depthLimit - 1, true, alfa, beta, player);

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
                            if (player == 3) revertMakeMoveBottomPlayer(state, board, move);
                            else revertMoveTopPlayer(state, board, move);
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public static double minimax(State state, int[][] board, int depth, int depthLimit, boolean isMax, int player) {
        double score;
        if (depthLimit == 0) {
            return state.updateScore(state, board, player);
        }
        if (state.checkGameOver(board)) {
            return state.updateScore(state, board, player);
        }
        if (isMax) {
            double bestScore = -99999999;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board.length; y++) {
                    for (int d = 0; d < 4; d++) {
                        Move move = new Move(x, y, d, player);
                        if (validMove(state, board, move, player)) {
                            if (player == 3) makeMoveTopPlayer(state, board, move);
                            else makeMoveBottomPlayer(state, board, move);
                            for (int xv = 0; xv < board.length; xv++) {
                                for (int yv = 0; yv < board.length; yv++) {
                                    for(int voidDir = 0; voidDir < 4; voidDir++) {
                                        Move moveVoid = new Move(xv, yv, voidDir, player);
                                        if (voidDir == 0) {
                                            moveVoid.direction = "MoveUp";
                                        } else if (voidDir == 1) {
                                            moveVoid.direction = "MoveLeft";
                                        } else if (voidDir == 2) {
                                            moveVoid.direction = "MoveDown";
                                        } else if (voidDir == 3) {
                                            moveVoid.direction = "MoveRight";
                                        }
                                        if (validVoidMove(state, board, moveVoid)) {
                                            makeVoidMove(state, board, moveVoid);
            
                                            score = minimax(state, board, depth + 1, depthLimit - 1, false, player);
                                            
                                            revertVoidMove(state, board, moveVoid);

                                            if (score > bestScore) {
                                                bestScore = score;
                                            }
                                        }
                                    }
                                }
                            }
                            if(player == 3) revertMoveTopPlayer(state, board, move);
                            else revertMakeMoveBottomPlayer(state, board, move);
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
                        Move move;
                        if (player == 3) move = new Move(x, y, d, 2);
                        else move = new Move(x, y, d, 3);
                        
                        int p;
                        if(player == 3) {
                            move = new Move(x, y, d, 2);
                            p = 2;
                        }
                        else {
                            move = new Move(x, y, d, 3);
                            p = 3;
                        }
                        
                        if (validMove(state, board, move, p)) {
                            if(player == 3) makeMoveBottomPlayer(state, board, move);
                            else makeMoveTopPlayer(state, board, move);

                            for (int xv = 0; xv < board.length; xv++) {
                                for (int yv = 0; yv < board.length; yv++) {
                                    for(int voidDir = 0; voidDir < 4; voidDir++) {
                                        Move moveVoid = new Move(xv, yv, voidDir, player);
                                        if (voidDir == 0) {
                                            moveVoid.direction = "MoveUp";
                                        } else if (voidDir == 1) {
                                            moveVoid.direction = "MoveLeft";
                                        } else if (voidDir == 2) {
                                            moveVoid.direction = "MoveDown";
                                        } else if (voidDir == 3) {
                                            moveVoid.direction = "MoveRight";
                                        }
                                        if (validVoidMove(state, board, moveVoid)) {
                                            makeVoidMove(state, board, moveVoid);
                                            
                                            score = minimax(state, board, depth + 1, depthLimit - 1, true, player);

                                            revertVoidMove(state, board, moveVoid);

                                            if (score < bestScore) {
                                                bestScore = score;
                                            }
                                        }
                                    }
                                }
                            }
                            if (player == 3) revertMakeMoveBottomPlayer(state, board, move);
                            else revertMoveTopPlayer(state, board, move);
                        }
                    }
                }
            }
            return bestScore;
        }
    }
}