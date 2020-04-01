package com.mfzn.deepusesSer.adapter.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mfzn.deepusesSer.R;


public class TeamApplyAdapter extends BaseAdapter {

    private Context mContext;
    private OnItemClickLisenter mOnItemClickLisenter;

    public TeamApplyAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.listview_team_apply,null);
            viewHolder.iv_app_item_yes = convertView.findViewById(R.id.iv_app_item_yes);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.iv_app_item_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickLisenter.onItemClick(position);
            }
        });

        return convertView;
    }

    class ViewHolder{
        ImageView iv_app_item_yes;
    }

    public interface OnItemClickLisenter {
        void onItemClick(int position);
    }

    public void setOnItemClickLisenter(OnItemClickLisenter onItemClickLisenter) {
        this.mOnItemClickLisenter = onItemClickLisenter;
    }
}
