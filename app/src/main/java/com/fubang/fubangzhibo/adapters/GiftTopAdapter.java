package com.fubang.fubangzhibo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.entities.GiftTopEntity;
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
 * 创建时间：2016-06-27 14:09
 * 修改人：dell
 * 修改时间：2016-06-27 14:09
 * 修改备注：
 */
public class GiftTopAdapter extends ListBaseAdapter<GiftTopEntity> {
    public GiftTopAdapter(List<GiftTopEntity> list, Context context) {
        super(list, context);
    }

    @Override

    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_gifttop_list,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.numberTv.setText(position+1+"");
        holder.nameTv.setText(list.get(position).getAnchorName());
        holder.ValueTv.setText(list.get(position).getNcount());
        return convertView;
    }
    static class ViewHolder {
        TextView numberTv, nameTv, ValueTv;

        public ViewHolder(View itemView) {
            numberTv = (TextView) itemView.findViewById(R.id.billboard_gifttop_number);
            nameTv = (TextView) itemView.findViewById(R.id.billboard_gifttop_name);
            ValueTv = (TextView) itemView.findViewById(R.id.billboard_gifttop_value);
        }
    }
}
