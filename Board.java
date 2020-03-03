import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class Board {

    private JPanel gui = new JPanel(new BorderLayout(2, 2));
    private Color DARKWOOD = new Color(51, 0, 0);
    private Color WOOD = new Color(102, 51, 0);
    private Color VERYDARKBLUE = new Color(0, 0, 154);

    Board() {
    }

    public void displayBoard(int[][] board) {
        JButton[][] boardSquares = new JButton[board.length][board.length];
        gui.setBorder(new EmptyBorder(10, 10, 10, 10));
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        gui.add(toolbar, BorderLayout.PAGE_START);
        toolbar.add(new JButton("New Game"));
        JPanel gameBoard = new JPanel(new GridLayout(board.length, board.length));
        gameBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(gameBoard);

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < boardSquares.length; ii++) {
            for (int jj = 0; jj < boardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if (board[ii][jj] == 0) {
                    b.setBackground(DARKWOOD);
                } else if (board[ii][jj] == 1) {
                    b.setBackground(WOOD);
                } else if (board[ii][jj] == 2) {
                    b.setBackground(VERYDARKBLUE);
                } else if (board[ii][jj] == 3) {
                    b.setBackground(Color.YELLOW);
                } else {
                    b.setBackground(Color.BLACK);
                }
                boardSquares[jj][ii] = b;
                gameBoard.add(boardSquares[jj][ii]);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        b.setBackground(VERYDARKBLUE);
                    }
                });
            }
        }

        JFrame frame = new JFrame("Parquet");
        frame.add(gui);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
    }
}