package com.mfzn.deepuses.adapter.project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepuses.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemplateSelectAdapter extends BaseAdapter {

    private Context mContext;

    public TemplateSelectAdapter(Context context) {
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
            convertView = View.inflate(mContext, R.layout.listview_template_select, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTeItemTitle.getPaint().setFakeBoldText(true);

        if(position == 0) {
            viewHolder.tvTeItemTj.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvTeItemTj.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_te_item_title)
        TextView tvTeItemTitle;
        @BindView(R.id.tv_te_item_name)
        TextView tvTeItemName;
        @BindView(R.id.tv_te_item_number)
        TextView tvTeItemNumber;
        @BindView(R.id.tv_te_item_cishu)
        TextView tvTeItemCishu;
        @BindView(R.id.tv_te_item_tj)
        TextView tvTeItemTj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
