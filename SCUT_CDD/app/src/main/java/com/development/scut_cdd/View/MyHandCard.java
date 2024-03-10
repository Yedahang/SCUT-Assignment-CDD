package com.development.scut_cdd.View;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.development.scut_cdd.Config;

import Model.Entity.Card;

public class MyHandCard extends CardView{
    private boolean isCardSelected = false;

    public boolean isCardSelected() {
        return isCardSelected;
    }

    public void setCardSelected(boolean cardSelected) {
        isCardSelected = cardSelected;
    }

    public MyHandCard(@NonNull Context context, Card c) {
        super(context,c, Config.handCardAreaHeight-Config.handCardAreaUpBlankHeight);
    }

    public MyHandCard(@NonNull Context context) {
        super(context);
    }

    public MyHandCard(@NonNull Context context, Card c, int targetHeight) {
        super(context, c, targetHeight);
    }

    public MyHandCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHandCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
