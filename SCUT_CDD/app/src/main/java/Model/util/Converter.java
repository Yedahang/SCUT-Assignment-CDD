package Model.util;

import Model.Entity.Card;
import Model.Entity.CardGroup;

public class Converter {
    public static String cardToString(Card c){
        return Integer.toString(c.getNumber())+"_"+Integer.toString(c.getSuit().getValue());
    }

    public static String cardGroupToString(CardGroup cg){
        StringBuilder sb = new StringBuilder();
        for(Card c:cg.getCards()){
            sb.append(cardToString(c)).append(";");
        }
        return sb.toString();
    }

    public static CardGroup cardStringToCards(String input){
        if(input==null) return new CardGroup();
        String[] parts = input.split(";");
        CardGroup cardGroup = new CardGroup();
        for(String part:parts){
            String[] card = part.split("_"); //点数_花色
            cardGroup.addCard(new Card(Integer.parseInt(card[1]),Integer.parseInt(card[0])));
        }
        return cardGroup;
    }
}
