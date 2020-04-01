package com.mfzn.deepusesSer.adapter.project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressAdapter extends BaseAdapter {

    private Context mContext;

    public ProgressAdapter(Context context) {
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
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_progress, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvPrItemTitle.getPaint().setFakeBoldText(true);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_pr_item_title)
        TextView tvPrItemTitle;
        @BindView(R.id.tv_pr_item_name)
        TextView tvPrItemName;
        @BindView(R.id.tv_pr_item_time)
        TextView tvPrItemTime;
        @BindView(R.id.tv_pr_item_sheji)
        TextView tvPrItemSheji;
        @BindView(R.id.tv_pr_item_jl)
        TextView tvPrItemJl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
