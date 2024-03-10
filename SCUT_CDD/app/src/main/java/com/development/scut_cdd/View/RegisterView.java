package com.development.scut_cdd.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.development.scut_cdd.Config;
import com.development.scut_cdd.R;

public class RegisterView extends RelativeLayout {

    private Context mContext;
    private LayoutInflater Inflater;
    private View inflate;
    private TextView askForNickName;
    private EditText acceptNickName;
    private ImageButton button_registerAccept;
    private ImageButton button_close_register;
    public String tem_nickname;
    private StartView startView;
    RegisterView mine;

    private static AppCompatActivity appCompatActivity;

    public static void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        RegisterView.appCompatActivity = appCompatActivity;
    }

    public RegisterView(Context context)
    {
        super(context);
        mContext = context;
        init();
    }

    public RegisterView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        init();
    }

    public RegisterView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mContext = context;
        /*Inflater = LayoutInflater.from(context);
        // 加载布局文件
        inflate = Inflater.inflate(R.layout.start_view, this, true);*/
        init();
    }

    public void init()
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.register_view, this, true);
        mine=this;
        askForNickName = findViewById(R.id.prompt_register);
        acceptNickName = findViewById(R.id.input_nickName);

        // 确认 的监听器
        button_registerAccept = findViewById(R.id.accept_nickName);
        button_registerAccept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tem_nickname = acceptNickName.getText().toString();
                if(tem_nickname.isEmpty())
                {
                    askForNickName.setText("昵称不能为空");
                    acceptNickName.clearFocus(); // 清除输入框的焦点
                }
                else
                {
                    askForNickName.setText("注册/登录成功！");
                    acceptNickName.clearFocus(); // 清除输入框的焦点

                    acceptNickName.setEnabled(false);
                    button_close_register.setEnabled(true);
//                    mine.setVisibility(GONE);
                    Config.USER_ID=tem_nickname;
                }
            }
        });

        // 关闭 的监听器
        button_close_register = findViewById(R.id.closeRegister);
        button_close_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RegisterView registerView = appCompatActivity.findViewById(R.id.registerView);
                registerView.setVisibility(GONE);

                startView = appCompatActivity.findViewById(R.id.startView);
                startView.button_logo.setEnabled(true);
                startView.button_return.setEnabled(true);
                startView.button_blueTooth.setEnabled(true);
                startView.button_wlan.setEnabled(true);
            }
        });
    }

    public String getTem_nickname(){return tem_nickname;}
    public void restart()
    {
        askForNickName.setText("请输入昵称");
        acceptNickName.setText("");
        acceptNickName.setEnabled(true);
        button_registerAccept.setEnabled(true);
        button_close_register.setEnabled(false);
    }
}
