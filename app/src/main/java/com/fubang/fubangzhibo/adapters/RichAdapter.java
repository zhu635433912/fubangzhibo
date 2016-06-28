package com.fubang.fubangzhibo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.entities.RichEntity;
import com.zhuyunjian.library.ListBaseAdapter;

import java.util.List;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * <p/>
 * 项目名称：MyApplication
 * 类描述：
 * 创建人：dell
 * 创建时间：2016-06-24 16:10
 * 修改人：dell
 * 修改时间：2016-06-24 16:10
 * 修改备注：
 */
public class RichAdapter extends ListBaseAdapter<RichEntity> {
    public RichAdapter(List<RichEntity> list, Context context) {
        super(list, context);
    }

    @Override

    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_rich_list,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.numberTv.setText(position+1+"");
        holder.nameTv.setText(list.get(position).getRichName());
        holder.ValueTv.setText(list.get(position).getNusermoney());
        if (list.get(position).getAnchorName().equals("null")){
            holder.LikerTv.setText("");
        }else {
            holder.LikerTv.setText(list.get(position).getAnchorName());
        }
        return convertView;
    }
    static class ViewHolder {
        TextView numberTv, nameTv, ValueTv, LikerTv;

        public ViewHolder(View itemView) {
            numberTv = (TextView) itemView.findViewById(R.id.billboard_rich_number);
            nameTv = (TextView) itemView.findViewById(R.id.billboard_rich_name);
            ValueTv = (TextView) itemView.findViewById(R.id.billboard_rich_value);
            LikerTv = (TextView) itemView.findViewById(R.id.billboard_rich_liker);
        }
    }
}
