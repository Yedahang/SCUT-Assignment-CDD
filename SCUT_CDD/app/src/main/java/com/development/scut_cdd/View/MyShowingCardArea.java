package com.development.scut_cdd.View;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.development.scut_cdd.Config;
import com.development.scut_cdd.View.MyShowingCard;

import java.util.ArrayList;
import java.util.List;

import Model.Entity.Card;
import Model.Entity.CardGroup;
import Model.Entity.Suit;
import Model.Entity.Value;

/** <p>出牌区显示</p>*/
public class MyShowingCardArea extends RelativeLayout {

    private List<MyShowingCard> showingCardList = new ArrayList<>();

    public MyShowingCardArea(Context context) {
        super(context);
//        updateShowingCard();
    }

    public MyShowingCardArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyShowingCardArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyShowingCardArea(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

     public void updateShowingCard(List<Card> shownCards) {
        showingCardList.clear();
        for(Card c :shownCards){
            showingCardList.add(new MyShowingCard(getContext(),c));
        }
        for(int i=0;i<showingCardList.size();i++){
            addHandCardToView(i);
        }
    }
    public void updateShowingCard(CardGroup shownCards){

            List<Card> temp = new ArrayList<>();
            temp.addAll(shownCards.getCards());
            updateShowingCard(temp);

    }
    public void clearMyShowingCardArea(){
        this.removeAllViews();
    }

    private void addHandCardToView(int index) {
        int posX;
        if (index == 0) {
//            posX = Config.screenWidth / 3 - (showingCardList.size()) / 2 * 50;
            posX=0;
        } else {
            posX = (int) showingCardList.get(index - 1).getX() + 50;
        }

        showingCardList.get(index).setX(posX);//设置偏移量 在手牌取的起始位置
        RelativeLayout.LayoutParams para = new LayoutParams(
                showingCardList.get(index).getCardWidth(),
                showingCardList.get(index).getCardHeight()
        );
        para.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(showingCardList.get(index),para);
    }

    public List<MyShowingCard> getShowingCardList() {
        return showingCardList;
    }
}
