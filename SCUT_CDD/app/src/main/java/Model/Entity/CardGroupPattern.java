package Model.Entity;

import Model.ServiceImpl.GameRule.CardsPatternRecognizerImpl;

/**
* <p>牌型</p>
*/
public class CardGroupPattern {
    private int pattern =0;

    /**
     * 描述:设置牌型
     * @author 叶达杭
     * @param scg 选择的牌组
     * @return void 添加说明
    */
    public void setPattern(SelectedCardGroup scg){
        CardsPatternRecognizerImpl recognizer=new CardsPatternRecognizerImpl();
        pattern = recognizer.recognizePattern(scg);
    }


    /**
     * 描述:牌型之间的比较。五张牌的牌型中，同花顺最大，四个带单张第二，三个带一对第三，同花
     *     五第四，杂顺最小
     * @author 叶达杭
     * @param cp 被比较的牌组
     * @return int 1-比cp大  -1-比cp小  0-一样
    */
    public int compareTo(CardGroupPattern cp){
        if(this.pattern>cp.getPattern()){
            return 1;
        }else if(this.pattern==cp.getPattern()){
            return 0;
        }else{
            return -1;
        }
    }

    public int getPattern() {
        return pattern;
    }

    /** <p>不确定类型</p>*/
    public final static int Undetermined = 0;

    /** <p>单张:任何一张单牌</p>*/
    public final static int Single = 1;

    /** <p>一对:两张张牌点相同的牌</p>*/
    public final static int APair = 2;

    /** <p>三个:三张牌点相同的牌。</p>*/
    public final static int Triple = 3;//

    /** <p>顺子:五张连续的牌。同花顺、杂顺都属于顺子</p>*/
    public final static int Straight = 8;

    /** <p>杂顺：花色不全部相同的牌称为杂顺</p>*/
    public final static int MixedStraight = 16;

    /** <p>同花五:由相同花色的五张牌组成，但不是顺，称”同花五”。如红桃”278JK”</p>*/
    public final static int SameFaceSuitFive = 17;

    /** <p>三个带一对：例如：99955。</p>*/
    public final static int ThreeWithOnePair = 18;

    /** <p>/四个带单张：例如：99995。</p>*/
    public final static int FourWithSingle = 19;

    /** <p>同花顺</p>*/
    public final static int SameFaceSuitStraight = 20;

    /**
     * 实现牌型id到String的转化，获得牌型的文字描述
     * @author 叶达杭
     * @param id 需要识别的id
     * @return java.lang.String 牌型的文字描述
    */
    public static String typeToString(int id){
        switch (id) {
            case Single:
                return "单张";
            case APair:
                return  "一对";
            case Triple:
                return  "三个";
            case MixedStraight:
                return  "杂顺";
            case SameFaceSuitStraight:
                return  "同花顺";
            case ThreeWithOnePair:
                return  "三个带一对";
            case FourWithSingle:
                return  "四个带单张";
            default:
                return "识别失败";
        }
    }
    public String toString(){
        return  typeToString(pattern);
    }
}
