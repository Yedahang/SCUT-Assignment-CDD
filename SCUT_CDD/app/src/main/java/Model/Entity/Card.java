package Model.Entity;

import java.util.Random;

public class Card {

    private Suit suit;
    private Value value;


    //参数是牌的属性花色Suit,数字Value，对其进行初始化，返回值是初始化之后的牌
    public Card (Suit suit , Value value){
        this.suit=suit;
        this.value=value;
    }
    //参数为int整形变量，前一个参数1-4分别代表黑桃，红桃，梅花，方块，后一个参数从1-13分别代表ACE到KING

    public Card (int suit,int value){
        for (Suit s:Suit.values()){
            if(s.getValue()==suit){
                this.suit=s;
            }
        }
        for (Value v:Value.values()){
            if(v.getValue()==value){
                this.value=v;
            }
        }
    }
    //参数是牌的属性花色Suit和int整数变量，前一个参数分别代表黑桃，红桃，梅花，方块，后一个参数从1-13分别代表ACE到KING
    public Card (Suit suit,int value){
        for (Suit s:Suit.values()){
            if(s.getValue()==suit.getValue()){
                this.suit=s;
            }
        }
        for (Value v:Value.values()){
            if(v.getValue()==value){
                this.value=v;
            }
        }
    }

    public static Card getRandomCard() {
        Random random = new Random();
        int suit = random.nextInt(4) + 1;
        int value = random.nextInt(13) + 1;
        return new Card(suit, value);
    }


    //访问花色Suit的接口函数
    public Suit getSuit(){
        return this.suit;
    }


    //访问牌的点数Value的接口函数
    public Value getValue(){
        return this.value;
    }
    /**
     * 获得卡牌的点数
     * @author 叶达杭
     * @return int 点数
    */
    public int getNumber(){
        return this.value.getValue();
    }
    //返回这个牌的String类型表达
    public String toString(){

        //return suit.toString() + " " + value.toString();
        return CardToString();
    }


    public String ValueToInt()
    {
        switch(value)
        {
            case ACE:
                return "A";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
        }
        return null;
    }


    public String CardToString()
    {
        switch(suit)
        {
            case SPADES:
                return "♠ " + ValueToInt();
            case HEARTS:
                return "♥ " + ValueToInt();
            case CLUBS:
                return "♣ " + ValueToInt();
            case DIAMONDS:
                return "◇ " + ValueToInt();
        }
        return null;
    }

    public boolean isValueEqualTo(Card c){
        return this.value == c.getValue();
    }
    /**
     * 描述:比较牌的大小，先比较点数，再比较花色。
     * @author 叶达杭
     * @param card 与之比较的牌
     * @return int 比card大:1 和card一样:0 比card小:-1 |
    */
    public int compareTo(Card card){
        if(this.getValue()==Value.TWO){
            if(card.getValue()==Value.TWO) {
                //两者都是二时候 比较花色
                return suitComparator(card);
            }else{
                return  1;
            }
        }else if(this.getValue()==Value.ACE){
            //当前对象为A时
            if(card.getValue()==Value.TWO){
                return -1;
            }else if(card.getValue()==Value.ACE){
                //相同点数比较花色
                return suitComparator(card);
            }else{
                return 1;
            }
        }
        //比较时 当前对象this不是 A 2时
        if(card.getValue()==Value.ACE||card.getValue()==Value.TWO){
            return -1;
        }else {
            if (this.value.getValue() > card.getValue().getValue()) {
                return 1;
            } else if (this.value.getValue() == card.getValue().getValue()) {
                //点数相同比较花色
                return suitComparator(card);
            } else {
                return -1;
            }
        }
    }

    /**
     * 描述:比较花色大小
     * @author 叶达杭
     * @param c 被比较的牌
     * @return int 比被比较的牌c大:1 相同:0 比被比较的牌c小:-1
    */
    private int suitComparator(Card c){
        if(this.suit.getValue()<c.getSuit().getValue()){
            //当前对象的花色比card的大
            return 1;
        }else if(this.suit.getValue()==c.getSuit().getValue()){
            return 0;//两牌花色点数一样
        }else{
            return -1;
        }
    }


}
