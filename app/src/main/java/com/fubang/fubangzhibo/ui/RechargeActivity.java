package com.fubang.fubangzhibo.ui;


import android.view.View;
import android.widget.ImageView;

import com.fubang.fubangzhibo.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 充值页面
 */
@EActivity(R.layout.activity_recharge)
public class RechargeActivity extends BaseActivity {
    @ViewById(R.id.recharge_back_btn)
    ImageView backImage;

    @Override
    public void initView() {
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
