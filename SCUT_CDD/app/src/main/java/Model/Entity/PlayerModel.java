package Model.Entity;

import static Model.util.Converter.cardGroupToString;
import static Model.util.Converter.cardStringToCards;

import java.util.Vector;

public class PlayerModel {

    String USER_ID;
    int ROOM_ID;
    int CARD_NUM;
    String CARDS;

    String ITS_FIRST_BOTTOM_PLAYER;
    String ITS_SECOND_BOTTOM_PLAYER;
    String ITS_THIRD_BOTTOM_PLAYER;
    public Player toPlayer(){
        return new Player(USER_ID,ROOM_ID,cardStringToCards(CARDS));
    }


    public String getUSER_ID() {
        return USER_ID;
    }

    public int getROOM_ID() {
        return ROOM_ID;
    }

    public int getCARD_NUM() {
        return CARD_NUM;
    }

    public String getCARDS() {
        return CARDS;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public void setROOM_ID(int ROOM_ID) {
        this.ROOM_ID = ROOM_ID;
    }

    public void setCARD_NUM(int CARD_NUM) {
        this.CARD_NUM = CARD_NUM;
    }

    public void setCARDS(String CARDS) {
        this.CARDS = CARDS;
    }

    public PlayerModel(String USER_ID, int ROOM_ID, int CARD_NUM, String CARDS, String ITS_FIRST_BOTTOM_PLAYER,
                       String ITS_SECOND_BOTTOM_PLAYER, String ITS_THIRD_BOTTOM_PLAYER) {
        this.USER_ID = USER_ID;
        this.ROOM_ID = ROOM_ID;
        this.CARD_NUM = CARD_NUM;
        this.CARDS = CARDS;
        this.ITS_FIRST_BOTTOM_PLAYER = ITS_FIRST_BOTTOM_PLAYER;
        this.ITS_SECOND_BOTTOM_PLAYER = ITS_SECOND_BOTTOM_PLAYER;
        this.ITS_THIRD_BOTTOM_PLAYER = ITS_THIRD_BOTTOM_PLAYER;
    }

    public PlayerModel(String USER_ID, int ROOM_ID) {
        this.USER_ID = USER_ID;
        this.ROOM_ID = ROOM_ID;
    }

    public PlayerModel(Player player){
        this.USER_ID=player.getUSER_ID();
        this.ROOM_ID= player.getROOM_ID();
        this.CARD_NUM=player.getCardsNum();
        this.CARDS=cardGroupToString(player.getOwnCardGroup());
    }

    public String getITS_FIRST_BOTTOM_PLAYER() {
        return ITS_FIRST_BOTTOM_PLAYER;
    }

    public String getITS_SECOND_BOTTOM_PLAYER() {
        return ITS_SECOND_BOTTOM_PLAYER;
    }

    public String getITS_THIRD_BOTTOM_PLAYER() {
        return ITS_THIRD_BOTTOM_PLAYER;
    }

    public void setITS_FIRST_BOTTOM_PLAYER(String ITS_FIRST_BOTTOM_PLAYER) {
        this.ITS_FIRST_BOTTOM_PLAYER = ITS_FIRST_BOTTOM_PLAYER;
    }

    public void setITS_SECOND_BOTTOM_PLAYER(String ITS_SECOND_BOTTOM_PLAYER) {
        this.ITS_SECOND_BOTTOM_PLAYER = ITS_SECOND_BOTTOM_PLAYER;
    }

    public void setITS_THIRD_BOTTOM_PLAYER(String ITS_THIRD_BOTTOM_PLAYER) {
        this.ITS_THIRD_BOTTOM_PLAYER = ITS_THIRD_BOTTOM_PLAYER;
    }

    public void setBottomPlayers(Vector<String> order){
        int p=-1;
        for(int i=0;i<4;i++){
            if (order.get(i).equals(USER_ID)){
                p=i;
            }
        }
        if(p!=-1) {
            p = (p + 1) % 4;
            ITS_FIRST_BOTTOM_PLAYER = order.get(p);
            p = (p + 1) % 4;
            ITS_SECOND_BOTTOM_PLAYER=order.get(p);
            p = (p + 1) % 4;
            ITS_THIRD_BOTTOM_PLAYER=order.get(p);
        }
    }

    public Vector<String> getItsOrderStrings(){
        Vector<String> t= new Vector<>();
        t.add(USER_ID);
        t.add(ITS_FIRST_BOTTOM_PLAYER);
        t.add(ITS_SECOND_BOTTOM_PLAYER);
        t.add(ITS_THIRD_BOTTOM_PLAYER);
        return t;
    }
}
