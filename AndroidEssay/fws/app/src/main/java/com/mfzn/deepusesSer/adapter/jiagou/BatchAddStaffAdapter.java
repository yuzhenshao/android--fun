package com.mfzn.deepusesSer.adapter.jiagou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BatchAddStaffAdapter extends BaseAdapter {

    private Context mContext;
    private String types;
    private List<ZuzhiJiagouModel.StaffBeanXX> staff;
    private List<ZuzhiJiagouModel.SonsBeanX.StaffBeanX> staff2;
    private List<ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean> staff3;

    public BatchAddStaffAdapter(Context context, ZuzhiJiagouModel model, String types,int positions,int positions2) {
        this.mContext = context;
        this.types = types;
        switch (types) {
            case "1":
                staff = model.getStaff();
                break;
            case "2":
                staff2 = model.getSons().get(positions).getStaff();
                break;
            case "3":
                staff3 = model.getSons().get(positions).getSons().get(positions2).getStaff();
                break;
        }
    }

    @Override
    public int getCount() {
        switch (types) {
            case "1":
                return staff.size();
            case "2":
                return staff2.size();
            case "3":
                return staff3.size();
            default:
                return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        switch (types) {
            case "1":
                return staff.get(position);
            case "2":
                return staff2.get(position);
            case "3":
                return staff3.get(position);
            default:
                return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_batch_add_staff, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String u_head = null;
        String u_name = null;
        boolean selectType = false;

        switch (types) {
            case "1":
                ZuzhiJiagouModel.StaffBeanXX staffBeanX = staff.get(position);
                u_head = staffBeanX.getU_head();
                u_name = staffBeanX.getU_name();
                selectType = staffBeanX.getSelectType();
                break;
            case "2":
                ZuzhiJiagouModel.SonsBeanX.StaffBeanX staffBeanX2 = staff2.get(position);
                u_head = staffBeanX2.getU_head();
                u_name = staffBeanX2.getU_name();
                selectType = staffBeanX2.getSelectType();
                break;
            case "3":
                ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean staffBeanX3 = staff3.get(position);
                u_head = staffBeanX3.getU_head();
                u_name = staffBeanX3.getU_name();
                selectType = staffBeanX3.getSelectType();
                break;
        }

        Glide.with(mContext).load(ApiHelper.BASE_URL + u_head).into(viewHolder.ivBaItemIcon);
        viewHolder.ivBaItemName.setText(u_name);

        if (selectType) {
            viewHolder.ivBaItemSelect.setImageResource(R.mipmap.regi_xuanzhong);
        } else {
            viewHolder.ivBaItemSelect.setImageResource(R.mipmap.regi_weixuan);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_ba_item_select)
        ImageView ivBaItemSelect;
        @BindView(R.id.iv_ba_item_icon)
        RoundImageView ivBaItemIcon;
        @BindView(R.id.iv_ba_item_name)
        TextView ivBaItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
