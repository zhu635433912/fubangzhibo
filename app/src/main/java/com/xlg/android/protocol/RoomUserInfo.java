package com.xlg.android.protocol;

import com.xlg.android.utils.Tools;

public class RoomUserInfo {
	@StructOrder(0)
	private int	userid;
	@StructOrder(1)
	private int	actorid;			//艺人身份
	@StructOrder(2)
	private int	vcbid;
	@StructOrder(3)
	private int	level1;				//用户级别
	@StructOrder(4)
	private int	decocolor;			//马甲颜色
	@StructOrder(5)
	private short	reserve;			//预留//用于表示是否为正常用户0-正常，1-机器人1
	@StructOrder(6)
	private short	sealid;				//盖章id
	//	unsigned int	pigcount;			//猪头(暂未使用)
	@StructOrder(7)
	private long	sealbringtime;		//盖章时长（秒）1970后时长
	@StructOrder(8)
	private int	ipaddr;				//最后登录ip
	@StructOrder(9)
	private int	userstate;			//用户状态,用了低16位。
	@StructOrder(10)
	private int	starflag;			//周星标识
	@StructOrder(11)
	private int	activityflag;		//活动星标识
	@StructOrder(12)
	private short  chargemicgiftid;    //用于收费麦的礼物id
	@StructOrder(13)
	private short  chargemicgiftcount;  //用于收费麦的礼物数目
	@StructOrder(14)
	private byte			publicmixindex;		//公麦序。最大255
	@StructOrder(15)
	private byte			gender;				//性别（0－女，1－男，2－未知）
	@StructOrder(16)
	private int			colorbarnum;		//自己的彩条总数
	@StructOrder(17)
	private byte[]			useralias = new byte[32];//昵称
	// add by:baddie@126.com  返回金币做蝴蝶显示用 返回麦时麦序供限时麦用
	@StructOrder(18)
	private long		nk;                  //金币
	@StructOrder(19)
	private long		nexpend;			//上月消费额
	@StructOrder(20)
	private long		nexpend2;			//当月消费额
	@StructOrder(21)
	private long		ncharisma;			//魅力值
	@StructOrder(22)
	private long		nintimacy;			//亲密度
	@StructOrder(23)
	private short           micindex;            //在1麦还是2麦
	@StructOrder(24)
	private long    micendtime;             //麦时结束时间
	@StructOrder(25)
	private long    micnowtime;           // 现在麦时过了多久
	@StructOrder(26)
	private byte[]            carname = new byte[32];              //座驾的名字
	@StructOrder(27)
	private int				isallowupmic;			//是否允许抱麦
	@StructOrder(28)
	private int				headid;					//头像id
	@StructOrder(29)
	private int				topline;				//上头条
	@StructOrder(30)
	private byte[]			clastloginmac = new byte[32];//mac地址
	@StructOrder(31)
	private byte[]				cphoto = new byte[32];					//自定义头像名称
	@StructOrder(32)
	private byte[]				cteamname = new byte[10];				//团名
	@StructOrder(33)
	private int			actorid_w ;	//佩戴的徽章的艺人id
	@StructOrder(34)
	private long			convalue_w ;	//佩戴的徽章的贡献值
	@StructOrder(35)
	private byte[]				cteamname_w = new byte[10];				//佩戴徽章的团名
	public long getConvalue_w() {
		return convalue_w;
	}

	public void setConvalue_w(long convalue_w) {
		this.convalue_w = convalue_w;
	}

	public String getCteamname_w() {
		return Tools.ByteArray2StringGBK(cteamname_w);
	}

	public void setCteamname_w(String cteamname_w) {
		Tools.String2ByteArrayGBK(this.cteamname_w , cteamname_w);
	}


	// 	int				nshiledgiftonsmallspeaker; //送的礼物是否上广播和跑道显示
// 	int				nshiledgiftshow;		//是否屏蔽礼物的滚动显示效果
// 	int				nshiledgifteffectsound;	//是否屏蔽礼物/烟花的flash效果和道具声音
// 	int				nshileddalabasound;		//是否屏蔽大喇叭声音效果
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getVcbid() {
		return vcbid;
	}
	public void setVcbid(int vcbid) {
		this.vcbid = vcbid;
	}
	public int getLevel1() {
		return level1;
	}
	public void setLevel1(int level1) {
		this.level1 = level1;
	}
	public int getDecocolor() {
		return decocolor;
	}
	public void setDecocolor(int decocolor) {
		this.decocolor = decocolor;
	}
	public short getReserve() {
		return reserve;
	}
	public void setReserve(short reserve) {
		this.reserve = reserve;
	}
	public short getSealid() {
		return sealid;
	}
	public void setSealid(short sealid) {
		this.sealid = sealid;
	}
	public long getSealbringtime() {
		return sealbringtime;
	}
	public void setSealbringtime(long sealbringtime) {
		this.sealbringtime = sealbringtime;
	}
	public int getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(int ipaddr) {
		this.ipaddr = ipaddr;
	}
	public int getUserstate() {
		return userstate;
	}
	public void setUserstate(int userstate) {
		this.userstate = userstate;
	}
	public int getStarflag() {
		return starflag;
	}
	public void setStarflag(int starflag) {
		this.starflag = starflag;
	}
	public int getActivityflag() {
		return activityflag;
	}
	public void setActivityflag(int activityflag) {
		this.activityflag = activityflag;
	}
	public short getChargemicgiftid() {
		return chargemicgiftid;
	}
	public void setChargemicgiftid(short chargemicgiftid) {
		this.chargemicgiftid = chargemicgiftid;
	}
	public short getChargemicgiftcount() {
		return chargemicgiftcount;
	}
	public void setChargemicgiftcount(short chargemicgiftcount) {
		this.chargemicgiftcount = chargemicgiftcount;
	}
	public byte getPublicmixindex() {
		return publicmixindex;
	}
	public void setPublicmixindex(byte publicmixindex) {
		this.publicmixindex = publicmixindex;
	}
	public byte getGender() {
		return gender;
	}
	public void setGender(byte gender) {
		this.gender = gender;
	}
	public int getColorbarnum() {
		return colorbarnum;
	}
	public void setColorbarnum(int colorbarnum) {
		this.colorbarnum = colorbarnum;
	}
	public String getUseralias() {
		return Tools.ByteArray2StringGBK(useralias);
	}
	public void setUseralias(String useralias) {
		Tools.String2ByteArrayGBK(this.useralias, useralias);
	}
	public long getNk() {
		return nk;
	}
	public void setNk(long nk) {
		this.nk = nk;
	}
	public long getNexpend() {
		return nexpend;
	}
	public void setNexpend(long nexpend) {
		this.nexpend = nexpend;
	}
	public long getNexpend2() {
		return nexpend2;
	}
	public void setNexpend2(long nexpend2) {
		this.nexpend2 = nexpend2;
	}
	public short getMicindex() {
		return micindex;
	}
	public void setMicindex(short micindex) {
		this.micindex = micindex;
	}
	public long getMicendtime() {
		return micendtime;
	}
	public void setMicendtime(long micendtime) {
		this.micendtime = micendtime;
	}
	public long getMicnowtime() {
		return micnowtime;
	}
	public void setMicnowtime(long micnowtime) {
		this.micnowtime = micnowtime;
	}
	public String getCarname() {
		return Tools.ByteArray2StringGBK(carname);
	}
	public void setCarname(String carname) {
		Tools.String2ByteArrayGBK(this.carname, carname);
	}
	public int getIsallowupmic() {
		return isallowupmic;
	}
	public void setIsallowupmic(int isallowupmic) {
		this.isallowupmic = isallowupmic;
	}
	public int getHeadid() {
		return headid;
	}
	public void setHeadid(int headid) {
		this.headid = headid;
	}
	public String getClastloginmac() {
		return Tools.ByteArray2StringGBK(clastloginmac);
	}
	public void setClastloginmac(String clastloginmac) {
		Tools.String2ByteArrayGBK(this.clastloginmac, clastloginmac);
	}

	public int getActorid() {
		return actorid;
	}

	public void setActorid(int actorid) {
		this.actorid = actorid;
	}

	public long getNcharisma() {
		return ncharisma;
	}

	public void setNcharisma(long ncharisma) {
		this.ncharisma = ncharisma;
	}

	public long getNintimacy() {
		return nintimacy;
	}

	public void setNintimacy(long nintimacy) {
		this.nintimacy = nintimacy;
	}

	public int getTopline() {
		return topline;
	}

	public void setTopline(int topline) {
		this.topline = topline;
	}

	public String getCphoto() {
		return Tools.ByteArray2StringGBK(cphoto);
	}

	public void setCphoto(String cphoto) {
		Tools.String2ByteArrayGBK(this.cphoto,cphoto);
	}

	public String getCteamname() {
		return Tools.ByteArray2StringGBK(cteamname);
	}

	public void setCteamname(String cteamname) {
		Tools.String2ByteArrayGBK(this.cteamname ,cteamname);
	}

	public int getActorid_w() {
		return actorid_w;
	}

	public void setActorid_w(int actorid_w) {
		this.actorid_w = actorid_w;
	}

}
