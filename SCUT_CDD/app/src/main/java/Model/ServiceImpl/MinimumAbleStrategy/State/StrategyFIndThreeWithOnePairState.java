package Model.ServiceImpl.MinimumAbleStrategy.State;

import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.Value;
import Model.ServiceImpl.MinimumAbleStrategy.MinimumAbleStrategy;
import Model.ServiceImpl.MinimumAbleStrategy.util.Tool;

public class StrategyFIndThreeWithOnePairState implements  StrategyBaseState{
    @Override
    public void next() {
        three =null;
        //获取四张牌
        if (!isAnyWay) {
            nextInThreeWithAPair();
            if(three ==null){
                nextInAnyWay();
            }
        } else {
            nextInAnyWay();
        }
        if(three !=null) {
            //获取单张牌
            getAPair();
            Vector<Card> result = new Vector<>(three);
            result.add(APair_1);
            result.add(APair_2);
            minimumAbleStrategy.updateCurrentInferenceCardGroup(result);
        }
    }

    MinimumAbleStrategy minimumAbleStrategy;
    private Value CurrentValue;

    private int i_three;  //1-13对应 A-K

    private int i_aPair;//1-13对应 A-K

    private Card APair_1 =null;
    private Card APair_2 =null;

    private Vector<Card> three =null;
    private boolean isAnyWay = false;

    private Card upperMaxCard = null;

    public StrategyFIndThreeWithOnePairState(MinimumAbleStrategy minimumAbleStrategy) {
        this.minimumAbleStrategy = minimumAbleStrategy;
        Value fourCard=null;

        for(Map.Entry<Value,Integer> entry:minimumAbleStrategy.getUpperShownCards().getCountMap().entrySet()){
            if(entry.getValue()==3){
                fourCard=entry.getKey();
            }
        }
        if(fourCard!=null){
            for(Card c:minimumAbleStrategy.getUpperShownCards().getCards()){
                //找到三张中最大的
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
        this.i_three = upperMaxCard.getNumber();
    }

    /**
     * 描述:先找只有三张的牌型的牌出
     *
     * @return void
     * @author 叶达杭
     */
    private void nextInThreeWithAPair() {
        Vector<Card> result = new Vector<>();
        boolean isFound = false;
        //找三张
        //3->k->A->2
        while (!isFound) {
            //从3开始找 3-》K
            CurrentValue = Value.fromOrdinal(i_three - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (minimumAbleStrategy.getCardClass().get(CurrentValue).size() == 3) {
                    if (upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(0)) == -1
                            || upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(1)) == -1
                            || upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(2)) == -1) {
                        three = new Vector<>();
                        three.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(0));
                        three.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(1));
                        three.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(2));
                        isFound = true;
                    }
                }
            }
            //3->k->A->2 找完到最大了 没找到
            if (i_three == 2) {
                isAnyWay = true;
                setI();
                break;
            }
            i_three = i_three % 13 + 1;
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
            CurrentValue = Value.fromOrdinal(i_three - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (Objects.requireNonNull(minimumAbleStrategy.getCardClass().get(CurrentValue)).size() > 3) {
                    Vector<Card> temp = minimumAbleStrategy.getCardClass().get(CurrentValue);
                    //先排序 升序
                    assert temp != null;
                    Tool.cardVectorSort(temp);
                    for (int i = 2; i < temp.size(); i++) {
                        if (temp.get(i).compareTo(upperMaxCard) == 1) {
                            three = new Vector<>();
                            three.add(temp.get(0));
                            three.add(temp.get(1));
                            three.add(temp.get(i));
                            return;
                        }
                    }

                }
            }
            i_three = i_three % 13 + 1;
        }

    }

    //默认取最小的
    public void getAPair(){
        boolean isFound = false;
        i_aPair =3;
        //3->k->A->2
        while (!isFound) {
            //从最小开始找 3-》K
            CurrentValue = Value.fromOrdinal(i_aPair - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (Objects.requireNonNull(minimumAbleStrategy.getCardClass().get(CurrentValue)).size() == 2) {
                    Vector<Card> temp = minimumAbleStrategy.getCardClass().get(CurrentValue);
                    //先排序 升序
                    assert temp != null;
                    APair_1=temp.get(0);
                    APair_2=temp.get(1);
                    return;
                }
            }
            //3->k->A->2 找完到最大了 没找到
            if (i_aPair == 2) {
                break;
            }
            i_aPair = i_aPair % 13 + 1;
        }
        //没找到
        if(!isFound){
            while (!isFound) {
                //从最小开始找 3-》K
                CurrentValue = Value.fromOrdinal(i_aPair - 1);
                if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                    if (Objects.requireNonNull(minimumAbleStrategy.getCardClass().get(CurrentValue)).size() > 2) {
                        Vector<Card> temp = minimumAbleStrategy.getCardClass().get(CurrentValue);
                        //先排序 升序
                        assert temp != null;
                        Tool.cardVectorSort(temp);
                        for (int i = 2; i < temp.size(); i++) {
                            if (temp.get(i).compareTo(upperMaxCard) == 1) {
                                APair_1=temp.get(0);
                                APair_2=temp.get(i);
                                return;
                            }
                        }

                    }
                }
                i_aPair = i_aPair % 13 + 1;
            }

        }
    }

}
