package com.mfzn.deepusesSer.adapter.project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectCopyAdapter extends BaseAdapter {

    private Context mContext;

    public ProjectCopyAdapter(Context context) {
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
            convertView = View.inflate(mContext, R.layout.listview_project_copy, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvPrItemTitle.getPaint().setFakeBoldText(true);

        if (position == 0) {
            viewHolder.tvPrItemTj.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvPrItemTj.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_pr_item_title)
        TextView tvPrItemTitle;
        @BindView(R.id.tv_pr_item_name)
        TextView tvPrItemName;
        @BindView(R.id.tv_pr_item_number)
        TextView tvPrItemNumber;
        @BindView(R.id.tv_pr_item_cishu)
        TextView tvPrItemCishu;
        @BindView(R.id.tv_pr_item_tj)
        TextView tvPrItemTj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
