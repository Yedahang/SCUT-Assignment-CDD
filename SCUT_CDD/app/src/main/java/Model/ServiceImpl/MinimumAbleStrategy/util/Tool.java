package Model.ServiceImpl.MinimumAbleStrategy.util;

import java.util.Vector;

import Model.Entity.Card;

public class Tool {
    public static void cardVectorSort(Vector<Card> cards){
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).getNumber() > cards.get(j).getNumber()) {
                    Card temp = cards.get(j);
                    cards.set(j, cards.get(i));
                    cards.set(i, temp);
                }
            }
        }
    }


}
