import java.awt.EventQueue;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class MinesweeperGUI {

    private JFrame start;
    private JFrame game;
    public int mines;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    MinesweeperGUI window = new MinesweeperGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MinesweeperGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // Setup of start window
        start = new JFrame();
        start.setBounds(100, 100, 300, 100);
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.getContentPane().setLayout(new GridLayout(1, 3, 0, 0));

        JButton easy = new JButton("Easy");
        start.getContentPane().add(easy);

        JButton medium = new JButton("Medium");
        start.getContentPane().add(medium);

        JButton hard = new JButton("Hard");
        start.getContentPane().add(hard);

        start.setVisible(true);

        easy.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mines = 10;
                BoardLayout board = new BoardLayout(mines, 9, 9);

                createWindow(board);

            }
        });
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mines = 40;
                BoardLayout board = new BoardLayout(mines, 16, 16);

                createWindow(board);

            }
        });
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mines = 99;
                BoardLayout board = new BoardLayout(mines, 16, 30);

                createWindow(board);
            }
        });
    }

    public void createWindow(BoardLayout board) {
        // setup of game window

        game = new JFrame();
        game.setBounds(0, 0, 50 * board.getWidth(), 50 * board.getHeight());
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getContentPane().setLayout(new GridLayout(board.getHeight(), board.getWidth(), 0, 0));

        JButton[][] buttons = new JButton[board.getHeight()][board.getWidth()];

        // Creating all of the buttons
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                int row = i;
                int col = j;

                JButton button = new JButton();
                button.setBackground(Color.BLUE);
                buttons[i][j] = button;

                // left click to expose
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // int clicks = 0;
                        if (board.getValue(row, col) == 'X') {
                            JOptionPane.showMessageDialog(null, "You hit a mine, you lose!");
                            start.setVisible(true);
                            game.setVisible(false);
                        } else {
                            if (board.getValue(row, col) == '0') {
                                floodFill(board, row, col, buttons);

                            } else {
                                buttons[row][col].setText("" + board.getValue(row, col));
                                buttons[row][col].setBackground(Color.CYAN);
                            }

                        }
                    }

                });
                // right click to set flag
                button.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            buttons[row][col].setBackground(Color.RED);

                            if (board.getValue(row, col) == 'X') {
                                buttons[row][col].setEnabled(false);
                                mines--;
                                if (mines == 0) {
                                    JOptionPane.showMessageDialog(null, "Congratulations, you win!");
                                    start.setVisible(true);
                                    game.setVisible(false);
                                }
                            }
                        }
                    }
                });

                game.getContentPane().add(button);
            }
        }
        start.setVisible(false);
        game.setVisible(true);
    }

    public void floodFill(BoardLayout board, int i, int j, JButton[][] buttons) {
        if (buttons[i][j].getBackground() != Color.CYAN) {
            buttons[i][j].setText("" + board.getValue(i, j));
            buttons[i][j].setBackground(Color.CYAN);
            if (board.getValue(i, j) == '0') {
                // First Row
                if (i == 0) {
                    // Top Left Corner
                    if (j == 0) {
                        floodFill(board, 0, 0 + 1, buttons);

                        floodFill(board, 0 + 1, 0 + 1, buttons);

                        floodFill(board, 0, 0, buttons);
                    }
                    // Top Right Corner
                    else if (j == board.getWidth() - 1) {

                        floodFill(board, 1, board.getWidth() - 1, buttons);

                        floodFill(board, 1, board.getWidth() - 2, buttons);

                        floodFill(board, 0, board.getWidth() - 2, buttons);
                    }
                    // Edge
                    else {

                        floodFill(board, 0, j + 1, buttons);

                        floodFill(board, 1, j + 1, buttons);

                        floodFill(board, 1, j, buttons);

                        floodFill(board, 1, j - 1, buttons);

                        floodFill(board, 0, j - 1, buttons);
                    }
                }
                // Bottom Row
                else if (i == board.getHeight() - 1) {
                    // Bottom Left Corner
                    if (j == 0) {

                        floodFill(board, board.getHeight() - 2, 0, buttons);

                        floodFill(board, board.getHeight() - 2, 1, buttons);

                        floodFill(board, board.getHeight() - 1, 1, buttons);
                    }
                    // Bottom Right Corner
                    else if (j == board.getWidth() - 1) {

                        floodFill(board, board.getHeight() - 1, board.getWidth() - 2, buttons);

                        floodFill(board, board.getHeight() - 2, board.getWidth() - 2, buttons);

                        floodFill(board, board.getHeight() - 2, board.getWidth() - 1, buttons);
                    }
                    // Edge
                    else {

                        floodFill(board, board.getHeight() - 1, j - 1, buttons);

                        floodFill(board, board.getHeight() - 2, j - 1, buttons);

                        floodFill(board, board.getHeight() - 1, j, buttons);

                        floodFill(board, board.getHeight() - 2, j + 1, buttons);

                        floodFill(board, board.getHeight() - 1, j + 1, buttons);
                    }
                }
                // Left Edge
                else if (j == 0 && i != 0 && i != board.getHeight() - 1) {

                    floodFill(board, i - 1, 0, buttons);

                    floodFill(board, i - 1, 1, buttons);

                    floodFill(board, i, 1, buttons);

                    floodFill(board, i + 1, 1, buttons);

                    floodFill(board, i + 1, 0, buttons);
                }
                // Right Edge
                else if (j == board.getWidth() - 1 && i != 0 && i != board.getHeight() - 1) {
                    floodFill(board, i + 1, board.getWidth() - 1, buttons);

                    floodFill(board, i + 1, board.getWidth() - 2, buttons);

                    floodFill(board, i, board.getWidth() - 2, buttons);

                    floodFill(board, i - 1, board.getWidth() - 2, buttons);

                    floodFill(board, i - 1, board.getWidth() - 1, buttons);
                }
                // Interior
                else {

                    floodFill(board, i - 1, j, buttons);

                    floodFill(board, i - 1, j + 1, buttons);

                    floodFill(board, i, j + 1, buttons);

                    floodFill(board, i + 1, j + 1, buttons);

                    floodFill(board, i + 1, j, buttons);

                    floodFill(board, i + 1, j - 1, buttons);

                    floodFill(board, i, j - 1, buttons);

                    floodFill(board, i - 1, j - 1, buttons);
                }
            }
        }
    }
}
