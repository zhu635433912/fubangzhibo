package sample.login;

import com.xlg.android.LoginChannel;
import com.xlg.android.LoginHandler;
import com.xlg.android.protocol.LogonError;
import com.xlg.android.protocol.LogonResponse;

public class TestLogin implements LoginHandler {
	private LoginChannel channel = new LoginChannel(this);


	public static void main(String[] args) {
		TestLogin login = new TestLogin();

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
		
		channel.SendHello();
		
		channel.SendLogonRequest(10, 1, "426032", "android-test", "");
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
		
		System.out.println("Calias: " + res.getCalias());
		System.out.println("Cidiograph: " + res.getCidiograph());
		System.out.println("Cidiograph: " + res.getCidiograph());
		System.out.println("Decocolor: " + res.getDecocolor());
		System.out.println("Nb: " + res.getNb());
		System.out.println("Ndeposit: " + res.getNdeposit());
		System.out.println("Nk: " + res.getNk());
		System.out.println("Nlevel: " + res.getNlevel());
		System.out.println("Nverison: " + res.getNverison());
		System.out.println("Userid: " + res.getUserid());
		System.out.println("Gender: " + res.getGender());
		System.out.println("Headpic: " + res.getHeadpic());
		System.out.println("Online_stat: " + res.getOnline_stat());
		System.out.println("Reserve: " + res.getReserve());
		
		channel.Close();
	}

	@Override
	public void onLogonError(LogonError err) {
		// TODO Auto-generated method stub
		System.out.println("登录失败");
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		System.out.println("与服务器断开");
	}
}
