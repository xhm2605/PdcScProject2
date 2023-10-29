/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

/**
 *
 * @author daniel.legrand
 */
class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int[] from, int[] to, Piece[][] board) { //this method adds constraints to the Knight piece allowing it to move in its 'L' shaped pattern on the game board
        int dx = Math.abs(to[0] - from[0]);
        int dy = Math.abs(to[1] - from[1]);

        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
    }

    @Override
    public String toString() { //this method differetiates the White pieces from the Black pieces for easier identification, where White is Uppercase and Black is lowercase
        return isWhite() ? "N" : "n";
    }
}
