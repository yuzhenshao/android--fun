package com.mfzn.deepusesSer.adapter.project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShouhouAdapter extends BaseAdapter {

    private Context mContext;

    public ShouhouAdapter(Context context) {
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
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_shouhou, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvShItemTitle.getPaint().setFakeBoldText(true);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_sh_item_title)
        TextView tvShItemTitle;
        @BindView(R.id.tv_sh_item_name)
        TextView tvShItemName;
        @BindView(R.id.tv_sh_item_time)
        TextView tvShItemTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
