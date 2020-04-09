package com.mfzn.deepuses.adapter.jiagou;

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


public class ManageJiagouAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZuzhiJiagouModel.StaffBean> list;
    private int type;

    public ManageJiagouAdapter(Context context, List<ZuzhiJiagouModel.StaffBean> list) {
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
            convertView = View.inflate(mContext, R.layout.listview_manage_jiagou, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZuzhiJiagouModel.StaffBean staffBeanX = list.get(position);

        String u_head = staffBeanX.getUserAvatar();
        if(!TextUtils.isEmpty(u_head)) {
            viewHolder.ivZuItemIcon.setVisibility(View.VISIBLE);
            viewHolder.tvZuItemIcon.setVisibility(View.GONE);
            Glide.with(mContext).load(ApiHelper.BASE_URL + u_head).into(viewHolder.ivZuItemIcon);
        }else {
            viewHolder.ivZuItemIcon.setVisibility(View.GONE);
            viewHolder.tvZuItemIcon.setVisibility(View.VISIBLE);
            String staffName = staffBeanX.getStaffName();
            if(!TextUtils.isEmpty(staffName)&&staffName.length() > 2) {
                String substring = staffName.substring(staffName.length() - 2, staffName.length());
                viewHolder.tvZuItemIcon.setText(substring);
            }else {
                viewHolder.tvZuItemIcon.setText(staffName);
            }
        }
        viewHolder.ivZuItemName.setText(staffBeanX.getStaffName());

        if(type == 1) {
            viewHolder.ivZuItemBj.setVisibility(View.VISIBLE);
        }else {
            viewHolder.ivZuItemBj.setVisibility(View.GONE);
        }

        //"roleID": 1:创建者  2：超级管理员  3：普普通通管理员 4：普通员工
        int roleID = staffBeanX.getRoleID();
        if(roleID == 1) {
            viewHolder.tv_zu_item_type.setVisibility(View.VISIBLE);
            viewHolder.tv_zu_item_type.setText("创建者");
        }else if(roleID == 2) {
            viewHolder.tv_zu_item_type.setVisibility(View.VISIBLE);
            viewHolder.tv_zu_item_type.setText("管理员");
        }else {
            viewHolder.tv_zu_item_type.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_zu_item_icon)
        RoundImageView ivZuItemIcon;
        @BindView(R.id.iv_zu_item_name)
        TextView ivZuItemName;
        @BindView(R.id.tv_zu_item_type)
        TextView tv_zu_item_type;
        @BindView(R.id.iv_zu_item_bj)
        ImageView ivZuItemBj;
        @BindView(R.id.tv_zu_item_icon)
        TextView tvZuItemIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setShow(int type){
        this.type = type;
        notifyDataSetChanged();
    }
}
