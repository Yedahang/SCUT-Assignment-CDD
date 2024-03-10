package Model.ServiceImpl.GameRule;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Model.Entity.Card;


//比较牌型大小
public class CardsCompare {
    /**
     参数列表
    |参数名|必选|类型|说明|
    :----|:---|:----- |-----   |
     返回值
     |参数名|类型|说明|
     |:-----  |:-----|-----                        |
    **/
    public boolean SingleCompare(Vector<Card> upperShownCards, Vector<Card> bottomShownCards){
             if(getNumberOfCard(upperShownCards.get(0))<getNumberOfCard(bottomShownCards.get(0))){
                 return true;
             }else if(getNumberOfCard(upperShownCards.get(0))==getNumberOfCard(bottomShownCards.get(0))){
                 //点数相同比较花色
                 return getFaceSuitOfCard(upperShownCards.get(0))<getNumberOfCard(bottomShownCards.get(0));
             }else{
                 return false;
             }
    }

    /**
     简要介绍：下家出牌与上家牌型相同时，比较下家是否可以比的过上家
     **参数列表**
     |参数名|必选|类型|说明|
     |upperShownCards    |:---|Vector<Card> |上家出牌 |
     |bottomShownCards    |:---|Vector<Card> |下家出牌 |

     **返回参数说明 **
     |参数名|类型|说明|
     | |boolean| 下家出牌比的过上家-true 否则-false |

     **备注 **
     添加备注                    |
     **/
    public boolean ThreeWithOnePairCompare(Vector<Card> upperShownCards,Vector<Card> bottomShownCards){
        Map<Integer,Integer> upperCardsMap = cardSVectorToHashMap(upperShownCards);
        Map<Integer,Integer> bottomCardsMap = cardSVectorToHashMap(bottomShownCards);
        int upperCardsNumberOfThree = -1;
        for(Map.Entry<Integer,Integer> entry:upperCardsMap.entrySet()){
            if(entry.getValue()==3) {
                upperCardsNumberOfThree = entry.getKey();break;
            }
        }
        int bottomCardsNumberOfThree = -1;
        for(Map.Entry<Integer,Integer> entry:bottomCardsMap.entrySet()){
            if(entry.getValue()==3) {
                bottomCardsNumberOfThree = entry.getKey();break;
            }
        }
        return bottomCardsNumberOfThree > upperCardsNumberOfThree;
    }

    /**
     简要介绍：下家出牌与上家牌型相同时,为四带一时，比较下家是否可以比的过上家
     **参数列表**
     |参数名|必选|类型|说明|
     |upperShownCards    |:---|Vector<Card> |上家出牌 |
     |bottomShownCards    |:---|Vector<Card> |下家出牌 |

     **返回参数说明 **
     |参数名|类型|说明|
     | |boolean| 下家出牌比的过上家-true 否则-false |

     **备注 **
     添加备注                    |
     **/
    public boolean FourWithSingleCompare(Vector<Card> upperShownCards,Vector<Card> bottomShownCards){
        Map<Integer,Integer> upperCardsMap = cardSVectorToHashMap(upperShownCards);
        Map<Integer,Integer> bottomCardsMap = cardSVectorToHashMap(bottomShownCards);
        int upperCardsNumberOfThree = -1;
        for(Map.Entry<Integer,Integer> entry:upperCardsMap.entrySet()){
            if(entry.getValue()==4) {
                upperCardsNumberOfThree = entry.getKey();break;
            }
        }
        int bottomCardsNumberOfThree = -1;
        for(Map.Entry<Integer,Integer> entry:bottomCardsMap.entrySet()){
            if(entry.getValue()==4) {
                bottomCardsNumberOfThree = entry.getKey();break;
            }
        }
        return bottomCardsNumberOfThree > upperCardsNumberOfThree;
    }

    /**
     简要介绍：下家出牌与上家牌型相同时,为顺子时，前提：两个杂顺比较或者两个同花顺比较，比较下家是否可以比的过上家.
     实现摘要：顺子中最大的一张进行比较，注意2在顺子中作为小牌，如：6 5 4 3 2顺子比较时，只取6进行比较：A在和K相连作顺
             时，按大牌进行比较，在和2连时候最小。

     **参数列表**
     |参数名|必选|类型|说明|
     |upperShownCards    |:---|Vector<Card> |上家出牌 |
     |bottomShownCards    |:---|Vector<Card> |下家出牌 |

     **返回参数说明 **
     |参数名|类型|说明|
     | |boolean| 下家出牌比的过上家-true 否则-false |

     **备注 **
     添加备注                    |
     **/
    public boolean StraightCompare(Vector<Card> upperShownCards,Vector<Card> bottomShownCards){
           Card maxNumOfUpper =upperShownCards.get(0);
           for(int i=1;i<upperShownCards.size();i++) {
               int judge = maxNumOfUpper.compareTo(upperShownCards.get(i));
               if (judge != -1) {
                   continue;
               } else {
                   maxNumOfUpper = upperShownCards.get(i);
               }
           }

           Card maxNumOfBottom =upperShownCards.get(0);
           for(int i=1;i<upperShownCards.size();i++) {
               int judge1 = maxNumOfUpper.compareTo(upperShownCards.get(i));
               if (judge1 != -1) {
                   continue;
               } else {
                   maxNumOfUpper = upperShownCards.get(i);
               }
           }

           int judge2=maxNumOfBottom.compareTo(maxNumOfUpper);
           if(judge2==1){
               return true;
           }else{
               return false;
           }
    }

    /**
     简要介绍：获取顺子中的最大一张
     前提：牌组是顺子
     实现摘要：顺子中最大的一张进行比较，注意2在顺子中作为小牌，如：6 5 4 3 2顺子比较时，只取6进行比较：A在和K相连作顺
     时，按大牌进行比较，在和2连时候最小。

     **参数列表**
     |参数名|必选|类型|说明|
     |upperShownCards    |:---|Vector<Card> |上家出牌 |
     |bottomShownCards    |:---|Vector<Card> |下家出牌 |

     **返回参数说明 **
     |参数名|类型|说明|
     | |boolean| 下家出牌比的过上家-true 否则-false |

     **备注 **
     添加备注                    |
     **/
//    public Card getMaxCardOfStraight(CardGroup cg){
//        cg.cardGroupSort(1);
//
//
//
//    }


    /**
     简要介绍：把Vector转化成Map。如Vector{1,1,2,3,2,2,2}->Map{(1,2),(2,4),(3,1)}
     **参数列表**
     |参数名|必选|类型|说明|
     |cards   |:---|Vector<Card> |牌组 |

     **返回参数说明 **
     |参数名|类型|说明|
     |map  |Map<Integer,Integer>|(牌点数,牌数)        |

     **备注 **
     添加备注                    |
     **/
    public  Map<Integer,Integer> cardSVectorToHashMap(Vector<Card> cards){
        Map<Integer,Integer> map = new HashMap<>();
        for (Card temp :cards){
            Integer current_CardNumber=getNumberOfCard(temp);
            if(map.containsKey(current_CardNumber)){
                if(map.get(current_CardNumber)!=null){
                    Integer temp_count=map.get(current_CardNumber);
                    if(temp_count!=null)map.put(current_CardNumber,temp_count+1);
                }
            }else{
                map.put(getNumberOfCard(temp),1);
            }
        }
        return map;
    }
    public int getNumberOfCard(Card card){
        return card.getValue().getValue();
    }
    public int getFaceSuitOfCard(Card card){
        return card.getSuit().getValue();
    }
}
