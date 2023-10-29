/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

/**
 *
 * @author daniel.legrand
 */
abstract class Piece { //this class differentiates the the colour pieces on the chess board by checking if it is player White's turn
    private boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean isValidMove(int[] from, int[] to, Piece[][] board);

    @Override
    public abstract String toString();
}
