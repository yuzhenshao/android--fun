package com.mfzn.deepuses.adapter.fg;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartmentAdapter;

public class WorkRecommendAdapter extends BaseAdapter {

    private Context mContext;

    public WorkRecommendAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView = View.inflate(mContext, R.layout.listview_work_recommend,null);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    class ViewHolder{
    }
}
