package com.mfzn.deepuses.adapter.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.ProjectChengyModel;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;


/**
 * Created by sun on 2018/6/12.
 */

public class ProjectDetailsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ProjectChengyModel> others;
    private int type = -1;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnDeleteItemClickListener onDeleteItemClickListener = null;
    private OnAddItemClickListener onAddItemClickListener = null;

    public ProjectDetailsAdapter(Context mContext, List<ProjectChengyModel> others) {
        this.context = mContext;
        this.others = others;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_project_details, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MoreViewHolder bbnViewHolder = (MoreViewHolder) holder;

        ProjectChengyModel othersBean = others.get(position);

        if(position == 0) {
            bbnViewHolder.ivStagItemIcon.setImageResource(R.mipmap.pro_add2);
            bbnViewHolder.tvStagItemName.setText("添加");

            bbnViewHolder.ivStagItemIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAddItemClickListener.onAddItemClick(v,position);
                }
            });
        }else {
            String u_head = othersBean.getU_head();
            if(!TextUtils.isEmpty(u_head)) {
                Glide.with(context).load(ApiHelper.BASE_URL + u_head).into(bbnViewHolder.ivStagItemIcon);
            }
            bbnViewHolder.tvStagItemName.setText(othersBean.getU_name());
            bbnViewHolder.tvStagItemType.setText(othersBean.getLabelName());
        }

        if(type == 1) {
            if(position == 0) {
                bbnViewHolder.iv_stag_item_delete.setVisibility(View.GONE);
            }else {
                bbnViewHolder.iv_stag_item_delete.setVisibility(View.VISIBLE);
            }
        }else {
            bbnViewHolder.iv_stag_item_delete.setVisibility(View.GONE);
        }

        bbnViewHolder.iv_stag_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteItemClickListener.onDeteleItemClick(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return others.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_stag_item_icon)
        RoundImageView ivStagItemIcon;
        @BindView(R.id.tv_stag_item_name)
        TextView tvStagItemName;
        @BindView(R.id.tv_stag_item_type)
        TextView tvStagItemType;
        @BindView(R.id.iv_stag_item_delete)
        ImageView iv_stag_item_delete;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnDeleteItemClickListener {
        void onDeteleItemClick(View view, int position);
    }

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener listener) {
        this.onDeleteItemClickListener = listener;
    }

    public interface OnAddItemClickListener {
        void onAddItemClick(View view, int position);
    }

    public void setOnAddItemClickListener(OnAddItemClickListener listener) {
        this.onAddItemClickListener = listener;
    }

    public void setType(int type){
        this.type = type;
        notifyDataSetChanged();
    }
}
