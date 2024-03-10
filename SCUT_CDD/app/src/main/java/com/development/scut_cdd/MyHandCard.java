package com.development.scut_cdd;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyHandCard extends androidx.appcompat.widget.AppCompatImageView {


    private int cardWidth;
    private int cardHeight;

    public int getCardWidth() {
        return cardWidth;
    }

    public int getCardHeight() {
        return cardHeight;
    }

    private boolean isSelected = false;


    public void init(){

    }
    public MyHandCard(@NonNull Context context) {
        super(context);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.poker_0, options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        float wToH=(float) imageWidth/imageHeight;//图像的  宽度/高度  用来等比例缩放
        cardHeight = Config.handCardAreaHeight-Config.handCardAreaUpBlankHeight;
        cardWidth = (int)( cardHeight*wToH);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();

        //初始化单张HandCard ImageView的大小
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(cardWidth,cardHeight);
        } else {
            layoutParams.width = 100;
            layoutParams.height = 100;
        }
        setLayoutParams(layoutParams);

        setImageResource(R.drawable.poker_0);
        setScaleType(ScaleType.CENTER_CROP);
//        setBackgroundColor(Color.RED);

    }

    public MyHandCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHandCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
