import java.util.ArrayList;

/**
 * Name: Bhaswati Das Gupta
 * Date: 12/2/2020
 * Period: 3
 * Time: 4 hours
 * Reflection: I think this lab is a bit harder since the instructions aren't specific and are open to interpretation. I am still stuck on the DeckTester 
 * class because I'm not sure how to use ArrayLists in the DeckTester class and have it transfer over to the Deck class. Whenever I try printing something,or
 * running the main method, nothing happens. I am planning on resubmitting this lab later. 
 * 
 * Reflection Part 2: After the review we did in class, this project is making much more sense to me. I wasn't sure what the exact purpose of each class was 
 * and what methods each class should include. I included all the methods that the class brainstormed together. I also made my DeckTester and CardTester class
 * more extensive so that it tests each method at least once to see if it is fully working. 
 *
 */
public class DeckTester{
    public static void main(String[] args){
        //Adding cards to deck
        Deck deck = new Deck();
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for (int i = 0; i < symbols.length; i++){
            deck.add(new Card(symbols[i], values[i]));
        }
        deck.faceUpAll();
        System.out.println(deck);
        
//        //faceUpAll and faceDownAll testing
//        deck.faceUpAll();
//        deck.toString();
//        System.out.println(deck);
//        deck.faceDownAll();
//        System.out.println(deck);
//        deck.faceUpAll();
        
        //shuffle testing
        deck.shuffle();
        System.out.println(deck);
        
//        //draw testing 
//        System.out.println(deck.draw());
//        System.out.println(deck.draw(0));
//        
//        //getNumCards testing
//        System.out.println(deck.getNumCards());
//        
//        //getCardAt Testing
//        System.out.println(deck.getCardAt(1));
        
        //sort testing
        deck.sort();
        System.out.println(deck);
        
//        //getInfo testing
//        for (int i = 0; i < 3; i++){
//            deck.getCardAt(i).setFaceUp(false);
//        }
//        System.out.println(deck.getInfo());
//        Deck deck2 = new Deck(deck.getInfo());
//        System.out.println(deck2.getNumCards());
//        deck2.toString();
//        System.out.println(deck2);
        
        
    }
}