/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
        this.isFaceUp = false;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Getter method to access this <code>Card</code>'s value.
     * 
     * @return this <code>Card</code>'s value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Method to check is this <code>Card</code> is facing up.
     * 
     * @return boolean stating whether or not the <code>Card</code> is facing up. 
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Method to set the <code>Card</code> so that it is facing up.
     * 
     * @param state a <code>boolean</code> with the current state of the <code>Card</code>
     * 
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     * 
     * @param other another <code>Card</code>  object 
     *  
     *  @return whether or not this Card is equal to other.
     */
    public boolean equals(Card other) {
        if (this.value == other.getValue() && this.symbol.equals(other.getSymbol())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Compares the value of the card to another card and returns the difference in value. 
     *  
     *  
     *  @return an <code>int</code> value containing the difference in values of the 2 cards
     */
    @Override
    public int compareTo(Card o){
        return this.value - o.getValue();
    }

    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of the card.
     */
    @Override
    public String toString() {
        if (!isFaceUp()){
            return "X";
        }else{
            return symbol;
        }
    }
}
