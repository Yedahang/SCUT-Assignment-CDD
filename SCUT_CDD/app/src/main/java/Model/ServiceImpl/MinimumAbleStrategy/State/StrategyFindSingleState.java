package Model.ServiceImpl.MinimumAbleStrategy.State;

import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.Value;
import Model.ServiceImpl.MinimumAbleStrategy.MinimumAbleStrategy;

public class StrategyFindSingleState implements StrategyBaseState {
    MinimumAbleStrategy minimumAbleStrategy;
    private Value CurrentValue;

    private int i;  //1-13对应 A-K

    private boolean isAnyWay = false;

    private Card upperCard = null;

    public StrategyFindSingleState(MinimumAbleStrategy minimumAbleStrategy) {
        this.minimumAbleStrategy =minimumAbleStrategy;
        upperCard = minimumAbleStrategy.getUpperShownCards().getCards().get(0);//单张
        setI();
    }

    public void setI() {
        this.i = upperCard.getNumber();
    }

    @Override
    public void next() {
        if (!isAnyWay) {
            nextInSingle();
        } else {
            nextInAnyWay();
        }
    }

    /**
     * 描述:先找只有单张的牌出
     *
     * @return void
     * @author 叶达杭
     */
    private void nextInSingle() {
        Vector<Card> result = new Vector<>();
        boolean isFound = false;
        //3->k->A->2
        while (!isFound) {
            //从3开始找 3-》K
            CurrentValue = Value.fromOrdinal(i - 1);
            if (minimumAbleStrategy.getCardClass().containsKey(CurrentValue)) {
                if (minimumAbleStrategy.getCardClass().get(CurrentValue).size() == 1) {
                    if (upperCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(0)) ==-1) {
                        result.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(0));
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
     * 描述:从3开始 随便找一张最小的牌
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
                    if (upperCard.compareTo(
                            minimumAbleStrategy.getCardClass().get(CurrentValue).get(0)) == -1) {
                        result.add(minimumAbleStrategy.getCardClass().get(CurrentValue).get(0));
                        minimumAbleStrategy.updateCurrentInferenceCardGroup(result);
                        isFound = true;
                    }
            }
            //3->k->A->2 找完到最大了 没找到
            if (i == 2) {

                break;
            }
            i = i % 13 + 1;
        }
    }
}
