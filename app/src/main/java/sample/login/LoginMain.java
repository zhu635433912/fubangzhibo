package sample.login;

import android.content.Context;
import android.widget.Toast;

import com.xlg.android.LoginChannel;
import com.xlg.android.LoginHandler;
import com.xlg.android.protocol.LogonError;
import com.xlg.android.protocol.LogonResponse;
import com.xlg.android.utils.Tools;
import com.zhuyunjian.library.StartUtil;

import org.simple.eventbus.EventBus;

import io.vov.vitamio.utils.Log;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * <p/>
 * 项目名称：fubangzhibo
 * 类描述：
 * 创建人：dell
 * 创建时间：2016-05-31 13:40
 * 修改人：dell
 * 修改时间：2016-05-31 13:40
 * 修改备注：
 */
public class LoginMain implements LoginHandler{
    private LoginChannel channel = new LoginChannel(this);
    private int id;
    private String pwd;

    public LoginMain(int id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public void start(int userId, String userPwd) {
        id = userId;
        pwd = userPwd;
        LoginMain login = new LoginMain(userId,userPwd);

        // 121.43.155.221:15518
        // 121.43.63.101:18517
        login.channel.Connect("121.43.63.101", 18517);

        try {
            Thread.sleep(1000 * 50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectSuccessed() {
        // TODO Auto-generated method stub
        System.out.println("连接服务器成功");
        android.util.Log.d("123",id+pwd);
        channel.SendHello();

        channel.SendLogonRequest(id, 1, pwd, "android-test", "");
    }

    @Override
    public void onConnectFailed() {
        // TODO Auto-generated method stub
        System.out.println("连接服务器失败");
    }

    @Override
    public void onLogonResponse(LogonResponse res) {
        // TODO Auto-generated method stub
        System.out.println("登录回应");
//        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
        System.out.println("Calias: " + res.getCalias());
        System.out.println("Cidiograph: " + res.getCidiograph());
        System.out.println("Cidiograph: " + res.getCidiograph());
        System.out.println("Cvalue: " + res.getCvalue());
        System.out.println("Decocolor: " + res.getDecocolor());
        System.out.println("Nb: " + res.getNb());
        System.out.println("Ndeposit: " + res.getNdeposit());
        System.out.println("Nfamilyid: " + res.getNfamilyid());
        System.out.println("Nk: " + res.getNk());
        System.out.println("Nlevel: " + res.getNlevel());
        System.out.println("Nverison: " + res.getNverison());
        System.out.println("Userid: " + res.getUserid());
        System.out.println("Gender: " + res.getGender());
        System.out.println("Headpic: " + res.getHeadpic());
        System.out.println("Online_stat: " + res.getOnline_stat());
        System.out.println("Reserve: " + res.getReserve());
        EventBus.getDefault().post(res,"login_success");
        channel.Close();
    }

    @Override
    public void onLogonError(LogonError err) {
        // TODO Auto-generated method stub
        System.out.println("登录失败");
        EventBus.getDefault().post("0","login_fail");
    }

    @Override
    public void onDisconnected() {
        // TODO Auto-generated method stub
        System.out.println("与服务器断开");
    }
}
