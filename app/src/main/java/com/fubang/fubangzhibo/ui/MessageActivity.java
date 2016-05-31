package com.fubang.fubangzhibo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fubang.fubangzhibo.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_message)
public class MessageActivity extends BaseActivity {
    @ViewById(R.id.message_back_btn)
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
