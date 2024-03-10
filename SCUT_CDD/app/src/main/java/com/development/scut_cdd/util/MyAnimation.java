package com.development.scut_cdd.util;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class MyAnimation {

    /**
     * 描述:实现ImageView的放大后缩小 灵动的效果
     * @author 叶达杭
     * @param imageView
     * @return void
    */
    public static void zoomInAndOut(ImageView imageView){

       // 创建AnimatorSet
        AnimatorSet animatorSet = new AnimatorSet();

        // 创建ValueAnimator对象
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(1f, 2f, 1.5f);
        valueAnimator1.setDuration(1000);
        valueAnimator1.setInterpolator(new DecelerateInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                imageView.setScaleX(scale);
                imageView.setScaleY(scale);
            }
        });

        // 创建ValueAnimator对象
        ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(1f, 0.5f, 1f);
        valueAnimator2.setDuration(1000);
        valueAnimator2.setInterpolator(new DecelerateInterpolator());
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                imageView.setAlpha(alpha);//设置透明度
            }
        });

         // 将ValueAnimator对象添加到AnimatorSet中
        animatorSet.play(valueAnimator1).with(valueAnimator2);

        // 启动动画
        animatorSet.start();
    }
}
