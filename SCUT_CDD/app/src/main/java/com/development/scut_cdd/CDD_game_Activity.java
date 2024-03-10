package com.development.scut_cdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;


/** <p>游戏控制器</p>*/
public class CDD_game_Activity extends AppCompatActivity {

    /** <p>声音池</p>*/
    SoundPool soundPool;
    /** <p>声音池中声音ID与自定义声音ID的Map</p>*/
    HashMap<Integer,Integer> soundPoolMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cdd_game);
        //设置全屏显示
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //强制横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);//游戏过程中只允许多媒体音量
    }


    public  void goToGameView(){

    }

    /**
     * 描述:初始化音量
     * @author 叶达杭
     * @return void
    */
    public void initSound(){
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(attributes)
                .build();
        soundPoolMap = new HashMap<>();
        //在此添加声音资源
        soundPoolMap.put(1,soundPool.load(this,R.raw.man_1,1));//男人 出牌A 发音
    }
    /**
     * 描述:播放声音
     * @author 叶达杭
     * @param sound
     * @param loop
     * @return void
    */
    public void playSound(int sound, int loop){
        AudioManager mgr =(AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent/streamVolumeMax;
            soundPool.play(
                    soundPoolMap.get(sound),   //声音资源id
                    volume,    //左声道音量
                    volume,     //右声道音量
                    1,    //优先级
                    loop,  //循环次数 -1表示永远
                    0.5f   //回放速度0.5f ~2.0f 0.5f表示播放速度减半，2.0f表示播放速度加倍，1.0f表示正常速度。
            );
    }
}



