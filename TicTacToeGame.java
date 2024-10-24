import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {

    // Constants for the game
    private static final int BOARD_SIZE = 3;
    
    // Game state variables
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private boolean playerX = true;  // True for player X, false for player O
    
    // Constructor for setting up the game
    public TicTacToeGame() {
        // Set up the frame
        setTitle("Tic-Tac-Toe Game");
        setSize(400, 400);
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize the buttons
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 100));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        // If the button is already marked, do nothing
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        // Mark the button with X or O depending on the player's turn
        if (playerX) {
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("O");
        }

        // Check for a winner or a draw
        if (checkForWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + (playerX ? "X" : "O") + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        } else {
            // Switch player turns
            playerX = !playerX;
        }
    }

    // Check if the board is full (for draw condition)
    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if a player has won
    private boolean checkForWinner() {
        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }
        
        // Check diagonals
        return checkDiagonal();
    }

    private boolean checkRow(int row) {
        return buttons[row][0].getText().equals(buttons[row][1].getText()) &&
               buttons[row][1].getText().equals(buttons[row][2].getText()) &&
               !buttons[row][0].getText().equals("");
    }

    private boolean checkColumn(int col) {
        return buttons[0][col].getText().equals(buttons[1][col].getText()) &&
               buttons[1][col].getText().equals(buttons[2][col].getText()) &&
               !buttons[0][col].getText().equals("");
    }

    private boolean checkDiagonal() {
        return (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) ||
               (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals(""));
    }

    // Reset the board for a new game
    private void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        playerX = true;
    }

    // Main method to run the game
    public static void main(String[] args) {
        new TicTacToeGame();
    }
}