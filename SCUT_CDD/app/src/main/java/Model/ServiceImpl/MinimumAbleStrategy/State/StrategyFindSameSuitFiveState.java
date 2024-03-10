package Model.ServiceImpl.MinimumAbleStrategy.State;


import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.CardGroup;
import Model.ServiceImpl.MinimumAbleStrategy.MinimumAbleStrategy;

/** <p>同花五</p>*/
public class StrategyFindSameSuitFiveState implements StrategyBaseState{

    private MinimumAbleStrategy minimumAbleStrategy;
    private Vector<Vector<Card>> AsSuitClass = new Vector<>();//0-方块 1-梅花 2-红桃 3-黑桃
    private Card maxCardOfUpper =null;
    private int suit_index;//0-方块 1-梅花 2-红桃 3-黑桃

    private Vector<Card> sameSuitFive =null;

    public StrategyFindSameSuitFiveState(MinimumAbleStrategy minimumAbleStrategy) {
        this.minimumAbleStrategy = minimumAbleStrategy;
        AsSuitClass.add(new Vector<Card>());
        AsSuitClass.add(new Vector<Card>());
        AsSuitClass.add(new Vector<Card>());
        AsSuitClass.add(new Vector<Card>());
        setSameSuit();

        switch (minimumAbleStrategy.getUpperShownCards().getCards().get(0).getSuit()){
            case DIAMONDS:
                suit_index=0;break;
            case CLUBS:
                suit_index=1;break;
            case HEARTS:
                suit_index=2;break;
            case SPADES:
                suit_index=3;break;
        }

        minimumAbleStrategy.getUpperShownCards().cardGroupSortAsRule(-1);//降序排列
        maxCardOfUpper=minimumAbleStrategy.getUpperShownCards().getCards().get(0);


    }


    @Override
    public void next() {
         boolean isFound =false;
         sameSuitFive=null;
         while (!isFound){
             if(AsSuitClass.get(suit_index).size()>4){
                 CardGroup cg =new CardGroup();
                 cg.setCardGroup(AsSuitClass.get(suit_index));
                 cg.cardGroupSortAsRule(1);//升序排列
                 Vector<Card> temp = cg.getCards();
                 for(int i=4;i<temp.size();i++){
                     if(temp.get(i).compareTo(maxCardOfUpper)==1){
                         sameSuitFive = new Vector<>();
                         sameSuitFive.add(temp.get(0));
                         sameSuitFive.add(temp.get(1));
                         sameSuitFive.add(temp.get(2));
                         sameSuitFive.add(temp.get(3));
                         sameSuitFive.add(temp.get(i));
                         isFound=true;
                     }
                 }
             }
             if(suit_index ==3){
                 break;
             }
             suit_index =suit_index%4+1;
         }
         minimumAbleStrategy.updateCurrentInferenceCardGroup(sameSuitFive);
    }

    private void setSameSuit(){
        for(Card c:minimumAbleStrategy.getCurrentCardGroup().getCards()){
            switch (c.getSuit()){
                case DIAMONDS:
                    AsSuitClass.get(0).add(c);break;
                case CLUBS:
                    AsSuitClass.get(1).add(c);break;
                case HEARTS:
                    AsSuitClass.get(2).add(c);break;
                case SPADES:
                    AsSuitClass.get(3).add(c);break;
            }
        }

    }
}
