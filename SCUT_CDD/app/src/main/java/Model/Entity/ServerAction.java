package Model.Entity;

public class ServerAction {
    String ACTION_TYPE;
    public static final String ACTION_TYPE_Init_GAME="ACTION_TYPE_Init_GAME";
    public static final String ACTION_In_Game="ACTION_In_Game";

    public static final String ACTION_NEW_JION="ACTION_NEW_JION";
    public static final String ACTION_In_Single="ACTION_In_Single";
    public ServerAction(String ACTION_TYPE) {
        this.ACTION_TYPE = ACTION_TYPE;
    }

    public String getACTION_TYPE() {
        return ACTION_TYPE;
    }
}
