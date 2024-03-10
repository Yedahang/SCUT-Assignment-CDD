package Model.Entity;

import android.graphics.Bitmap;

import java.util.Vector;

public class Player {

    String USER_ID;
    int ROOM_ID;
    String Type;
    boolean isPass;
    public static final String TYPE_ROBOT="TYPE_ROBOT";
    public static final String TYPE_USER="TYPE_USER";

    private String nickName;
    private int order;
    private int score;
    private CardGroup ownCardGroup = new CardGroup();//玩家剩余的手牌
    private SelectedCardGroup selCards = new SelectedCardGroup();//玩家选中即将要出的牌


    private SelectedCardGroup shownCards =null;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int rank=0;

    public Title getTitles() {
        return titles;
    }

    private final Title titles =new Title(0,"平平无奇","未获得任何成就");

    public String getTitle() {
        return title;
    }

    private String title;

    public int getWinTimes() {
        return winTimes;
    }

    public void addWinTimes() {
        this.winTimes+=1;
    }

    private int winTimes=0;

    public int getLoseTimes() {
        return loseTimes;
    }

    public void addLoseTimes() {
        this.loseTimes +=1;
    }

    private int loseTimes=0;

    public Bitmap getHeadBitmap() {
        return headBitmap;
    }

    public void setHeadBitmap(Bitmap headBitmap) {
        this.headBitmap = headBitmap;
    }
    Bitmap headBitmap = null;
    public int winRate(){
        if(winTimes+loseTimes==0)
            return  0;
        else return winTimes/(winTimes+loseTimes);
    }


    public Player(String USER_ID, int ROOM_ID, CardGroup ownCardGroup) {
        this.USER_ID = USER_ID;
        this.ROOM_ID = ROOM_ID;
        this.ownCardGroup = ownCardGroup;
    }

    public Player(String USER_ID, String type) {
        this.USER_ID = USER_ID;
        Type = type;
    }

    public Player(int order, String name, int score) {
        this.nickName=name;
        this.order=order;
        this.score = score;
//        this.props = new Vector<>(4);
//        props.add(new Prop(1, "偷龙转凤", 300));
//        props.add(new Prop(2, "透视眼", 400));
//        props.add(new Prop(3, "移花接木", 500));
//        props.add(new Prop(4, "偷天换日", 600));
//        playmate =new Vector<>(3);
        title=this.titles.Id_Name(winTimes);
    }

    public void setShownCards(SelectedCardGroup shownCards) {
        this.shownCards = shownCards;
    }

    public SelectedCardGroup getShownCards() {
        return shownCards;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public int getROOM_ID() {
        return ROOM_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public void setROOM_ID(int ROOM_ID) {
        this.ROOM_ID = ROOM_ID;
    }

//    public void propInitial(CardboardServiceImpl board) {
//        for (Prop prop : props) {
//            if(prop.getId()==1||prop.getId()==2)
//            {
//                if (prop.canUse(score)&&board.getGameState()==-1) {
//                prop.setCanUse(true);}
//
//              }
//            else if(prop.getId()==3||prop.getId()==4)
//            {
//                if (prop.canUse(score)&&board.getGameState()==1) {
//                prop.setCanUse(true);
//                }
//
//            }
//        }
//    }

//    public void useProp(int id){
//        for (Prop prop:props) {
//            if(prop.getId()==id) {
//                switch(id) {
//                    case 1:
//                        prop.Tou_Long_Zhuan_Feng(this);
//                        break;
//                    case 2:
//                        prop.Tou_Shi_Yan(this);
//                        break;
//                    case 3:
//                        prop.Yin_Hua_Jie_Mu(this);
//                        break;
//                    case 4:
//                        prop.Tou_Tian_Huan_Ri(this);
//                        break;
//                    default:
//                        System.out.println("无效的道具ID");
//                }
//                return;
//            }
//        }
//    }

//    public Prop getPropById(int id) {
//        for (Prop prop : props) {
//            if (prop.getId() == id) {
//                return prop;
//            }
//        }
//        return null; // 如果未找到指定id的道具，则返回null
//    }




    private Card getSingleCard()
    {
        if(selCards.getCards().size()==1)
            return selCards.getCards().get(0);
        else if (selCards.getCards().size()!=1){
            System.out.println("该道具只能交换一张牌");
        }
        return null;
    }


    public String getNickName(){
        return nickName;
    }

    public void setNickName(String name){
        this.nickName=name;
    }

    public int getScore(){
        return score;
    }

    public CardGroup getOwnCardGroup(){
        return ownCardGroup;
    }//获得玩家手牌的接口函数
    public void displayHandCards(){
        System.out.println("玩家"+order+"的手牌为：");
        for(int i = 0; i< ownCardGroup.getCards().size(); i++)
        {
            Card card = ownCardGroup.getCards().elementAt(i);
            System.out.print("  (" + card.CardToString() + ")");
        }
        System.out.println();
    }

    public SelectedCardGroup getSelCards(){
        return selCards;
    }//获得玩家选择的牌的接口函数


//    public Vector<Player> getPlaymate() {
//        return playmate;
//    }
//    public void setPlaymate(Player player1,Player player2,Player player3){
//        this.playmate.add(player1);
//        this.playmate.add(player2);
//        this.playmate.add(player3);
//    }

    public int getCardsNum(){
        return this.ownCardGroup.getCards().size();
    }


    public String getType() {
        return Type;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setSelCards(SelectedCardGroup selCards) {
        this.selCards = selCards;
    }
}
