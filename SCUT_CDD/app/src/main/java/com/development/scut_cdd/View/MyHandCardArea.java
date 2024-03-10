package com.development.scut_cdd.View;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.development.scut_cdd.Config;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Controller.GameTurnCon;
import ControllerImpl.GameTurnConImpl;
import Model.Entity.Card;
import Model.Entity.CardGroup;
import Model.Entity.SelectedCardGroup;
import Model.Entity.Suit;
import Model.Entity.Value;

public class MyHandCardArea extends RelativeLayout {

    private ImageButton ibt_play;//出牌按钮
    float X_thisRelativeParent;//在父组件的坐标 这里是这个MyHandCardArea在屏幕上的坐标
    float Y_thisRelativeParent;

    float mLastX;
    float mLastY;

    private List<MyHandCard> myHandCardList = new ArrayList<>();

    public List<MyHandCard> getMyHandCardList() {
        return myHandCardList;
    }

    private List<Card> selectedCardSet = new ArrayList<>();

    public boolean canShow(){
        ibt_play.setEnabled(true);
        if(selectedCardSet.isEmpty()){
            return false;
        }else {
            return true;
        }
    };

    public MyHandCardArea(Context context) {
        super(context);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mLastX=event.getX();
                        mLastY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        //点击选牌
//                        canShow();
                        selectCard(event.getX(),event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                }
                return true;
            }
        });
    }
    public MyHandCardArea(Context context,ImageButton m_ibt_play) {
        super(context);
        ibt_play=m_ibt_play;
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mLastX=event.getX();
                        mLastY = event.getY();
                        //检查选的牌是否有效
                        break;
                    case MotionEvent.ACTION_UP:
                        //点击选牌
                        selectCard(event.getX(),event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:


                }
                return true;
            }
        });
    }
    public MyHandCardArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHandCardArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyHandCardArea(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void initHandCard(){
//        this.setBackgroundColor(Color.BLACK);


        //不能在onCreat里面调用 因为布局没有完全确定之前 获取到的值都是0
//         X_thisRelativeParent = this.getX();
//         Y_thisRelativeParent = this.getY();

    }

    public void setHandCard(CardGroup cg){
        for(Card c:cg.getCards()){
            myHandCardList.add(new MyHandCard(this.getContext(),c));
        }
    }

    public void addAllToView(){
        for(int i=0;i<myHandCardList.size();i++){
            addHandCardtoView(i);
        }
    }
    private void addHandCardtoView(int index){
        int posX;
        if(index==0){
            posX= Config.screenWidth/3-(myHandCardList.size())/2*50;
        }else {
            posX=(int)myHandCardList.get(index-1).getX()+Config.handCardGap;
        }

//        myHandCardList.get(index).setBackgroundColor(Color.BLACK);
//        myHandCardList.get(index).setAlpha(0.8f);

        myHandCardList.get(index).setX(posX);//设置偏移量 在手牌取的起始位置
        RelativeLayout.LayoutParams para = new LayoutParams(
                myHandCardList.get(index).getCardWidth(),
                myHandCardList.get(index).getCardHeight()
        );
        para.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(myHandCardList.get(index),para);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // 在这里获取位置
        X_thisRelativeParent = this.getX();
        Y_thisRelativeParent = this.getY();
    }
    public void selectCard(float x,float y) {
        //从左到右遍历
        for (int i=0;i<myHandCardList.size();i++) {
            MyHandCard c = myHandCardList.get(i);
            Rect currentRect =new Rect();
            Rect nextRect = new Rect();
            c.getGlobalVisibleRect(currentRect);//当前牌的整体点击区域范围
            if(i+1<myHandCardList.size()){
                //还有下一张牌
                myHandCardList.get(i+1).getGlobalVisibleRect(nextRect);
                //点击区域在当前遍历到的牌 且不在下一张牌的点击区域时
                if (currentRect.contains((int)(x+ X_thisRelativeParent),(int)(y+ Y_thisRelativeParent))
                        &&!nextRect.contains((int)(x+ X_thisRelativeParent),(int)(y+ Y_thisRelativeParent))) {
                      cardMove(c);
                      updateSelectedCards();
                }
            }else{
                //没有下一张牌  整张牌点击区域裸露
                if(currentRect.contains((int)(x+ X_thisRelativeParent),(int)(y+ Y_thisRelativeParent))) {
                    cardMove(c);
                    updateSelectedCards();
                }
            }

        }
    }


    private void cardMove(MyHandCard c){
        if (!c.isCardSelected()) {
            cardMoveUp(c);
            c.setCardSelected(true);
        } else {
            cardMoveDown(c);
            c.setCardSelected(false);
        }
    }
    private void cardMoveUp(MyHandCard c){
        ObjectAnimator animator = ObjectAnimator.ofFloat(c, "translationY", 0, -100);
        animator.setDuration(500); // 设置动画时长为1000毫秒
        animator.start(); // 启动动画
    }
    private void cardMoveDown(MyHandCard c){
        ObjectAnimator animator = ObjectAnimator.ofFloat(c, "translationY", -100, 0);
        animator.setDuration(500); // 设置动画时长为1000毫秒
        animator.start(); // 启动动画
    }

    private void updateSelectedCards(){
        selectedCardSet.clear();
         for(MyHandCard mc :myHandCardList){
             if(mc.isCardSelected()){
                 selectedCardSet.add(mc.getCard());
             }
         }
    }

    public List<Card> getSelectedCards() {
        return selectedCardSet;
    }
    public SelectedCardGroup getSelectedCardGroup() {
        SelectedCardGroup temp=new SelectedCardGroup();
        Vector<Card> v_Cards=new Vector<>();
        for(Card c:getSelectedCards()){
            v_Cards.add(c);
        }
        temp.setSelectedCardGroup(v_Cards);
        return temp;
    }
    public void removeShownCards(){
        for(MyHandCard mc: myHandCardList ){
//            if(mc.isCardSelected()){
                this.removeView(mc);
//                this.invalidate();
//            }
        }
        //减去被选中的牌
        Vector<MyHandCard> temp=new Vector<>();
        for(MyHandCard c:myHandCardList){
            if(c.isCardSelected()){
               temp.add(c);
            }
        }
        for(MyHandCard c:temp){
            myHandCardList.remove(c);
        }


        addAllToView();
//        invalidate();
    }


    public void updateHandCardArea(CardGroup c){
        for(MyHandCard mc: myHandCardList ){
//            if(mc.isCardSelected()){
                this.removeView(mc);
//                  this.invalidate();
//            }
        }
        myHandCardList.clear();
        setHandCard(c);
        addAllToView();
        invalidate();
    }
}
