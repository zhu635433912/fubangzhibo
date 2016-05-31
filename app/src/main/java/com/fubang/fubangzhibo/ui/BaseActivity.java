package com.fubang.fubangzhibo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fubang.fubangzhibo.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_base)
public class BaseActivity extends AppCompatActivity {

    @AfterInject
    public void before(){

    }
    @AfterViews
    public final void init(){
        initView();
        initData();
    }

    public void initData() {

    }

    public void initView() {

    }

}
