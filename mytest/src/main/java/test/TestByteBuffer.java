package test;

import com.xlg.android.utils.ByteBuffer;

public class TestByteBuffer {
	public static void main(String []args) {
		ByteBuffer buf = new ByteBuffer();
		
		
		
		buf.addShort((short) 0xf5f8);
		buf.addInt(0xf0f1f2f3);
		buf.addLong(0xf0f1f2f3f4f5f6f7l);
		
		byte [] data = new byte[20];
		
		buf.getBytes(data, 0, 14);
		
		short i1 = buf.getShort(0);
		int i2 = buf.getInt(2);
		long i3 = buf.getLong(6);
		
		return;
	}
}
