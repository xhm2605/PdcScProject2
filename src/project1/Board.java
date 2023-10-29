/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

/**
 *
 * @author daniel.legrand
 */
public class Board {
    private Piece[][] board;
    public boolean whiteToMove;
    public boolean kingCaptured;

    public Board() {
        board = new Piece[8][8];
        whiteToMove = true;
        kingCaptured = false;
    }

    public void initialise() { //this fucntion will initialise the board to the standard starting format with White and Black pieces
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(true);
        }

        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
        }
    }

    public void printBoard() { //Generates and prints the game board with location markers
        System.out.println("  a b c d e f g h");
        System.out.println("  - - - - - - - -");
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + "|");
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(piece.toString() + " ");
                }
            }
            System.out.println("\b|" + (8 - i));
        }
        System.out.println("  - - - - - - - -");
        System.out.println("  a b c d e f g h");
    }

    public boolean makeMove(String move) { //this method is responsible for all the movement that happens on the game board
        int[] from = coordinatesToIndices(move.substring(0, 2));
        int[] to = coordinatesToIndices(move.substring(3, 5));

        if (from == null || to == null) { //checks for valid input
            return false;
        }

        Piece fromPiece = board[from[0]][from[1]]; //check to make sure piece is selected and is the correct colour for player playing
        if (fromPiece == null || fromPiece.isWhite() != whiteToMove) { 
            return false;
        }

        if (!fromPiece.isValidMove(from, to, board)) { //checks the piece maintains its designated contraints
            return false;
        }

        if (board[to[0]][to[1]] != null && board[to[0]][to[1]].isWhite() != whiteToMove) { //check to see if opponents piece is taken, and if king is captured games ends
            if (board[to[0]][to[1]] instanceof King) {
                kingCaptured = true;
            }
            System.out.println("Captured " + board[to[0]][to[1]].toString());
        }

        board[to[0]][to[1]] = fromPiece; //moving the piece to now location and delete piece at old location
        board[from[0]][from[1]] = null;

        return true;
    }

    public void switchTurn() { //switchs turn
        whiteToMove = !whiteToMove;
    }

    private int[] coordinatesToIndices(String coordinates) { //converts user string into valid array indices so pieces can move
        if (coordinates.length() != 2) {
            return null;
        }

        char file = Character.toLowerCase(coordinates.charAt(0));
        char rank = coordinates.charAt(1);

        if (file < 'a' || file > 'h' || rank < '1' || rank > '8') {
            return null;
        }

        int[] indices = new int[2];
        indices[0] = 7 - (rank - '1'); // Convert rank to row index
        indices[1] = file - 'a'; // Convert file to column index
        return indices;
    }
}
