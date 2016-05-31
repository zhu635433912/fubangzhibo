package com.fubang.fubangzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fubang.fubangzhibo.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * fragment基类
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_base)
public class BaseFragment extends Fragment {

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
