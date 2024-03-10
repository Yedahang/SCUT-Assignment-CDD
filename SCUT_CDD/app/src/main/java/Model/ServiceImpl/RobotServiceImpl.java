package Model.ServiceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.CardGroup;
import Model.Entity.SelectedCardGroup;
import Model.Entity.Suit;
import Model.Entity.Value;
import Model.Service.RobotService;



/** <p>最小可出策略</p>*/
public class RobotServiceImpl implements RobotService {

    Set<Card> markedSet = new HashSet<>();
    CardGroup cardGroup =null;
    Map<Value, Vector<Card>> cardClass = new HashMap<>();
    CardGroup cg =new CardGroup();
    SelectedCardGroup robotSCG =new SelectedCardGroup();

    //思路 先把所有可能的牌型遍历确定存入表中
    Vector<CardGroup> APair = new Vector<>();
    Vector<CardGroup> Triple = new Vector<>();

    Vector<CardGroup> Straight = new Vector<>();

    Vector<CardGroup> ThreeWithOnePair = new Vector<>();

    Vector<CardGroup> FourWithSingle = new Vector<>();
    public void init(CardGroup cg){
        cardGroup =cg;
        for(Card c:cg.getCards()){
            if(!cardClass.containsKey(c.getValue())) {
                cardClass.put(c.getValue(),new Vector<Card>());
                cardClass.get(c.getValue()).add(c);
            }else{
                cardClass.get(c.getValue()).add(c);
            }
        }
    }

    private void getAllAPair(){
        for(Map.Entry<Value, Vector<Card>> entry:cardClass.entrySet()){
            Vector <Card> current=entry.getValue();
            if(current.size()>1){
                for(int i=0;i<current.size();i++){
                    for(int j=i+1;j<current.size();j++){
                        CardGroup temp = new CardGroup();
                        temp.getCards().add(current.get(i));
                        temp.getCards().add(current.get(j));
                        APair.add(temp);
                    }
                }
            }
        }
    }

    private void getAllTriple(){
        for(Map.Entry<Value, Vector<Card>> entry:cardClass.entrySet()){
            Vector <Card> current=entry.getValue();
            if(current.size()>2){
                for(int i=0;i<current.size();i++){
                    for(int j=i+1;j<current.size();j++){
                        for(int k=j+1;k<current.size();k++){
                            CardGroup temp = new CardGroup();
                            temp.getCards().add(current.get(i));
                            temp.getCards().add(current.get(j));
                            temp.getCards().add(current.get(k));
                            Triple.add(temp);
                        }
                        }
                }
            }
        }
    }

    private void getStraightWithMax(Value max){
        Vector<Value> front = new Vector<>();
        int maxValue = max.getValue()-1;//0 -》A 1->2
        if(maxValue>3){
            front.add(Value.fromOrdinal(maxValue-4));
            front.add(Value.fromOrdinal(maxValue-3));
            front.add(Value.fromOrdinal(maxValue-2));
            front.add(Value.fromOrdinal(maxValue-1));
            front.add(Value.fromOrdinal(maxValue));
        }else if(maxValue==0){
            front.add(Value.fromOrdinal(9));//10
            front.add(Value.fromOrdinal(10));//J
            front.add(Value.fromOrdinal(11));//Q
            front.add(Value.fromOrdinal(12));//K
            front.add(Value.fromOrdinal(0));//A
        }

            if(cardClass.containsKey(front.get(0))&&
                cardClass.containsKey(front.get(1))&&
                cardClass.containsKey(front.get(2))&&
                cardClass.containsKey(front.get(3))&&
                cardClass.containsKey(front.get(4))){
            Vector <Card> temp = new Vector<>();
            temp.add(cardClass.get(front.get(0)).get(0));
            temp.add(cardClass.get(front.get(1)).get(0));
            temp.add(cardClass.get(front.get(2)).get(0));
            temp.add(cardClass.get(front.get(3)).get(0));
            temp.add(cardClass.get(front.get(4)).get(0));
            CardGroup tempGroup = new CardGroup();
            tempGroup.setCardGroup(temp);
            Straight.add(tempGroup);
        }

    }

    private void getThreeWithOnePairWithMax(Value Max){
        if(cardClass.containsKey(Max)){
            Vector<Card> temp = cardClass.get(Max);
            Vector<Card> result= new Vector<>();
            if(temp.size()>2){
                result.add(temp.get(0));
                result.add(temp.get(1));
                result.add(temp.get(2));
                markedSet.add(temp.get(0));
                markedSet.add(temp.get(1));
                markedSet.add(temp.get(2));
            }
            //这里对子默认拿最小的
            if(APair.size()>0){
                for(CardGroup cg:APair){
                    if(!markedSet.contains(cg.getCards().get(0))
                    &&!markedSet.contains(cg.getCards().get(1))) {
                        result.add(cg.getCards().get(0));
                        result.add(cg.getCards().get(1));
                    }
                }

            }
            CardGroup tempGroup =new CardGroup();
            tempGroup.setCardGroup(result);
            ThreeWithOnePair.add(tempGroup);
        }
    }

    private void getFourWithSingleWithMax(Value Max){
        if(cardClass.containsKey(Max)){
            Vector<Card> temp = cardClass.get(Max);
            Vector<Card> result= new Vector<>();
            if(temp.size()>3){
                result.add(temp.get(0));
                result.add(temp.get(1));
                result.add(temp.get(2));
                result.add(temp.get(3));
                markedSet.add(temp.get(0));
                markedSet.add(temp.get(1));
                markedSet.add(temp.get(2));
                markedSet.add(temp.get(3));
            }
            //这里单张默认拿最小的 默认先找单张的  找不到单张的就先随机选一张
            //3-K
            Value CurrentValue;
            Card theFirstSmallestCard=null;
             for(int i =3;i<16;i++){
                 if(i<14) {
                     //从3开始找 3-》K
                     CurrentValue = Value.fromOrdinal(i - 1);
                     if (cardClass.containsKey(CurrentValue)) {
                         if(theFirstSmallestCard==null
                                 &&cardClass.get(CurrentValue).size()!=0
                               &&!markedSet.contains(cardClass.get(CurrentValue).get(0))){
                             theFirstSmallestCard=cardClass.get(CurrentValue).get(0);
                         }
                         if(cardClass.get(CurrentValue).size() == 1) {
                             result.add(cardClass.get(CurrentValue).get(0));
                             markedSet.add(cardClass.get(CurrentValue).get(0));
                             CardGroup tempGroup = new CardGroup();
                             tempGroup.setCardGroup(result);
                             FourWithSingle.add(tempGroup);
                             return;
                         }
                     }
                 }else {
                     //A->2
                     CurrentValue = Value.fromOrdinal(i -1-13);
                     if (cardClass.containsKey(CurrentValue)) {
                         if(theFirstSmallestCard==null
                                 &&cardClass.get(CurrentValue).size()!=0
                                 &&!markedSet.contains(cardClass.get(CurrentValue).get(0))){
                             theFirstSmallestCard=cardClass.get(CurrentValue).get(0);
                         }
                         if(cardClass.get(CurrentValue).size() == 1) {
                             result.add(cardClass.get(CurrentValue).get(0));
                             markedSet.add(cardClass.get(CurrentValue).get(0));
                             CardGroup tempGroup = new CardGroup();
                             tempGroup.setCardGroup(result);
                             FourWithSingle.add(tempGroup);
                             return;
                         }
                     }
                 }
            }

             //没找到单张 随便选一张最小的
            //TODO:后面再优化
            result.add(theFirstSmallestCard);
             markedSet.add(theFirstSmallestCard);
            CardGroup tempGroup = new CardGroup();
            tempGroup.setCardGroup(result);
            FourWithSingle.add(tempGroup);
            return;
        }
    }

    private void getSameSuitFive(Suit suit){

    }
    private void getFrontCardValue(Value max){

    }








    /**
     * 描述:机器人对于单张牌的响应
     * @author 叶达杭
     * @param scg 添加说明
     * @return Model.Entity.CardGroup 添加说明
    */
    private CardGroup showingSingle(SelectedCardGroup scg){
        CardGroup temp = null;
        if(scg.getCards().size()==1){
            Card upper = scg.getCards().get(0);
            for(Card c :cg.getCards()){
                if(c.compareTo(upper)==1){
                    temp.getCards().add(c);
                }
            }
        }
        return temp;
    }

    /**
     * 描述:机器人响应一对
     * @author 叶达杭
     * @param scg
     * @return Model.Entity.CardGroup
    */
    private CardGroup showingAPair(SelectedCardGroup scg){
        CardGroup temp = null;
        if(scg.getCards().size()==2){
            Card upper = scg.getCards().get(0);
            for(Card c :cg.getCards()){
                if(c.compareTo(upper)==1){
                    temp.getCards().add(c);
                    break;
                }
            }
        }
        return temp;
    }

    /**
     * 描述:机器人响应三张牌型
     * @author 叶达杭
     * @param scg
     * @return Model.Entity.CardGroup
    */
    private CardGroup showingTriple(SelectedCardGroup scg){
        CardGroup temp = null;
        if(scg.getCards().size()==3){
            Card upper = scg.getCards().get(0);
            for(Card c :cg.getCards()){
                if(c.compareTo(upper)==1){
                    temp.getCards().add(c);
                    break;
                }
            }
        }
        return temp;
    }

    public String allAPairToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("All A pair: \n");
        int count =1;
        for(CardGroup cg:APair) {
            sb.append(count).append(":").append(cg.toString()).append("\n");
            count++;
         }
        sb.append("\n");
        return sb.toString();
    }

    public String allTripleToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("All Triples: \n");
        int count =1;
        for(CardGroup cg:Triple) {
            sb.append(count).append(":").append(cg.toString()).append("\n");
            count++;
        }
        sb.append("\n");
        return sb.toString();
    }

    public String allStraightToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("All Straight: \n");
        int count =1;
        for(CardGroup cg:Straight) {
            sb.append(count).append(":").append(cg.toString()).append("\n");
            count++;
        }
        sb.append("\n");
        return sb.toString();
    }

    public String allFourWithSingleToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("All Four with Single: \n");
        int count =1;
        for(CardGroup cg:FourWithSingle) {
            sb.append(count).append(":").append(cg.toString()).append("\n");
            count++;
        }
        sb.append("\n");
        return sb.toString();
    }
    public String allThreeWithOnePairToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("All Three with one Pair: \n");
        int count =1;
        for(CardGroup cg:ThreeWithOnePair) {
            sb.append(count).append(":").append(cg.toString()).append("\n");
            count++;
        }
        sb.append("\n");
        return sb.toString();
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
        myV.add(new Card(Suit.SPADES, Value.FIVE));
        myV.add(new Card(Suit.SPADES, Value.TWO));
        myV.add(new Card(Suit.SPADES, Value.THREE));
        my.setCardGroup(myV);
        my.cardGroupSortAsRule(1);

        System.out.println("My card group: \n"+my.toString());
        RobotServiceImpl robotService = new RobotServiceImpl();
        robotService.init(my);
        robotService.getAllAPair();
        robotService.getAllTriple();
//        System.out.println(robotService.allTripleToString());
//        robotService.getStraightWithMax(Value.ACE);
//        System.out.println(robotService.allStraightToString());

//        robotService.getThreeWithOnePairWithMax(Value.THREE);
//        System.out.println(robotService.allThreeWithOnePairToString());

        robotService.getFourWithSingleWithMax(Value.THREE);
        System.out.println(robotService.allFourWithSingleToString());
    }



}
