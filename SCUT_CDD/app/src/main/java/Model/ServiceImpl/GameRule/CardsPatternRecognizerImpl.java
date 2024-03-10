package Model.ServiceImpl.GameRule;


import static Model.Entity.CardGroupPattern.APair;
import static Model.Entity.CardGroupPattern.FourWithSingle;
import static Model.Entity.CardGroupPattern.MixedStraight;
import static Model.Entity.CardGroupPattern.SameFaceSuitFive;
import static Model.Entity.CardGroupPattern.SameFaceSuitStraight;
import static Model.Entity.CardGroupPattern.Single;
import static Model.Entity.CardGroupPattern.Straight;
import static Model.Entity.CardGroupPattern.ThreeWithOnePair;
import static Model.Entity.CardGroupPattern.Triple;
import static Model.Entity.CardGroupPattern.Undetermined;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.SelectedCardGroup;
import Model.Entity.Value;
import Model.Service.GameRule.CardsPatternRecognizer;

public class CardsPatternRecognizerImpl implements CardsPatternRecognizer {

    /**
     * 识别牌型
     * para: 需要识别的出牌组
     * return:
     * test: OK
     */
    @Override
    public int recognizePattern(SelectedCardGroup scg){
        scg.generateCardGroupHashMap();
        switch (scg.getCards().size()){
            case 1:
                return isSingleCard(scg);
            case 2:
                return isAPair(scg);
            case 3:
                return isTriple(scg);
            case 5:
                //五张牌的牌型中，同花顺最大，四个带单张第二，三个带一对第三，同花五第四，杂顺最小
                //在类型值中 int数值大小暗含了类型大小
                Vector <Integer> patterns =new Vector<>();
                patterns.add(distinguishStraight(scg));
                patterns.add(isSameFaceSuitFive(scg));
                patterns.add(isFourWithSingle(scg));
                patterns.add(isThreeWithOnePair(scg));
                //现在默认识别为类型最大的牌型
                int max =-1;
                for(Integer i:patterns){
                    if(max<i){
                        max=i;
                    }
                }
                return max;
            default:
                return Undetermined;
        }
    }


    private int isSingleCard(SelectedCardGroup scg){
        if(scg.getCards().size()==1){
            return Single;
        }else{
            return Undetermined;
        }
    }

    private int isAPair(SelectedCardGroup scg){
        if(scg.getCards().size()==2){
            if(getValueOfCard(scg.getCards().get(0))==getValueOfCard(scg.getCards().get(1))) {
                return APair;
            }
        };
        return Undetermined;
    }


    private int isTriple(SelectedCardGroup scg){
        Vector<Card> cards =scg.getCards();
        if(cards.size()==3){
            if(getValueOfCard(cards.get(0))==getValueOfCard(cards.get(1))
                    &&getValueOfCard(cards.get(1))==getValueOfCard(cards.get(2))) {
                return Triple;
            }
        };
        return Undetermined;
    }

    private int isStraight(SelectedCardGroup scg){
        Vector<Card> cards =scg.getCards();
        if(cards.size()==5&&scg.getCountMap().size()==5){
           int[] NumbersArr = new int[5];
           for(int i=0 ;i<5 ;i++){
               NumbersArr[i]=cards.get(i).getNumber();
           }
           //升序排列
            Arrays.sort(NumbersArr);

           if(NumbersArr[0]==1){
               //含有A   "A 2 3 4 5" 或 "A 10 J Q K"
               if(NumbersArr[1]==2){
                 //第二的为2时候  如果是顺子 则考虑是否为"A 2 3 4 5"
                 for(int i=2;i<5 ;i++) {
                     if(NumbersArr[i]-NumbersArr[i-1]!=1){
                         return Undetermined;
                     }
                 }
                 return Straight;
               }else{
                   //第二张不是2时候 如果是顺子 则是考虑A在顺子尾部的情况 "A 10 J Q K" ->"10 J Q K A"
                   if(NumbersArr[1]==10&&NumbersArr[2]==11&&NumbersArr[3]==12&&NumbersArr[4]==13){
                       return Straight;
                   }else {
                       return Undetermined;
                   }
               }
           }else{
               //不含有A时
               for(int i=1 ;i<5 ;i++){
                   if(NumbersArr[i]-NumbersArr[i-1]!=1){
                       return Undetermined;
                   }
               }
               return Straight;
           }

        };
        return Undetermined;
    }


    private int distinguishStraight(SelectedCardGroup scg){
        Vector<Card> cards =scg.getCards();
        if(cards.size()==5&&isStraight(scg)==Straight) {
            if (isSameFaceSuit(scg)) {
                return SameFaceSuitStraight;
            } else {
                return MixedStraight;
            }
        }else {
            return Undetermined;
        }
    }

    /**
     * 判断同花五:由相同花色的五张牌组成，但不是顺，称”同花五”。．如红桃”278JK”。
     * para: 需要识别的出牌组
     * return: Undetermined-不是  SameFaceSuitFive-是
     * test: OK
     */
    private int isSameFaceSuitFive(SelectedCardGroup scg){
        Vector<Card> cards =scg.getCards();
        if(cards.size()==5&&isSameFaceSuit(scg)){
             return SameFaceSuitFive;
        };
        return Undetermined;
    }

    /**
     * 识别所有牌是否为同花
     * para: 需要识别的出牌组
     * return: false-不是  true-是
     * test: OK
     */
    private boolean isSameFaceSuit(SelectedCardGroup scg){
        Vector<Card> cards =scg.getCards();
        int firstCardFaceSuit = getFaceSuitOfCard(cards.get(0));
        for(int i =1 ;i<cards.size();i++){
            if(firstCardFaceSuit!=getFaceSuitOfCard(cards.get(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 识别三个带一对：例如：99955。
     * para: 需要识别的出牌组
     * return: Undetermined-不是  ThreeWithOnePair-是
     * test: OK
     */
    private int isThreeWithOnePair(SelectedCardGroup scg){
        Vector<Card> cards =scg.getCards();
        if(cards.size()==5){
            Map<Integer,Integer> map = new HashMap<>();
            for (Card temp :cards){
                Integer current_CardNumber=getValueOfCard(temp).getValue();
                if(map.containsKey(current_CardNumber)){
                    if(map.get(current_CardNumber)!=null){
                        Integer temp_count=map.get(current_CardNumber);
                        map.put(current_CardNumber,temp_count+1);
                    }
                }else{
                    map.put(getValueOfCard(temp).getValue(),1);
                }
            }

            if(map.size()!=2){
                return 0;
            }else{
                boolean isTwoFound=false;
                boolean isTreeFound=false;
              for(Map.Entry<Integer,Integer> entry:map.entrySet()){
                  if(entry.getValue()==2){
                      isTwoFound=true;
                  }else if(entry.getValue()==3){
                      isTreeFound=true;
                  }else{
                      return Undetermined;
                  }
              }
              if(isTreeFound&&isTwoFound){
                  return ThreeWithOnePair;
              }else{
                  return Undetermined;
              }
            }
        };
        return Undetermined;
    }

    /**
     * 四个带单张：例如：99995。
     * para: 需要识别的出牌组
     * return: Undetermined-不是  FourWithSingle-是
     * test: OK
     */
    private int isFourWithSingle(SelectedCardGroup scg){
        Vector<Card> cards =scg.getCards();
        if(cards.size()==5){
            Map<Integer,Integer> map = new HashMap<>();
            for (Card temp :cards){
                Integer current_CardNumber=getValueOfCard(temp).getValue();
                if(map.containsKey(current_CardNumber)){
                    if(map.get(current_CardNumber)!=null){
                        Integer temp_count=map.get(current_CardNumber);
                        map.put(current_CardNumber,temp_count+1);
                    }
                }else{
                    map.put(getValueOfCard(temp).getValue(),1);
                }
            }

            if(map.size()!=2){
                return 0;
            }else{
                boolean isFourFound=false;
                boolean isSingleFound=false;
                for(Map.Entry<Integer,Integer> entry:map.entrySet()){
                    if(entry.getValue()==4){
                        isFourFound=true;
                    }else if(entry.getValue()==1){
                        isSingleFound=true;
                    }else{
                        return Undetermined;
                    }
                }
                if(isFourFound&&isSingleFound){
                    return FourWithSingle;
                }else{
                    return Undetermined;
                }
            }
        };
        return Undetermined;
    }

    private Value getValueOfCard(Card card){
        return card.getValue();
    }

    private int getFaceSuitOfCard(Card card){
        return card.getSuit().getValue();
    }

//    public Vector<Card> cardGroupToVector(CardGroup cardGroup){
//        return cardGroup.getCards();
//    }



}
