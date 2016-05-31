package test;

import com.xlg.android.utils.Tools;

public class TestStringByteConvert {
	public static void main(String [] args) {
		byte [] buf = new byte[32];
		
		Tools.String2ByteArrayUTF(buf, "中国");
		String utf = Tools.ByteArray2StringUTF(buf);
		
		int ok = utf.compareTo("中国");
		
		Tools.String2ByteArrayGBK(buf, "中国");
		String gbk = Tools.ByteArray2StringGBK(buf);
		
		ok = utf.compareTo("中国");
		
		int i = 0;
	}
}
