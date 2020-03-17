import java.util.Scanner;
import java.util.Random;

public class Parquet {
    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            System.out.println();
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + "   ");
            }
        }
        System.out.println();
    }

    private static boolean validMove(State state, Move move) {
        if (move.direction.equals("MoveRight") && move.y < 6 && move.y >= 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x][move.y + 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveLeft") && move.y < 6 && move.y >= 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x][move.y - 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveUp") && move.x < 6 && move.x >= 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x - 1][move.y] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveDown") && move.x < 6 && move.x >= 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x + 1][move.y] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveUpRight") && move.x < 6 && move.x >= 0 && move.y < 6 && move.y >= 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x - 1][move.y + 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveDownLeft") && move.x < 6 && move.x >= 0 && move.y < 6 && move.y >= 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x + 1][move.y - 1] == 0)
                    return true;
            }
        } else if (move.direction.equals("MoveUpRight2") && move.x < 6 && move.x >= 0 && move.y < 6 && move.y >= 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x - 1][move.y + 1] != 0 && state.board[move.x - 1][move.y + 1] != 1) {
                    if (state.board[move.x - 2][move.y + 2] == 0)    
                        return true;
                }
            }
        } else if (move.direction.equals("MoveDownLeft2") && move.x < 6 && move.x >= 0 && move.y < 6 && move.y >= 0) {
            if (state.board[move.x][move.y] == state.player) {
                if (state.board[move.x + 1][move.y - 1] != 0 && state.board[move.x + 1][move.y - 1] != 1) {
                    if (state.board[move.x + 2][move.y - 2] == 0)    
                        return true;
                }
            }
        }

        return false;
    }

    private static State makeMove(State state, Move move) {
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
            if (move.direction.equals("MoveUpRight2")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x - 2][move.y + 2] = state.player;
            }

            state.player++;
        }
        return state;
    }

    private static State makeEnemyMove(State state, Move move) {
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
            if (move.direction.equals("MoveDownLeft2")) {
                state.board[move.x][move.y] = 0;
                state.board[move.x + 2][move.y - 2] = state.player;
            }

            state.player--;
        }
        return state;
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
        else if(randomNumber == 1)
            direction = "MoveDown";
        else if(randomNumber == 2)
            direction = "MoveDownLeft";
        else if(randomNumber == 3)
            direction = "MoveDownLeft2";

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

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        State state = new State(2);
        printBoard(state.board);

        while (!state.gameover) {
            Move move = getUserMove(scanner, state);
            if (validMove(state, move)) {
                state = makeMove(state, move);
                printBoard(state.board);
            } else {
                System.out.print("Invalid Move\n");
            }
            state = makeEnemyMove(state, getPCRandomMove(state));
            printBoard(state.board);
            state.checkGameOver();
        }

        scanner.close();
        System.out.print("GameOver\n");
    }
}