package com.mfzn.deepuses.adapter.company;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectManage3Adapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.StaffBean> list;

    public SelectManage3Adapter(Context context, List<ZuzhiJiagouModel.StaffBean> list) {
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
            convertView = View.inflate(mContext, R.layout.listview_select_manage3, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.StaffBean staffBeanX = list.get(position);

        String u_head = staffBeanX.getUserAvatar();
        if(!TextUtils.isEmpty(u_head)) {
            viewHolder.ivSeItemIcon.setVisibility(View.VISIBLE);
            viewHolder.tvZuItemIcon.setVisibility(View.GONE);
            Glide.with(mContext).load(ApiHelper.BASE_URL + u_head).into(viewHolder.ivSeItemIcon);
        }else {
            viewHolder.ivSeItemIcon.setVisibility(View.GONE);
            viewHolder.tvZuItemIcon.setVisibility(View.VISIBLE);
            String staffName = staffBeanX.getStaffName();
            if(staffName.length() > 2) {
                String substring = staffName.substring(staffName.length() - 2, staffName.length());
                viewHolder.tvZuItemIcon.setText(substring);
            }else {
                viewHolder.tvZuItemIcon.setText(staffName);
            }
        }
        viewHolder.ivSeItemName.setText(staffBeanX.getStaffName());

        if(staffBeanX.getMoren()) {
            viewHolder.ivSeItemSelect.setImageResource(R.mipmap.team_yixuan);
        }else {
            if (staffBeanX.getSelectType()) {
                viewHolder.ivSeItemSelect.setImageResource(R.mipmap.regi_xuanzhong);
            } else {
                viewHolder.ivSeItemSelect.setImageResource(R.mipmap.regi_weixuan);
            }
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_se_item_select)
        ImageView ivSeItemSelect;
        @BindView(R.id.iv_se_item_icon)
        RoundImageView ivSeItemIcon;
        @BindView(R.id.iv_se_item_name)
        TextView ivSeItemName;
        @BindView(R.id.tv_zu_item_icon)
        TextView tvZuItemIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
