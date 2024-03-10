package Model.ServiceImpl.MinimumAbleStrategy.State;

import java.util.Objects;
import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.Value;
import Model.ServiceImpl.MinimumAbleStrategy.MinimumAbleStrategy;
import Model.ServiceImpl.MinimumAbleStrategy.util.Tool;


/** <p>一对</p>*/
public class StrategyFindAPairState implements StrategyBaseState{

    @Override
    public void next() {
        if (!isAnyWay) {
            nextInAPair();
        } else {
            nextInAnyWay();
        }
    }
    MinimumAbleStrategy minimumAbleStrategy;
    private Value CurrentValue;

    private int i;  //1-13对应 A-K

    private boolean isAnyWay = false;

    private Card upperMaxCard = null;

    public StrategyFindAPairState(MinimumAbleStrategy minimumAbleStrategy) {
        this.minimumAbleStrategy = minimumAbleStrategy;
        upperMaxCard = minimumAbleStrategy.getUpperShownCards().getCards().get(0);
        if(upperMaxCard.compareTo(minimumAbleStrategy.getUpperShownCards().getCards().get(1))==-1){
            upperMaxCard=minimumAbleStrategy.getUpperShownCards().getCards().get(1);
        }
        setI();
    }

    public void setI() {
        this.i =upperMaxCard.getNumber();
    }
    /**
     * 描述:先找只有单张的牌出
     *
     * @return void
     * @author 叶达杭
     */
    private void nextInAPair() {
        Vector<Card> result = new Vector<>();
        boolean isFound = false;
        //3->k->A->2
        while (!isFound) {
            //从3开始找 3-》K
            CurrentValue = Value.fromOrdinal(i - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (minimumAbleStrategy.getCardClass().get(CurrentValue).size() == 2) {
                    if (upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(0)) ==-1
                            ||upperMaxCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(1)) ==-1) {
                        result.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(0));
                        result.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(1));
                        minimumAbleStrategy.updateCurrentInferenceCardGroup(result);
                        isFound = true;
                    }
                }
            }
            //3->k->A->2 找完到最大了 没找到
            if (i == 2) {
                isAnyWay = true;
                setI();
                break;
            }
            i = i % 13 + 1;
        }

    }

    /**
     * 描述:从3开始 随便找最小一对的牌
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
            CurrentValue = Value.fromOrdinal(i - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (Objects.requireNonNull(minimumAbleStrategy.getCardClass().get(CurrentValue)).size() > 2) {
                    Vector<Card> temp =minimumAbleStrategy.getCardClass().get(CurrentValue);
                    //先排序 升序
                    assert temp != null;
                    Tool.cardVectorSort(temp);
                    for(int i = 1; i< temp.size(); i++) {
                          if(temp.get(i).compareTo(upperMaxCard)==1) {
                              result.add(temp.get(0));
                              result.add(temp.get(i));
                              minimumAbleStrategy.updateCurrentInferenceCardGroup(result);
                              isFound = true;
                              return;
                          }
                    }

                }
            }
            //3->k->A->2 找完到最大了 没找到
//            if (i == 2) {
//
//            }
            i = i % 13 + 1;
        }

        }
    }


