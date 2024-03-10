package Database;

import android.provider.BaseColumns;

import Model.Entity.RoomModel;

public final class DatabaseContract {
    private DatabaseContract(){};


    /** <p>用户表</p>*/
    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_USER_NICKNAME = "user_nickname";
        public static final String COLUMN_USER_SCORE = "user_score";

        public static final String SQL_CREATE_ENTRIES=
        "CREATE TABLE "+UserEntry.TABLE_NAME+" ("+
        UserEntry.COLUMN_USER_ID+" INTEGER PRIMARY KEY,"+
        UserEntry.COLUMN_USER_NICKNAME +" TEXT,"+
        UserEntry.COLUMN_USER_SCORE +" TEXT)";

        public static final String SQL_DELETE_ENTRIES=
                "DROP TABLE IF EXISTS "+TABLE_NAME;
    }

    /** <p>游戏房间表</p>*/
    public static class GameRoomEntry implements BaseColumns{
        public static final String TABLE_NAME ="GameRoom";
        public static final String COLUMN_ROOM_ID ="RoomId";//房间id
        public static final String COLUMN_Creator_id ="CreatorId";//房间创建者id

        public static final String COLUMN_PLAYER_NUM="PlayerNum";//房间内人数
        public static final String COLUMN_FIRST_SEAT_PLAYER_ID = "FirstSeatPlayerId";//第一个座位的玩家id
        public static final String COLUMN_SECOND_SEAT_PLAYER_ID = "SecondSeatPlayerId";//第二个座位的玩家id
        public static final String COLUMN_THIRD_SEAT_PLAYER_ID = "ThirdSeatPlayerId";//第三个座位的玩家id
        public static final String COLUMN_FORTH_SEAT_PLAYER_ID = "ForthSeatPlayerId";//第四个座位的玩家id

        public static final String COLUMN_FIRST_SEAT_TYPE = "FirstSeatPlayerType";//第一个座位的参与者类型 玩家或机器人
        public static final String COLUMN_SECOND_SEAT_TYPE = "SecondSeatPlayerType";//第二个座位的参与者类型 玩家或机器人
        public static final String COLUMN_THIRD_SEAT_TYPE = "ThirdSeatPlayerType";//第三个座位的参与者类型 玩家或机器人
        public static final String COLUMN_FORTH_SEAT_TYPE = "ForthSeatPlayerType";//第四个座位的参与者类型 玩家或机器人

        public static final String COLUMN_CURRENT_PLAYER_INDEX ="CurrentPlayerIndex";


        public static final String COLUMN_PREVIOUS_PLAYER ="UpperPlayer";//当前Player的上一家
        public static final String COLUMN_PREVIOUS_SHOWN_CARD ="UpperShownCards";//上家出的牌

        public static final String COLUMN_PREVIOUS_PLAYER_OPERATION ="PreviousPlayerOperation";//上一家的操作


        public static final String COLUMN_CURRENT_PLAYER ="CurrentPlayer";//当前玩家

        public static final String COLUMN_CURRENT_SHOWN_CARD ="CurrentShownCards";//当前出的牌

        public static final String COLUMN_CURRENT_PLAYER_OPERATION ="CurrentPlayerOperation";//当前家的操作


        public static final String COLUMN_IS_FIRST_ROUND = "IsFirstRound";
        public static final String SQL_CREATE_ENTRIES=
                "CREATE TABLE "+TABLE_NAME+" ("+
                        _ID+" INTEGER PRIMARY KEY,"+
                        COLUMN_ROOM_ID+" INTEGER,"+
                        COLUMN_Creator_id+" TEXT,"+
                        COLUMN_PLAYER_NUM+" INTEGER,"+
                        COLUMN_CURRENT_PLAYER_INDEX +" INTEGER,"+
                        COLUMN_IS_FIRST_ROUND+" TEXT,"+
                        COLUMN_FIRST_SEAT_PLAYER_ID+" TEXT,"+
                        COLUMN_SECOND_SEAT_PLAYER_ID+" TEXT,"+
                        COLUMN_THIRD_SEAT_PLAYER_ID+" TEXT,"+
                        COLUMN_FORTH_SEAT_PLAYER_ID+" TEXT,"+
                        COLUMN_FIRST_SEAT_TYPE+" TEXT,"+
                        COLUMN_SECOND_SEAT_TYPE+" TEXT,"+
                        COLUMN_THIRD_SEAT_TYPE+" TEXT,"+
                        COLUMN_FORTH_SEAT_TYPE+" TEXT," +
                        COLUMN_PREVIOUS_SHOWN_CARD +" TEXT," +
                        COLUMN_PREVIOUS_PLAYER +" TEXT," +
                        COLUMN_PREVIOUS_PLAYER_OPERATION +" TEXT,"+
                        COLUMN_CURRENT_PLAYER +" TEXT,"+
                        COLUMN_CURRENT_SHOWN_CARD +" TEXT,"+
                        COLUMN_CURRENT_PLAYER_OPERATION +" TEXT)";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " +TABLE_NAME;

    }

    /** <p>玩家表</p>*/
    public static class PlayerEntry implements BaseColumns{
        public static final String TABLE_NAME ="Player";
        public static final String COLUMN_USER_ID = "UserId";
        public static final String COLUMN_ROOM_ID ="RoomId";//当前所在的房间
        public static final String COLUMN_HAND_CARD_NUM="HandCardNum";//手牌数量
        public static final String COLUMN_HAND_CARDS ="HandCard";//玩家手牌  A_1 牌点数_花色

        public static final String COLUMN_ITS_FIRST_BOTTOM_PLAYER="FirstBottomPlayer";//第一个下家 对应屏幕右方

        public static final String COLUMN_ITS_SECOND_BOTTOM_PLAYER="SecondBottomPlayer";//第二个下家 对应屏幕上方

        public static final String COLUMN_ITS_THIRD_BOTTOM_PLAYER="ThirdBottomPlayer";//第四个下家 对应屏幕左方

        public static final String SQL_CREATE_ENTRIES=
                "CREATE TABLE "+TABLE_NAME+" ("+
                        _ID+" INTEGER PRIMARY KEY,"+
                        COLUMN_USER_ID+" TEXT,"+
                        COLUMN_ROOM_ID+" INTEGER,"+
                        COLUMN_HAND_CARD_NUM+" INTEGER,"+
                        COLUMN_HAND_CARDS +" TEXT," +
                        COLUMN_ITS_FIRST_BOTTOM_PLAYER+" TEXT,"+
                        COLUMN_ITS_SECOND_BOTTOM_PLAYER+" TEXT,"+
                        COLUMN_ITS_THIRD_BOTTOM_PLAYER+" TEXT)";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " +TABLE_NAME;
    }

    public static void main(String[] args) {
        System.out.println(GameRoomEntry.SQL_CREATE_ENTRIES);
    }

}
