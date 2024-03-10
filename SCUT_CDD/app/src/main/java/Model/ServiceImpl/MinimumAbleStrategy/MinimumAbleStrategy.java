package Model.ServiceImpl.MinimumAbleStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.CardGroup;
import Model.Entity.SelectedCardGroup;
import Model.Entity.Suit;
import Model.Entity.Value;
import Model.ServiceImpl.MinimumAbleStrategy.State.StrategyBaseState;
import Model.Entity.CardGroupPattern;
import Model.ServiceImpl.MinimumAbleStrategy.State.StrategyFIndThreeWithOnePairState;
import Model.ServiceImpl.MinimumAbleStrategy.State.StrategyFindAPairState;
import Model.ServiceImpl.MinimumAbleStrategy.State.StrategyFindFourWithSingleState;
import Model.ServiceImpl.MinimumAbleStrategy.State.StrategyFindSameSuitFiveState;
import Model.ServiceImpl.MinimumAbleStrategy.State.StrategyFindSingleState;
import Model.ServiceImpl.MinimumAbleStrategy.State.StrategyFindTripleState;

public class MinimumAbleStrategy {

    private Map<Value, Vector<Card>> cardClass = new HashMap<>();

    private CardGroup currentCardGroup;

    private CardGroup currentInferenceCardGroup = new CardGroup();

    boolean isGet=false;

    private SelectedCardGroup upperShownCards =null;
    private StrategyBaseState state;


    public SelectedCardGroup getUpperShownCards() {
        return upperShownCards;
    }

    public void init(CardGroup currentCardGroup,SelectedCardGroup upper){
        this.currentCardGroup=currentCardGroup;
        for(Card c:currentCardGroup.getCards()){
            if(!cardClass.containsKey(c.getValue())) {
                cardClass.put(c.getValue(),new Vector<Card>());
                cardClass.get(c.getValue()).add(c);
            }else{
                cardClass.get(c.getValue()).add(c);
            }
        }
        upperShownCards =upper;
        minimumAbleStrategyImplementor();
    }
    public void minimumAbleStrategyImplementor(){
        switch (upperShownCards.getCardGroupPattern().getPattern()){
            case CardGroupPattern.Single:
                changeStateTo(new StrategyFindSingleState(this));
                break;
            case CardGroupPattern.APair:
                changeStateTo(new StrategyFindAPairState(this));
                break;
            case CardGroupPattern.Triple:
                changeStateTo(new StrategyFindTripleState(this));
                break;
            case CardGroupPattern.FourWithSingle:
                changeStateTo(new StrategyFindFourWithSingleState(this));
                break;
            case CardGroupPattern.ThreeWithOnePair:
                changeStateTo(new StrategyFIndThreeWithOnePairState(this));
            case CardGroupPattern.SameFaceSuitFive:
                changeStateTo(new StrategyFindSameSuitFiveState(this));
        }

    }

    public CardGroup getCurrentCardGroup() {
        return currentCardGroup;
    }

    private void changeStateTo(StrategyBaseState m_state){
        state = m_state;
    }

    public void getNext(){
        state.next();
    }
    public Map<Value, Vector<Card>> getCardClass() {
        return cardClass;
    }

    public CardGroup getCurrentInferenceCardGroup() {
        return currentInferenceCardGroup;
    }

    public StrategyBaseState getState() {
        return state;
    }

    public void updateCurrentInferenceCardGroup(Vector<Card> cards){
        currentInferenceCardGroup.setCardGroup(cards);
        isGet=true;
    }
    public String toString(){
        if(currentInferenceCardGroup!=null) {
            return currentInferenceCardGroup.toString();
        }
        else{
            return "None";
        }
    }

    public static void main(String[] args) {
        CardGroup my = new CardGroup();
        Vector<Card> myV = new Vector<>();
        myV.add(new Card(Suit.HEARTS, Value.THREE));
        myV.add(new Card(Suit.CLUBS, Value.THREE));
        myV.add(new Card(Suit.SPADES, Value.FIVE));
        myV.add(new Card(Suit.DIAMONDS, Value.THREE));
//        myV.add(new Card(Suit.SPADES, Value.KING));
        myV.add(new Card(Suit.SPADES, Value.TWO));
        myV.add(new Card(Suit.SPADES, Value.TWO));
        myV.add(new Card(Suit.SPADES, Value.FIVE));
        myV.add(new Card(Suit.SPADES, Value.TWO));
        myV.add(new Card(Suit.SPADES, Value.THREE));
        myV.add(new Card(Suit.SPADES, Value.TWO));
        my.setCardGroup(myV);
        my.cardGroupSortAsRule(1);
        System.out.println("My:"+my.toString()+"\n");

        SelectedCardGroup upperscg = new SelectedCardGroup();
        Vector<Card> upperV = new Vector<>();
        upperV.add(new Card(Suit.DIAMONDS, Value.THREE));
        upperV.add(new Card(Suit.DIAMONDS, Value.THREE));
        upperV.add(new Card(Suit.DIAMONDS, Value.THREE));
        upperV.add(new Card(Suit.DIAMONDS, Value.FOUR));
        upperV.add(new Card(Suit.DIAMONDS, Value.FOUR));

        upperscg.setSelectedCardGroup(upperV);

        MinimumAbleStrategy minimumAbleStrategy = new MinimumAbleStrategy();
        minimumAbleStrategy.init(my,upperscg);

        minimumAbleStrategy.getNext();
        CardGroup temp=minimumAbleStrategy.getCurrentInferenceCardGroup();
//        minimumAbleStrategy.getNext( );
        System.out.println("出牌："+minimumAbleStrategy.toString());
    }

    public boolean isGet() {
        return isGet;
    }

    public void setGet(boolean get) {
        isGet = get;
    }
}
