package com.development.scut_cdd.View;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.development.scut_cdd.R;

import Model.Entity.Card;

public class CardView extends androidx.appcompat.widget.AppCompatImageView{
    private Card card=null;

    private int cardWidth;

    private int cardHeight;

    public Card getCard() {
        return card;
    }

    public int getCardWidth() {
        return cardWidth;
    }

    public int getCardHeight() {
        return cardHeight;
    }

    private void initImage(){
        //背面
        if(card ==null){
            setImageResource(R.drawable.poker_back);
            return;
        }
        switch (card.getValue()){
            case THREE:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_0);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_1);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_2);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_3);
                        break;
                }
                break;
            case FOUR:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_4);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_5);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_6);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_7);
                        break;
                }
                break;
            case FIVE:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_8);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_9);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_10);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_11);
                        break;
                }
                break;
            case SIX:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_12);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_13);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_14);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_15);
                        break;
                }
                break;
            case SEVEN:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_16);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_17);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_18);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_19);
                        break;
                }
                break;
            case EIGHT:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_20);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_21);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_22);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_23);
                        break;
                }
                break;
            case NINE:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_24);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_25);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_26);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_27);
                        break;
                }
                break;
            case TEN:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_28);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_29);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_30);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_31);
                        break;
                }
                break;
            case JACK:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_32);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_33);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_34);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_35);
                        break;
                }
                break;
            case QUEEN:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_36);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_37);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_38);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_39);
                        break;
                }
                break;
            case KING:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_40);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_41);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_42);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_43);
                        break;
                }
                break;
            case ACE:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_44);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_45);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_46);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_47);
                        break;
                }
                break;
            case TWO:
                switch (card.getSuit()){
                    case SPADES:
                        setImageResource(R.drawable.poker_48);
                        break;
                    case HEARTS:
                        setImageResource(R.drawable.poker_49);
                        break;
                    case CLUBS:
                        setImageResource(R.drawable.poker_50);
                        break;
                    case DIAMONDS:
                        setImageResource(R.drawable.poker_51);
                        break;
                }
                break;
        }

    }


    /**
     * 描述:初始化卡牌视图大小 将卡牌png等比例缩放
     * @author 叶达杭
     * @return void 添加说明
    */
    private void initViewSize(int targetHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //因为这里每张卡牌图片大小都一样 所以随便弄一个卡牌
        BitmapFactory.decodeResource(getResources(),R.drawable.poker_0 , options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        float wToH=(float) imageWidth/imageHeight;//图像的  宽度/高度  用来等比例缩放
        cardHeight = targetHeight;
        cardWidth = (int)( targetHeight*wToH);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();

        //初始化单张HandCard ImageView的大小
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(cardWidth,cardHeight);
        } else {
            layoutParams.width = cardWidth;
            layoutParams.height = cardHeight;
        }
        setLayoutParams(layoutParams);
        setScaleType(ScaleType.CENTER_CROP);
    }

    public CardView(@NonNull Context context) {
        super(context);
    }

    public CardView(@NonNull Context context,Card c,int targetHeight) {
        super(context);
        this.card = c;
        initImage();
        initViewSize(targetHeight);
    }

    public CardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
