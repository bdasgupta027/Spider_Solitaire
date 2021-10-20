import java.util.*;
import java.io.*;

/**
 * Name: Bhaswati Das Gupta
 * Date: 12/11/2020
 * Period: 3
 * Time: 0.5 hours
 * Reflection: This part of the lab was a lot simpler than the other parts. There wasn't
 * much to do except for add 2 try-catch statements. This part of the lab also helped clear
 * up any confusion I had regarding try-catch statements since we were working with 
 * inputMismatchExceptions and I had to do some research before figuring out how to address it. 
 * I'm still struggling to complete the makeMove() method in the Board Class. 
 *
 */
public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 7;

    /** Number of complete decks used in this game.  A 1-suit deck, which is the
     *  default for this lab, consists of 13 cards (Ace through King).
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();

            if (command.equals("move")) {
                try{
                    String symbol = input.next();
                    int sourceStack = input.nextInt();
                    int destinationStack = input.nextInt();
                    board.makeMove(symbol, sourceStack - 1, destinationStack - 1);
                }catch(Exception i){
                    System.out.println("Invalid command1!");
                }
            }else if (command.equals("draw")) {
                board.drawCards();
            }
            else if (command.equals("clear")) {
                try{
                    int sourceStack = input.nextInt();
                    board.clear(sourceStack);
                }catch(Exception i){
                    System.out.println("Invalid command!");
                }
            }
            else if (command.equals("load")) {
                board.restoreGame();
            }
            else if (command.equals("restart")) {
                board = new Board(NUM_STACKS, NUM_DECKS);
            }
            else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }else if (command.equals("save")){
                board.saveGame();
            }
            else {
                System.out.println("Invalid command.");
            }

            board.printBoard();

            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}
