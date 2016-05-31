package com.fubang.fubangzhibo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.xlg.android.RoomChannel;
import com.xlg.android.RoomHandler;
import com.xlg.android.protocol.*;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;


public class RoomService extends Service implements RoomHandler {


    private Binder binder;

    private RoomChannel channel = new RoomChannel(this);
    private boolean isConnected = false;


    public RoomChannel getChannel() {
        return channel;
    }

    public boolean isOK() {
        return isConnected;
    }
    /**
     * 连接服务器
     */
    private void connectService() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                getChannel().setRoomID(10000);
                getChannel().setUserID(10);
                getChannel().setUserPwd("426032");
                getChannel().Connect("121.43.63.101",10927);
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000 * 10);
                        getChannel().SendKeepAliveReq();
                        getChannel().sendChatMsg(0,(byte)0,(byte)0,"<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">test</FONT>");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
    @Subscriber(tag="sendChatMessage",mode = ThreadMode.ASYNC)
    public void sendMessage(final String msg){
        Log.d("123","发送消息"+msg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getChannel().sendChatMsg(0,(byte)0,(byte)0,"<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">"+msg+"</FONT>");
            }
        }).start();
    }

    @Override
    public void onConnectSuccessed() {
        isConnected = true;
        // 连接成功
        Log.d("123","onConnectSuccessed: 连接成功"+isConnected+"---");
        // 加入房间
        getChannel().SendHello();
        // 发送连接
        getChannel().SendJoinRoomReq();
    }

    @Override
    public void onConnectFailed() {
        Log.d("123","连接失败");
    }

    @Override
    public void onJoinRoomResponse(JoinRoomResponse obj) {
        Log.d("123","jionroomresionse--"+obj.getGroupid());
    }

    @Override
    public void onRoomUserNotify(RoomUserInfo obj) {

    }

    @Override
    public void onGetOpenChestInfoResponse(OpenChestInfo obj) {

    }

    @Override
    public void onOpenChestNotify(OpenChestInfo obj) {

    }

    @Override
    public void onChestBonusAmountNotify(ChestBonusAmount obj) {

    }

    @Override
    public void onUserChestChangeNotify(UserChestNumInfo obj) {

    }

    @Override
    public void onJoinRoomError(int err) {

    }

    @Override
    public void onRoomNoticeNotify(RoomNotice[] obj) {

    }

    @Override
    public void onGetRoomUserListResponse(int g1, RoomUserInfo[] userList) {

    }

    @Override
    public void onGetRoomMicListResponse(UseridList obj) {

    }

    @Override
    public void onGetFlygiftListResponse(BigGiftRecord[] obj) {

    }

    @Override
    public void onAuthorityRejected(AuthorityRejected obj) {

    }

    @Override
    public void onSiegeInfoNotify(SiegeInfo obj) {

    }

    @Override
    public void onKickoutRoomUserNotify(RoomKickoutUserInfo obj) {

    }

    @Override
    public void onChatMsgNotify(RoomChatMsg txt) {
        Log.d("123","roomchatMsg"+txt.getContent());
        EventBus.getDefault().post(txt,"RoomChatMsg");
    }

    @Override
    public void onChatMsgError(int err) {

    }

    @Override
    public void onUserPayResponse(UserPayResponse obj) {

    }

    @Override
    public void onSendSealNotify(SendSeal obj) {

    }

    @Override
    public void onSetMicStateNotify(MicState obj) {

    }

    @Override
    public void onSetDevStateNotify(DevState obj) {

    }

    @Override
    public void onSyncUserAliasNotify(UserAlias obj) {

    }

    @Override
    public void onSyncUserHeadPicNotify(UserHeadPic obj) {

    }

    @Override
    public void onSyncDecoColorNotify(DecoColor obj) {

    }

    @Override
    public void onSetRoomBKgroupNotify(RoomBKGround obj) {

    }

    @Override
    public void onSetRoomBaseInfoResponse() {

    }

    @Override
    public void onRoomBaseInfoNotify(RoomBaseInfo obj) {

    }

    @Override
    public void onSetRoomManagersResponse() {

    }

    @Override
    public void onSetRoomManagersNotify(RoomManagerInfo obj) {

    }

    @Override
    public void onRoomMediaURLNotify(RoomMediaInfo obj) {

    }

    @Override
    public void onSetRoomRunStateNotify(RoomState obj) {

    }

    @Override
    public void onSetUserPriorityResponse() {

    }

    @Override
    public void onSetUserProfileResponse(SetUserProfileResp obj) {

    }

    @Override
    public void onSetUserPwdRepsonse(SetUserPwdResp obj) {

    }

    @Override
    public void onSetUserPwdError() {

    }

    @Override
    public void onSetUserPriorityNotify(UserPriority obj) {

    }

    @Override
    public void onTransMediaRequest(TransMediaInfo obj) {

    }

    @Override
    public void onTransMediaResponse(TransMediaInfo obj) {

    }

    @Override
    public void onTransMediaError(TransMediaInfo obj) {

    }

    @Override
    public void onRoomUserKeepAliveResponse() {

    }

    @Override
    public void onForbidUserChatNotify(ForbidUserChat obj) {

    }

    @Override
    public void onTradeGiftResponse() {

    }

    @Override
    public void onTradeGiftError(int i) {

    }

    @Override
    public void onTradeGiftNotify(BigGiftRecord obj) {

    }

    @Override
    public void onLotteryGiftNotify(LotteryGiftNotice obj) {

    }

    @Override
    public void onLotteryNotify(LotteryNotice obj) {

    }

    @Override
    public void onSysCastNotify(SysCastNotice obj) {

    }

    @Override
    public void onLocateUserIPResponse(LocateIPResp obj) {

    }

    @Override
    public void onGiveColorbarNotify(GiveColorbarInfo obj) {

    }

    @Override
    public void onAddMicTimeNotify(UserAddMicTimeInfo obj) {

    }

    @Override
    public void onActWaitMicUserNotify(ActWaitMicUserInfo obj) {

    }

    @Override
    public void onAddClosedFriendNotify(AddClosedFriendInfo obj) {

    }

    @Override
    public void onBankDepositResponse(UserBankDepositRespInfo obj) {

    }

    @Override
    public void onBankDepositError() {

    }

    @Override
    public void onDoNotReachRoomServer() {

    }

    @Override
    public void onDigTreasureResponse(DigTreasureResponse obj) {

    }

    @Override
    public void onRedPagerResponse(RedPagerRequest obj) {

    }

    @Override
    public void onRedPagerError(int i) {

    }

    @Override
    public void onGrabRedPagerResponse(GrabRedPagerRequest obj) {

    }

    @Override
    public void onPreTradeGiftResponse(PreTradeGift obj) {

    }

    @Override
    public void onDisconnected() {

    }

    public class InterBinder extends Binder {

        public RoomService getService() {
            return RoomService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        binder = new InterBinder();
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        connectService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
