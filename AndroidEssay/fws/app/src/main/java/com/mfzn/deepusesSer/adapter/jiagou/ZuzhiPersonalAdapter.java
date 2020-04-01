package com.mfzn.deepusesSer.adapter.jiagou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ZuzhiPersonalAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.StaffBeanXX> list;

    public ZuzhiPersonalAdapter(Context context, List<ZuzhiJiagouModel.StaffBeanXX> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_zuzhi_personal, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.StaffBeanXX staffBeanX = list.get(position);

        Glide.with(mContext).load(ApiHelper.BASE_URL + staffBeanX.getU_head()).into(viewHolder.ivZuItemIcon);
        viewHolder.ivZuItemName.setText(staffBeanX.getU_name());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_zu_item_icon)
        RoundImageView ivZuItemIcon;
        @BindView(R.id.iv_zu_item_name)
        TextView ivZuItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
