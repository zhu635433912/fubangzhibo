package sample.room;

import com.xlg.android.RoomChannel;
import com.xlg.android.RoomHandler;
import com.xlg.android.protocol.*;
import com.xlg.android.utils.Tools;


public class MyRoom implements RoomHandler {
	private RoomChannel channel = new RoomChannel(this);
	private boolean isConnected = false;

	public RoomChannel getChannel() {
		return channel;
	}
	
	public boolean isOK() {
		return isConnected;
	}
	
	@Override
	public void onConnectSuccessed() {
		// TODO Auto-generated method stub
		isConnected = true;
		// 连接成功
		System.out.println("onConnectSuccessed: 连接成功");
		// 加入房间
		channel.SendHello();
		// 发送连接
		channel.SendJoinRoomReq();
	}

	@Override
	public void onConnectFailed() {
		// TODO Auto-generated method stub
		System.out.println("onConnectFailed: 连接失败");
	}

	@Override
	public void onJoinRoomResponse(JoinRoomResponse obj) {
		// TODO Auto-generated method stub
		System.out.println("onJoinRoomResponse: ");
		Tools.PrintObject(obj);
	}

	@Override
	public void onRoomUserNotify(RoomUserInfo obj) {
		// TODO Auto-generated method stub
		System.out.println("onRoomUserNotify: ");
		Tools.PrintObject(obj);
	}

	@Override
	public void onGetOpenChestInfoResponse(OpenChestInfo obj) {
		// TODO Auto-generated method stub
		System.out.println("onGetOpenChestInfoResponse: ");
		Tools.PrintObject(obj);
	}

	@Override
	public void onOpenChestNotify(OpenChestInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onOpenChestNotify: ");
	}

	@Override
	public void onChestBonusAmountNotify(ChestBonusAmount obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onChestBonusAmountNotify: ");
	}

	@Override
	public void onUserChestChangeNotify(UserChestNumInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onUserChestChangeNotify: ");
	}

	@Override
	public void onJoinRoomError(int err) {
		// TODO Auto-generated method stub
		PrintUnknown("onJoinRoomError: ");
	}

	@Override
	public void onRoomNoticeNotify(RoomNotice[] obj) {
		// TODO Auto-generated method stub
		System.out.println("onRoomNoticeNotify: ");
		for(int i = 0; i < obj.length; i++) {
			Tools.PrintObject(obj[i]);
		}
	}

	@Override
	public void onGetRoomUserListResponse(int g1, RoomUserInfo [] obj) {
		// TODO Auto-generated method stub
		System.out.println("onGetRoomUserListResponse: " + g1);
		for(int i = 0; i < obj.length; i++) {
			Tools.PrintObject(obj[i]);
		}
	}

	private void PrintUnknown(String string) {
		// TODO Auto-generated method stub
		System.out.println("==============================:" + string);
	}

	@Override
	public void onGetRoomMicListResponse(UseridList obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onGetRoomMicListResponse: ");
	}

	@Override
	public void onGetFlygiftListResponse(BigGiftRecord[] obj) {
		// TODO Auto-generated method stub
		System.out.println("onGetFlygiftListResponse: ");
		for(int i = 0; i < obj.length; i++) {
			Tools.PrintObject(obj[i]);
		}
	}

	@Override
	public void onAuthorityRejected(AuthorityRejected obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onAuthorityRejected: ");
	}

	@Override
	public void onSiegeInfoNotify(SiegeInfo obj) {
		// TODO Auto-generated method stub
		System.out.println("onSiegeInfoNotify: ");
		Tools.PrintObject(obj);
	}

	@Override
	public void onKickoutRoomUserNotify(RoomKickoutUserInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onKickoutRoomUserNotify: ");
	}

	@Override
	public void onChatMsgNotify(RoomChatMsg txt) {
		// TODO Auto-generated method stub
		System.out.println("onChatMsgNotify: ");
		//txt.getContent 报空指针
		Tools.PrintObject(txt);
		// 回应消息
		// getChannel().sendChatMsg(0, (byte)0, (byte)0, "testChat");
	}

	@Override
	public void onChatMsgError(int err) {
		// TODO Auto-generated method stub
		PrintUnknown("onChatMsgError: ");
	}

	@Override
	public void onUserPayResponse(UserPayResponse obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onUserPayResponse: ");
	}

	@Override
	public void onSendSealNotify(SendSeal obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSendSealNotify: ");
	}

	@Override
	public void onSetMicStateNotify(MicState obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSetMicStateNotify: ");
	}

	@Override
	public void onSetDevStateNotify(DevState obj) {
		// TODO Auto-generated method stub
		System.out.println("onSetDevStateNotify: ");
		Tools.PrintObject(obj);
	}

	@Override
	public void onSyncUserAliasNotify(UserAlias obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSyncUserAliasNotify: ");
	}

	@Override
	public void onSyncUserHeadPicNotify(UserHeadPic obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSyncUserHeadPicNotify: ");
	}

	@Override
	public void onSyncDecoColorNotify(DecoColor obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSyncDecoColorNotify: ");
	}

	@Override
	public void onSetRoomBKgroupNotify(RoomBKGround obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSetRoomBKgroupNotify: ");
	}

	@Override
	public void onSetRoomBaseInfoResponse() {
		// TODO Auto-generated method stub
		PrintUnknown("onSetRoomBaseInfoResponse: ");
	}

	@Override
	public void onRoomBaseInfoNotify(RoomBaseInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onRoomBaseInfoNotify: ");
	}

	@Override
	public void onSetRoomManagersResponse() {
		// TODO Auto-generated method stub
		PrintUnknown("onSetRoomManagersResponse: ");
	}

	@Override
	public void onSetRoomManagersNotify(RoomManagerInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSetRoomManagersNotify: ");
	}

	@Override
	public void onRoomMediaURLNotify(RoomMediaInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onRoomMediaURLNotify: ");
	}

	@Override
	public void onSetRoomRunStateNotify(RoomState obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSetRoomRunStateNotify: ");
	}

	@Override
	public void onSetUserPriorityResponse() {
		// TODO Auto-generated method stub
		PrintUnknown("onSetUserPriorityResponse: ");
	}

	@Override
	public void onSetUserProfileResponse(SetUserProfileResp obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSetUserProfileResponse: ");
	}

	@Override
	public void onSetUserPwdRepsonse(SetUserPwdResp obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSetUserPwdRepsonse: ");
	}

	@Override
	public void onSetUserPwdError() {
		// TODO Auto-generated method stub
		PrintUnknown("onSetUserPwdError: ");
	}

	@Override
	public void onSetUserPriorityNotify(UserPriority obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSetUserPriorityNotify: ");
	}

	@Override
	public void onTransMediaRequest(TransMediaInfo obj) {
		// TODO Auto-generated method stub
		System.out.println("onTransMediaRequest: ");
		Tools.PrintObject(obj);
	}

	@Override
	public void onTransMediaResponse(TransMediaInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onTransMediaResponse: ");
	}

	@Override
	public void onTransMediaError(TransMediaInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onTransMediaError: ");
	}

	@Override
	public void onRoomUserKeepAliveResponse() {
		// TODO Auto-generated method stub
		System.out.println("onRoomUserKeepAliveResponse: 心跳回应");
	}

	@Override
	public void onForbidUserChatNotify(ForbidUserChat obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onForbidUserChatNotify: ");
	}

	@Override
	public void onTradeGiftResponse() {
		// TODO Auto-generated method stub
		PrintUnknown("onTradeGiftResponse: ");
	}

	@Override
	public void onTradeGiftError(int i) {
		// TODO Auto-generated method stub
		PrintUnknown("onTradeGiftError: ");
	}

	@Override
	public void onTradeGiftNotify(BigGiftRecord obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onTradeGiftNotify: ");
	}

	@Override
	public void onLotteryGiftNotify(LotteryGiftNotice obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onLotteryGiftNotify: ");
	}

	@Override
	public void onLotteryNotify(LotteryNotice obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onLotteryNotify: ");
	}

	@Override
	public void onSysCastNotify(SysCastNotice obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onSysCastNotify: ");
	}

	@Override
	public void onLocateUserIPResponse(LocateIPResp obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onLocateUserIPResponse: ");
	}

	@Override
	public void onGiveColorbarNotify(GiveColorbarInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onGiveColorbarNotify: ");
	}

	@Override
	public void onAddMicTimeNotify(UserAddMicTimeInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onAddMicTimeNotify: ");
	}

	@Override
	public void onActWaitMicUserNotify(ActWaitMicUserInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onActWaitMicUserNotify: ");
	}

	@Override
	public void onAddClosedFriendNotify(AddClosedFriendInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onAddClosedFriendNotify: ");
	}

	@Override
	public void onBankDepositResponse(UserBankDepositRespInfo obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onBankDepositResponse: ");
	}

	@Override
	public void onBankDepositError() {
		// TODO Auto-generated method stub
		PrintUnknown("onBankDepositError: ");
	}

	@Override
	public void onDoNotReachRoomServer() {
		// TODO Auto-generated method stub
		PrintUnknown("onDoNotReachRoomServer: ");
	}

	@Override
	public void onDigTreasureResponse(DigTreasureResponse obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onDigTreasureResponse: ");
	}

	@Override
	public void onRedPagerResponse(RedPagerRequest obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onRedPagerResponse: ");
	}

	@Override
	public void onRedPagerError(int i) {
		// TODO Auto-generated method stub
		PrintUnknown("onRedPagerError: ");
	}

	@Override
	public void onGrabRedPagerResponse(GrabRedPagerRequest obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onGrabRedPagerResponse: ");
	}

	@Override
	public void onPreTradeGiftResponse(PreTradeGift obj) {
		// TODO Auto-generated method stub
		PrintUnknown("onPreTradeGiftResponse: ");
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		isConnected = false;
	}

}
