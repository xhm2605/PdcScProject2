/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

/**
 *
 * @author daniel.legrand
 */
class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int[] from, int[] to, Piece[][] board) { //this method adds constraints to the Pawen piece allowing it to only move forward 1 space (or 2 spaces if starting from the beginning of the game) or 1 space diagonally when capturing in opposing players piece.
        int dx = to[0] - from[0];
        int dy = to[1] - from[1];

        int direction = isWhite() ? -1 : 1;
        
        if (dx == direction && dy == 0) {
            return board[to[0]][to[1]] == null;
        }

        if (dx == 2 * direction && dy == 0 && from[0] == (isWhite() ? 6 : 1)) {
            int clearPath = from[0] + direction;
            return board[to[0]][to[1]] == null && board[clearPath][to[1]] == null;
        }

        if (dx == direction && Math.abs(dy) == 1) {
            Piece targetPiece = board[to[0]][to[1]];
            return targetPiece != null && targetPiece.isWhite() != isWhite();
        }

        return false;
    }

    @Override
    public String toString() { //this method differetiates the White pieces from the Black pieces for easier identification, where White is Uppercase and Black is lowercase
        return isWhite() ? "P" : "p";
    }
}
