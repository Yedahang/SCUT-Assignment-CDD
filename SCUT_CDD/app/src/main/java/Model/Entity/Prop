package Model.Entity;


import java.util.Scanner;
import java.util.Vector;

public class Prop {
    private int id;
    private String name;
    private int limitScore;
    private boolean canUse;

    public Prop(int id, String name, int limitScore) {
        this.id = id;
        this.name = name;
        this.limitScore = limitScore;
        this.canUse = false;
    }

    public void Tou_Long_Zhuan_Feng(Player player1) {
        Player player2 = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("使用道具偷龙转凤，用自己的一张牌换取其他玩家一张牌，请选择你想要换牌的玩家：");
        String str = scanner.nextLine(); // 获取输入的玩家姓名
        System.out.println("请输入你想要换的牌的顺序索引值（从1开始从左到右）：");
        int order1=scanner.nextInt();
        int order2=scanner.nextInt();
        scanner.nextLine(); // 读取换行符
        scanner.close();
        // 查找姓名匹配的玩家
        for (Player player : player1.getPlaymate()) {
            if (player.getNickName().equals(str)) {
                player2 = player;
                break;
            }
        }
        if (player2 != null) {
            Prop prop = player1.getPropById(1); // 获取偷龙转凤道具
            if (prop != null && prop.canUse(player1.getScore())) {
                //Card card1 = player1.getSingleCard();
                //Card card2 = player2.getSingleCard();
                Card card1 = player1.getPlayerCardGroup().getCards().elementAt(order1-1);
                System.out.println(card1.toString());
                Card card2 = player2.getPlayerCardGroup().getCards().elementAt(order2-1);
                System.out.println(card2.toString());
                player1.getPlayerCardGroup().getCards().remove(card1);
                player1.getPlayerCardGroup().getCards().add(order1-1,card2);
                player2.getPlayerCardGroup().getCards().remove(card2);
                player2.getPlayerCardGroup().getCards().add(order2-1,card1);

                prop.setCanUse(false); // 标记道具已使用过
            }
        } else {
            System.out.println("未找到该玩家，请重新选择");
        }
    }

    public void Tou_Shi_Yan(Player player1) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("使用道具透视眼，请选择要透视的玩家：");
        String str = scanner.nextLine(); // 获取输入的玩家姓名
        scanner.close();
        // 查找姓名匹配的玩家
        Player player2 = null;
        for (Player player : player1.getPlaymate()) {
            if (player.getNickName().equals(str)) {
                player2 = player;
                break;
            }
        }
        if (player2 != null) {
            Prop prop = player1.getPropById(2); // 获取透视眼道具
            if (prop != null && prop.canUse(player1.getScore())) {
                CardGroup cards = player2.getPlayerCardGroup();
                System.out.println(player2.getNickName() + "的牌为：" + cards.toString());
                prop.setCanUse(false); // 标记道具已使用过
            }
        } else {
            System.out.println("未找到该玩家，请重新选择");
        }
    }

    public void Yin_Hua_Jie_Mu(Player player1) {
        Prop prop = player1.getPropById(3); // 获取移花接木道具
        System.out.println("使用道具移花接木，将手中的三张牌换成其他随机的牌：");
        if (prop != null && prop.canUse(player1.getScore())) {
            Vector<Card> cards = player1.getPlayerCardGroup().getCards();
            if (cards.size() >= 3) {
                Vector<Card> extendCards=new Vector<>(3);
                for (int i = 0; i < 3; i++) {
                   extendCards.add(Card.getRandomCard());
                }
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入你想要换的牌的顺序索引值（从1开始从左到右）：");
                int order1=scanner.nextInt();
                int order2=scanner.nextInt();
                int order3=scanner.nextInt();
                cards.remove(order1-1);cards.remove(order2-2);cards.remove(order3-3);
               // cards.remove(player1.getSelCards());
                cards.addAll(extendCards);
                prop.setCanUse(false); // 标记道具已使用过
            } else {
                System.out.println("手牌不足，无法使用该道具");
            }
        }
    }

    public void Tou_Tian_Huan_Ri(Player player1) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("使用道具偷天换日，请选择您指定的牌(前一个参数1-4分别代表黑桃，红桃，梅花，方块，后一个参数从1-13分别代表ACE到KING)：");
        int suit =scanner.nextInt();
        int value=scanner.nextInt();
        Card newCard=new Card(suit,value);
        System.out.println("请输入你想要换的牌的顺序索引值（从1开始从左到右）：");
        int order1=scanner.nextInt();
        //Card card = player1.getSingleCard(); // 获取要替换的牌
        scanner.close();
        Prop prop = player1.getPropById(4); // 获取偷天换日道具
        if (prop != null && prop.canUse(player1.getScore())) {
            Vector<Card> cards = player1.getPlayerCardGroup().getCards();
            if (order1<cards.size()) {

                cards.set(order1-1, newCard);
                System.out.println("替换成功，新的牌为：" + newCard);
                prop.setCanUse(false); // 标记道具已使用过
            } else {
                System.out.println("手牌中不存在该牌，无法使用该道具");
            }
        }
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLimitScore() {
        return limitScore;
    }

    public boolean canUse(int score) {
        return score >= limitScore && !canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }



}

