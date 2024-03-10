package Model.Entity;

import java.util.Vector;


public class DeckOfCards
{
    // Store all cards
    public Vector<Card> poke = new Vector<>(52);

    // Initial poke
    public DeckOfCards()
    {
        for(Suit suit : Suit.values())
            for(Value value : Value.values())
                poke.add(new Card(suit,value));
    }
}
