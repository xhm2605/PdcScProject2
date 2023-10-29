/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;
import java.util.Scanner;

/**
 *
 * @author daniel.legrand
 */
public class ChessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do { //This do while loop initiates the main menu and allows the user to choose from the following options while also checking that a valid respone is given
            System.out.println("Welcome to the Chess Game!");
            System.out.println("Main Menu:");
            System.out.println("1. Instructions");
            System.out.println("2. Play Game");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            while (!scanner.hasNextInt()) { //this while loop checks if the user input is valid
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print("Enter your choice: ");
                scanner.next(); // Consume invalid input
            }
            
            choice = scanner.nextInt();
            scanner.nextLine(); // this reads the users input
            
            switch (choice) {
                case 1:
                    displayInstructions(); //when option 1 is selected the displayInstructions method is run
                    break;
                case 2:
                    playGame(scanner); //when option 2 is selected the playGame method is run
                    break;
                case 3:
                    System.out.println("Goodbye for Now!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
        
        scanner.close();
    }

    private static void displayInstructions() { //this method displays the user with a list of instructions for how to play the gamne with an option to return to the main menu
        System.out.println("Instructions:");
        System.out.println("Observe the grid around the board in the game");
        System.out.println("If you would like to move a piece, look on the grid to see its location. For example a Pawn could be located at e2 (Column e Row 2)");
        System.out.println("The rules of chess would allow the Pawn to move up to 2 spaces forward to a new location of E4 (Column e Row 4)");
        System.out.println("To play the game you would need to enter the location of the piece you want to move, followed by a space and then location you want it to go to");
        System.out.println("An example of this would be 'e2 e4' which will shift the piece located at 2 to space E4 on the game board");
        System.out.println(" \nIf you would like to exit the game at anytime, simplly type 'exit'\n");
        System.out.println("Press Enter to return to the main menu.");
        new Scanner(System.in).nextLine();
    }

    private static void playGame(Scanner scanner) { //this method calls on the board class to initalise the board in order to play the game
        System.out.println("Starting the game...\n");
        Board board = new Board();
        board.initialise();

        while (true) { //this loops check to see if it is currently player whites turn as well as checking if either players King has been captured
            board.printBoard();
            System.out.println(board.whiteToMove ? "White's Turn (Uppercase)" : "Black's Turn (Lowercase)");
            System.out.print("Enter your move (e.g., 'e2 e4'): ");
            String move = scanner.nextLine();
            
            if (move.equalsIgnoreCase("exit")) {
                break;
            }
            
            try {
                if (board.makeMove(move)) {
                    board.switchTurn();
                    if (board.kingCaptured) {
                        System.out.println("Checkmate! " + (board.whiteToMove ? "Black" : "White") + " wins!");
                        break;
                    } 
                }else {
                    System.out.println("Invalid move. Try again.");
                }
            }catch (Exception e) { //this checks to make sure a valid input is entered
                System.out.println("Please enter the co-ordinate of the piece you want to move as shown in the example above");
            }
        }
    }
}
