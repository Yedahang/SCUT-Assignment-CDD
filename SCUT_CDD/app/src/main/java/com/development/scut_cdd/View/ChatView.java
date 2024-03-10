package com.development.scut_cdd.View;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.development.scut_cdd.R;

public class ChatView extends RelativeLayout
{
    private Context mContext;
    private LayoutInflater Inflater;
    private ImageButton chat_Button;
    private View inflate;
    private Button button_message1;
    private Button button_message2;
    private Button button_message3;
    private Button button_message4;
    private TextView messageFromPlayer1;
    private TextView messageFromPlayer2;
    private TextView messageFromPlayer3;
    private TextView messageFromPlayer4;
    private String messageSent;
    protected Handler handler = new Handler();


    public ChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        Inflater = LayoutInflater.from(context);

        // 加载布局文件
        inflate = Inflater.inflate(R.layout.chatting_view, this, true);
        init();
    }

    protected void init()
    {
        chat_Button = findViewById(R.id.chatting_button);
        button_message1 = findViewById(R.id.message1);
        button_message2 = findViewById(R.id.message2);
        button_message3 = findViewById(R.id.message3);
        button_message4 = findViewById(R.id.message4);
        messageFromPlayer2 = findViewById(R.id.messageFromPlayer2);
        messageFromPlayer3 = findViewById(R.id.messageFromPlayer3);
        messageFromPlayer4 = findViewById(R.id.messageFromPlayer4);

        chat_Button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                View chat = findViewById(R.id.chatting_background);
                if(chat.getVisibility() == View.VISIBLE)
                {
                    chat.setVisibility(View.GONE);
                    button_message1.setVisibility(GONE);
                    button_message2.setVisibility(GONE);
                    button_message3.setVisibility(GONE);
                    button_message4.setVisibility(GONE);
                }
                else {
                    chat.setVisibility(View.VISIBLE);
                    button_message1.setVisibility(VISIBLE);
                    button_message2.setVisibility(VISIBLE);
                    button_message3.setVisibility(VISIBLE);
                    button_message4.setVisibility(VISIBLE);
                }
            }
        });

        // 消息1 的监听器
        button_message1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                messageFromPlayer1 = findViewById(R.id.messageFromPlayer1);
                messageFromPlayer1.setText("大家好，很高兴见到各位！");
                messageSent = "大家好，很高兴见到各位！";
                messageFromPlayer1.setVisibility(View.VISIBLE);
                button_message1.setEnabled(false);
                button_message2.setEnabled(false);
                button_message3.setEnabled(false);
                button_message4.setEnabled(false);
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        messageFromPlayer1.setVisibility(View.GONE);
                        button_message1.setEnabled(true);
                        button_message2.setEnabled(true);
                        button_message3.setEnabled(true);
                        button_message4.setEnabled(true);
                    }
                }, 3000); // 3000表示延迟的时间，单位为毫秒
            }
        });

        // 消息2 的监听器
        button_message2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                messageFromPlayer1 = findViewById(R.id.messageFromPlayer1);
                messageFromPlayer1.setText("你是GG还是MM");
                messageSent = "你是GG还是MM";
                messageFromPlayer1.setVisibility(View.VISIBLE);
                button_message1.setEnabled(false);
                button_message2.setEnabled(false);
                button_message3.setEnabled(false);
                button_message4.setEnabled(false);
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        messageFromPlayer1.setVisibility(View.GONE);
                        button_message1.setEnabled(true);
                        button_message2.setEnabled(true);
                        button_message3.setEnabled(true);
                        button_message4.setEnabled(true);
                    }
                }, 3000); // 3000表示延迟的时间，单位为毫秒
            }
        });

        // 消息3 的监听器
        button_message3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                messageFromPlayer1 = findViewById(R.id.messageFromPlayer1);
                messageFromPlayer1.setText("快点吧，我等的花都谢了");
                messageSent = "快点吧，我等的花都谢了";
                messageFromPlayer1.setVisibility(View.VISIBLE);
                button_message1.setEnabled(false);
                button_message2.setEnabled(false);
                button_message3.setEnabled(false);
                button_message4.setEnabled(false);
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        messageFromPlayer1.setVisibility(View.GONE);
                        button_message1.setEnabled(true);
                        button_message2.setEnabled(true);
                        button_message3.setEnabled(true);
                        button_message4.setEnabled(true);
                    }
                }, 3000); // 3000表示延迟的时间，单位为毫秒
            }
        });

        // 消息4 的监听器
        button_message4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                messageFromPlayer1 = findViewById(R.id.messageFromPlayer1);
                messageFromPlayer1.setText("真牛！");
                messageSent = "真牛！";
                messageFromPlayer1.setVisibility(View.VISIBLE);
                button_message1.setEnabled(false);
                button_message2.setEnabled(false);
                button_message3.setEnabled(false);
                button_message4.setEnabled(false);
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        messageFromPlayer1.setVisibility(View.GONE);
                        button_message1.setEnabled(true);
                        button_message2.setEnabled(true);
                        button_message3.setEnabled(true);
                        button_message4.setEnabled(true);
                    }
                }, 3000); // 3000表示延迟的时间，单位为毫秒
            }
        });
    }

    public void setMessageFromPlayer1(String text)
    {
        messageFromPlayer1.setText(text);
        messageFromPlayer1.setVisibility(VISIBLE);
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                messageFromPlayer1.setVisibility(View.GONE);
            }
        }, 3000); // 3000表示延迟的时间，单位为毫秒
    }

    public void setMessageFromPlayer2(String text)
    {
        messageFromPlayer2.setText(text);
        messageFromPlayer2.setVisibility(VISIBLE);
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                messageFromPlayer2.setVisibility(View.GONE);
            }
        }, 3000); // 3000表示延迟的时间，单位为毫秒
    }

    public void setMessageFromPlayer3(String text)
    {
        messageFromPlayer3.setText(text);
        messageFromPlayer3.setVisibility(VISIBLE);
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                messageFromPlayer3.setVisibility(View.GONE);
            }
        }, 3000); // 3000表示延迟的时间，单位为毫秒
    }

    public void setMessageFromPlayer4(String text)
    {
        messageFromPlayer4.setText(text);
        messageFromPlayer4.setVisibility(VISIBLE);
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                messageFromPlayer4.setVisibility(View.GONE);
            }
        }, 3000); // 3000表示延迟的时间，单位为毫秒
    }
}
