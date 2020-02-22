package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sun on 2018/6/12.
 */

public class ShareSelCusAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnPhotoItemClickListener mOnPhotoItemClickListener = null;

    private List<MyShareModel.DataBean.CustomerInfoBean> staff;

    public ShareSelCusAdapter(Context mContext, List<MyShareModel.DataBean.CustomerInfoBean> model) {
        this.context = mContext;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
        staff = new ArrayList<>();
        for (int i = 0; i < model.size(); i++) {
            MyShareModel.DataBean.CustomerInfoBean beanXX = model.get(i);
            if (beanXX.getSelectType()) {
                staff.add(beanXX);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_multiple_select, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        MyShareModel.DataBean.CustomerInfoBean dataBean = staff.get(position);
        String u_name = dataBean.getCustomerName();
        if(u_name.length() > 2) {
            String substring = u_name.substring(0, 2);
            viewHolder.tv_zu_item_icon.setText(substring);
        }else {
            viewHolder.tv_zu_item_icon.setText(u_name);
        }
    }

    @Override
    public int getItemCount() {
        return staff.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_zu_item_icon;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            tv_zu_item_icon = itemView.findViewById(R.id.tv_zu_item_icon);
        }
    }

    public interface OnPhotoItemClickListener {
        void onItemPhotoClick(View view, int position, ArrayList<String> lists, String text);
    }

    public void setOnPhotoClickListener(OnPhotoItemClickListener listener) {
        this.mOnPhotoItemClickListener = listener;
    }
}
