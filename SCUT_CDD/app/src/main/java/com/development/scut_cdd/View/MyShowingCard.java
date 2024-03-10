package com.development.scut_cdd.View;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.development.scut_cdd.Config;

import Model.Entity.Card;

public class MyShowingCard extends CardView {


    public MyShowingCard(@NonNull Context context, Card c) {
        super(context,c, Config.ShowingCardAreaHeight);
    }

    public MyShowingCard(@NonNull Context context) {
        super(context);
    }

    public MyShowingCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyShowingCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
