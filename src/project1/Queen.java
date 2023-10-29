/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

/**
 *
 * @author daniel.legrand
 */
class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int[] from, int[] to, Piece[][] board) { //this method adds constraints to the Queen piece allowing it to move any amount of spaces in any direction within the game board
        int dx = to[0] - from[0];
        int dy = to[1] - from[1];

        return dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy);
    }

    @Override
    public String toString() { //this method differetiates the White pieces from the Black pieces for easier identification, where White is Uppercase and Black is lowercase
        return isWhite() ? "Q" : "q";
    }
}
