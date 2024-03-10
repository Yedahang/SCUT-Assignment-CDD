package com.development.scut_cdd.View;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.development.scut_cdd.CardBoardActivity;
import com.development.scut_cdd.R;
import com.development.scut_cdd.UserState.MultiplyPlayingClientState;
import com.development.scut_cdd.UserState.MultiplyPlayingMainState;

import java.util.regex.Pattern;

public class InputIPView extends RelativeLayout
{
    private Context mContext;
    private LayoutInflater Inflater;
    private View inflate;
    private ImageButton button_server;
    private ImageButton button_client;
    private ImageButton button_start;
    private ImageButton button_accept;
    private ImageButton button_close;
    private TextView serverIP;
    private TextView askForIP;
    private EditText acceptIp;
    protected StartView startView;
    public String ip;

    private static AppCompatActivity appCompatActivity;

    public InputIPView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        Inflater = LayoutInflater.from(context);
        // 加载布局文件
        inflate = Inflater.inflate(R.layout.input_ip_view, this, true);

        init();
    }

    public static void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        InputIPView.appCompatActivity = appCompatActivity;
    }

    public void setServerIP(String ip) {serverIP.setText(ip);}

    public boolean isValidIPv4(String input) {
        String pattern = "^((\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
        return Pattern.matches(pattern, input);
    }

    protected void init()
    {
        serverIP = findViewById(R.id.serverIP);
        button_server = findViewById(R.id.asServer);
        button_client = findViewById(R.id.asClient);
        button_start = findViewById(R.id.startForWlan);
        button_accept = findViewById(R.id.accept);
        button_close = findViewById(R.id.closeInputIP);
        acceptIp = findViewById(R.id.acceptIP);
        askForIP = findViewById(R.id.askForIP);
        acceptIp.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15) });
        acceptIp.setInputType(InputType.TYPE_CLASS_NUMBER);
        acceptIp.setInputType(InputType.TYPE_CLASS_TEXT);

        // 主机 的监听器
        button_server.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                findViewById(R.id.promptForStart).setVisibility(View.VISIBLE);
//                serverIP.setVisibility(View.VISIBLE);
//                findViewById(R.id.startForWlan).setVisibility(View.VISIBLE);
                button_server.setVisibility(GONE);
                button_client.setVisibility(GONE);
                CardBoardActivity.getCardBoardActivity().changeStateTo(new MultiplyPlayingMainState());
            }
        });

        // 客户 的监听器
        button_client.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_server.setVisibility(GONE);
                button_client.setVisibility(GONE);

                findViewById(R.id.askForIP).setVisibility(View.VISIBLE);
                findViewById(R.id.prompt).setVisibility(View.VISIBLE);
                findViewById(R.id.acceptIP).setVisibility(View.VISIBLE);
                findViewById(R.id.accept).setVisibility(View.VISIBLE);
            }
        });

        // 确认 的监听器
        button_accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String unjudge_IP = acceptIp.getText().toString();
                if(!isValidIPv4(unjudge_IP))
                {
                    askForIP.setText("请输入正确的ip地址");
                    acceptIp.setText(""); // 清空输入框的内容
                    acceptIp.clearFocus(); // 清除输入框的焦点
                }
                else
                {
                    askForIP.setText("IP地址格式正确");
                    ip = unjudge_IP;
                    acceptIp.setText(ip);
                    acceptIp.clearFocus(); // 清除输入框的焦点
                    CardBoardActivity.getCardBoardActivity().changeStateTo(new MultiplyPlayingClientState(ip));
                }
            }
        });

        // 开始 的监听器
        button_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        // 关闭 的监听器
        button_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                InputIPView inputIPView = appCompatActivity.findViewById(R.id.inputIPView);
                inputIPView.setVisibility(GONE);
                button_server.setVisibility(VISIBLE);
                button_client.setVisibility(VISIBLE);
                button_start.setVisibility(GONE);
                button_accept.setVisibility(GONE);
                findViewById(R.id.askForIP).setVisibility(GONE);
                findViewById(R.id.prompt).setVisibility(GONE);
                findViewById(R.id.acceptIP).setVisibility(GONE);
                findViewById(R.id.promptForStart).setVisibility(GONE);
                findViewById(R.id.serverIP).setVisibility(GONE);

                startView = appCompatActivity.findViewById(R.id.startView);
                startView.button_logo.setEnabled(true);
                startView.button_return.setEnabled(true);
                startView.button_blueTooth.setEnabled(true);
                startView.button_wlan.setEnabled(true);
            }
        });
    }

    public String getIP(){return ip;}
}
