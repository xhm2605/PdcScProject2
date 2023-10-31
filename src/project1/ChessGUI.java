/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

/**
 *
 * @author daniel.legrand
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessGUI {
    //the following 11 lines import .png files of corresponding chess pieces from the icons folder at the root of the project file
    final private ImageIcon whitePawnIcon = new ImageIcon("icons/white_pawn.png");
    final private ImageIcon blackPawnIcon = new ImageIcon("icons/black_pawn.png");
    final private ImageIcon whiteRookIcon = new ImageIcon("icons/white_rook.png");
    final private ImageIcon blackRookIcon = new ImageIcon("icons/black_rook.png");
    final private ImageIcon whiteKnightIcon = new ImageIcon("icons/white_knight.png");
    final private ImageIcon blackKnightIcon = new ImageIcon("icons/black_knight.png");
    final private ImageIcon whiteBishopIcon = new ImageIcon("icons/white_bishop.png");
    final private ImageIcon blackBishopIcon = new ImageIcon("icons/black_bishop.png");
    final private ImageIcon whiteQueenIcon = new ImageIcon("icons/white_queen.png");
    final private ImageIcon blackQueenIcon = new ImageIcon("icons/black_queen.png");
    final private ImageIcon whiteKingIcon = new ImageIcon("icons/white_king.png");
    final private ImageIcon blackKingIcon = new ImageIcon("icons/black_king.png");

    final private JFrame frame; //creates java window for GUI
    final private JMenuBar menuBar; //creates menu bar at top left of window
    final private JMenu menu; //creates menu
    final private JMenuItem instructionsMenuItem; //creates item on the menu
    final private JMenuItem playGameMenuItem;
    final private JMenuItem creditsMenuItem;
    final private JMenuItem exitMenuItem;
    final private JButton[][] chessBoardButtons; //creates gameboard buttons
    private Board gameBoard; //instance of the class baord
    private JButton selectedButton; //active clicked button
    private int[] selectedPosition; //active postion of selected button
    final private JLabel turnLabel; //player turn label
    private int moveCount = 0; //sets move count to 0
    private JButton[][] buttons = new JButton[8][8]; //creates 8x8 grid of buttons for the gameboard
    
    public ChessGUI() {
        frame = new JFrame("Chess Game"); //Titles and creates the Jframe window
        frame.setSize(700, 700); //sets size of Jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes instance of program when the close button is clicked
        frame.setLayout(new BorderLayout());
        gameBoard = new Board(); //creates new board instance
        gameBoard.initialise(); // initialises the gameboard with pieces

        // Menu setup
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        
        //Instuctions Button Functionality
        instructionsMenuItem = new JMenuItem("Instructions");
        instructionsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });
        menu.add(instructionsMenuItem);
        //Restart Button Functionality
        playGameMenuItem = new JMenuItem("Restart Game");
        playGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBoard = new Board(); //resets gameboard
                selectedButton = null;
                gameBoard.initialise();
                moveCount = 0;

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        buttons[i][j].setEnabled(true);
                    }
                }
                updateBoardDisplay();
                updateTurnDisplay();
            }
        });
        menu.add(playGameMenuItem);
        
        //Credits Button Functionality
        creditsMenuItem = new JMenuItem("Credits");
        creditsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCredits();
            }
        });
        menu.add(creditsMenuItem);
        
        //Exit Button Functionality
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exitMenuItem);

        frame.setJMenuBar(menuBar);

        // Chessboard setup by setting the buttons in a gameboard 8x8 grid
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        chessBoardButtons = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick((JButton) e.getSource());
                    }
                });
                
                boardPanel.add(buttons[i][j]);
                chessBoardButtons[i][j] = buttons[i][j];
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBorderPainted(false);  // This removes the default button border, making it look more like a square on a board

                //Creates checkerboard pattern on the gameboard
                if ((i + j) % 2 == 0) {
                    buttons[i][j].setBackground(Color.WHITE);
                } else {
                    buttons[i][j].setBackground(Color.GRAY);
                }
            }
        }
        
        // Turn display setup
        turnLabel = new JLabel("");
        updateTurnDisplay();
        frame.add(turnLabel, BorderLayout.NORTH);
        //Board setup
        frame.add(boardPanel, BorderLayout.CENTER); 
        updateBoardDisplay();
        //show the Jframe window
        frame.setVisible(true);
    }

    private void showInstructions() { //Displays the Instuctions to play the game when the Instructions button is clicked from the menu
        String instructionsText = 
            "Welcome to the Chess Game!\n\n" +
            "To play the game, click on the piece you want to move, then click on the target square.\n" +
            "If the move is valid, the piece will move to the target square.\n" +
            "If the move is invalid, a message will inform you.\n" +
            "The game will notify you of check, checkmate, and captured pieces.\n" +
            "If you would like to reset the game at anytime, simplly click the menu button in the top left followed by Reset Game\n" +
            "Enjoy your game!";
            
        JOptionPane.showMessageDialog(frame, instructionsText, "Instructions", JOptionPane.INFORMATION_MESSAGE); //Creates new frame window to display Instructions
    }
    
    private void showCredits() { //Displays the Credits when the Credits button is clicked from the menu
        String creditsText = 
            "The Chess Piece icons were created by cburnett and licensed under the\nCreative Commons Attribution-ShareAlike 3.0 Unported license.\n" +
            "The icons can be found here: \n" +
            "https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent";
            
        JOptionPane.showMessageDialog(frame, creditsText, "Credits", JOptionPane.INFORMATION_MESSAGE); //Creates new frame to show Credits
    }

    private void handleButtonClick(JButton button) {
    int[] position = findButtonPosition(button); //Loops through all buttons to find a position in the array
    Piece selectedPiece = gameBoard.board[position[0]][position[1]];

    if (selectedButton == null) {
        // First click: select the piece
        if (selectedPiece != null && ((moveCount % 2 == 0 && selectedPiece.isWhite()) || (moveCount % 2 == 1 && !selectedPiece.isWhite()))) { //Checks if the correct players piece is selected
            selectedButton = button;
            selectedPosition = position;
        }
    } else {
        // Second click: attempt to move the piece
        String moveString = positionToString(selectedPosition) + " " + positionToString(position); //builds movement string
        Piece destinationPieceBeforeMove = gameBoard.board[position[0]][position[1]]; // Check the piece at destination before making the move
        if (gameBoard.makeMove(moveString)) {
            moveCount++;  // Increase the move count after a valid move
            updateBoardDisplay(); //Update the board display
            updateTurnDisplay(); // Update the turn label

            // Notify when a piece is captured
            Piece destinationPieceAfterMove = gameBoard.board[position[0]][position[1]];
            if (destinationPieceBeforeMove != null && destinationPieceBeforeMove != destinationPieceAfterMove) {
                JOptionPane.showMessageDialog(frame, "A " + destinationPieceBeforeMove + " was captured!"); //Creates new frame to show piece has been captured
            }

            // Check if the game is over due to king capture
            if (gameBoard.kingCaptured) {
                // Determine the winner based on the last move made
                String winner = (moveCount % 2 == 1) ? "Player White" : "Player Black";
                JOptionPane.showMessageDialog(frame, winner + " wins! The game is over."); //Creates new frame to show that a player has won
                JOptionPane.showMessageDialog(frame,"Please Select Menu followed by Restart Game to Play Again.");
                
                for (int i = 0; i < 8; i++) { //this prevents the board from being played after the game has been completed
                    for (int j = 0; j < 8; j++) {
                        buttons[i][j].setEnabled(false);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid move!"); //Creates new frame to show that a invalid move has been made
        }
        selectedButton.setBackground((selectedPosition[0] + selectedPosition[1]) % 2 == 0 ? Color.WHITE : Color.GRAY); //updates the background colour of the buttons
        selectedButton = null;
        selectedPosition = null;
    }
}


    private void updateTurnDisplay() { //Updates Play Turn Display
        if (gameBoard.whiteToMove) {
            turnLabel.setText("Player White's Turn");
        } else {
            turnLabel.setText("Player Black's Turn");
        }
    }

    private void updateBoardDisplay() { //Loops through array and assigns correct icon to correct button
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = gameBoard.board[i][j];  
                if (piece != null) {
                    if (piece instanceof Pawn) {
                        chessBoardButtons[i][j].setIcon(piece.isWhite() ? whitePawnIcon : blackPawnIcon);
                    } else if (piece instanceof Rook) {
                        chessBoardButtons[i][j].setIcon(piece.isWhite() ? whiteRookIcon : blackRookIcon);
                    } else if (piece instanceof Knight) {
                        chessBoardButtons[i][j].setIcon(piece.isWhite() ? whiteKnightIcon : blackKnightIcon);
                    } else if (piece instanceof Bishop) {
                        chessBoardButtons[i][j].setIcon(piece.isWhite() ? whiteBishopIcon : blackBishopIcon);
                    } else if (piece instanceof Queen) {
                        chessBoardButtons[i][j].setIcon(piece.isWhite() ? whiteQueenIcon : blackQueenIcon);
                    } else if (piece instanceof King) {
                        chessBoardButtons[i][j].setIcon(piece.isWhite() ? whiteKingIcon : blackKingIcon); 
                    }
                } else {
                    chessBoardButtons[i][j].setIcon(null);
                }
            }
        }
    }

    private int[] findButtonPosition(JButton button) { //When button is selected it finds position in the array
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoardButtons[i][j] == button) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private String positionToString(int[] position) { //Converts position into string
        char file = (char) ('a' + position[1]);
        char rank = (char) ('8' - position[0]);
        return "" + file + rank;
    }

    public static void main(String[] args) { //Runs instance of the game
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChessGUI GUI = new ChessGUI();
            }
        });
    }
}