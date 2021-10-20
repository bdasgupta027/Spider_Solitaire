public class CardTester{
    public static void main(String[] args){
        //Initialize Cards 
        Card card1 = new Card("Ace", 1);
        Card card2 = new Card("5", 5);
        Card card3 = new Card("5", 5);
        Card card4 = new Card("10", 10);
        
        System.out.println(card1);
        System.out.println(card2);
        System.out.println(card3);
        System.out.println(card4);
        
        //getSymbol Testing
        System.out.println(card1.getSymbol());
        System.out.println(card4.getSymbol());
        
        //getValue Testing
        System.out.println(card1.getValue());
        System.out.println(card4.getValue());
        
        //setFaceUp and isFaceUp Testing
        card1.setFaceUp(true);
        System.out.println(card1.isFaceUp());
        System.out.println(card2.isFaceUp());
        
        //equals Testing
        System.out.println(card1.equals(card4));
        System.out.println(card2.equals(card3));
        System.out.println(card1.equals(card1));
        
        //compareTo Testing
        System.out.println(card1.compareTo(card4));
        System.out.println(card2.compareTo(card1));
        
        //toString Testing
        System.out.println(card1.toString());
        System.out.println(card3.toString());
        
    }
    
}