package test;

import com.xlg.android.utils.SimpSecret;

public class TestSimpSecret {

	public static void main(String argc[]) {
		byte data[] = new byte[]{
			(byte) 0xf0, (byte) 0xf1, (byte) 0xf2, (byte) 0xf3, (byte) 0xf4, 
			(byte) 0xf5, (byte) 0xf6, (byte) 0xf7, (byte) 0xf8, (byte) 0xf9
		};
		
		SimpSecret.SimpEncrypt(data);
		
		SimpSecret.SimpDecrypt(data);
		
		System.out.println(data);
	}
}
