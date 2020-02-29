package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by sun on 2018/6/12.
 */

public class ProjectStagingAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<StagingListModel.EnginerBean> others;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    public ProjectStagingAdapter(Context mContext, List<StagingListModel.EnginerBean> others) {
        this.context = mContext;
        this.others = others;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_project_staging, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MoreViewHolder bbnViewHolder = (MoreViewHolder) holder;

        StagingListModel.EnginerBean othersBean = others.get(position);

        String u_head = othersBean.getUserAvatar();
        if (!TextUtils.isEmpty(u_head)) {
            Glide.with(context).load(ApiHelper.BASE_URL + u_head).into(bbnViewHolder.ivStagItemIcon);
        }
        bbnViewHolder.tvStagItemName.setText(othersBean.getUserName());
        bbnViewHolder.tvStagItemType.setText(othersBean.getLabelName());
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

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
