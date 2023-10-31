/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package project1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel.legrand
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialise method, of class Board.
     */
    @Test
    public void testInitialise() {
        System.out.println("initialise");
        Board instance = new Board();
        instance.initialise();
        // Check if the board has the correct pieces in the correct positions for white
        assertTrue(instance.board[7][0] instanceof Rook && instance.board[7][0].isWhite());
        assertTrue(instance.board[7][1] instanceof Knight && instance.board[7][1].isWhite());
        assertTrue(instance.board[7][7] instanceof Rook && instance.board[7][7].isWhite());
        for (int i = 0; i < 8; i++) {
            assertTrue(instance.board[6][i] instanceof Pawn && instance.board[6][i].isWhite());
        }

        // Check if the board has the correct pieces in the correct positions for black
        assertTrue(instance.board[0][0] instanceof Rook && !instance.board[0][0].isWhite());
        assertTrue(instance.board[0][1] instanceof Knight && !instance.board[0][1].isWhite());
        assertTrue(instance.board[0][7] instanceof Rook && !instance.board[0][7].isWhite());
        for (int i = 0; i < 8; i++) {
            assertTrue(instance.board[1][i] instanceof Pawn && !instance.board[1][i].isWhite());
        }
    }

    /**
     * Test of printBoard method, of class Board.
     */
    @Test
    public void testPrintBoard() {
        System.out.println("printBoard");
        Board instance = new Board();
        instance.initialise();
        try {
            instance.printBoard(); // Simply call the method and ensure no exceptions are thrown
        } catch (Exception e) {
            fail("printBoard method threw an exception.");
        }
    }


    /**
     * Test of makeMove method, of class Board.
     */
    @Test
    public void testMakeMove() {
        System.out.println("makeMove");
        Board instance = new Board();
        instance.initialise();

        // Test a valid move: Moving white pawn forward
        assertTrue(instance.makeMove("e2 e3"));

        // Test an invalid move: Moving white pawn backwards
        assertFalse(instance.makeMove("e3 e2"));

        // Test capturing a piece: Black pawn capturing white pawn
        instance.makeMove("e7-e5");
        assertTrue(instance.makeMove("d2 d4"));
        assertTrue(instance.makeMove("e5 d4"));

        // Test moving opponent's piece during the wrong turn
        assertFalse(instance.makeMove("e8 e7"));
    }


    /**
     * Test of switchTurn method, of class Board.
     */
    @Test
    public void testSwitchTurn() {
        System.out.println("switchTurn");
        Board instance = new Board();
        instance.initialise();

        // Initially, it's white's turn
        assertTrue(instance.whiteToMove);

        // Switch turn
        instance.switchTurn();
        assertFalse(instance.whiteToMove); // Now it should be black's turn

        // Switch turn again
        instance.switchTurn();
        assertTrue(instance.whiteToMove); // It should be white's turn again
    }
    
    /**
     * Test of testCoordinatesToIndices method, of class Board.
     */
    @Test
    public void testCoordinatesToIndices() {
        System.out.println("coordinatesToIndices");
        Board instance = new Board();

        // Test valid inputs
        assertArrayEquals(new int[]{7, 4}, instance.coordinatesToIndices("e1"));
        assertArrayEquals(new int[]{0, 3}, instance.coordinatesToIndices("d8"));

        // Test invalid inputs
        assertNull(instance.coordinatesToIndices("i1"));
        assertNull(instance.coordinatesToIndices("a9"));
        assertNull(instance.coordinatesToIndices("e0"));
        assertNull(instance.coordinatesToIndices("k5"));
    }


    
}
