package sample.room;

public class RoomMain {
	public static void main(String [] args) {
		MyRoom room = new MyRoom();
		
		room.getChannel().setRoomID(10000);
		room.getChannel().setUserID(10);
		room.getChannel().setUserPwd("426032");
		
		// 121.43.155.221:15518
		// 121.43.63.101:18517
		// tryroom://10000;121.43.63.101:10927;121.43.63.101:10927;121.43.63.101:10927;61.153.111.126:10927
		room.getChannel().Connect("121.43.63.101", 10927);
		
		
		try {
			for(int i = 0; i < 10; i++) {
				// 暂停10秒
				Thread.sleep(1000 * 10);
				if(room.isOK()) {
					// 发起心跳
					room.getChannel().SendKeepAliveReq();
//					发送聊天消息
					room.getChannel().sendChatMsg(0, (byte)0, (byte)0, "这是测试");
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
