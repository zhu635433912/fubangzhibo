package com.fubang.fubangzhibo.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fubang.fubangzhibo.App_;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.adapters.RoomChatAdapter;
import com.fubang.fubangzhibo.view.MyEditText;
import com.xlg.android.RoomChannel;
import com.xlg.android.RoomHandler;
import com.xlg.android.protocol.AVNotifyMessage;
import com.xlg.android.protocol.ActWaitMicUserInfo;
import com.xlg.android.protocol.AddClosedFriendInfo;
import com.xlg.android.protocol.AuthorityRejected;
import com.xlg.android.protocol.BigGiftRecord;
import com.xlg.android.protocol.ChestBonusAmount;
import com.xlg.android.protocol.DecoColor;
import com.xlg.android.protocol.DevState;
import com.xlg.android.protocol.DigTreasureResponse;
import com.xlg.android.protocol.ForbidUserChat;
import com.xlg.android.protocol.GiveColorbarInfo;
import com.xlg.android.protocol.GrabRedPagerRequest;
import com.xlg.android.protocol.JoinRoomResponse;
import com.xlg.android.protocol.LocateIPResp;
import com.xlg.android.protocol.LotteryGiftNotice;
import com.xlg.android.protocol.LotteryNotice;
import com.xlg.android.protocol.MicState;
import com.xlg.android.protocol.OpenChestInfo;
import com.xlg.android.protocol.PreTradeGift;
import com.xlg.android.protocol.RedPagerRequest;
import com.xlg.android.protocol.RoomBKGround;
import com.xlg.android.protocol.RoomBaseInfo;
import com.xlg.android.protocol.RoomChatMsg;
import com.xlg.android.protocol.RoomKickoutUserInfo;
import com.xlg.android.protocol.RoomManagerInfo;
import com.xlg.android.protocol.RoomMediaInfo;
import com.xlg.android.protocol.RoomNotice;
import com.xlg.android.protocol.RoomState;
import com.xlg.android.protocol.RoomUserInfo;
import com.xlg.android.protocol.SendSeal;
import com.xlg.android.protocol.SetUserProfileResp;
import com.xlg.android.protocol.SetUserPwdResp;
import com.xlg.android.protocol.SiegeInfo;
import com.xlg.android.protocol.SysCastNotice;
import com.xlg.android.protocol.TransMediaInfo;
import com.xlg.android.protocol.UserAddMicTimeInfo;
import com.xlg.android.protocol.UserAlias;
import com.xlg.android.protocol.UserBankDepositRespInfo;
import com.xlg.android.protocol.UserChestNumInfo;
import com.xlg.android.protocol.UserHeadPic;
import com.xlg.android.protocol.UserPayResponse;
import com.xlg.android.protocol.UserPriority;
import com.xlg.android.protocol.UseridList;
import com.xlg.android.video.AVModuleMgr;
import com.xlg.android.video.AVNotify;
import com.xlg.android.video.AudioPlay;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import sample.room.MicNotify;
import sample.room.RoomMain;

@EActivity(R.layout.activity_room)
public class RoomActivity extends BaseActivity implements  MicNotify, RoomHandler, AVNotify {

    @ViewById(R.id.room_chat_edittext)
    MyEditText editText;
    @ViewById(R.id.room_chat_send)
    Button sendBtn;
    @ViewById(R.id.room_message_list)
    ListView listView;

    private List<RoomChatMsg> data = new ArrayList<>();

    private RoomChatAdapter adapter;

    private RoomChannel channel = new RoomChannel(this);
    private boolean isConnected = true;
    public final int FT_ROOMUSER_STATUS_PUBLIC_MIC = 0x1;

    public String videoIP;
    public int videoPort;
    public int videoRand;
    public int videoUID;

    private AVModuleMgr mgr = null;
    @ViewById(R.id.room_surface)
    SurfaceView surfaceView;
    private Bitmap bmp;
    private static AudioPlay play = new AudioPlay();

    private Thread keepThread = new Thread(new Runnable() {
        @Override
        public void run() {
            getChannel().setRoomID(10000);
            getChannel().setUserID(Integer.parseInt(StartUtil.getUserId(RoomActivity.this)));
            getChannel().setUserPwd(StartUtil.getUserPwd(RoomActivity.this));
            getChannel().Connect("121.43.63.101",10927);
            play.start();
            while(isConnected) {
                try {
                    Thread.sleep(1000 * 5);
                    getChannel().SendKeepAliveReq();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    });

    public RoomChannel getChannel() {
        return channel;
    }

    public boolean isOK() {
        return isConnected;
    }

    @Override
    public void before() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        adapter = new RoomChatAdapter(data,this);
        listView.setAdapter(adapter);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editText.getText())){
                    getChannel().sendChatMsg(0,(byte)0,(byte)0,"<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">"+editText.getText()+"</FONT>");
                    editText.setText("");
                }
            }
        });
        keepThread.start();
        new Thread(new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
            }

        }).start();
    }


    @Subscriber(tag="RoomChatMsg")
    public void getRoomChatMsg(RoomChatMsg msg){
        Log.d("123",msg.getContent());
        data.add(msg);
        adapter.notifyDataSetChanged();
        listView.setSelection(listView.getCount()-1);
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
        EventBus.getDefault().post(obj,"JoinRoomResponse");
    }
    @Subscriber(tag = "JoinRoomResponse")
    public void getJoinRoomResponse(JoinRoomResponse obj){
        String str = obj.getMediaserver();
        String ips[] = str.split(";");
        if(ips.length > 0) {
            String s[] = ips[0].split(":");
            if(s.length > 1) {
                videoIP = s[0];
                videoPort = Integer.valueOf(s[1]).intValue();
            }
        }
        Log.d("123",videoIP+"   :   "+videoPort+videoUID);
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
    public void onGetRoomUserListResponse(int g1, RoomUserInfo[] obj) {

        for(int i = 0; i < obj.length; i++) {
            // Tools.PrintObject(obj[i]);
            if(0 != (obj[i].getUserstate() & FT_ROOMUSER_STATUS_PUBLIC_MIC)) {
                EventBus.getDefault().post(obj[i],"RoomUserInfo");
            }
        }
    }
    @Subscriber(tag = "RoomUserInfo")
    public void getRoomUserInfo(RoomUserInfo obj){
        videoUID = obj.getUserid();
        Log.d("123","===================: find mic: " + videoUID);
        onMic(videoIP, videoPort, videoRand, videoUID);
    }
    @Override
    public void onGetRoomMicListResponse(UseridList obj) {

    }

    @Override
    public void onGetFlygiftListResponse(BigGiftRecord[] obj) {
        Log.d("123",obj[0].getGiftid()+"------------");
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
        Log.d("123","连接断开");
    }
    @Override
    protected void onDestroy() {
        isConnected = false;
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        mgr.StopRTPSession();
        mgr.Uninit();
        play.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        new Thread(new Runnable() {
            @Override
            public void run() {
                play.stop();
                mgr.StopRTPSession();
                mgr.Uninit();
            }
        }).start();
    }

    public void StartAV(String ip, int port, int rand, int uid) {
        mgr.Init();

        mgr.CreateRTPSession(0);
        mgr.SetServerAddr2(ip, port, 0);
        mgr.StartRTPSession();

        if (rand < 1800000000)
            rand = 1800000000;

        int ssrc = rand - uid;

        mgr.AddRTPRecver(0, ssrc, 99, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 99, 1);

        mgr.AddRTPRecver(0, ssrc, 97, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 97, 1);

        mgr.AddAudioStream(ssrc, 1, this);
        mgr.AddVideoStream(ssrc, 0, 1, this);

    }

    @Override
    public void onVideo(int ssrc, int width, int height, byte[] img) {
        EventBus.getDefault().post(new AVNotifyMessage(ssrc, width, height, img),"onVideo");
        Log.d("123","========== onVideo: " + width + ":" + height + "(" + img.length + ")");
        // 删除旧的
        if(null != bmp) {
            if(width !=bmp.getWidth() || height != bmp.getHeight()) {
                bmp = null;
            }
        }

        // 创建新的
        if(null == bmp) {
            bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }

        ByteBuffer buf = ByteBuffer.wrap(img);
        bmp.copyPixelsFromBuffer(buf);
        // 这是在线程里操作的，千万不要直接在画布上绘制
        // 在surfaceView中显示
        if(null != surfaceView.getHolder()) {
            Canvas canvas = surfaceView.getHolder().lockCanvas();

            if(null != canvas) {
                try{
                    Rect rt1 = new Rect(0, 0, surfaceView.getWidth(), surfaceView.getHeight());
                    Rect rt2 = new Rect(0, 0, width, height);
                    canvas.drawBitmap(bmp, rt2, rt1, null);
                } finally {
                    // 必须要释放，不然下次不会再绘制
                    surfaceView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }

    }

    @Override
    public void onAudio(int ssrc, int sample, int channel, byte[] pcm) {
        System.out.println("========== onAudio: " + sample + ":" + channel + "(" + pcm.length + ")");
        play.setConfig(sample, channel);
        play.play(pcm);
    }

    @Override
    public void onMic(String ip, int port, int rand, int uid) {
        // TODO Auto-generated method stub
        if(null == mgr) {
            mgr = new AVModuleMgr();
//            mgr = App_.getInstance().getMgr();
            StartAV(ip, port, rand, uid);
        }
    }
}
