package ControllerImpl;

import android.content.Context;

import com.development.scut_cdd.View.MyShowingCard;

import java.util.List;
import java.util.Vector;

import Controller.GameTurnCon;
import Model.Dao.GameRoomDao;
import Model.DaoImpl.GameRoomDaoImpl;
import Model.Entity.CardGroup;
import Model.Entity.Player;
import Model.Service.GameTurnService;
import Model.ServiceImpl.GameTurnServiceImpl;


/** <p>游戏对局控制器</p>*/
public class GameTurnConImpl implements GameTurnCon {
    private GameTurnService gameTurnService=new GameTurnServiceImpl();
    private GameRoomDao gameRoomDao;

    public GameTurnConImpl() {
    }

    public GameTurnConImpl(Context context){
        gameRoomDao=new GameRoomDaoImpl(context);
        gameTurnService = new GameTurnServiceImpl(context);
    }
    public void outputCurrentShowingPlayer(){
        System.out.println("Turn to: "+gameTurnService.getCurrentShowingPlayerInString());
    }
    public void outputUpperShownCards(){
        System.out.println("Shown Cards: "+gameTurnService.getUpperShownCardGroup().toString());
    }

    /**
     * 描述:进入回合
     * @author 叶达杭
     * @return void
    */
    public void comeIntoRound(int op){
        switch (op){
            case 1://出牌
                break;
            case 2://跳过
                passRound();
                break;
        }
    }

    /**
     * 描述:跳过回合
     * @author 叶达杭
     * @return void
    */
    public void passRound(){
        gameTurnService.upPassCount();
        gameTurnService.turnToNextPlayer();
    }

    /**
     * 描述:玩家出牌
     * @author 叶达杭
     * @param list 添加说明
     * @return void 添加说明
    */
    public void playerShowingCards(List<MyShowingCard> list,String user_id){
          gameTurnService.playerShowingCards(list,user_id);
    }
    public void showCards(Vector<Integer> positions){
        if(gameTurnService.playerShowCards(positions)){
            //出牌有效
            gameTurnService.turnToNextPlayer();
            outputUpperShownCards();
            gameTurnService.clearPassCount();
        }
    }

    public CardGroup getHandCard(String user_id){
        return gameTurnService.getPlayer(user_id).getOwnCardGroup();
    }

    /**
     * 描述:机器人出牌
     * @author 叶达杭
     * @param room_id 添加说明
     * @return void 添加说明
    */
    public void robotShow(int room_id) {
        Vector<Player> players = gameRoomDao.getAllPlayerInRoom(room_id);
    }



}
