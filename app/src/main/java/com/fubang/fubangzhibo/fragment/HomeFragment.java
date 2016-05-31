package com.fubang.fubangzhibo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.fubang.fubangzhibo.AppConstant;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.adapters.HomeTitleAdapter;
import com.fubang.fubangzhibo.ui.SearchActivity;
import com.fubang.fubangzhibo.ui.SearchActivity_;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.Intents;

/**
 * 首页
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @ViewById(R.id.home_viewpager)
    ViewPager viewPager;
    @ViewById(R.id.home_tablayout)
    TabLayout tabLayout;
    @ViewById(R.id.main_head_icon)
    ImageView iconImage;
    @ViewById(R.id.main_head_scanner)
    ImageView scannerImage;
    @ViewById(R.id.main_head_search)
    ImageView searchImage;
    @ViewById(R.id.main_head_history)
    ImageView historyImage;

    @Override
    public void initView() {
        for (int i = 0; i < AppConstant.HOME_TYPE_TITLE.length; i++) {
            titles.add(AppConstant.HOME_TYPE_TITLE[i]);
        }
        fragments.add(HomeItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(0)).build());
        fragments.add(HomeItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(1)).build());
        fragments.add(HomeItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(2)).build());
        fragments.add(HomeItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(3)).build());
        scannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //google官方扫描二维码
//                new IntentIntegrator(getActivity()).initiateScan();
                //第三方的扫描
                startActivityForResult(new Intent(getContext(), CaptureActivity.class),0);
            }
        });
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SearchActivity_.intent(getContext()).get());
            }
        });
    }

    @Override
    public void initData() {
        HomeTitleAdapter adapter = new HomeTitleAdapter(getChildFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        String contents = intentResult.getContents();
        if (resultCode == getActivity().RESULT_OK){
            String scan_result = data.getStringExtra(Intents.Scan.RESULT);
            Toast.makeText(getContext(), scan_result, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "扫描失败", Toast.LENGTH_SHORT).show();
        }
    }
}
