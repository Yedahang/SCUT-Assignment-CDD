package Model.ServiceImpl.MinimumAbleStrategy.State;

import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.Value;
import Model.ServiceImpl.MinimumAbleStrategy.MinimumAbleStrategy;
import Model.ServiceImpl.MinimumAbleStrategy.util.Tool;


/** <p>四带一</p>*/
public class StrategyFindFourWithSingleState implements StrategyBaseState{
    @Override
    public void next() {
        four=null;
        //获取四张牌
        if (!isAnyWay) {
            nextInFourWithSingle();
        } else {
            nextInAnyWay();
        }
        if(four!=null) {
            //获取单张牌
            getSingle();
            Vector<Card> result = new Vector<>(four);
            result.add(single);
            minimumAbleStrategy.updateCurrentInferenceCardGroup(result);
        }
    }

    MinimumAbleStrategy minimumAbleStrategy;
    private Value CurrentValue;

    private int i_four;  //1-13对应 A-K

    private int i_single;//1-13对应 A-K

    private Card single =null;

    private Vector<Card> four =null;
    private boolean isAnyWay = false;

    private Card upperMaxCard = null;

    public StrategyFindFourWithSingleState(MinimumAbleStrategy minimumAbleStrategy) {
        this.minimumAbleStrategy = minimumAbleStrategy;
        Value fourCard=null;

        for(Map.Entry<Value,Integer> entry:minimumAbleStrategy.getUpperShownCards().getCountMap().entrySet()){
            if(entry.getValue()==4){
                fourCard=entry.getKey();
            }
        }
        if(fourCard!=null){
            for(Card c:minimumAbleStrategy.getUpperShownCards().getCards()){
                //找到四张中最大的
                if(upperMaxCard==null){
                    upperMaxCard=c;
                }else{
                    if(c.compareTo(upperMaxCard)==1){
                        upperMaxCard=c;
                    }
                }
            }
        }
        setI();
    }

    public void setI() {
        this.i_four = upperMaxCard.getNumber();
    }

    /**
     * 描述:先找只有三张的牌型的牌出
     *
     * @return void
     * @author 叶达杭
     */
    private void nextInFourWithSingle() {
        Vector<Card> result = new Vector<>();
        boolean isFound = false;
        //先找四张 再找单张
        //3->k->A->2
        while (!isFound) {
            //从3开始找 3-》K
            CurrentValue = Value.fromOrdinal(i_four - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (minimumAbleStrategy.getCardClass().get(CurrentValue).size() == 4) {
                    if (upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(0)) == -1
                            || upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(1)) == -1
                            || upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(2)) == -1
                            || upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(3)) == -1) {
                        four = new Vector<>();
                        four.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(0));
                        four.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(1));
                        four.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(2));
                        four.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(3));
                        isFound = true;
                    }
                }
            }
            //3->k->A->2 找完到最大了 没找到
            if (i_four == 2) {
                isAnyWay = true;
                setI();
                break;
            }
            i_four = i_four % 13 + 1;
        }

    }

    /**
     * 描述:从3开始 随便找最小三张的牌
     *
     * @return void 添加说明
     * @author 叶达杭
     */
    private void nextInAnyWay() {
        Vector<Card> result = new Vector<>();
        boolean isFound = false;
        //3->k->A->2
        while (!isFound) {
            //从最小开始找 3-》K
            CurrentValue = Value.fromOrdinal(i_four - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (Objects.requireNonNull(minimumAbleStrategy.getCardClass().get(CurrentValue)).size() > 4) {
                    Vector<Card> temp = minimumAbleStrategy.getCardClass().get(CurrentValue);
                    //先排序 升序
                    assert temp != null;
                    Tool.cardVectorSort(temp);
                    for (int i = 2; i < temp.size(); i++) {
                        if (temp.get(i).compareTo(upperMaxCard) == 1) {
                            four = new Vector<>();
                            four.add(temp.get(0));
                            four.add(temp.get(1));
                            four.add(temp.get(2));
                            four.add(temp.get(i));
                            return;
                        }
                    }

                }
            }
            i_four = i_four % 13 + 1;
        }
        //3->k->A->2 找完到最大了 没找到
//            if (i == 2) {
//
//            }
    }

    //默认取最小的
    public void getSingle(){
        Vector<Card> result = new Vector<>();
        boolean isFound = false;
        i_single =3;
        //在只有单张的牌中找找单张
        //3->k->A->2
        while (!isFound) {
            //从3开始找 3-》K
            CurrentValue = Value.fromOrdinal(i_single - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (minimumAbleStrategy.getCardClass().get(CurrentValue).size() == 1) {
                    single = minimumAbleStrategy.getCardClass().get(CurrentValue).get(0);
                    isFound = true;
                }
            }
            //3->k->A->2 找完到最大了 没找到
            if (i_single == 2) {
                setI();
                break;
            }
            i_single = i_single % 13 + 1;
        }
        if(!isFound) {
            //在单张牌中找不到 在整副牌中取最小
            minimumAbleStrategy.getUpperShownCards().cardGroupSortAsRule(1);
            single = minimumAbleStrategy.getUpperShownCards().getCards().get(0);
        }
    }
}
