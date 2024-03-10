package Model.ServiceImpl.GameRule;

import com.development.scut_cdd.ServerLayer.GameRoomData;

import Model.Dao.GameRuleDao;
import Model.Dao.GameTurnDao;
import Model.DaoImpl.GameRuleDaoImpl;
import Model.DaoImpl.GameTurnDaoImpl;
import Model.Entity.CardGroupPattern;
import Model.Entity.SelectedCardGroup;
import Model.Entity.Suit;
import Model.Entity.Value;
import Model.Service.GameRule.CardsComparator;
import Model.Service.GameRule.CardsPatternRecognizer;
import Model.Service.GameRule.GameRule;

public class GameRuleImpl implements GameRule {
    GameRuleDao gameRuleDao =new GameRuleDaoImpl();
    GameTurnDao gameTurnDao = new GameTurnDaoImpl();


    public boolean validate(SelectedCardGroup scg){
         if(gameRuleDao.getFirstRoundFlag()){
             return beginValidate(scg);
         }else{
             //中间对局
             return middleValidate(scg);
         }
    }
    public boolean validate(GameRoomData gameRoomData,SelectedCardGroup scg){
        if(gameRoomData.getRoundCount()==0){
            return beginValidate(gameRoomData,scg);
        }else{
            //中间对局
            return middleValidate(gameRoomData,scg);
        }
    }

    private boolean beginValidate(GameRoomData gameRoomData,SelectedCardGroup scg){
            //是第一回合
            //是房间内的第一次对局 由拿方块3的一方首先出牌，而且第一轮出牌中必须包含方块3
            if(scg.isContain(Value.THREE, Suit.DIAMONDS)){
                //含有方块3 判断是否为合法牌型
                CardsPatternRecognizer cardsPatternRecognizer =new CardsPatternRecognizerImpl();
                if(cardsPatternRecognizer.recognizePattern(scg)!= CardGroupPattern.Undetermined){
                    return true;
                }
            }

        return false;
    }
    private boolean middleValidate(GameRoomData gameRoomData,SelectedCardGroup scg) {
        CardsComparator comparator = new Model.ServiceImpl.GameRule.CardsComparator();
        if (gameRoomData.getPassCount()==3) {
            if (scg.getCardGroupPattern().getPattern() != CardGroupPattern.Undetermined) {
                return true;
            } else {
                return false;
            }
        } else {
            return comparator.SelectedCardGroupCompare(gameRoomData.getPreviousShownCards(), scg);
        }
    }

    private boolean beginValidate(SelectedCardGroup scg){
         if(gameRuleDao.getFirstRoundFlag()){
             //是第一回合
             //是房间内的第一次对局 由拿方块3的一方首先出牌，而且第一轮出牌中必须包含方块3
             if(scg.isContain(Value.THREE, Suit.DIAMONDS)){
                 //含有方块3 判断是否为合法牌型
                 CardsPatternRecognizer cardsPatternRecognizer =new CardsPatternRecognizerImpl();
                 if(cardsPatternRecognizer.recognizePattern(scg)!= CardGroupPattern.Undetermined){
                     gameRuleDao.setFirstRoundFlag(false);
                     return true;
                 }
             }
         }
         return false;
    }
    /**
     * 描述:中间对局验证
     *     首家出牌后，下家所出的牌张数必须和首家的相同，同时比首家所出的牌
     *     大；下家也可以Pass表示不出牌，由再下一家继续出牌。
     *     如果连续三家都Pass，这时最后出牌的一家具有下一轮出牌权，并可以打
     *     出任意牌型。
     * @author 叶达杭
     * @param scg
     * @return boolean
    */
    private boolean middleValidate(SelectedCardGroup scg) {
        CardsComparator comparator = new Model.ServiceImpl.GameRule.CardsComparator();
        if (gameTurnDao.getPassCount() == 3) {
            if (scg.getCardGroupPattern().getPattern() != CardGroupPattern.Undetermined) {
                return true;
            } else {
                return false;
            }
        } else {
            return comparator.SelectedCardGroupCompare(gameTurnDao.getUpperShownCardGroup(), scg);
        }
    }
}
