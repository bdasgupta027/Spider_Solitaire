import java.util.ArrayList;
import java.util.Random;

/**
 * Name: Bhaswati Das Gupta
 * Date: 12/17/2020
 * Period: 3
 * Time: 2.5 hours
 * Activity 7 Reflection: I'm a bit confused with this part of the project because it's
 * sort of a new concept. I'm not sure if what I'm doing is right. So far, I have the code
 * for saving the game but I haven't done the restore method yet. I still can't figure out
 * if what I did for the save method is correct.
 * 
 * ACTIVITY 7 RESUBMISSION
 * Name: Bhaswati Das Gupta
 * Date: 12/17/2020
 * Period: 3
 * Time: 7.5 hours
 * Activity 7 Reflection: I completed activity 7 of the lab and it is fully functional. 
 * I found a couple of bugs in my program and I've tried fixing it but it keeps making the
 * code even worse. I'm unable to pinpoint where the problem is stemming from. 
 */
public class Deck{
    ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<Card>();
    }
    
    public Deck(String str){
        deck = new ArrayList<Card>();;
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == 'X'){
                String sym = Character.toString(str.charAt(i + 1));
                String strvalue= "";
                if (sym.equals("K") || sym.equals("Q") || sym.equals("J") || sym.equals("T")){
                    strvalue = str.substring(i + 2, i + 4); 
                    i += 3;
                }else{
                    strvalue = Character.toString(str.charAt(i + 2));
                    i += 2;
                }
                int val = Integer.parseInt(strvalue);
                deck.add(new Card(sym, val));
                
            }else{
                String sym = Character.toString(str.charAt(i));
                String value = "";
                if (sym.equals("K") || sym.equals("Q") || sym.equals("J") || sym.equals("T")){
                    value = str.substring(i + 1, i + 3); 
                    i += 2;
                }else{
                    value = Character.toString(str.charAt(i + 1));
                    i++;
                }
                int val = Integer.parseInt(value);
                Card card = new Card(sym, val);
                card.setFaceUp(true);
                deck.add(card);
                
            }
        }
        
        
        
        
    }

    public void shuffle(){
        Random rand = new Random();
        for (int i = 0; i < deck.size(); i ++){
            int x = rand.nextInt(deck.size()-1);
            Card xCard = deck.get(x);
            Card iCard = deck.get(i);
            deck.set(i, xCard);
            deck.set(x, iCard);
        }

    }

    @Override
    public String toString(){
        String deckStr = "";
        for (int i = 0; i < deck.size(); i++){
            deckStr += deck.get(i);
        }
        return deckStr;
    }

    public void add(Card c){
        deck.add(c);
    }


    public Card draw(){
        if (deck.size() > 0){
            Card c = deck.get(0);
            deck.remove(0);
            return c;
        }else{
            return null;
        }
    }

    public Card draw(int index){
        if (deck.size() > index){
            Card c = deck.get(index);
            deck.remove(index);
            return c;
        }else{
            return null;
        }
    }

    public void faceUpAll(){
        for (int i = 0; i < deck.size(); i ++){
            deck.get(i).setFaceUp(true);
        }
    }

    public void faceDownAll(){
        for (int i = 0; i < deck.size(); i++){
            deck.get(i).setFaceUp(false);
        }
    }

    public int getNumCards(){
        return deck.size();
    }

    public Card getCardAt(int index){
        return deck.get(index);
    }

    public void sort(){
        
        
        for (int i = deck.size() - 1; i >= 1; i--) {
        	int biggest = 0;
        	for (int j = 0; j <= i; j++) {
        		if (deck.get(j).compareTo(deck.get(biggest)) > 0) {
        			biggest = j;
                    
        		}
        	}
        	Card c1 = deck.get(i);
            Card c2 = deck.get(biggest);
            deck.set(i, c2);
            deck.set(biggest, c1);
            
        }
        
        
    }

    public String getInfo(){
        String info = "";
        for (int i = 0; i < this.getNumCards(); i++){
            if (!this.getCardAt(i).isFaceUp()){
                info += "X" + this.getCardAt(i).getSymbol() + this.getCardAt(i).getValue();
            }else{
                info += this.getCardAt(i).getSymbol() + this.getCardAt(i).getValue();
            }
        }
        return info;
    }
    
    

}
