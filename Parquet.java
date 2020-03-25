import java.util.Scanner;
import java.util.Random;

public class Parquet {
    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static boolean validMove(State state, Move move) {
        if (move.direction.equals("MoveRight") && move.y < 5) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x][move.y + 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveLeft") && move.y > 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x][move.y - 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUp") && move.x > 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x - 1][move.y] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveDown") && move.x < 5) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x + 1][move.y] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUpRight") && move.x > 0 && move.y < 5) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x - 1][move.y + 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveDownLeft") && move.y > 0 && move.x < 5) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x + 1][move.y - 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUpRightJump") && move.x > 1 && move.y < 4) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x - 1][move.y + 1] != 0 && state.board[move.x - 1][move.y + 1] != 1) {
                    if (state.board[move.x - 2][move.y + 2] == 0) {
                        return true;
                    }
                }
            }
        }
        if (move.direction.equals("MoveDownLeftJump") && move.y > 1 && move.x < 4) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x + 1][move.y - 1] != 0 && state.board[move.x + 1][move.y - 1] != 1) {
                    if (state.board[move.x + 2][move.y - 2] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void makeMove(State state, Move move) {
        if (state.player == 2) {
            if (move.direction.equals("MoveRight")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x][move.y + 1] = state.player;
            }
            if (move.direction.equals("MoveUp")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x - 1][move.y] = state.player;
            }
            if (move.direction.equals("MoveUpRight")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x - 1][move.y + 1] = state.player;
            }
            if (move.direction.equals("MoveUpRightJump")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x - 2][move.y + 2] = state.player;
            }
            state.player++;
        }
    }

    private static void revertMakeMove(State state, Move move) {
        state.player--;
        if (state.player == 2) {
            if (move.direction.equals("MoveRight")) {
                state.board[move.x][move.y + 1] = 0;
                state.board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveUp")) {
                state.board[move.x - 1][move.y] = 0;
                state.board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveUpRight")) {
                state.board[move.x - 1][move.y + 1] = 0;
                state.board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveUpRightJump")) {
                state.board[move.x - 2][move.y + 2] = 0;
                state.board[move.x][move.y] = state.player;
            }
        }
    }

    private static void makeEnemyMove(State state, Move move) {
        if (state.player == 3) {
            if (move.direction.equals("MoveLeft")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x][move.y - 1] = state.player;
            }
            if (move.direction.equals("MoveDown")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x + 1][move.y] = state.player;
            }
            if (move.direction.equals("MoveDownLeft")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x + 1][move.y - 1] = state.player;
            }
            if (move.direction.equals("MoveDownLeftJump")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x + 2][move.y - 2] = state.player;
            }
            state.player--;
        }
    }

    private static void revertEnemyMove(State state, Move move) {
        state.player++;
        if (state.player == 3) {
            if (move.direction.equals("MoveLeft")) {
                state.board[move.x][move.y - 1] = 0;
                state.board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveDown")) {
                state.board[move.x + 1][move.y] = 0;
                state.board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveDownLeft")) {
                state.board[move.x + 1][move.y - 1] = 0;
                state.board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveDownLeftJump")) {
                state.board[move.x + 2][move.y - 2] = 0;
                state.board[move.x][move.y] = state.player;
            }
        }
    }

    private static Move getUserMove(Scanner scanner, State state) {
        System.out.print("Input <PosX PosY Direction>\n");
        String input = scanner.nextLine();
        String[] str = input.split(" ");
        int x = Integer.parseInt(str[0]);
        int y = Integer.parseInt(str[1]);
        Move move = new Move(x, y, str[2]);
        return move;
    }

    private static Move getPCRandomMove(State state) {
        String direction = "MoveLeft";
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        if (randomNumber == 0)
            direction = "MoveLeft";
        else if (randomNumber == 1)
            direction = "MoveDown";
        else if (randomNumber == 2)
            direction = "MoveDownLeft";
        else if (randomNumber == 3)
            direction = "MoveDownLeftJump";
        int randomNumber1 = rand.nextInt(6);
        int randomNumber2 = rand.nextInt(6);
        Move move = new Move(randomNumber1, randomNumber2, direction);
        while (state.board[randomNumber1][randomNumber2] != 3 || !validMove(state, move)) {
            randomNumber1 = rand.nextInt(6);
            randomNumber2 = rand.nextInt(6);
            move.x = randomNumber1;
            move.y = randomNumber2;
        }
        System.out.print("PC Random Move from [" + randomNumber1 + "][" + randomNumber2 + "] " + direction);
        return move;
    }

    private static boolean validVoidMove(State state, Move move) {
        if (move.direction.equals("MoveRight") && move.y < 6 && move.y >= 0) {
            if (state.board[move.x][move.y] == 1) {
                if (state.board[move.x][move.y + 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveLeft") && move.y < 6 && move.y >= 0) {
            if (state.board[move.x][move.y] == 1) {
                if (state.board[move.x][move.y - 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveUp") && move.x < 6 && move.x >= 0) {
            if (state.board[move.x][move.y] == 1) {
                if (state.board[move.x - 1][move.y] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveDown") && move.x < 6 && move.x >= 0) {
            if (state.board[move.x][move.y] == 1) {
                if (state.board[move.x + 1][move.y] == 0)
                    return true;
            }
        }
        return false;
    }

    private static void makeVoidMove(State state, Move move) {
        switch (move.direction) {
            case "MoveUp":
                state.board[move.x][move.y] = 0;
                state.board[move.x - 1][move.y] = 1;
                break;
            case "MoveDown":
                state.board[move.x][move.y] = 0;
                state.board[move.x + 1][move.y] = 1;
                break;
            case "MoveLeft":
                state.board[move.x][move.y] = 0;
                state.board[move.x][move.y - 1] = 1;
                break;
            case "MoveRight":
                state.board[move.x][move.y] = 0;
                state.board[move.x][move.y + 1] = 1;
                break;
        }
    }

    private static Move getPCBestMove(State state) {
        Move bestMove = new Move(0, 0, 0);
        int bestScore = -99999999;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                for (int d = 0; d < 4; d++) {
                    Move move = new Move(x, y, d);
                    if (validMove(state, move)) {
                        makeEnemyMove(state, move);
                        state.score = minimax(state, 0, false);
                        revertEnemyMove(state, move);
                        if (state.score > bestScore) {
                            bestScore = state.score;
                            bestMove = move;
                        }
                    }
                }
            }
        }
        System.out.println("Minimax bestMove: x=" + bestMove.x + " y=" + bestMove.y + " d=" + bestMove.direction);
        return bestMove;
    }

    private static int minimax(State state, int depth, boolean isMax) {
        if (state.checkGameOver()) {
            return state.score;
        }
        if (isMax) {
            int bestScore = -99999999;
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 6; y++) {
                    for (int d = 0; d < 4; d++) {
                        Move move = new Move(x, y, d);
                        if (validMove(state, move)) {
                            makeEnemyMove(state, move);
                            state.score = minimax(state, depth + 1, false);
                            revertEnemyMove(state, move);
                            if (state.score > bestScore) {
                                bestScore = state.score;
                            }
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 99999999;
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 6; y++) {
                    for (int d = 0; d < 4; d++) {
                        Move move = new Move(x, y, d);
                        if (d == 0) {
                            move.direction = "MoveRight";
                        } else if (d == 1) {
                            move.direction = "MoveUp";
                        } else if (d == 2) {
                            move.direction = "MoveUpRight";
                        } else
                            move.direction = "MoveUpRightJump";
                        if (validMove(state, move)) {
                            makeMove(state, move);
                            state.score = minimax(state, depth + 1, true);
                            revertMakeMove(state, move);
                            if (state.score < bestScore) {
                                bestScore = state.score;
                            }
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Move startingMove = new Move(5, 0, "MoveRight");
        State state = new State("Board", 2);
        makeMove(state, startingMove);
        makeEnemyMove(state, getPCBestMove(state));
        while (!state.checkGameOver()) {
            printBoard(state.board);
            Move move = getUserMove(scanner, state);
            while (!(validMove(state, move))) {
                printBoard(state.board);
                System.out.print("Invalid Move\n");
                move = getUserMove(scanner, state);
            }
            makeMove(state, move);
            makeEnemyMove(state, getPCBestMove(state));
            state.checkGameOver();
        }
        scanner.close();
        System.out.println("GameOver");
        String winner = "Human";
        if (state.player == 2) {
            winner = "PC";
        }
        System.out.println(winner + " Won!");
    }
}