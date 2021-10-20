import java.util.ArrayList;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * Name: Bhaswati Das Gupta
 * Date: 12/8/2020
 * Period: 3
 * Time: 4 hours
 * Reflection: I've been struggling with the strategy for the clear method. It took
 * me a while to understand that I would have to go through each stack to see which
 * one has the correct order of cards. I'm not sure what I did wrong because I'm no 
 * longer able to run the code. An out of bounds error keeps popping up in the Deck 
 * class when it was working before I started the clear() method. I plan on resubmitting
 * this portion of the project later. 
 *
 * Resubmission: 
 * Date: 12/15/2020
 * Period: 3
 * Time: 7 hours
 * Reflection: I ended up figuring out the strategy for my clear method. I also really struggled
 * with the makeMove() function as my code was really long. I purposely separated it in the way 
 * that I did so that it would be easier to pinpoint any errors. However, this ended up making
 * my code even more complicated and I always removed cards from the starting deck but never
 * added them to my destination deck even though I had clearly called the add() method. The
 * debugging process for this error took me about 4 hours as I printed every value correctly
 * but the card still wasn't being added to the deck. After calling a println statement on 
 * practically every line of code, I figured out where my error was. Overall, this lab really
 * gave me a feel for larger CS projects and helped my time management skills. 
 */
public class Board
{   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    ArrayList<Deck> arrStacks;
    ArrayList<Deck> completed;
    ArrayList<Deck> drawPile;
    int completedStacks = 0;
    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  Here are examples:
     *  
     *  # numDecks     #cards in overall Deck
     *      1          13 (all same suit)
     *      2          26 (all same suit)
     *      3          39 (all same suit)
     *      4          52 (all same suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.
     */    
    public Board(int numStacks, int numDecks) {
        drawPile = new ArrayList<Deck>();
        completed = new ArrayList<Deck>();
        arrStacks = new ArrayList<Deck>();

        Deck deck = new Deck();
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int numTotalCards = numDecks * 13;
        for (int j = 0; j < numDecks; j++){
            for (int i = 0; i < 13; i++){
                deck.add(new Card(symbols[i], values[i]));
            }
        }

        deck.shuffle();

        Deck drawDeck = new Deck();
        for (int i = 0 ; i < (numTotalCards / 2); i ++){
            drawDeck.add(deck.draw());

        }
        drawPile.add(drawDeck);

        for (int i = 0; i < numStacks; i++){
            arrStacks.add(new Deck());
        }
        
        
        while(deck.getNumCards() > 0){
            for (int j = 0; j < numStacks; j++){
                if ((deck.getNumCards()) > 0){   
                    arrStacks.get(j).add(deck.draw());
                }
            }
        }
        

    }

    /**
     *  Moves a run of cards from src to dest (if possible) and turns the
     *  next card face up (if one is available).  Change the parameter list
     *  to match your implementation of Card if you need to.
     */
    public void makeMove(String symbol, int src, int dest){
        Deck initialD = arrStacks.get(src);
        int index = 0; 
        boolean found = false;
        for (int i = initialD.getNumCards() - 1; i >= 0; i--){
            if (initialD.getCardAt(i).getSymbol().equals(symbol)){
                index = i;

                found = true;
                break;
            }
        }

        Deck finalD = arrStacks.get(dest);
        Deck testD = new Deck();
        int initialNum = initialD.getNumCards();
        int testNum = 0;

        if (found == true){
            for (int i = index; i < initialNum; i ++){
                testD.add(initialD.draw(index));
            }
            testNum = testD.getNumCards();

            boolean increasing = true;
            for (int i = testD.getNumCards() - 1; i >= 1; i--){
                if (testD.getCardAt(i).getValue() + 1 != testD.getCardAt(i - 1).getValue()){
                    increasing = false;
                }
            }
            boolean faceUp = true;
            for (int i = 0; i < testD.getNumCards(); i++){
                if (!testD.getCardAt(i).isFaceUp()){
                    faceUp = false;
                    break;
                }
            }
            boolean validRun = true;
            if (finalD.getCardAt(finalD.getNumCards() - 1).getValue() != testD.getCardAt(0).getValue() + 1){
                validRun = false;
            }

            if (increasing == true){
                if (faceUp == true){
                    if (validRun == true){
                        if (arrStacks.get(dest).getNumCards() == 0){
                            for (int i = 0; i < testNum; i++){
                                arrStacks.get(dest).add(testD.draw());
                            }
                        }else{
                            for (int i = 0; i < testNum; i ++){

                                arrStacks.get(dest).add(testD.draw());
                            }
                        }
                    }else{
                        System.out.println("Invalid run!");
                        for (int i = 0; i < testNum; i ++){
                            arrStacks.get(src).add(testD.draw());
                        }
                    }

                }else{
                    System.out.println("Card not found!");
                    for (int i = 0; i < testNum; i ++){
                        arrStacks.get(src).add(testD.draw());
                    }
                }
            }else{
                System.out.println("Illegal run!");
                for (int i = 0; i < testNum; i ++){
                    arrStacks.get(src).add(testD.draw());
                }
            }
        }else{
            System.out.println("Card not found!");
            for (int i = 0; i < testNum; i ++){
                arrStacks.get(src).add(testD.draw());
            }
        }

    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        boolean emptyStack = false;
        for (int i = 0; i < arrStacks.size(); i ++){
            if (arrStacks.get(i).getNumCards() == 0){
                emptyStack = true;
                break;
            }
        }
        if (emptyStack == false){
            for (int i = 0; i < arrStacks.size(); i ++){
                if (drawPile.get(0).getNumCards() > 0){
                    arrStacks.get(i).add(drawPile.get(0).draw());
                }else{
                    break;
                }

            }
        }
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        if(arrStacks.size() + drawPile.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        Deck deck = arrStacks.get(sourceStack); // getting the deck 
        String deckstr = "";
        int counter = 0;
        int start = 0;

        // getting all the cards that are face up in the deck
        for (int l = 0; l < deck.getNumCards(); l++){
            if (deck.getCardAt(l).isFaceUp()){
                if (deck.getCardAt(l).getSymbol().equals("K")){
                    start = l;

                }
                counter ++;
            }
        }

        if (counter != 13){
            System.out.println("Invalid move!");
        }else{

            for (int j = 0; j < deck.getNumCards(); j++){
                if (deck.getCardAt(j).isFaceUp()){

                    deckstr += deck.getCardAt(j).toString();
                }
            }

            if (deckstr.equals("KQJ1098765432A")){
                completed.add(new Deck());
                for (int k = start; k < start + 14; k++){
                    completed.get(completedStacks).add(deck.draw(start));

                }
                completedStacks ++;
            }else{
                System.out.println("Sourcestack does not have a complete run!");

            }
        }

    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        System.out.println("**DRAW PILE**");
        for (int i = 0; i < drawPile.size(); i ++){
            System.out.print("[");
            for (int j = 0; j < drawPile.get(i).getNumCards(); j ++){
                if (j == drawPile.get(i).getNumCards() - 1 ){
                    System.out.println(drawPile.get(i).getCardAt(j) + "]");
                }else{
                    System.out.print(drawPile.get(i).getCardAt(j)+ ", ");
                }
            }
        } 

        for (int i = 0; i < arrStacks.size(); i ++){
            int deckSize = arrStacks.get(i).getNumCards();
            if(deckSize > 0){
                arrStacks.get(i).getCardAt(deckSize - 1).setFaceUp(true);
            }

        }

        System.out.println();
        System.out.println("**STACKS**");
        for (int i = 0; i < arrStacks.size(); i ++){
            System.out.print((i + 1) + ": ");
            if (arrStacks.get(i).getNumCards() > 0){
                System.out.print("[");
                for (int j = 0; j < arrStacks.get(i).getNumCards(); j ++){
                    if (j == arrStacks.get(i).getNumCards() - 1 ){
                        System.out.println(arrStacks.get(i).getCardAt(j) + "]");
                    }else{
                        System.out.print(arrStacks.get(i).getCardAt(j)+ ", ");
                    }
                }
            }else{
                System.out.print("\n");

            }

        }
        System.out.println();
        System.out.println("**COMPLETED STACKS**");
        for (int i = 0; i < completed.size(); i ++){
            System.out.print((i + 1) + ": ");
            System.out.print("[");
            for (int j = 0; j < 13; j ++){
                if (j == completed.get(i).getNumCards() - 2 ){
                    System.out.println(completed.get(i).getCardAt(j) + "]");
                }else{
                    System.out.print(completed.get(i).getCardAt(j)+ ", ");
                }
            }
        }
    }

    public void saveGame(){
        // Create a JFileChooser that points to the current directory
        JFileChooser chooser = new JFileChooser(".");
        // Ask the JFileChooser for the File the user typed in or selected
        chooser.showSaveDialog(null);
        File file = chooser.getSelectedFile();
        // Create a FileWriter that can write to the selected File
        try{
            FileWriter game = new FileWriter(file);
            String str = drawPile.get(0).getInfo() + "\n";
            str += arrStacks.size() + "\n";
            for (int i = 0; i < arrStacks.size(); i ++){
                String deck = arrStacks.get(i).getInfo();
                str += deck + "\n";
            } 
            str += completed.size() + "\n";
            for (int i = 0; i < completed.size(); i++){
                String deck = completed.get(i).getInfo();
                str += deck + "\n";
            }
            game.write(str, 0, str.length());
            game.close();
            System.out.println(str);
        }catch(Exception i){
            System.out.println("Invalid command!");
        }

    }

    public void restoreGame(){
        // Create a JFileChooser that points to the current directory
        JFileChooser chooser = new JFileChooser(".");
        // Ask the JFileChooser for the File the user typed in or selected
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        arrStacks.clear();
        drawPile.clear();
        completed.clear();
        try{
            Scanner in = new Scanner(file); 
            String draw = in.nextLine();
            drawPile.add(new Deck(draw));
            int numArr = in.nextInt(); 
            for (int i = 1; i <= numArr; i++){
                String deck = in.nextLine();
                arrStacks.add(new Deck(deck));
            }

            if (completedStacks > 0){
                int comp = in.nextInt();
                for (int i = 1; i <= comp; i++){
                    String complete = in.nextLine();
                    completed.add(new Deck(complete));
                }
            }
        }catch(IOException i){
            System.out.println("Error: " + i.getMessage());
        }

    }
}