package com.wevey.selector.dialog.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wevey.selector.dialog.R;
import com.wevey.selector.dialog.bean.SelectModel;

import java.util.List;

/**
 * Created by sun on 2018/12/7.
 */

public class GenjinListviewAdapter extends BaseAdapter {

    private Context mContext;
    private List<SelectModel.FollowStatusBean> listModel;
    private int selectedId;

    public GenjinListviewAdapter(Context context, List<SelectModel.FollowStatusBean> listModel,int id) {
        this.mContext = context;
        this.listModel = listModel;
        this.selectedId = id;
    }

    @Override
    public int getCount() {
        return listModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.listview_genjin_listview,null);
            viewHolder.tv_item_bo_name = convertView.findViewById(R.id.tv_item_bo_name);
            viewHolder.iv_gen_select = convertView.findViewById(R.id.iv_gen_select);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SelectModel.FollowStatusBean bottomListviewModel = listModel.get(position);
        
        viewHolder.tv_item_bo_name.setText(bottomListviewModel.getName());
        
        if(bottomListviewModel.getData_id() == selectedId) {
            viewHolder.iv_gen_select.setImageResource(R.drawable.regi_xuanzhong);
        }else {
            viewHolder.iv_gen_select.setImageResource(R.drawable.regi_weixuan);
        }

        return convertView;
    }

    class ViewHolder{
        TextView tv_item_bo_name;
        ImageView iv_gen_select;
    }
}
