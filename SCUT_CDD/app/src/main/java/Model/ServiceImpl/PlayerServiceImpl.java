package Model.ServiceImpl;

import java.util.Vector;

import Model.Dao.GameTurnDao;
import Model.DaoImpl.GameTurnDaoImpl;
import Model.Entity.Player;
import Model.Service.PlayerService;
import Model.ServiceImpl.GameRule.GameRuleImpl;

public class PlayerServiceImpl implements PlayerService {
    Player player;
    GameTurnDao gameTurnDao =new GameTurnDaoImpl();

    public PlayerServiceImpl(Player player) {
        this.player = player;
    }


    @Override
    public boolean setSelectedCards(Vector<Integer> index){
        int location1=0;
        player.getSelCards().getCards().clear();
        //先加入selectedCardGroup 如果出牌有效 则在玩家手牌中减去
        for (int i:index) {
            player.getSelCards().getCards().add(player.getOwnCardGroup().getCards().elementAt(i-1-location1));
            location1++;
        }
        player.getSelCards().updatePattern();

        //验证..
       GameRuleImpl gameRuleImpl =new GameRuleImpl();
       if(!gameRuleImpl.validate(player.getSelCards())){
           System.out.println("出牌无效");
           return false;
       };
        //出牌有效 移出
        int location2=0;
        for (int i:index) {
            player.getOwnCardGroup().getCards().remove(i-1-location2);
            location2++;
        }
        //更新上家已出手牌
        gameTurnDao.setUpperShownCardGroup(player.getSelCards());
        return true;
    }



}
