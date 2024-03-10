package Model.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
* <p>玩家在出牌阶段选择的牌组
*</p>
*/
public class SelectedCardGroup extends CardGroup {

    /**
     * <p>当前CardGroup的牌型类别 具体分类看CardGroupPattern</p>
     */
    private CardGroupPattern cardGroupPattern = new CardGroupPattern();
    public void setSelectedCardGroup(CardGroup cg){
        super.setCardGroup(cg.getCards());
        generateCardGroupHashMap();
        cardGroupPattern.setPattern(this);
    }
    public void setSelectedCardGroup(Vector <Card> cards){
        CardGroup cg = new CardGroup();
        cg.setCardGroup(cards);
        super.setCardGroup(cg.getCards());
        generateCardGroupHashMap();
        cardGroupPattern.setPattern(this);
    }

    public void updatePattern(){
     cardGroupPattern.setPattern(this);
    }

    public CardGroupPattern getCardGroupPattern() {
        return cardGroupPattern;
    }

    /**
    * <p>用来对SelectedCardGroup按点数分类,并统计同点数的牌的数目 如<FOUR(4),3>表示有3张4
    *</p>
    */
    private  Map<Value,Integer> countMap=null;




    /**
     * 把父类Vector<Card> cards转化成Map,储存在成员count中,
     * @author 叶达杭
    */
    public void generateCardGroupHashMap(){
        Map<Value,Integer> map = new HashMap<>();
        for (Card temp :this.getCards()){
            Value currentValue = temp.getValue();
            if(map.containsKey(currentValue)){
                if(map.get(currentValue)!=null){
                    Integer temp_count=map.get(currentValue);
                    if(temp_count!=null)map.put(currentValue,temp_count+1);
                }
            }else{
                map.put(currentValue,1);
            }
        }
        countMap = map;
    }

    public Map<Value,Integer> getCountMap(){
           return  countMap;
    }

    /**
     * 获得牌型文字描述
     * @author 叶达杭
     * @return java.lang.String
    */
    public String getSelectedCardGroupTypeInString(){
        return cardGroupPattern.toString();
    }
}
