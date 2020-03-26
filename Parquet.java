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

    protected static boolean validMove(State state, int[][] board, Move move) {
        if (move.direction.equals("MoveRight") && move.y < board.length - 1) {
            if (board[move.x][move.y] == state.player) {
                if (board[move.x][move.y + 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveLeft") && move.y > 0) {
            if (board[move.x][move.y] == state.player) {
                if (board[move.x][move.y - 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUp") && move.x > 0) {
            if (board[move.x][move.y] == state.player) {
                if (board[move.x - 1][move.y] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveDown") && move.x < board.length - 1) {
            if (board[move.x][move.y] == state.player) {
                if (board[move.x + 1][move.y] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUpRight") && move.x > 0 && move.y < board.length - 1) {
            if (board[move.x][move.y] == state.player) {
                if (board[move.x - 1][move.y + 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveDownLeft") && move.y > 0 && move.x < board.length - 1) {
            if (board[move.x][move.y] == state.player) {
                if (board[move.x + 1][move.y - 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUpRightJump") && move.x > 1 && move.y < board.length - 2) {
            if (board[move.x][move.y] == state.player) {
                if (board[move.x - 1][move.y + 1] != 0 && board[move.x - 1][move.y + 1] != 1) {
                    if (board[move.x - 2][move.y + 2] == 0) {
                        return true;
                    }
                }
            }
        }
        if (move.direction.equals("MoveDownLeftJump") && move.y > 1 && move.x < board.length - 2) {
            if (board[move.x][move.y] == state.player) {
                if (board[move.x + 1][move.y - 1] != 0 && board[move.x + 1][move.y - 1] != 1) {
                    if (board[move.x + 2][move.y - 2] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected static void makeMove(State state, int[][] board, Move move) {
        if (state.player == 2) {
            if (move.direction.equals("MoveRight")) {
                board[move.x][move.y] = 0;
                board[move.x][move.y + 1] = state.player;
            }
            if (move.direction.equals("MoveUp")) {
                board[move.x][move.y] = 0;
                board[move.x - 1][move.y] = state.player;
            }
            if (move.direction.equals("MoveUpRight")) {
                board[move.x][move.y] = 0;
                board[move.x - 1][move.y + 1] = state.player;
            }
            if (move.direction.equals("MoveUpRightJump")) {
                board[move.x][move.y] = 0;
                board[move.x - 2][move.y + 2] = state.player;
            }
            state.player++;
            state.updateScore(state, board);
        }
    }

    protected static void revertMakeMove(State state, int[][] board, Move move) {
        state.player--;
        if (state.player == 2) {
            if (move.direction.equals("MoveRight")) {
                board[move.x][move.y + 1] = 0;
                board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveUp")) {
                board[move.x - 1][move.y] = 0;
                board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveUpRight")) {
                board[move.x - 1][move.y + 1] = 0;
                board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveUpRightJump")) {
                board[move.x - 2][move.y + 2] = 0;
                board[move.x][move.y] = state.player;
            }
            state.updateScore(state, board);
        }
    }

    protected static void makeEnemyMove(State state, int[][] board, Move move) {
        if (state.player == 3) {
            if (move.direction.equals("MoveLeft")) {
                board[move.x][move.y] = 0;
                board[move.x][move.y - 1] = state.player;
            }
            if (move.direction.equals("MoveDown")) {
                board[move.x][move.y] = 0;
                board[move.x + 1][move.y] = state.player;
            }
            if (move.direction.equals("MoveDownLeft")) {
                board[move.x][move.y] = 0;
                board[move.x + 1][move.y - 1] = state.player;
            }
            if (move.direction.equals("MoveDownLeftJump")) {
                board[move.x][move.y] = 0;
                board[move.x + 2][move.y - 2] = state.player;
            }
            state.player--;
            state.updateScore(state, board);
        }
    }

    protected static void revertEnemyMove(State state, int[][] board, Move move) {
        state.player++;
        if (state.player == 3) {
            if (move.direction.equals("MoveLeft")) {
                board[move.x][move.y - 1] = 0;
                board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveDown")) {
                board[move.x + 1][move.y] = 0;
                board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveDownLeft")) {
                board[move.x + 1][move.y - 1] = 0;
                board[move.x][move.y] = state.player;
            }
            if (move.direction.equals("MoveDownLeftJump")) {
                board[move.x + 2][move.y - 2] = 0;
                board[move.x][move.y] = state.player;
            }
            state.updateScore(state, board);
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

    private static Move getPCRandomMove(State state, int[][] board) {
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
        int randomNumber1 = rand.nextInt(board.length);
        int randomNumber2 = rand.nextInt(board.length);
        Move move = new Move(randomNumber1, randomNumber2, direction);
        while (board[randomNumber1][randomNumber2] != 3 || !validMove(state, board, move)) {
            randomNumber1 = rand.nextInt(board.length);
            randomNumber2 = rand.nextInt(board.length);
            move.x = randomNumber1;
            move.y = randomNumber2;
        }
        System.out.print("PC Random Move from [" + randomNumber1 + "][" + randomNumber2 + "] " + direction);
        return move;
    }

    private static boolean validVoidMove(State state, int[][] board, Move move) {
        if (move.direction.equals("MoveRight") && move.y < board.length && move.y >= 0) {
            if (board[move.x][move.y] == 1) {
                if (board[move.x][move.y + 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveLeft") && move.y < board.length && move.y >= 0) {
            if (board[move.x][move.y] == 1) {
                if (board[move.x][move.y - 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveUp") && move.x < board.length && move.x >= 0) {
            if (board[move.x][move.y] == 1) {
                if (board[move.x - 1][move.y] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveDown") && move.x < board.length && move.x >= 0) {
            if (board[move.x][move.y] == 1) {
                if (board[move.x + 1][move.y] == 0)
                    return true;
            }
        }
        return false;
    }

    private static void makeVoidMove(State state, int[][] board, Move move) {
        switch (move.direction) {
            case "MoveUp":
                board[move.x][move.y] = 0;
                board[move.x - 1][move.y] = 1;
                break;
            case "MoveDown":
                board[move.x][move.y] = 0;
                board[move.x + 1][move.y] = 1;
                break;
            case "MoveLeft":
                board[move.x][move.y] = 0;
                board[move.x][move.y - 1] = 1;
                break;
            case "MoveRight":
                board[move.x][move.y] = 0;
                board[move.x][move.y + 1] = 1;
                break;
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        State state = new State("Board", 3);
        int size = 0;
        int board[][] = { {} };

        while (size != 1 && size != 2) {
            System.out.println("Choose board to play:");
            System.out.println("Board 4x4 - press 1");
            System.out.println("Board 6x6 - press 2");
            String input = scanner.nextLine();
            size = Integer.parseInt(input);
            if (size == 1)
                board = state.board4x4;
            else if (size == 2)
                board = state.board6x6;

            int depthLimit = 3;

            long startTime = System.nanoTime();
            makeEnemyMove(state, board, Minimax.getPCBestMove(state, board, depthLimit));
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println(
                    "getPCBestMove DepthLimit = " + depthLimit + " Duration = " + duration / 1000000 + " Miliseconds");

            while (!state.checkGameOver(board)) {
                printBoard(board);
                Move move = getUserMove(scanner, state);
                while (!(validMove(state, board, move))) {
                    printBoard(board);

                    System.out.print("Invalid Move\n");
                    move = getUserMove(scanner, state);
                }
                makeMove(state, board, move);
                startTime = System.nanoTime();
                makeEnemyMove(state, board, Minimax.getPCBestMove(state, board, depthLimit));
                endTime = System.nanoTime();
                duration = (endTime - startTime);
                System.out.println("getPCBestMove DepthLimit = " + depthLimit + " Duration = " + duration / 1000000
                        + " Miliseconds");
                state.checkGameOver(board);
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
}