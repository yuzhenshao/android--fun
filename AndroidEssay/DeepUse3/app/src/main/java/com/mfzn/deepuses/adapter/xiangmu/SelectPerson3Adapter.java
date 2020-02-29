package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectPerson3Adapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.StaffBeanXX> list;
    private int type;

    public SelectPerson3Adapter(Context context, List<ZuzhiJiagouModel.StaffBeanXX> list) {
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
            convertView = View.inflate(mContext, R.layout.listview_select_person3, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.StaffBeanXX staffBeanX = list.get(position);

        String u_head = staffBeanX.getU_head();
        if(!TextUtils.isEmpty(u_head)) {
            viewHolder.ivPerItemIcon.setVisibility(View.VISIBLE);
            viewHolder.tvZuItemIcon.setVisibility(View.GONE);
            Glide.with(mContext).load(ApiHelper.BASE_URL + u_head).into(viewHolder.ivPerItemIcon);
        }else {
            viewHolder.ivPerItemIcon.setVisibility(View.GONE);
            viewHolder.tvZuItemIcon.setVisibility(View.VISIBLE);
            String staffName = staffBeanX.getStaffName();
            if(staffName.length() > 2) {
                String substring = staffName.substring(staffName.length() - 2, staffName.length());
                viewHolder.tvZuItemIcon.setText(substring);
            }else {
                viewHolder.tvZuItemIcon.setText(staffName);
            }
        }
        viewHolder.ivPerItemName.setText(staffBeanX.getU_name());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_per_item_icon)
        RoundImageView ivPerItemIcon;
        @BindView(R.id.iv_per_item_name)
        TextView ivPerItemName;
        @BindView(R.id.tv_zu_item_icon)
        TextView tvZuItemIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
