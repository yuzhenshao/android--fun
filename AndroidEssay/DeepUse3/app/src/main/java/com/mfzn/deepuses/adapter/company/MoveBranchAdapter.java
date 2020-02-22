package com.mfzn.deepuses.adapter.company;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sun on 2018/6/12.
 */

public class MoveBranchAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnDelItemClickListener onDelItemClickListener = null;

    private List<String> staff;

    public MoveBranchAdapter(Context mContext, List<String> staff) {
        this.context = mContext;
        this.staff = staff;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_move_branch, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        String staffBeanX = staff.get(position);

        viewHolder.tv_br_name.setText(staffBeanX);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDelItemClickListener.onDelItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return staff.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_br_name;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            tv_br_name = itemView.findViewById(R.id.tv_br_name);
        }
    }

    public interface OnDelItemClickListener {
        void onDelItemClick(View view, int position);
    }

    public void setOnDelItemClickListener(OnDelItemClickListener listener) {
        this.onDelItemClickListener = listener;
    }
}
