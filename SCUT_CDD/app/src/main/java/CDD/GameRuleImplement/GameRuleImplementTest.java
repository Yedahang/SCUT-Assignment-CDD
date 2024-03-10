package CDD.GameRuleImplement;

import java.util.Arrays;
import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.CardGroup;
import Model.Entity.SelectedCardGroup;
import Model.ServiceImpl.GameRule.CardsComparator;


public class GameRuleImplementTest {

    public static Vector<Card> generateCards(Vector<Integer> number){
        Vector<Card> cards=new Vector<>();
        for(Integer i :number){
            cards.add(new Card(i,0));
        }
        return cards;
    }
    public static Vector<Card> generateCards(Vector<Integer> number, Vector<Integer>suit){
        Vector<Card> cards=new Vector<>();
        for(int i=0;i<number.size();i++){
            cards.add(new Card(number.get(i),suit.get(i)));
        }
        return cards;
    }

    public static void main(String[] args){

        Vector<Integer> Numbers =new Vector<Integer>(Arrays.asList(3,3));
        Vector<Integer> Suits =new Vector<Integer>(Arrays.asList(1,3));
        CardGroup cg=new CardGroup();
        cg.setCardGroup(generateCards(Suits,Numbers));
        SelectedCardGroup scgUpper =new SelectedCardGroup();
        scgUpper.setSelectedCardGroup(cg);

        //bottom
        Vector<Integer> Numbers1 =new Vector<Integer>(Arrays.asList(4,4));
        Vector<Integer> Suits1 =new Vector<Integer>(Arrays.asList(1,2));
        CardGroup cg1=new CardGroup();
        cg.setCardGroup(generateCards(Suits1,Numbers1));
        SelectedCardGroup scgBottom =new SelectedCardGroup();
        scgBottom.setSelectedCardGroup(cg);

        CardsComparator comparator = new CardsComparator();
        System.out.println(comparator.SelectedCardGroupCompare(scgUpper,scgBottom));

//      System.out.println(sp.toPatternString(sp.recognizePattern(generateCards(Numbers,Suits))));
       // cg.cardGroupSort(1);
        //System.out.println(cg.toString());

    }
}
