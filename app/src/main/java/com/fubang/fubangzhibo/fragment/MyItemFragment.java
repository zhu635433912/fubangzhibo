package com.fubang.fubangzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fubang.fubangzhibo.AppConstant;
import com.fubang.fubangzhibo.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_my_item)
public class MyItemFragment extends BaseFragment {
    @ViewById(R.id.my_item_text)
    TextView textView;

    private String type;
    @Override
    public void before() {
        type = getArguments().getString(AppConstant.HOME_TYPE);
    }

    @Override
    public void initView() {
        textView.setText(type);
    }
}
