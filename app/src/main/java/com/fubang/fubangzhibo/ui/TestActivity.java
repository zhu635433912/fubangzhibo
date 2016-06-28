package com.fubang.fubangzhibo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.PowerManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fubang.fubangzhibo.App;
import com.fubang.fubangzhibo.App_;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.adapters.FaceAdapter;
import com.fubang.fubangzhibo.adapters.GiftAdapter;
import com.fubang.fubangzhibo.adapters.RoomChatAdapter;
import com.fubang.fubangzhibo.adapters.UserAdapter;
import com.fubang.fubangzhibo.entities.FaceEntity;
import com.fubang.fubangzhibo.entities.GiftEntity;
import com.fubang.fubangzhibo.utils.FaceUtil;
import com.fubang.fubangzhibo.utils.GiftUtil;
import com.fubang.fubangzhibo.view.MyEditText;
import com.xlg.android.protocol.BigGiftRecord;
import com.xlg.android.protocol.JoinRoomResponse;
import com.xlg.android.protocol.RoomChatMsg;
import com.xlg.android.protocol.RoomKickoutUserInfo;
import com.xlg.android.protocol.RoomUserInfo;
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

/**
 * 加入房间页面
 */
@EActivity(R.layout.activity_test)
public class TestActivity extends BaseActivity implements AVNotify, MicNotify, View.OnClickListener {
    @ViewById(R.id.linear_container)
    LinearLayout linearLayout;
    @ViewById(R.id.room_chat_edittext)
    MyEditText editText;
    @ViewById(R.id.room_chat_send)
    Button sendBtn;
    @ViewById(R.id.room_message_list)
    ListView listView;
    @ViewById(R.id.room_gift)
    ImageView giftImage;
    @ViewById(R.id.chat_image_btn)
    ImageButton faceButton;
    @ViewById(R.id.room_send_user)
    Button userSendBtn;

    private Button giftSendBtn;
    private GridView gridView;
    private ListView userList;
    private boolean isRunning = true;

    private List<RoomChatMsg> data = new ArrayList<>();

    private RoomChatAdapter adapter;

    private AVModuleMgr mgr;
    @ViewById(R.id.text_surface)
    SurfaceView surfaceView;
    private Bitmap bmp;
    private static AudioPlay play = new AudioPlay();
    private RoomMain roomMain = new RoomMain(this);

    private PopupWindow popupWindow;
    private PopupWindow faceWindow;
    private PopupWindow userWindow;
    private List<GiftEntity> gifts = new ArrayList<>();
    private List<FaceEntity> faces = new ArrayList<>();
    private boolean isShow = false;
    private int toid;
    private int giftId;
    private int roomId;
    private String ip;
    private int port;
    private int ssrc;
    private int topline;
    private String toName;
    private List<RoomUserInfo> userInfos = new ArrayList<>();
    private RoomUserInfo sendToUser;
    private UserAdapter userAdapter;
//    private Thread runThread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            while (isRunning) {
//                play.start();
//                Log.d("123","chongxingqidong");
//                roomMain.Start(roomId, Integer.parseInt(StartUtil.getUserId(TestActivity.this)), StartUtil.getUserPwd(TestActivity.this), ip, port);
//            }
//        }
//    });;
    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;

    private String roomIp;
    private App app;


    @Override
    public void before() {
        app = (App) getApplication();
//        if (app.getMgr() != null)
//            mgr = app.getMgr();
        Log.d("123","avModuleMgr-----"+mgr);
        roomIp = getIntent().getStringExtra("roomIp");
        String[] Ips = roomIp.split(";");
        String[] ports = Ips[0].split(":");
        ip = ports[0];
        port = Integer.parseInt(ports[1]);
        roomId = Integer.parseInt(getIntent().getStringExtra("roomId"));
        Log.d("123",roomId+"roomId");
        EventBus.getDefault().register(this);
    }


    @Override
    public void initView() {
//        runThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (isRunning) {
//                    play.start();
//                    roomMain.Start(roomId, Integer.parseInt(StartUtil.getUserId(TestActivity.this)), StartUtil.getUserPwd(TestActivity.this), ip, port);
//                }
//            }
//        });
        Log.d("123","oncreate---");
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        RoomUserInfo roomUser = new RoomUserInfo();
        roomUser.setUseralias("大厅");
        userInfos.add(roomUser);
        RoomChatMsg joinMsg = new RoomChatMsg();
        joinMsg.setSrcid(Integer.parseInt(StartUtil.getUserId(this)));
        joinMsg.setContent("加入了房间");
        data.add(joinMsg);
//        runThread.start();
        adapter = new RoomChatAdapter(data,this);
        listView.setAdapter(adapter);
        showWindow();
        showFace();
        showUser();
    }

    private void showUser() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pop_user_list,null);
        userList = (ListView) view.findViewById(R.id.room_user_list);

        userWindow = new PopupWindow(view);
        userWindow.setFocusable(true);
        userAdapter = new UserAdapter(userInfos,this);
        userList.setAdapter(userAdapter);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("123",gifts.get(position)+"------------>");
                sendToUser = userInfos.get(position);
                userSendBtn.setText(sendToUser.getUseralias());
                userWindow.dismiss();
            }
        });
        userWindow.setWidth(200);
        ColorDrawable dw = new ColorDrawable(0xb0ffffff);
        userWindow.setBackgroundDrawable(dw);
        userWindow.setHeight(500);
        userWindow.setOutsideTouchable(true);
    }

    private void showFace() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pop_face_grid,null);
        gridView = (GridView) view.findViewById(R.id.room_face_list);

        faceWindow = new PopupWindow(view);
        faceWindow.setFocusable(true);
        faces.addAll(FaceUtil.getFaces());
        FaceAdapter faceAdapter = new FaceAdapter(faces,this);
        gridView.setAdapter(faceAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("123",faces.get(position)+"------------>");
                if (position < 9) {
                    int faceNumber = position + 1;
                    editText.setText(editText.getText() + "/mr70" + faceNumber);
                }
                if (position >= 9) {
                    int faceNumber = position + 1;
                    editText.setText(editText.getText() + "/mr7" + faceNumber);
                }
                faceWindow.dismiss();
            }
        });
        faceWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0xb0ffffff);
        faceWindow.setBackgroundDrawable(dw);
        faceWindow.setHeight(300);
        faceWindow.setOutsideTouchable(true);
    }

    private void showWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pop_gift_grid,null);
        gridView = (GridView) view.findViewById(R.id.room_gift_list);
        giftSendBtn = (Button) view.findViewById(R.id.gift_send_btn);
        final TextView giftName  = (TextView)view.findViewById(R.id.gift_name_txt);
        final EditText giftCount = (EditText) view.findViewById(R.id.gift_count);

        popupWindow = new PopupWindow(view);
        popupWindow.setFocusable(true);
        gifts.addAll(GiftUtil.getGifts());
        GiftAdapter giftAdapter = new GiftAdapter(gifts,this);
        gridView.setAdapter(giftAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("123",gifts.get(position)+"------------>");
                giftId = gifts.get(position).getGiftId();
                giftName.setText(giftId+"");
//                popupWindow.dismiss();
            }
        });
        giftSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(giftCount.getText().toString());
                roomMain.getRoom().getChannel().sendGiftRecord(toid,giftId,count,topline,toName);
                popupWindow.dismiss();
            }
        });
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0xb0ffffff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setHeight(400);
        popupWindow.setOutsideTouchable(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_container:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;
            case R.id.chat_image_btn:
                popupWindow.dismiss();
                if (faceWindow.isShowing()){
                    faceWindow.dismiss();
                }else {
                    Log.d("123","showPop------------------");
                    faceWindow.showAsDropDown(faceButton);
                }
                break;
            case R.id.room_gift:
                faceWindow.dismiss();
                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else {
                    Log.d("123","showPop------------------");
                    popupWindow.showAsDropDown(giftImage);
                }
                break;
            case R.id.room_send_user:
                if (userWindow.isShowing()){
                    userWindow.dismiss();
                }else {
                    userWindow.showAsDropDown(userSendBtn);
                }
                break;
        }
    }
    @Override
    public void initData() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editText.getText())){
                    if (userSendBtn.getText().toString().contains("大厅"))
                    {
                        roomMain.getRoom().getChannel().sendChatMsg(0, (byte) 0, (byte) 0, "<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">" + editText.getText() + "</FONT>");
                        editText.setText("");
                    }else if (!TextUtils.isEmpty(sendToUser.getUseralias())) {
                        roomMain.getRoom().getChannel().sendChatMsg(sendToUser.getUserid(), (byte) 0, (byte) 1, "<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">" + editText.getText() + "</FONT>");
                        editText.setText("");
                    }else {
                        roomMain.getRoom().getChannel().sendChatMsg(0, (byte) 0, (byte) 0, "<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">" + editText.getText() + "</FONT>");
                        editText.setText("");
                    }
                }
            }
        });
        giftImage.setOnClickListener(this);
        faceButton.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        userSendBtn.setOnClickListener(this);
    }
    //接收礼物消息更新
    @Subscriber(tag="BigGiftRecord")
    public void getGiftRecord(BigGiftRecord obj){
        int getGiftId = obj.getGiftid();
        int count = obj.getCount();
        String giftTxt = "";
        for (int i = 0; i < gifts.size(); i++) {
            if (getGiftId == gifts.get(i).getGiftId()){
                if (getGiftId<10)
                    giftTxt = "/g100"+getGiftId +"   x "+ count;
                if (getGiftId>=10 && getGiftId < 100)
                    giftTxt = "/g10"+getGiftId +"   x "+ count;
                if (getGiftId >= 100 )
                    giftTxt = "/g1" + getGiftId +"    x"+ count;
                RoomChatMsg msg = new RoomChatMsg();
                msg.setContent("<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">"+giftTxt+"</FONT>");
                msg.setSrcid(obj.getSrcid());
                data.add(msg);
                adapter.notifyDataSetChanged();
                listView.setSelection(listView.getCount()-1);
            }
        }

    }
    @Subscriber(tag = "JoinRoomResponse")
    public void getJoinRoomResponse(JoinRoomResponse obj){
        topline = obj.getTopline() - 1000;
    }
    //接收服务器发送的消息更新列表
    @Subscriber(tag="RoomChatMsg")
    public void getRoomChatMsg(RoomChatMsg msg){
//        roomText.setText(Html.fromHtml(msg.getContent()));
        Log.d("123",msg.getContent());
        data.add(msg);
        userAdapter.notifyDataSetChanged();
        listView.setSelection(listView.getCount()-1);
    }
    //用户离开房间
    @Subscriber(tag = "RoomKickoutUserInfo")
    public void getUserOut(RoomKickoutUserInfo obj){
        int leaveId = obj.getToid();
        for (int i = 0; i < userInfos.size(); i++) {
            if (userInfos.get(i).getUserid() == leaveId){
                userInfos.remove(i);
            }
        }
        userAdapter.notifyDataSetChanged();
//        userList.setAdapter(new UserAdapter(userInfos,this));
    }
    //获取用户列表
    @Subscriber(tag = "userList")
    public void getUserList(RoomUserInfo userInfo){
        userInfos.add(userInfo);
        userAdapter.notifyDataSetChanged();
//        userList.setAdapter(new UserAdapter(userInfos,this));
        Log.d("123",userInfo.getUserid()+"-----------<<");
    }
    @Subscriber(tag = "onMicUser")
    public void getonMicUser(RoomUserInfo obj){
        toName = obj.getUseralias();
    }
    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
//        Log.d("123","onResume---");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    play.start();
                    Log.d("123", "chongxingqidong");
                    roomMain.Start(roomId, Integer.parseInt(StartUtil.getUserId(TestActivity.this)), StartUtil.getUserPwd(TestActivity.this), ip, port);
                }
            }
        }).start();
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        isRunning = true;
//        Log.d("123","onRestart---");
////        runThread.interrupt();
////        runThread.start();
////        startActivity(TestActivity_.intent(this)
////                .extra("roomIp",roomIp).extra("roomId",roomId+"").get());
//    }


    protected void onPause() {
        super.onPause();
        isRunning = false;
        Log.d("123","onPause---");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mgr != null) {
                    play.stop();
                    mgr.StopRTPSession();
                    mgr.Uninit();
                    mgr = null;
                }
            }
        }).start();
//        roomMain.getRoom().getChannel().sendLeaveRoom(Integer.parseInt(StartUtil.getUserId(this)));
//        roomMain.getRoom().onDisconnected();
    }

    @Override
    public void onStop() {
        Log.d("123","onStop---");
        isRunning = false;
        super.onStop();
        if (mgr != null){
            play.stop();
            mgr.StopRTPSession();
            mgr.Uninit();
        }
        roomMain.getRoom().getChannel().sendLeaveRoom(Integer.parseInt(StartUtil.getUserId(this)));
        roomMain.getRoom().onDisconnected();
//        runThread.interrupt();
//        finish();
    }

    public void StartAV(String ip, int port, int rand, int uid) {
//        if (app.getMgr() == null) {
            mgr.Init();
            Log.d("123", "===uid" + uid);
            mgr.CreateRTPSession(0);
            mgr.SetServerAddr2(ip, port, 0);
            mgr.StartRTPSession();
//        }

        if (rand < 1800000000)
            rand = 1800000000;

        ssrc = rand - uid;

        mgr.AddRTPRecver(0, ssrc, 99, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 99, 1);

        mgr.AddRTPRecver(0, ssrc, 97, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 97, 1);
        Log.d("123","ssrc====="+ssrc);
        mgr.AddAudioStream(ssrc, 1, this);
        mgr.AddVideoStream(ssrc,  0, 1, this);

    }

    @Override
    public void onVideo(int ssrc, int width, int height, byte[] img) {
        System.out.println("========== onVideo: " + width + ":" + height + "(" + img.length + ")");
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
        if (play != null) {
            play.setConfig(sample, channel);
            play.play(pcm);
        }
    }

    @Override
    public void onMic(String ip, int port, int rand, int uid) {
        toid = uid;
        // TODO Auto-generated method stub
        if(null == mgr) {
                mgr = new AVModuleMgr();
            Log.d("123","mgr-----new--"+mgr);
            StartAV(ip, port, rand, uid);
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("123","onDestory---");
        isRunning = false;
        super.onDestroy();
        if (mgr == null){

        }else {
            mgr.StopRTPSession();
            mgr.Uninit();
            play.stop();
        }
        EventBus.getDefault().unregister(this);
    }


}
