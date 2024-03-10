package com.development.scut_cdd.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.development.scut_cdd.R;

public class SettlementScoreView extends RelativeLayout
{
    private Context mContext;
    private LayoutInflater Inflater;
    private View inflate;
    private ImageButton button_exit;
    private ImageButton button_next;

    public SettlementScoreView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        Inflater = LayoutInflater.from(context);
        // 加载布局文件
        inflate = Inflater.inflate(R.layout.settlement_view, this, true);

        init();
    }

    protected void init()
    {
        button_exit = findViewById(R.id.exit);
        button_next = findViewById(R.id.nextGame);

        // 退出 的监听器
        button_exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if(v.getId() == R.id.exit)
                    //finish();
            }
        });

        // 继续 的监听器
        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
            }
        });
    }

    public void displayResult(String nickname1,int cardPoint1,int scoreOfThisTurn1,int scoreOfAllTurn1,
                              String nickname2,int cardPoint2,int scoreOfThisTurn2,int scoreOfAllTurn2,
                              String nickname3,int cardPoint3,int scoreOfThisTurn3,int scoreOfAllTurn3,
                              String nickname4,int cardPoint4,int scoreOfThisTurn4,int scoreOfAllTurn4)
    {
        int[] arr = {scoreOfThisTurn1, scoreOfThisTurn2, scoreOfThisTurn3, scoreOfThisTurn4};
        int max = arr[0]; // 假设第一个元素为最大值

        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];

        displayPlayer1Information(nickname1,cardPoint1,scoreOfThisTurn1,scoreOfAllTurn1);
        displayPlayer2Information(nickname2,cardPoint2,scoreOfThisTurn2,scoreOfAllTurn2);
        displayPlayer3Information(nickname3,cardPoint3,scoreOfThisTurn3,scoreOfAllTurn3);
        displayPlayer4Information(nickname4,cardPoint4,scoreOfThisTurn4,scoreOfAllTurn4);

        if(max == scoreOfThisTurn1)
            findViewById(R.id.player1_background).setVisibility(View.VISIBLE);
        else if(max == scoreOfThisTurn2)
            findViewById(R.id.player2_background).setVisibility(View.VISIBLE);
        else if(max == scoreOfThisTurn3)
            findViewById(R.id.player3_background).setVisibility(View.VISIBLE);
        else if(max == scoreOfThisTurn4)
            findViewById(R.id.player4_background).setVisibility(View.VISIBLE);
    }

    protected void displayPlayer1Information(String nickname,int cardPoint,int scoreOfThisTurn,int scoreOfAllTurn)
    {
        TextView nickName_text = findViewById(R.id.player1);
        nickName_text.setText(nickname);
        TextView cardPoint_text = findViewById(R.id.player1_cardPoint);
        cardPoint_text.setText(String.valueOf(cardPoint));
        TextView scoreOfThisTurn_text = findViewById(R.id.player1_score);
        scoreOfThisTurn_text.setText(String.valueOf(scoreOfThisTurn));
        TextView scoreOfAllTurn_text = findViewById(R.id.player1_AllScore);
        scoreOfAllTurn_text.setText(String.valueOf(scoreOfAllTurn));
    }

    protected void displayPlayer2Information(String nickname,int cardPoint,int scoreOfThisTurn,int scoreOfAllTurn)
    {
        TextView nickName_text = findViewById(R.id.player2);
        nickName_text.setText(nickname);
        TextView cardPoint_text = findViewById(R.id.player2_cardPoint);
        cardPoint_text.setText(String.valueOf(cardPoint));
        TextView scoreOfThisTurn_text = findViewById(R.id.player2_score);
        scoreOfThisTurn_text.setText(String.valueOf(scoreOfThisTurn));
        TextView scoreOfAllTurn_text = findViewById(R.id.player2_AllScore);
        scoreOfAllTurn_text.setText(String.valueOf(scoreOfAllTurn));
    }

    protected void displayPlayer3Information(String nickname,int cardPoint,int scoreOfThisTurn,int scoreOfAllTurn)
    {
        TextView nickName_text = findViewById(R.id.player3);
        nickName_text.setText(nickname);
        TextView cardPoint_text = findViewById(R.id.player3_cardPoint);
        cardPoint_text.setText(String.valueOf(cardPoint));
        TextView scoreOfThisTurn_text = findViewById(R.id.player3_score);
        scoreOfThisTurn_text.setText(String.valueOf(scoreOfThisTurn));
        TextView scoreOfAllTurn_text = findViewById(R.id.player3_AllScore);
        scoreOfAllTurn_text.setText(String.valueOf(scoreOfAllTurn));
    }

    protected void displayPlayer4Information(String nickname,int cardPoint,int scoreOfThisTurn,int scoreOfAllTurn)
    {
        TextView nickName_text = findViewById(R.id.player4);
        nickName_text.setText(nickname);
        TextView cardPoint_text = findViewById(R.id.player4_cardPoint);
        cardPoint_text.setText(String.valueOf(cardPoint));
        TextView scoreOfThisTurn_text = findViewById(R.id.player4_score);
        scoreOfThisTurn_text.setText(String.valueOf(scoreOfThisTurn));
        TextView scoreOfAllTurn_text = findViewById(R.id.player4_AllScore);
        scoreOfAllTurn_text.setText(String.valueOf(scoreOfAllTurn));
    }
}
