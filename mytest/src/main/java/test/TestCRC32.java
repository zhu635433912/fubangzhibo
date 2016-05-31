package test;

import com.xlg.android.utils.CRC32;

public class TestCRC32 {
	public static void main(String args[]) {
		byte [] data = new byte [] {
			(byte) 0xf0, (byte) 0xf1, (byte) 0xf2, (byte) 0xf3,
			(byte) 0xf4, (byte) 0xf5, (byte) 0xf6, (byte) 0xf7, 
			(byte) 0xf8, (byte) 0xf9	
		};
		
//		byte [] data = new byte [] {
//				(byte) 0xf0	
//			};
		
		int crc = CRC32.crc32(data, 10, CRC32.CRC_MAGIC);
		int i = 0;
	}
}
