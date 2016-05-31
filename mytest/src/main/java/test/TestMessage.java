package test;

import com.xlg.android.utils.ByteBuffer;
import com.xlg.android.protocol.Header;
import com.xlg.android.protocol.LogonRequest;
import com.xlg.android.protocol.Hello;
import com.xlg.android.protocol.Message;

public class TestMessage {
	public static void main(String [] args) {
		ByteBuffer buf = new ByteBuffer();
		
		Header head = new Header();
		Hello obj = new Hello();
		
		head.setVersion((byte)0xc1);
//		head.setReserve((byte)0xc2);
		
		head.setCmd1((byte)0xe1);
		head.setCmd2((byte)0xe2);
		head.setCmd3((byte)0xe3);
		
		obj.setParam0(0xf0);
		obj.setParam1(0xf1);
		obj.setParam2(0xf2);
		
		
		// encode
		boolean ret = Message.EncodePack(buf, head, obj, true);
		
		System.out.println(ret);
		System.out.println(buf.getData());
		// decode
		com.xlg.android.protocol.Hello obj2 = new Hello();
		Header head2 = new Header();
		
		int len = Message.DecodeHeader(buf, head2);
		int size = Message.DecodeObject(buf, obj2);
		
		System.out.println(len);
		System.out.println(head2);
		System.out.println(size);
		System.out.println(obj2);
		
		
		LogonRequest req = new LogonRequest();
		
		req.setCmac("网卡");
		req.setCuserpwd("中国");
		
		ret = Message.EncodePack(buf, head, req, true);
		
		LogonRequest req2 = new LogonRequest();
		Message.DecodeObject(buf, req2);
		
		System.out.println(req2.getCmac());
		System.out.println(req2.getCuserpwd());
	}
}
