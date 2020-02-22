package com.wevey.selector.dialog.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wevey.selector.dialog.R;
import com.wevey.selector.dialog.bean.BottomListviewModel;
import com.wevey.selector.dialog.bean.ObtainProcessModel;

import java.util.List;

/**
 * Created by sun on 2018/12/7.
 */

public class BottomListviewAdapter extends BaseAdapter {

    private Context mContext;
    private List<BottomListviewModel> listModel;

    public BottomListviewAdapter(Context context,List<BottomListviewModel> listModel) {
        this.mContext = context;
        this.listModel = listModel;
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
            convertView = View.inflate(mContext, R.layout.listview_bottom_listview,null);
            viewHolder.tv_item_bo_name = convertView.findViewById(R.id.tv_item_bo_name);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BottomListviewModel bottomListviewModel = listModel.get(position);
        
        viewHolder.tv_item_bo_name.setText(bottomListviewModel.getName());
        
        if(bottomListviewModel.getType() == 1) {
            viewHolder.tv_item_bo_name.setTextColor(mContext.getResources().getColor(R.color.color_303133));
        }else if(bottomListviewModel.getType() == 2) {
            viewHolder.tv_item_bo_name.setTextColor(mContext.getResources().getColor(R.color.color_D0021B));
        }

        return convertView;
    }

    class ViewHolder{
        TextView tv_item_bo_name;
    }
}
