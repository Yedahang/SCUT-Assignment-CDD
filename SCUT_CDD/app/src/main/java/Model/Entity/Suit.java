package Model.Entity;

public enum Suit {
    SPADES(1), HEARTS(2), CLUBS(3), DIAMONDS(4);//分别代表黑桃，红桃，梅花，方块
    public final int suit;

    Suit(int suit) {
        this.suit = suit;
    }
    public int getValue() {
        return suit;
    }

}