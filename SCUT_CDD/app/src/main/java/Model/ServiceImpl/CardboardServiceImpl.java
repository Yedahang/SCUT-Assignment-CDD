package Model.ServiceImpl;

import android.content.Context;

import Database.LocalDB;
import Model.Dao.CardboardDao;
import Model.Dao.GameRoomDao;
import Model.Dao.GameRuleDao;
import Model.DaoImpl.CardboardDaoImpl;
import Model.DaoImpl.GameRoomDaoImpl;
import Model.DaoImpl.GameRuleDaoImpl;
import Model.Entity.Card;
import Model.Entity.Player;
import Model.Entity.RoomModel;
import Model.Service.CardboardService;
import Model.Service.GameTurnService;


public class CardboardServiceImpl implements CardboardService
{
    CardboardDao cardboardDao =new CardboardDaoImpl();
    GameRuleDao gameRuleDao =new GameRuleDaoImpl();

    GameTurnService gameTurnService;
    GameRoomDao gameRoomDao;

    RoomServiceImpl roomService;

    private static int  GameState=-1;

    public CardboardServiceImpl(Context context) {
        gameRoomDao = new GameRoomDaoImpl(context);
//        roomService = new RoomServiceImpl(context);
//        gameTurnService = new GameTurnServiceImpl(context);
    }
    public CardboardServiceImpl() {};

    @Override
    public void startGameInit() {
       Player player1 = new Player(1,"player1",1000);
       Player player2 = new Player(2,"player2",1000);
       Player player3 = new Player(3,"player3",1000);
       Player player4 = new Player(4,"player4",1000);
       cardboardDao.clearPlayerVec();
       cardboardDao.addPlayerToVec(player1);
       cardboardDao.addPlayerToVec(player2);
       cardboardDao.addPlayerToVec(player3);
       cardboardDao.addPlayerToVec(player4);
        GameTurnService gameTurnService = new GameTurnServiceImpl();
        gameTurnService.distributeCard();//发牌
        gameTurnService.decideShowingOrder();//决定出牌顺序
        gameRuleDao.setFirstRoundFlag(true);//设置flag
    }




    public void initialACardBoard(){
        cardboardDao.clearPlayerVec();
//        cardboardDao.addPlayerToVec(new Player(1,,1000);
    }



//    public  void setPlayerMates(Player player1, Player player2, Player player3, Player player4) {
////        cardboardDao.getPlayerQue().toArray();
//        player1.setPlaymate(player2, player3, player4);
//        player2.setPlaymate(player1, player3, player4);
//        player3.setPlaymate(player1, player2, player4);
//        player4.setPlaymate(player1, player2, player3);
//    }

    @Override
    public void displayAllPlayerCard() {
        cardboardDao.getPlayerVec().get(0).displayHandCards();
        cardboardDao.getPlayerVec().get(1).displayHandCards();
        cardboardDao.getPlayerVec().get(2).displayHandCards();
        cardboardDao.getPlayerVec().get(3).displayHandCards();
    }



    private void addActorIntoRoom(int room_id,String actor_id,String type){

    }


    public int  getGameState(){
        return this.GameState;
    }
}