package Model.ServiceImpl.GameRule;

import static Model.Entity.Suit.SPADES;
import static Model.Entity.Value.TWO;

import java.util.Vector;

import Model.Entity.Card;
import Model.ServiceImpl.GameTurnServiceImpl;

public class ScoringRule
{
    public int calculateCardPointOfOnePlayer(Vector<Card> handCard)
    {
        int handCardNum = handCard.size();
        int handCardPoint = handCardNum;
        if(handCardNum < 8)
            return handCardPoint;
        else if(handCardNum < 10)
            handCardPoint = handCardNum*2;
        else if(handCardNum < 13)
            handCardPoint = handCardNum*3;
        else
            handCardPoint = handCardNum*4;

        for(int i=0;i<handCardNum;i++)
            if(handCard.elementAt(i).getValue() == TWO
                    && handCard.elementAt(i).getSuit() == SPADES)
                handCardPoint = handCardPoint*2;

        return handCardPoint;
    }

    public void calculateCardPointOfAllPlayers(GameTurnServiceImpl gameTurnServiceImpl)
    {
        gameTurnServiceImpl.setPlayer1_cardPoint(calculateCardPointOfOnePlayer(gameTurnServiceImpl.getPlayer1_handCards()));
        gameTurnServiceImpl.setPlayer2_cardPoint(calculateCardPointOfOnePlayer(gameTurnServiceImpl.getPlayer2_handCards()));
        gameTurnServiceImpl.setPlayer3_cardPoint(calculateCardPointOfOnePlayer(gameTurnServiceImpl.getPlayer3_handCards()));
        gameTurnServiceImpl.setPlayer4_cardPoint(calculateCardPointOfOnePlayer(gameTurnServiceImpl.getPlayer4_handCards()));
    }

    public void calculateScoreOfAllPlayers(GameTurnServiceImpl gameTurnServiceImpl)
    {
        int score = 0;
        score = gameTurnServiceImpl.getPlayer2_cardPoint() + gameTurnServiceImpl.getPlayer3_cardPoint() + gameTurnServiceImpl.getPlayer4_cardPoint()
                - gameTurnServiceImpl.getPlayer1_cardPoint()*3;
        gameTurnServiceImpl.setPlayer1_score(score);

        score = gameTurnServiceImpl.getPlayer1_cardPoint() + gameTurnServiceImpl.getPlayer3_cardPoint() + gameTurnServiceImpl.getPlayer4_cardPoint()
                - gameTurnServiceImpl.getPlayer2_cardPoint()*3;
        gameTurnServiceImpl.setPlayer2_score(score);

        score = gameTurnServiceImpl.getPlayer1_cardPoint() + gameTurnServiceImpl.getPlayer2_cardPoint() + gameTurnServiceImpl.getPlayer4_cardPoint()
                - gameTurnServiceImpl.getPlayer3_cardPoint()*3;
        gameTurnServiceImpl.setPlayer3_score(score);

        score = gameTurnServiceImpl.getPlayer1_cardPoint() + gameTurnServiceImpl.getPlayer2_cardPoint() + gameTurnServiceImpl.getPlayer3_cardPoint()
                - gameTurnServiceImpl.getPlayer4_cardPoint()*3;
        gameTurnServiceImpl.setPlayer4_score(score);
    }

}
