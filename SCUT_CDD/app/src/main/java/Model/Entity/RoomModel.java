package Model.Entity;

import java.util.Vector;

public class RoomModel {
    int ROOM_ID;

    int PLAYER_NUM;

    int CURRENT_PLAYER_INDEX;
    String IS_FIRST_ROUND;
    public static final String IsFirstRound="TRUE";
    public static final String NotFirstRound="FALSE";


    String Creator_id;
    String FIRST_SEAT_PLAYER_ID;
    String SECOND_SEAT_PLAYER_ID;
    String THIRD_SEAT_PLAYER_ID;
    String FORTH_SEAT_PLAYER_ID;

    //两种类型 robot person
    public static final String type_robot="robot";
    public static final String type_person="person";
    String FIRST_SEAT_TYPE;
    String SECOND_SEAT_TYPE;
    String THIRD_SEAT_TYPE;
    String FORTH_SEAT_TYPE;

    String PREVIOUS_PLAYER;
    String PREVIOUS_SHOWN_CARD;//上家出的牌

    String PREVIOUS_PLAYER_OPERATION;

    String CURRENT_PLAYER;
    String CURRENT_SHOWN_CARD;
    String CURRENT_PLAYER_OPERATION;

    public static final String OPERATION_PASS="pass";
    public static final String OPERATION_SHOW="show";
    public void updateTheTurnToNextPlayer(){
        CURRENT_PLAYER_INDEX =(CURRENT_PLAYER_INDEX +1)%4;
        switch (CURRENT_PLAYER_INDEX){
            case 0:
                CURRENT_PLAYER=FIRST_SEAT_PLAYER_ID;
                break;
            case 1:
                CURRENT_PLAYER=SECOND_SEAT_PLAYER_ID;
            case 2:
                CURRENT_PLAYER=THIRD_SEAT_PLAYER_ID;
            case 3:
                CURRENT_PLAYER=FORTH_SEAT_PLAYER_ID;
        }
         CURRENT_SHOWN_CARD=null;
    }
    public int getROOM_ID() {
        return ROOM_ID;
    }

    public String getCreator_id() {
        return Creator_id;
    }

    public String getFIRST_SEAT_PLAYER_ID() {
        return FIRST_SEAT_PLAYER_ID;
    }

    public String getSECOND_SEAT_PLAYER_ID() {
        return SECOND_SEAT_PLAYER_ID;
    }

    public String getTHIRD_SEAT_PLAYER_ID() {
        return THIRD_SEAT_PLAYER_ID;
    }

    public String getFORTH_SEAT_PLAYER_ID() {
        return FORTH_SEAT_PLAYER_ID;
    }

    public String getFIRST_SEAT_TYPE() {
        return FIRST_SEAT_TYPE;
    }

    public String getSECOND_SEAT_TYPE() {
        return SECOND_SEAT_TYPE;
    }

    public String getTHIRD_SEAT_TYPE() {
        return THIRD_SEAT_TYPE;
    }

    public String getFORTH_SEAT_TYPE() {
        return FORTH_SEAT_TYPE;
    }

    public void setROOM_ID(int ROOM_ID) {
        this.ROOM_ID = ROOM_ID;
    }

    public void setCreator_id(String creator_id) {
        Creator_id = creator_id;
    }

    public void setFIRST_SEAT_PLAYER_ID(String FIRST_SEAT_PLAYER_ID) {
        this.FIRST_SEAT_PLAYER_ID = FIRST_SEAT_PLAYER_ID;
    }

    public void setSECOND_SEAT_PLAYER_ID(String SECOND_SEAT_PLAYER_ID) {
        this.SECOND_SEAT_PLAYER_ID = SECOND_SEAT_PLAYER_ID;
    }

    public void setTHIRD_SEAT_PLAYER_ID(String THIRD_SEAT_PLAYER_ID) {
        this.THIRD_SEAT_PLAYER_ID = THIRD_SEAT_PLAYER_ID;
    }

    public void setFORTH_SEAT_PLAYER_ID(String FORTH_SEAT_PLAYER_ID) {
        this.FORTH_SEAT_PLAYER_ID = FORTH_SEAT_PLAYER_ID;
    }

    public void setFIRST_SEAT_TYPE(String FIRST_SEAT_TYPE) {
        this.FIRST_SEAT_TYPE = FIRST_SEAT_TYPE;
    }

    public void setSECOND_SEAT_TYPE(String SECOND_SEAT_TYPE) {
        this.SECOND_SEAT_TYPE = SECOND_SEAT_TYPE;
    }

    public void setPLAYER_NUM(int PLAYER_NUM) {
        this.PLAYER_NUM = PLAYER_NUM;
    }

    public int getPLAYER_NUM() {
        return PLAYER_NUM;
    }

    public void setTHIRD_SEAT_TYPE(String THIRD_SEAT_TYPE) {
        this.THIRD_SEAT_TYPE = THIRD_SEAT_TYPE;
    }

    public void setFORTH_SEAT_TYPE(String FORTH_SEAT_TYPE) {
        this.FORTH_SEAT_TYPE = FORTH_SEAT_TYPE;
    }

    public int getCURRENT_PLAYER_INDEX() {
        return CURRENT_PLAYER_INDEX;
    }

    public void setCURRENT_PLAYER_INDEX(int CURRENT_PLAYER_INDEX) {
        this.CURRENT_PLAYER_INDEX = CURRENT_PLAYER_INDEX;
    }

    public void setIS_FIRST_ROUND(String IS_FIRST_ROUND) {
        this.IS_FIRST_ROUND = IS_FIRST_ROUND;
    }

    public String getIS_FIRST_ROUND() {
        return IS_FIRST_ROUND;
    }

    public String getPREVIOUS_SHOWN_CARD() {
        return PREVIOUS_SHOWN_CARD;
    }

    public void setPREVIOUS_SHOWN_CARD(String PREVIOUS_SHOWN_CARD) {
        this.PREVIOUS_SHOWN_CARD = PREVIOUS_SHOWN_CARD;
    }

    public String getPREVIOUS_PLAYER() {
        return PREVIOUS_PLAYER;
    }

    public String getPREVIOUS_PLAYER_OPERATION() {
        return PREVIOUS_PLAYER_OPERATION;
    }

    public String getCURRENT_PLAYER() {
        return CURRENT_PLAYER;
    }

    public String getCURRENT_SHOWN_CARD() {
        return CURRENT_SHOWN_CARD;
    }

    public String getCURRENT_PLAYER_OPERATION() {
        return CURRENT_PLAYER_OPERATION;
    }

    public void setPREVIOUS_PLAYER(String PREVIOUS_PLAYER) {
        this.PREVIOUS_PLAYER = PREVIOUS_PLAYER;
    }

    public void setPREVIOUS_PLAYER_OPERATION(String PREVIOUS_PLAYER_OPERATION) {
        this.PREVIOUS_PLAYER_OPERATION = PREVIOUS_PLAYER_OPERATION;
    }

    public void setCURRENT_PLAYER(String CURRENT_PLAYER) {
        this.CURRENT_PLAYER = CURRENT_PLAYER;
    }

    public void setCURRENT_SHOWN_CARD(String CURRENT_SHOWN_CARD) {
        this.CURRENT_SHOWN_CARD = CURRENT_SHOWN_CARD;
    }

    public void setCURRENT_PLAYER_OPERATION(String CURRENT_PLAYER_OPERATION) {
        this.CURRENT_PLAYER_OPERATION = CURRENT_PLAYER_OPERATION;
    }

    public Vector<String> getOrderStrings(){
        Vector<String> t= new Vector<>();
        t.add(FIRST_SEAT_PLAYER_ID);
        t.add(SECOND_SEAT_PLAYER_ID);
        t.add(THIRD_SEAT_PLAYER_ID);
        t.add(FORTH_SEAT_PLAYER_ID);
        return t;
    }

    public RoomModel() {
    }

    public RoomModel(int ROOM_ID, int PLAYER_NUM, int CURRENT_PLAYER_INDEX, String IS_FIRST_ROUND,
                     String creator_id, String FIRST_SEAT_PLAYER_ID, String SECOND_SEAT_PLAYER_ID,
                     String THIRD_SEAT_PLAYER_ID, String FORTH_SEAT_PLAYER_ID, String FIRST_SEAT_TYPE,
                     String SECOND_SEAT_TYPE, String THIRD_SEAT_TYPE, String FORTH_SEAT_TYPE, String PREVIOUS_PLAYER,
                     String PREVIOUS_SHOWN_CARD, String PREVIOUS_PLAYER_OPERATION, String CURRENT_PLAYER,
                     String CURRENT_SHOWN_CARD, String CURRENT_PLAYER_OPERATION) {
        this.ROOM_ID = ROOM_ID;
        this.PLAYER_NUM = PLAYER_NUM;
        this.CURRENT_PLAYER_INDEX = CURRENT_PLAYER_INDEX;
        this.IS_FIRST_ROUND = IS_FIRST_ROUND;
        Creator_id = creator_id;
        this.FIRST_SEAT_PLAYER_ID = FIRST_SEAT_PLAYER_ID;
        this.SECOND_SEAT_PLAYER_ID = SECOND_SEAT_PLAYER_ID;
        this.THIRD_SEAT_PLAYER_ID = THIRD_SEAT_PLAYER_ID;
        this.FORTH_SEAT_PLAYER_ID = FORTH_SEAT_PLAYER_ID;
        this.FIRST_SEAT_TYPE = FIRST_SEAT_TYPE;
        this.SECOND_SEAT_TYPE = SECOND_SEAT_TYPE;
        this.THIRD_SEAT_TYPE = THIRD_SEAT_TYPE;
        this.FORTH_SEAT_TYPE = FORTH_SEAT_TYPE;
        this.PREVIOUS_PLAYER = PREVIOUS_PLAYER;
        this.PREVIOUS_SHOWN_CARD = PREVIOUS_SHOWN_CARD;
        this.PREVIOUS_PLAYER_OPERATION = PREVIOUS_PLAYER_OPERATION;
        this.CURRENT_PLAYER = CURRENT_PLAYER;
        this.CURRENT_SHOWN_CARD = CURRENT_SHOWN_CARD;
        this.CURRENT_PLAYER_OPERATION = CURRENT_PLAYER_OPERATION;
    }
}
