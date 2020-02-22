package com.wevey.selector.dialog.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wevey.selector.dialog.R;
import com.wevey.selector.dialog.bean.ObtainProcessModel;

import java.util.List;

/**
 * Created by sun on 2018/12/7.
 */

public class ChoiseBuzhouAdapter extends BaseAdapter {

    private Context mContext;
    private List<ObtainProcessModel.MainNodesBean> mainNodes;

    public ChoiseBuzhouAdapter(Context context,List<ObtainProcessModel.MainNodesBean> mainNodes) {
        this.mContext = context;
        this.mainNodes = mainNodes;
    }

    @Override
    public int getCount() {
        return mainNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mainNodes.get(position);
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
            convertView = View.inflate(mContext, R.layout.listview_choise_buzhou,null);
            viewHolder.tv_item_ch_name = convertView.findViewById(R.id.tv_item_ch_name);
            viewHolder.iv_item_ch_type = convertView.findViewById(R.id.iv_item_ch_type);
            viewHolder.ll_item_ch_click = convertView.findViewById(R.id.ll_item_ch_click);

            final ObtainProcessModel.MainNodesBean mainNodesBean = mainNodes.get(position);

            viewHolder.tv_item_ch_name.setText(mainNodesBean.getName());

            if(mainNodesBean.getIsChoise()){
                viewHolder.iv_item_ch_type.setImageResource(R.drawable.ding_xuan);
            }else {
                viewHolder.iv_item_ch_type.setImageResource(R.drawable.ding_buxuan);
            }

            viewHolder.ll_item_ch_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mainNodesBean.getIsChoise()){
                        mainNodesBean.setIsChoise(false);
                        viewHolder.iv_item_ch_type.setImageResource(R.drawable.ding_buxuan);
                    }else {
                        mainNodesBean.setIsChoise(true);
                        viewHolder.iv_item_ch_type.setImageResource(R.drawable.ding_xuan);
                    }
                }
            });

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder{
        TextView tv_item_ch_name;
        ImageView iv_item_ch_type;
        LinearLayout ll_item_ch_click;
    }
}
