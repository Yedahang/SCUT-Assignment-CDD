package com.development.scut_cdd.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PlayerHeadView extends View {
    private String playerName =null;
    private Bitmap playerAvatar = null;
    Paint paint = new Paint();

    public String getPlayerName() {
        return playerName;
    }

    public Bitmap getPlayerAvatar() {
        return playerAvatar;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        invalidate();
    }

    public void setPlayerAvatar(Bitmap playerAvatar) {
        this.playerAvatar = playerAvatar;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
//        绘制view内容
        if(playerAvatar != null){
            //绘制玩家头像
            canvas.drawBitmap(playerAvatar,0,0,null);
        }
        if(playerName!=null){

            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            canvas.drawText(playerName,0,playerAvatar.getHeight()+10,paint);

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 确定View的尺寸
    }
    public PlayerHeadView(Context context) {
        super(context);
    }

    public PlayerHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PlayerHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
