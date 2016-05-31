package com.xlg.android.protocol;

import com.xlg.android.utils.Tools;

public class LogonResponse {	
	@StructOrder(0)
	private int	userid;						//用户ID
	@StructOrder(1)
	private int	nlevel;						//用户级别
	@StructOrder(2)
	private int	nfamilyid;					//家族id
	@StructOrder(3)
	private long       ndeposit;                   //银行存款
	@StructOrder(4)
	private long  		nk;							//虚拟币
	@StructOrder(5)
	private long	    nb;							//积分
	@StructOrder(6)
	private int		decocolor;					//马甲颜色
	@StructOrder(7)
	private int    nverison;                   //版本号(使用xxx.xxx.xxx.xxx转成的ip值)
	@StructOrder(8)
	private short	online_stat;				//状态（0离线 1在线2隐身）
	@StructOrder(9)
	private short  headpic;					//头像ID
	@StructOrder(10)
	private short	reserve;					//保留
	@StructOrder(11)
	private byte			nettype;					//电信or联通 0->电信 1->联通
	@StructOrder(12)
	private byte			gender;						//性别（0－女，1－男，2－未知）
	@StructOrder(13)
	private byte[]			calias = new byte[32];			//昵称
	@StructOrder(14)
	private byte[]			cidiograph = new byte[128];	//个性签名
	@StructOrder(15)
	private byte[]			cvalue = new byte[256];				//大厅配置	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getNlevel() {
		return nlevel;
	}
	public void setNlevel(int nlevel) {
		this.nlevel = nlevel;
	}
	public int getNfamilyid() {
		return nfamilyid;
	}
	public void setNfamilyid(int nfamilyid) {
		this.nfamilyid = nfamilyid;
	}
	public long getNdeposit() {
		return ndeposit;
	}
	public void setNdeposit(long ndeposit) {
		this.ndeposit = ndeposit;
	}
	public long getNk() {
		return nk;
	}
	public void setNk(long nk) {
		this.nk = nk;
	}
	public long getNb() {
		return nb;
	}
	public void setNb(long nb) {
		this.nb = nb;
	}
	public int getDecocolor() {
		return decocolor;
	}
	public void setDecocolor(int decocolor) {
		this.decocolor = decocolor;
	}
	public int getNverison() {
		return nverison;
	}
	public void setNverison(int nverison) {
		this.nverison = nverison;
	}
	public short getOnline_stat() {
		return online_stat;
	}
	public void setOnline_stat(short online_stat) {
		this.online_stat = online_stat;
	}
	public short getHeadpic() {
		return headpic;
	}
	public void setHeadpic(short headpic) {
		this.headpic = headpic;
	}
	public short getReserve() {
		return reserve;
	}
	public void setReserve(short reserve) {
		this.reserve = reserve;
	}
	public byte getNettype() {
		return nettype;
	}
	public void setNettype(byte nettype) {
		this.nettype = nettype;
	}
	public byte getGender() {
		return gender;
	}
	public void setGender(byte gender) {
		this.gender = gender;
	}
	
	public String getCalias() {
		return Tools.ByteArray2StringGBK(calias);
	}
	public void setCalias(String calias) {
		Tools.String2ByteArrayGBK(this.calias, calias);
	}
	public String getCidiograph() {
		return Tools.ByteArray2StringGBK(cidiograph);
	}
	public void setCidiograph(String cidiograph) {
		Tools.String2ByteArrayGBK(this.cidiograph, cidiograph);
	}
	public String getCvalue() {
		return Tools.ByteArray2StringGBK(cvalue);
	}
	public void setCvalue(String cvalue) {
		Tools.String2ByteArrayGBK(this.cvalue, cvalue);
	}
	
	
}
