package Model.Entity;
import java.util.Vector;

public class CardGroup {

    private Vector<Card> cards = new Vector<Card>(13);

    public Vector<Card> getCards(){
        return cards;
    }
    public void setCardGroup(Vector<Card> newCards){
        this.cards=newCards;
    }
    public String toString(){
        StringBuilder s=new StringBuilder();
        for(int i=0;i<cards.size();i++){
            s.append("(");
            s.append(cards.get(i).toString());
            s.append(")").append(" ");
        }
        return s.toString();
    }
    public void addCard(Card c){
        cards.add(c);
    }

    /**
     * 描述:把cg的Vector<Card> cards
     * 深拷贝覆盖到当前对象的cards
     * @author 殷飞
     * @param  cg :要拷贝的对象
     * @return void
     */
    public void copyOf(CardGroup cg){
        Vector<Card> newCards=new Vector<Card>();
        for(Card c:cg.getCards()){
            newCards.add(new Card(c.getSuit(), c.getValue()));
        }
        setCardGroup(newCards);
    }

    public boolean isContain(Value value, Suit suit){
        for (Card c:this.cards) {
            if(c.getValue()==value&&c.getSuit()==suit)
                return true;
        }
        return false;
    }

     /**
      * 描述:牌组排序,按照牌点顺序排列,牌点从大
      * 到小依次为：2 A K Q J i0 9 8 7 6 5 4 3
      * @author 叶达杭
      * @param upOrDown ==1：升序 ==-1：降序
      * @return void
     */
    public void cardGroupSortAsRule(int upOrDown) {
        if (upOrDown != 1 && upOrDown != -1) {
            System.out.println("upOrDown输入数值有误");
        }
        //冒泡排序
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).compareTo(cards.get(j)) == upOrDown) {
                    Card temp = cards.get(j);
                    cards.set(j, cards.get(i));
                    cards.set(i, temp);
                }
            }
        }
    }
    /**
     * 描述:牌组排序,按照数字顺序排列, A 2 3 4 5 6 7 8 9 10 J K Q
     * @author 叶达杭
     * @param upOrDown ==1：升序 ==-1：降序
     * @return void
     */
    public void cardGroupSortAsNumber(int upOrDown) {
        if (upOrDown != 1 && upOrDown != -1) {
            System.out.println("upOrDown输入数值有误");
        }
        //冒泡排序
        if(upOrDown==1) {
            for (int i = 0; i < cards.size(); i++) {
                for (int j = i + 1; j < cards.size(); j++) {
                    if (cards.get(i).getNumber() > cards.get(j).getNumber()) {
                        Card temp = cards.get(j);
                        cards.set(j, cards.get(i));
                        cards.set(i, temp);
                    }
                }
            }
        }else if(upOrDown==-1){
            for (int i = 0; i < cards.size(); i++) {
                for (int j = i + 1; j < cards.size(); j++) {
                    if (cards.get(i).getNumber() < cards.get(j).getNumber()) {
                        Card temp = cards.get(j);
                        cards.set(j, cards.get(i));
                        cards.set(i, temp);
                    }
                }
            }
        }
    }
}




