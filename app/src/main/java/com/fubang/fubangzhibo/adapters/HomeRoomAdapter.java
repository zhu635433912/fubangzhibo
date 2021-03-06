package com.fubang.fubangzhibo.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.entities.RoomListEntity;
import com.fubang.fubangzhibo.ui.TestActivity_;
import com.zhuyunjian.library.ListBaseAdapter;

import java.util.List;

/**
 * 首页房间列表的适配器
 * Created by dell on 2016/4/7.
 */
public class HomeRoomAdapter extends ListBaseAdapter<RoomListEntity>{
    private List<RoomListEntity> list;

    public HomeRoomAdapter(List<RoomListEntity> list, Context context) {
        super(list, context);
        this.list = list;
    }
    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getItemView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_room, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
//        if (position == 0){
////            holder.simpleDraweeView.setVisibility(View.GONE);
////            holder.roomLayout.setVisibility(View.GONE);
//        }else {
            holder.simpleDraweeView.setImageURI(Uri.parse(list.get(position).getRoompic()));
            holder.roomNumber.setText(list.get(position).getRscount() + "/" + list.get(position).getRoomrs());
            holder.roomText.setText(list.get(position).getRoomname());
            holder.roomLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("123",position+"-----------------");
                    context.startActivity(TestActivity_.intent(context)
                            .extra("roomIp",list.get(position).getGateway()).extra("roomId",list.get(position).getRoomid()).get());
                }
            });
//        }
        return convertView;
    }

    static class ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView roomNumber,roomText;
        LinearLayout roomLayout;

        public ViewHolder(View itemView) {
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.home_room_pic);
            roomNumber = (TextView)itemView.findViewById(R.id.home_room_number);
            roomText = (TextView) itemView.findViewById(R.id.home_room_name);
            roomLayout = (LinearLayout) itemView.findViewById(R.id.home_room_layout);
        }
    }
}
