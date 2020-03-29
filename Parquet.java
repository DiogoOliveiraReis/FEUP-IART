import java.util.Random;
import java.util.Scanner;

public class Parquet {
    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println();
    }

    protected static boolean validMove(State state, int[][] board, Move move, int player) {
        if (move.direction.equals("MoveRight") && move.y < board.length - 1) {
            if (board[move.x][move.y] == player) {
                if (board[move.x][move.y + 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveLeft") && move.y > 0) {
            if (board[move.x][move.y] == player) {
                if (board[move.x][move.y - 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUp") && move.x > 0) {
            if (board[move.x][move.y] == player) {
                if (board[move.x - 1][move.y] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveDown") && move.x < board.length - 1) {
            if (board[move.x][move.y] == player) {
                if (board[move.x + 1][move.y] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUpRight") && move.x > 0 && move.y < board.length - 1) {
            if (board[move.x][move.y] == player) {
                if (board[move.x - 1][move.y + 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveDownLeft") && move.y > 0 && move.x < board.length - 1) {
            if (board[move.x][move.y] == player) {
                if (board[move.x + 1][move.y - 1] == 0) {
                    return true;
                }
            }
        }
        if (move.direction.equals("MoveUpRightJump") && move.x > 1 && move.y < board.length - 2) {
            if (board[move.x][move.y] == player) {
                if (board[move.x - 1][move.y + 1] != 0 && board[move.x - 1][move.y + 1] != 1) {
                    if (board[move.x - 2][move.y + 2] == 0) {
                        return true;
                    }
                }
            }
        }
        if (move.direction.equals("MoveDownLeftJump") && move.y > 1 && move.x < board.length - 2) {
            if (board[move.x][move.y] == player) {
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
        if (move.direction.equals("MoveRight")) {
            board[move.x][move.y] = 0;
            board[move.x][move.y + 1] = 2;
        }
        if (move.direction.equals("MoveUp")) {
            board[move.x][move.y] = 0;
            board[move.x - 1][move.y] = 2;
        }
        if (move.direction.equals("MoveUpRight")) {
            board[move.x][move.y] = 0;
            board[move.x - 1][move.y + 1] = 2;
        }
        if (move.direction.equals("MoveUpRightJump")) {
            board[move.x][move.y] = 0;
            board[move.x - 2][move.y + 2] = 2;
        }
    }

    protected static void revertMakeMove(State state, int[][] board, Move move) {

        if (move.direction.equals("MoveRight")) {
            board[move.x][move.y + 1] = 0;
            board[move.x][move.y] = 2;
        }
        if (move.direction.equals("MoveUp")) {
            board[move.x - 1][move.y] = 0;
            board[move.x][move.y] = 2;
        }
        if (move.direction.equals("MoveUpRight")) {
            board[move.x - 1][move.y + 1] = 0;
            board[move.x][move.y] = 2;
        }
        if (move.direction.equals("MoveUpRightJump")) {
            board[move.x - 2][move.y + 2] = 0;
            board[move.x][move.y] = 2;
        }
    }

    protected static void makeEnemyMove(State state, int[][] board, Move move) {

        if (move.direction.equals("MoveLeft")) {
            board[move.x][move.y] = 0;
            board[move.x][move.y - 1] = 3;
        }
        if (move.direction.equals("MoveDown")) {
            board[move.x][move.y] = 0;
            board[move.x + 1][move.y] = 3;
        }
        if (move.direction.equals("MoveDownLeft")) {
            board[move.x][move.y] = 0;
            board[move.x + 1][move.y - 1] = 3;
        }
        if (move.direction.equals("MoveDownLeftJump")) {
            board[move.x][move.y] = 0;
            board[move.x + 2][move.y - 2] = 3;
        }
    }

    protected static void revertEnemyMove(State state, int[][] board, Move move) {

        if (move.direction.equals("MoveLeft")) {
            board[move.x][move.y - 1] = 0;
            board[move.x][move.y] = 3;
        }
        if (move.direction.equals("MoveDown")) {
            board[move.x + 1][move.y] = 0;
            board[move.x][move.y] = 3;
        }
        if (move.direction.equals("MoveDownLeft")) {
            board[move.x + 1][move.y - 1] = 0;
            board[move.x][move.y] = 3;
        }
        if (move.direction.equals("MoveDownLeftJump")) {
            board[move.x + 2][move.y - 2] = 0;
            board[move.x][move.y] = 3;
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
        while (board[randomNumber1][randomNumber2] != 3 || !validMove(state, board, move, state.player)) {
            randomNumber1 = rand.nextInt(board.length);
            randomNumber2 = rand.nextInt(board.length);
            move.x = randomNumber1;
            move.y = randomNumber2;
        }
        System.out.print("PC Random Move from [" + randomNumber1 + "][" + randomNumber2 + "] " + direction);
        return move;
    }

    protected static boolean validVoidMove(State state, int[][] board, Move move) {

        if (move.direction.equals("MoveRight") && move.y < board.length - 1 && move.y >= 0 && !(move.y == board.length - 2 && move.x == 0)) {
            if (board[move.x][move.y] == 1) {
                if (board[move.x][move.y + 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveLeft") && move.y < board.length && move.y > 0 && !(move.y == 1 && move.x == board.length - 1)) {
            if (board[move.x][move.y] == 1) {
                if (board[move.x][move.y - 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveUp") && move.x < board.length && move.x > 0 && !(move.x == 1 && move.y == board.length - 1)) {
            if (board[move.x][move.y] == 1) {
                if (board[move.x - 1][move.y] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveDown") && move.x < board.length - 1 && move.x >= 0 && !(move.x == board.length - 2 && move.y == 0)) {
            if (board[move.x][move.y] == 1) {
                if (board[move.x + 1][move.y] == 0)
                    return true;
            }
        }
        return false;
    }

    protected static void makeVoidMove(State state, int[][] board, Move move) {
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

    protected static void revertVoidMove(State state, int[][] board, Move move) {
        switch (move.direction) {
            case "MoveUp":
                board[move.x - 1][move.y] = 0;
                board[move.x][move.y] = 1;
                break;
            case "MoveDown":
                board[move.x + 1][move.y] = 0;
                board[move.x][move.y] = 1;
                break;
            case "MoveLeft":
                board[move.x][move.y - 1] = 0;
                board[move.x][move.y] = 1;
                break;
            case "MoveRight":
                board[move.x][move.y + 1] = 0;
                board[move.x][move.y] = 1;
                break;
        }
    }

    public static void main(String args[]) {

        System.out.println(" _____________________________________________________________________");
        System.out.println("|                                                                     |");
        System.out.println("|      ____     ___    ____      ___      _   _   ____    _______     |");
        System.out.println("|     |  _ \\   / _ \\  |  _ \\    / _ \\    | | | | |  ___| |__   __|    |");
        System.out.println("|     | |_| | | |_| | | |_| |  | | | |   | | | | | |___     | |       |");
        System.out.println("|     |  __/  |  _  | |  _  |  | | | |   | | | | |  ___|    | |       | ");
        System.out.println("|     | |     | | | | | | \\ \\  | |_|  \\  | |_| | | |___     | |       |");
        System.out.println("|     |_|     |_| |_| |_|  \\_\\  \\___/\\_\\ |_____| |_____|    |_|       |");
        System.out.println("|                                                                     |");
        System.out.println("|                                                                     |");
        System.out.println("|                           CHOOSE PLAY MODE:                         |");
        System.out.println("|                                                                     |");
        System.out.println("|                          Human vs Human - 1                         |");
        System.out.println("|                            Human vs PC - 2                          |");
        System.out.println("|                              PC vs PC - 3                           |");
        System.out.println("|                                                                     |");
        System.out.println("|_____________________________________________________________________|");
        System.out.println("                                                                       ");
        System.out.print("Press the number corresponding to the option you want:");
        Scanner scanner = new Scanner(System.in);

        play(scanner);

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
        }

        int depthLimit = 3;
        boolean alphaBetaPruning = true;

        while (!state.checkGameOver(board)) {

            System.out.println("main  " + state.player);
            long startTime = System.nanoTime();
            Move[] bestEnemyMoves = Minimax.getPCBestMove(state, board, depthLimit, alphaBetaPruning);
            makeEnemyMove(state, board, bestEnemyMoves[0]);
            printBoard(board);
            makeVoidMove(state, board, bestEnemyMoves[1]);
            printBoard(board);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("getPCBestMove DepthLimit = " + depthLimit + " Duration = " + duration / 1000000
                    + " Miliseconds");

            if (state.checkGameOver(board)) {
                System.out.println("PC WON!!");
                break;
            }

            Move move = getUserMove(scanner, state);
            while (!(validMove(state, board, move, 2))) {
                printBoard(board);

                System.out.print("Invalid Move\n");
                move = getUserMove(scanner, state);
            }
            makeMove(state, board, move);
            printBoard(board);
            if (state.checkGameOver(board)) {
                System.out.println("HUMAN WON!!");
                break;
            }
            
            Move voidMove = getUserMove(scanner, state);
            while (!(validVoidMove(state, board, voidMove))) {
                printBoard(board);

                System.out.print("Invalid Move\n");
                voidMove = getUserMove(scanner, state);
            }
            makeVoidMove(state, board, voidMove);
        }
        scanner.close();
        System.out.println("GameOver");
    }


    private static void play(Scanner scanner) {
        String input = scanner.nextLine();
        int mode = Integer.parseInt(input);

        if (mode == 1) {
            HumanVsHuman();
        }
        else if (mode == 2) {
            HumanVsPC();
        }
        else if (mode == 3) {
            PCVsPC();
        }
        else {
            play(scanner);
        }
    }

    private static void HumanVsHuman() {
        System.out.println("HH");
    }

    private static void HumanVsPC() {
        System.out.println("HP");
    }

    private static void PCVsPC() {
        System.out.println("PP");
    }

}