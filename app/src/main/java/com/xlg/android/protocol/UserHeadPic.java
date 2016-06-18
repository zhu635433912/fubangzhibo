package com.xlg.android.protocol;

import com.xlg.android.utils.Tools;

public class UserHeadPic {
	@StructOrder(0)
	private int  vcbid;    //房间id
	@StructOrder(1)
	private int  userid;   //发送请求者ID
	@StructOrder(2)
	private byte[]  cphoto = new byte[32]; //头像id
	public int getVcbid() {
		return vcbid;
	}
	public void setVcbid(int vcbid) {
		this.vcbid = vcbid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getCphoto() {
		return Tools.ByteArray2StringGBK(cphoto);
	}
	public void setCphoto(String cphoto) {
		Tools.String2ByteArrayGBK(this.cphoto , cphoto);
	}


}
