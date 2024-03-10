package Database;

import java.util.Vector;

import Model.Entity.Player;
import Model.Entity.SelectedCardGroup;

public class LocalDB {

//    public static final String user_id = "112233";

    /** <p>对局中的四个payer</p>*/
    private static Vector<Player> playerVector= new Vector<>();

    private static String myNickName = "Admin";
    private static int currentPlayer =-1;
    private static boolean firstRoundFlag =false;

    private static SelectedCardGroup upperShownCardGroup =null;

    private static int passCount = 0;


    public static int getPassCount() {
        return passCount;
    }

    public static void setPassCount(int passCount) {
        LocalDB.passCount = passCount;
    }

    public static SelectedCardGroup getUpperShownCardGroup() {
        return upperShownCardGroup;
    }

    public static void setUpperShownCardGroup(SelectedCardGroup upperShownCardGroup) {
        LocalDB.upperShownCardGroup = upperShownCardGroup;
    }

    public static Vector<Player> getPlayerVector() {
        return playerVector;
    }

    public static int getCurrentPlayer() {
        return currentPlayer;
    }

    public static boolean isFirstRoundFlag() {
        return firstRoundFlag;
    }

    public static void setFirstRoundFlag(boolean firstRoundFlag) {
        LocalDB.firstRoundFlag = firstRoundFlag;
    }

    public static void setCurrentPlayer(int currentPlayer) {
        LocalDB.currentPlayer = currentPlayer;
    }


}
