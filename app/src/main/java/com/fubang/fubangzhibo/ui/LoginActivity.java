package com.fubang.fubangzhibo.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.fubangzhibo.MainActivity_;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.entities.UserEntity;
import com.xlg.android.LoginChannel;
import com.xlg.android.LoginHandler;
import com.xlg.android.protocol.LogonError;
import com.xlg.android.protocol.LogonResponse;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import sample.login.LoginMain;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener {
    @ViewById(R.id.login_qq_image)
    ImageView  imageView;
    @ViewById(R.id.login_username)
    EditText userNameEdit;
    @ViewById(R.id.login_password)
    EditText userPswEdit;
    @ViewById(R.id.login_login)
    Button loginBtn;
    private int username;
    private String pwd;
    @ViewById(R.id.bt_username_clear)
    Button nameClearBtn;
    @ViewById(R.id.bt_pwd_clear)
    Button pwdClearBtn;


    @Override
    public void before() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        imageView.setOnClickListener(this);
        nameClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameEdit.setText("");
            }
        });
        pwdClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPswEdit.setText("");
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    username = Integer.parseInt(userNameEdit.getText().toString());
                    pwd = userPswEdit.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new LoginMain(username,pwd).start(username,pwd);
                    }
                }).start();
//                new LoginMain().start(username,pwd);
                Log.d("123",username+pwd);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.SSOSetting(false);
        qq.setPlatformActionListener(this);
        qq.authorize();
        qq.showUser(null);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
        //用户资源都保存到res
        //通过打印res数据看看有哪些数据是你想要的
        if (i == Platform.ACTION_USER_INFOR) {
            PlatformDb platDB = platform.getDb();//获取数平台数据DB
            //通过DB获取各种数据
            String token = platDB.getToken();
            String userGender = platDB.getUserGender();
            final String userIcon = platDB.getUserIcon();
            final String userId = platDB.getUserId();
            final String userName = platDB.getUserName();
            EventBus.getDefault().post(new UserEntity(userIcon,userId,userName),"UserInfo");
            StartUtil.editInfo(this,userName,userId,userIcon,"");
            Log.d("123",token+"  "+userId+"===="+userGender+"====="+userIcon+"======"+userName);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    textView.setText(userName+"-->"+userId);
//                    sdView.setImageURI(Uri.parse(userIcon));
                    startActivity(MainActivity_.intent(LoginActivity.this).get());
                }
            });
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscriber(tag = "login_success")
    public void loginSuccess(LogonResponse res){
        if (res != null){
            StartUtil.editInfo(this,res.getCalias(),res.getUserid()+"",res.getHeadpic()+"",pwd);
            startActivity(MainActivity_.intent(LoginActivity.this).get());
        }
    }
    @Subscriber(tag = "login_fail")
    public void loginFail(String flag){
        if (flag == "0"){
            Toast.makeText(this, "账号密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
