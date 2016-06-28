package com.fubang.fubangzhibo.fragment;


import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.fubangzhibo.AppConstant;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.adapters.HomeTitleAdapter;
import com.fubang.fubangzhibo.entities.UserEntity;
import com.fubang.fubangzhibo.ui.MessageActivity_;
import com.fubang.fubangzhibo.ui.PersonActivity_;
import com.fubang.fubangzhibo.ui.PrivilegeActivity_;
import com.fubang.fubangzhibo.ui.RechargeActivity_;
import com.fubang.fubangzhibo.ui.SettingActivity_;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_my)
public class MyFragment extends BaseFragment {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @ViewById(R.id.my_viewpage)
    ViewPager viewPager;
    @ViewById(R.id.my_tablayout)
    TabLayout tabLayout;
    @ViewById(R.id.my_user_headicon)
    SimpleDraweeView userIcon;
    @ViewById(R.id.my_username)
    TextView userName;
    @ViewById(R.id.my_recharge)
    LinearLayout myRecharge;
    @ViewById(R.id.my_message)
    LinearLayout myMessage;
    @ViewById(R.id.my_privileges)
    LinearLayout myPrivileges;
    @ViewById(R.id.my_setting)
    LinearLayout mySetting;
    @ViewById(R.id.user_info_message)
    LinearLayout personLL;
    @Override
    public void before() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        for (int i = 0; i < AppConstant.MY_TYPE_TITLE.length; i++) {
            titles.add(AppConstant.MY_TYPE_TITLE[i]);
        }
        fragments.add(MyItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(0)).build());
        fragments.add(MyItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(1)).build());
        fragments.add(MyItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(2)).build());
        myRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RechargeActivity_.intent(getContext()).get());
            }
        });
        myMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MessageActivity_.intent(getContext()).get());
            }
        });
        myPrivileges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PrivilegeActivity_.intent(getContext()).get());
            }
        });
        mySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SettingActivity_.intent(getContext()).get());
            }
        });
        personLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PersonActivity_.intent(getContext()).get());
            }
        });
    }

    @Override
    public void initData() {
        userIcon.setImageURI(Uri.parse(StartUtil.getUserIcon(getContext())));
        userName.setText(StartUtil.getUserName(getContext()));
        HomeTitleAdapter adapter = new HomeTitleAdapter(getChildFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Subscriber(tag = "UserInfo")
    public void getUserInfo(UserEntity userEntity){
        Log.d("123",userEntity.getUserIcon()+userEntity.getUserName());
        userIcon.setImageURI(Uri.parse(userEntity.getUserIcon()));
        userName.setText(userEntity.getUserName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
