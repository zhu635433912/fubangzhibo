package com.xlg.android.protocol;

public class GiveColorbarInfo {
	@StructOrder(0)
	private int   vcbid;       //房间id
	@StructOrder(1)
	private int   srcid;       //用户id
	@StructOrder(2)
	private int   toid;        //
	@StructOrder(3)
	private int colorbarnum; //彩条总数
	public int getVcbid() {
		return vcbid;
	}
	public void setVcbid(int vcbid) {
		this.vcbid = vcbid;
	}
	public int getSrcid() {
		return srcid;
	}
	public void setSrcid(int srcid) {
		this.srcid = srcid;
	}
	public int getToid() {
		return toid;
	}
	public void setToid(int toid) {
		this.toid = toid;
	}
	public int getColorbarnum() {
		return colorbarnum;
	}
	public void setColorbarnum(int colorbarnum) {
		this.colorbarnum = colorbarnum;
	}
	
	
}
