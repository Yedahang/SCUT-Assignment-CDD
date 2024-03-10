//package com.development.scut_cdd;
//
//import android.content.Context;
//import android.graphics.Rect;
//import android.util.AttributeSet;
//import android.view.Gravity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.LinearLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HandCardArea extends LinearLayout {
//    private float mLastX;
//    private float mLastY;
//    private boolean mIsScrolling;
//    private List<CardPrefab> mSelectedCards = new ArrayList<>();
//
//    public HandCardArea(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        setOrientation(HORIZONTAL);
//        setGravity(Gravity.CENTER_VERTICAL);
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        mLastX = event.getX();
//                        mLastY = event.getY();
//                        mIsScrolling = false;
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        float x = event.getX();
//                        float y = event.getY();
//                        float deltaX = x - mLastX;
//                        float deltaY = y - mLastY;
//                        if (Math.abs(deltaX) > 10 || Math.abs(deltaY) > 10) {
//                            mIsScrolling = true;
//                            // 滑动选牌
////                            selectCardsInRange(mLastX, mLastY, x, y);
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        if (!mIsScrolling) {
//                            // 点选牌
//                            selectCard(event.getX(), event.getY());
//                        }
//                        break;
//                }
//                return true;
//            }
//        });
//    }
//
//    private void selectCard(float x, float y) {
//        for (int i = 0; i < getChildCount(); i++) {
//            View child = getChildAt(i);
//            if (child instanceof CardPrefab) {
//                CardPrefab card = (CardPrefab) child;
//                if (card.getGlobalVisibleRect(new Rect())) {
//                    if (card.getCardValue() >= 0 && card.getCardValue() <= 12) {
//                        if (x >= card.getLeft() && x <= card.getRight() && y >= card.getTop() && y <= card.getBottom()) {
//                            card.setCardSelected(!card.isCardSelected());
//                            if (card.isCardSelected()) {
//                                mSelectedCards.add(card);
//                            } else {
//                                mSelectedCards.remove(card);
//                            }
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
//
////    private void selectCardsInRange(float startX, float startY, float endX, float endY) {
////        for (int i = 0; i < getChildCount(); i++) {
////            View child = getChildAt(i);
////            if (child instanceof CardPrefab) {
////                CardPrefab card = (CardPrefab) child;
////                if (card.getGlobalVisibleRect(new Rect())) {
////                    if (card.getCardValue() >= 0 && card.getCardValue() <= 12) {
////                        if (isPoint InRect(startX, startY, card.getLeft(), card.getTop(), card.getRight(), card.getBottom())
////                                || isPointInRect(endX, endY, card.getLeft(), card.getTop(), card.getRight(), card.getBottom())) {
////                            card.setCardSelected(true);
////                            if (!mSelectedCards.contains(card)) {
////                                mSelectedCards.add(card);
////                            }
////                        } else {
////                            card.setCardSelected(false);
////                            mSelectedCards.remove(card);
////                        }
////                    }
////                }
////            }
////        }
////    }
//
//    private boolean isPointInRect(float x, float y, int left, int top, int right, int bottom) {
//        return x >= left && x <= right && y >= top && y <= bottom;
//    }
//
//    public List<CardPrefab> getSelectedCards() {
//        return mSelectedCards;
//    }
//
//    public void clearSelectedCards() {
//        for (CardPrefab card : mSelectedCards) {
//            card.setCardSelected(false);
//        }
//        mSelectedCards.clear();
//    }
//}
//
