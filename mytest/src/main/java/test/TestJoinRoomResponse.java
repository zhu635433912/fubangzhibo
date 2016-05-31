package test;

import com.xlg.android.protocol.Header;
import com.xlg.android.protocol.JoinRoomResponse;
import com.xlg.android.protocol.Message;
import com.xlg.android.utils.ByteBuffer;

public class TestJoinRoomResponse {
	public static void main(String [] args) {
		JoinRoomResponse res = new JoinRoomResponse();
		int [] pl = new int[] {
				0xf1, 0xf2, 0xf3, 0xf4, 0xf5
		};
		
		int ret = Message.SizeOfObject(res);
		System.out.println(ret);

		res.setPlayerlist(pl);
		ret = Message.SizeOfObject(res);
		
		Header head = new Header();
		
		ByteBuffer buf = new ByteBuffer();
		Message.EncodePack(buf, head, res, true);
		
		
		JoinRoomResponse obj = new JoinRoomResponse();
		
		Message.DecodeObject(buf, obj);
		pl = obj.getPlayerlist();
		
		System.out.println(ret);
	}
}
