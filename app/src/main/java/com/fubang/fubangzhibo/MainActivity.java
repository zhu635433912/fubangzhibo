package com.fubang.fubangzhibo;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.RadioGroup;

import com.fubang.fubangzhibo.fragment.FollowFragment_;
import com.fubang.fubangzhibo.fragment.HomeFragment_;
import com.fubang.fubangzhibo.fragment.LiveFragment_;
import com.fubang.fubangzhibo.fragment.MyFragment_;
import com.fubang.fubangzhibo.fragment.UserFragment_;
import com.fubang.fubangzhibo.ui.BaseActivity;
import com.zhuyunjian.library.FragmentTabUtils;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById(R.id.main_bottom_radiogp)
    RadioGroup radioGroup;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void initView() {
        fragments.add(HomeFragment_.builder().build());
        fragments.add(LiveFragment_.builder().build());
        fragments.add(FollowFragment_.builder().build());
        fragments.add(MyFragment_.builder().build());
        new FragmentTabUtils(this,getSupportFragmentManager(),radioGroup,fragments,R.id.main_contaner);
    }

    @Override
    public void initData() {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        Log.d("123",ip);
    }
    private String intToIp(int i) {
        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }
}
