package Model.Entity;


/** <p>封装用户的操作</p>*/
public class UserAction {
    String USER_ID;//用户ID
    String ACTION_TYPE;//行为
    String TYPE;//类型
    public static final String TYPE_ROBOT="TYPE_ROBOT";

    public static final String TYPE_USER="TYPE_USER";
    private SelectedCardGroup cardGroup;
    private String SHOWN_CARDS;

    public static final String ACTION_TYPE_NEW_A_GAME="ACTION_TYPE_NEW_A_GAME";//开始一局新游戏
    public static final String ACTION_TYPE_SHOW="ACTION_TYPE_SHOW";//出牌

    public static final String ACTION_TYPE_PASS="ACTION_TYPE_PASS";//跳过

    public static final String ACTION_TYPE_NO_ACTION="ACTION_TYPE_NO_ACTION";
    public static final String ACTION_TYPE_COME_INTO_ROOM="ACTION_TYPE_COME_INTO_ROOM";

    public static final String ACTION_TYPE_ADD_ROBOT="ACTION_TYPE_ADD_ROBOT";
    public static final String ACTION_TYPE_SINGLE_PLAYING="ACTION_TYPE_SINGLE_PLAYING";

    public String getUSER_ID() {
        return USER_ID;
    }

    public String getACTION_TYPE() {
        return ACTION_TYPE;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public void setACTION_TYPE(String ACTION_TYPE) {
        this.ACTION_TYPE = ACTION_TYPE;
    }

    public String getSHOWN_CARDS() {
        return SHOWN_CARDS;
    }

    public void setSHOWN_CARDS(String SHOWN_CARDS) {
        this.SHOWN_CARDS = SHOWN_CARDS;
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(SelectedCardGroup cardGroup) {
        this.cardGroup = cardGroup;
    }
}
