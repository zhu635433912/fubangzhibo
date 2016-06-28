package com.fubang.fubangzhibo.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.fubang.fubangzhibo.MainActivity_;
import com.fubang.fubangzhibo.R;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 启动页面
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    @ViewById(R.id.splash_image)
    ImageView imageView;

    @Override
    public void initView() {
        super.initView();
        //创建启动夜间动画效果
        AlphaAnimation animation = new AlphaAnimation(1.0f,1.0f);
        animation.setDuration(15 * 100);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startIntent();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.setAnimation(animation);
        animation.start();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent();
            }
        });
    }
    private void startIntent() {
        if (StartUtil.isFirst(this)){
            startActivity(GuideActivity_.intent(this).get());
        }else if (!TextUtils.isEmpty(StartUtil.getUserId(this))){
            startActivity(MainActivity_.intent(this).get());
        }else {
            startActivity(LoginActivity_.intent(this).get());
        }
    }
}
