package Model.ServiceImpl.GameRule;

import java.util.Map;
import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.CardGroupPattern;
import Model.Entity.SelectedCardGroup;
import Model.Entity.Value;


//比较牌型大小
public class CardsComparator implements Model.Service.GameRule.CardsComparator {

    /**
     * 描述:对已选择的牌组进行大小比较
     * @author 叶达杭
     * @param upperShownCards 添加说明
     * @param bottomShownCards 添加说明
     * @return boolean 下家出的牌可以比得过上家-true
    */
    @Override
    public boolean SelectedCardGroupCompare(SelectedCardGroup upperShownCards,SelectedCardGroup bottomShownCards){
        int theSameSize =-1;
        if(upperShownCards.getCards().size()!=bottomShownCards.getCards().size()){
            //牌的数目不一样
            return false;
        }else {
            theSameSize=upperShownCards.getCards().size();
        }

        switch (theSameSize){
            case 1://都是一张牌 此时类型必然是Single
                return SingleCompare(upperShownCards,bottomShownCards);
            case 2://都是两张牌 必须先判断两手牌是不是都是一对 同类型才能比较
                if(upperShownCards.getCardGroupPattern().getPattern()==CardGroupPattern.APair&&
                        bottomShownCards.getCardGroupPattern().getPattern()==CardGroupPattern.APair) {
                    return APairCompare(upperShownCards, bottomShownCards);
                }else {
                    return false;
                }
            case 3://都是三张牌 必须先判断两手牌是不是都是三张 同类型才能比较
                if(upperShownCards.getCardGroupPattern().getPattern()==CardGroupPattern.Triple&&
                        bottomShownCards.getCardGroupPattern().getPattern()==CardGroupPattern.Triple) {
                    return ThreeCompare(upperShownCards, bottomShownCards);
                }else {
                    return false;
                }
            case 5://都是五张牌 五张牌的牌型有 三带一对 四带一 同花顺 杂顺 同花五
                return FiveCardsGroupCompare(upperShownCards, bottomShownCards);
            default:
                return false;
        }
    }



    //下面的都是private************************************************************************

    /**
     * 描述:单张牌之间比较大小
     * @author 叶达杭
     * @param upperShownCards 添加说明
     * @param bottomShownCards 添加说明
     * @return boolean 添加说明
    */
    private boolean SingleCompare(SelectedCardGroup upperShownCards, SelectedCardGroup bottomShownCards){
        Vector<Card> upperShownCardsV=upperShownCards.getCards();
        Vector<Card> bottomShownCardsV=bottomShownCards.getCards();
        return upperShownCardsV.get(0).compareTo(bottomShownCardsV.get(0))==-1;
//             if(getNumberOfCard(upperShownCardsV.get(0).compareTo())<getNumberOfCard(bottomShownCardsV.get(0))){
//                 return true;
//             }else if(getNumberOfCard(upperShownCardsV.get(0))==getNumberOfCard(bottomShownCardsV.get(0))){
//                 //点数相同比较花色
//                 return getFaceSuitOfCard(upperShownCardsV.get(0))>getFaceSuitOfCard(bottomShownCardsV.get(0));
//             }else{
//                 return false;
//             }
    }

    /**
     * 描述:三张牌型 牌之间比较大小
     * @author 叶达杭
     * @param upperShownCards 添加说明
     * @param bottomShownCards 添加说明
     * @return boolean 添加说明
     */
    private boolean ThreeCompare(SelectedCardGroup upperShownCards, SelectedCardGroup bottomShownCards){
        Vector<Card> upperShownCardsV=upperShownCards.getCards();
        Vector<Card> bottomShownCardsV=bottomShownCards.getCards();
        if(getNumberOfCard(upperShownCardsV.get(0))<getNumberOfCard(bottomShownCardsV.get(0))){
            return true;
        }else if(getNumberOfCard(upperShownCardsV.get(0))==getNumberOfCard(bottomShownCardsV.get(0))){
            //点数相同比较花色
            return getFaceSuitOfCard(upperShownCardsV.get(0))<getNumberOfCard(bottomShownCardsV.get(0));
        }else{
            return false;
        }
    }


    /**
     * 描述:下家出牌与上家牌型相同,均为三带一时，比较下家是否可以比的过上家
     * @author 叶达杭
     * @param upperShownCards 上家出牌
     * @param bottomShownCards 下家出牌
     * @return boolean 下家出牌比的过上家-true 否则-false
    */
    private boolean ThreeWithOnePairCompare(SelectedCardGroup upperShownCards, SelectedCardGroup bottomShownCards){

        Map<Value,Integer> upperCardsMap = upperShownCards.getCountMap();
        Map<Value,Integer> bottomCardsMap = bottomShownCards.getCountMap();
        int upperCardsNumberOfThree = -1;
        for(Map.Entry<Value,Integer> entry:upperCardsMap.entrySet()){
            if(entry.getValue()==3) {
                upperCardsNumberOfThree = entry.getKey().getValue();break;
            }
        }
        int bottomCardsNumberOfThree = -1;
        for(Map.Entry<Value,Integer> entry:bottomCardsMap.entrySet()){
            if(entry.getValue()==3) {
                bottomCardsNumberOfThree = entry.getKey().getValue();break;
            }
        }
        return bottomCardsNumberOfThree > upperCardsNumberOfThree;
    }

    /**
     * 描述:下家出牌与上家牌型相同时,为一对时，比较下家是否可以比的过上家
     * @author 叶达杭
     * @param upperShownCards 添加说明
     * @param bottomShownCards 添加说明
     * @return boolean 添加说明
    */
    private boolean APairCompare(SelectedCardGroup upperShownCards, SelectedCardGroup bottomShownCards){


        return upperShownCards.getCards().get(0).compareTo(bottomShownCards.getCards().get(0))==-1;
    }

    /**
     * 描述:下家出牌与上家牌型相同时,为四带一时，比较下家是否可以比的过上家
     * @author 叶达杭
     * @param upperShownCards 上家出牌
     * @param bottomShownCards 下家出牌
     * @return boolean 下家出牌比的过上家-true 否则-false
    */
    private boolean FourWithSingleCompare(SelectedCardGroup upperShownCards, SelectedCardGroup bottomShownCards){
        switch (getMaxCardOfFourWithSingle(upperShownCards).compareTo(getMaxCardOfFourWithSingle(bottomShownCards))){
            case 1:
            case 0:return false;
            case -1:return true;
            default:
                throw new Error("error!");
        }
    }

    private Card getMaxCardOfFourWithSingle(SelectedCardGroup scg){
        if(scg.getCards().size()==0){
            throw new Error("scg is null");
        }
        Value CardsValue = null;
        for(Map.Entry<Value,Integer> entry:scg.getCountMap().entrySet()){
            //entry.getValue()获取entry的的Integer
            if(entry.getValue()==4) {
                CardsValue = entry.getKey();break;
            }
        }

        Vector<Card> temp=new Vector<>();
        for(Card c:scg.getCards()){
            if(c.getValue()==CardsValue){
                temp.add(c);
            }
        }

        Card max =temp.get(0);
        for(int i=1;i<temp.size();i++){
            if(max.compareTo(temp.get(i))==1){
                max =temp.get(i);
            }
        }
        return max;
    }
     /**
      * 描述:下家出牌与上家牌型相同时,为顺子时，前提：两个杂顺比较或者两个同花顺比较，比较下家是否可以比的过上家.
      * 实现摘要：顺子中最大的一张进行比较，注意2在顺子中作为小牌，如：6 5 4 3 2顺子比较时，只取6进行比较：A在和K相连作顺
      *         时，按大牌进行比较，在和2连时候最小。
      * @author 叶达杭
      * @param upperShownCards 上家出牌
      * @param bottomShownCards 下家出牌
      * @return boolean 下家出牌比的过上家-true 否则-false
     */
     private boolean StraightCompare(SelectedCardGroup upperShownCards,SelectedCardGroup bottomShownCards){

           Card maxCardOfUpper =getMaxCardOfStraight(upperShownCards);
           Card maxCardOfBottom = getMaxCardOfStraight(bottomShownCards);

           int judge2=maxCardOfBottom.compareTo(maxCardOfUpper);
           if(judge2==1){
               return true;
           }else{
               return false;
           }
    }



   /**
    * 描述:同花五之间大小比较
    * @author 叶达杭
    * @param upperShownCards
    * @param bottomShownCards
    * @return boolean 下家出牌比的过上家-true 否则-false
   */
    private boolean SameFaceSuitFiveCompare(SelectedCardGroup upperShownCards,SelectedCardGroup bottomShownCards){

        Card maxCardOfUpper =getMaxCardOf(upperShownCards);
        Card maxCardOfBottom = getMaxCardOf(bottomShownCards);

        int judge2=maxCardOfBottom.compareTo(maxCardOfUpper);
        if(judge2==1){
            return true;
        }else{
            return false;
        }
    }

    private Card getMaxCardOf(SelectedCardGroup scg){
        if(scg.getCards().size()==0){
            throw new Error("scg is null");
        }
        Card max =scg.getCards().get(0);
        for(int i=1;i<scg.getCards().size();i++){
            if(max.compareTo(scg.getCards().get(i))==1){
                max =scg.getCards().get(i);
            }
        }
        return max;
    }

    /**
     * 描述:五张牌的牌型中，同花顺最大，四个带单张第二，三个带一对第三，同花
     *     五第四，杂顺最小。如果两手牌型相同，则按照相应的牌型规则比较
     * @author 叶达杭
     * @param upperShownCards 上家出牌
     * @param bottomShownCards 下家出牌
     * @return boolean 下家出牌比的过上家-true 否则-false
     */
    private boolean FiveCardsGroupCompare(SelectedCardGroup upperShownCards,SelectedCardGroup bottomShownCards){

        switch (upperShownCards.getCardGroupPattern().compareTo(bottomShownCards.getCardGroupPattern())){
            case 1:
                return false;
            case -1:
                return true;
            case 0://两组牌型一样
                int type =upperShownCards.getCardGroupPattern().getPattern();
                if(type == CardGroupPattern.ThreeWithOnePair){
                    return ThreeWithOnePairCompare(upperShownCards,bottomShownCards);
                }else if(type == CardGroupPattern.FourWithSingle){
                    return FourWithSingleCompare(upperShownCards,bottomShownCards);
                }else if(type ==CardGroupPattern.SameFaceSuitFive) {
                    return   SameFaceSuitFiveCompare(upperShownCards,bottomShownCards);
                } else if (type ==CardGroupPattern.SameFaceSuitStraight||type==CardGroupPattern.MixedStraight) {
                    return StraightCompare(upperShownCards,bottomShownCards);
                }
        }
        return false;
    };

    /**
     * 描述:获取顺子中的最大一张
     *     注意2在顺子中作为小牌，如：6 5 4 3 2顺子比较时，只取6进行比较：A在和K相连作顺
     *     时，按大牌进行比较，在和2连时候最小。
     * @author 叶达杭
     * @param scg 已选择的手牌
     * @return Model.Entity.Card 顺子中点数最大的牌
    */
    private Card getMaxCardOfStraight(SelectedCardGroup scg){
        scg.cardGroupSortAsNumber(1);//按数字升序排列
        if(scg.getCards().get(0).getValue()==Value.ACE&&scg.getCards().get(4).getValue()==Value.KING){
            //A 10 J Q K  此时A按大牌处理
            return scg.getCards().get(0);//返回A
        }else{
            return scg.getCards().get(4);//返回最大点数的牌
        }
    }

    private int getNumberOfCard(Card card){
        return card.getValue().getValue();
    }
    private int getFaceSuitOfCard(Card card){
        return card.getSuit().getValue();
    }


}
