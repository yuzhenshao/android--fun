package com.mfzn.deepusesSer.adapter.jiagou;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sun on 2018/6/12.
 */

public class MoveStaffAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnPhotoItemClickListener mOnPhotoItemClickListener = null;

    private String types;
    private List<ZuzhiJiagouModel.StaffBeanXX> staff;
    private List<ZuzhiJiagouModel.SonsBeanX.StaffBeanX> staff2;
    private List<ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean> staff3;

    public MoveStaffAdapter(Context mContext, ZuzhiJiagouModel model, String types, int positions, int positions2) {
        this.context = mContext;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);

        this.types = types;
        switch (types) {
            case "1":
                staff = new ArrayList<>();
                List<ZuzhiJiagouModel.StaffBeanXX> modelStaff = model.getStaff();
                for (int i = 0; i < modelStaff.size(); i++) {
                    ZuzhiJiagouModel.StaffBeanXX beanXX = modelStaff.get(i);
                    if (beanXX.getSelectType()) {
                        staff.add(beanXX);
                    }
                }
                break;
            case "2":
                staff2 = new ArrayList<>();
                List<ZuzhiJiagouModel.SonsBeanX.StaffBeanX> staff = model.getSons().get(positions).getStaff();
                for (int i = 0; i < staff.size(); i++) {
                    ZuzhiJiagouModel.SonsBeanX.StaffBeanX beanXX = staff.get(i);
                    if (beanXX.getSelectType()) {
                        this.staff2.add(beanXX);
                    }
                }
                break;
            case "3":
                staff3 = new ArrayList<>();
                List<ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean> staff1 = model.getSons().get(positions).getSons().get(positions2).getStaff();
                for (int i = 0; i < staff1.size(); i++) {
                    ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean staff1s = staff1.get(i);
                    if (staff1s.getSelectType()) {
                        this.staff3.add(staff1s);
                    }
                }
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_move_staff, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        String u_head = null;

        switch (types) {
            case "1":
                ZuzhiJiagouModel.StaffBeanXX staffBeanX = staff.get(position);
                u_head = staffBeanX.getU_head();
                break;
            case "2":
                ZuzhiJiagouModel.SonsBeanX.StaffBeanX staffBeanX2 = staff2.get(position);
                u_head = staffBeanX2.getU_head();
                break;
            case "3":
                ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean staffBeanX3 = staff3.get(position);
                u_head = staffBeanX3.getU_head();
                break;
        }

        Glide.with(context).load(ApiHelper.BASE_URL + u_head).into(viewHolder.iv_ry_item_icon);
    }

    @Override
    public int getItemCount() {
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

    class MoreViewHolder extends RecyclerView.ViewHolder {

        private RoundImageView iv_ry_item_icon;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            iv_ry_item_icon = itemView.findViewById(R.id.iv_ry_item_icon);
        }
    }

    public interface OnPhotoItemClickListener {
        void onItemPhotoClick(View view, int position, ArrayList<String> lists, String text);
    }

    public void setOnPhotoClickListener(OnPhotoItemClickListener listener) {
        this.mOnPhotoItemClickListener = listener;
    }
}