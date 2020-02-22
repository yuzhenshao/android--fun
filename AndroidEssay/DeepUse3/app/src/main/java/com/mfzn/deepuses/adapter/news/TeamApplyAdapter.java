package com.mfzn.deepuses.adapter.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.project.ProjectNewsAdapter;
import com.mfzn.deepuses.model.xiangmu.ProjectNewsModel;
import com.mfzn.deepuses.model.xx.TeamApplyModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.view.RoundImageView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;


public class TeamApplyAdapter extends RecyclerAdapter<TeamApplyModel.DataBean, TeamApplyAdapter.MsgBusinessHolder> {

    private Context mContext;
    private OnNoItemClickListener onNoItemClickListener = null;
    private OnYesItemClickListener onYesItemClickListener = null;

    public TeamApplyAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_team_apply, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        TeamApplyModel.DataBean bean = data.get(position);

        String u_head = bean.getU_head();
        if(!TextUtils.isEmpty(u_head)) {
            Glide.with(context).load(ApiHelper.BASE_URL + u_head).into(holder.ivPrItemIcon);
        }
        holder.tvPrItemTime.setText(DateUtils.stampDateTime(bean.getAddtime() + ""));
        holder.tvPrItemName.setText(bean.getStaffName());
        holder.tvPrItemJoin.setText(bean.getCompanyName());
        holder.tvPrItemXx.setText(bean.getFrom());
        holder.tvPrItemLy.setText(bean.getRemark());

        int isPass = bean.getIsPass();
        if(isPass == 0) {//还没处理
            holder.tvPrItemType.setVisibility(View.GONE);
            holder.llPrItemShow.setVisibility(View.VISIBLE);
        }else if(isPass == 1) {//同意
            holder.tvPrItemType.setVisibility(View.VISIBLE);
            holder.llPrItemShow.setVisibility(View.GONE);
            holder.tvPrItemType.setText("已通过");
            holder.tvPrItemType.setTextColor(context.getResources().getColor(R.color.color_3D7EFF));
        }else if(isPass == 2) {//拒绝
            holder.tvPrItemType.setVisibility(View.VISIBLE);
            holder.llPrItemShow.setVisibility(View.GONE);
            holder.tvPrItemType.setText("已拒绝");
            holder.tvPrItemType.setTextColor(context.getResources().getColor(R.color.color_909399));
        }else {
            holder.tvPrItemType.setVisibility(View.GONE);
            holder.llPrItemShow.setVisibility(View.GONE);
        }

        holder.ivPrItemNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoItemClickListener != null) {
                    onNoItemClickListener.onNoItemClick(v, position);
                }
            }
        });
        holder.ivPrItemYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onYesItemClickListener != null) {
                    onYesItemClickListener.onYesItemClick(v, position);
                }
            }
        });
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_pr_item_icon)
        RoundImageView ivPrItemIcon;
        @BindView(R.id.tv_pr_item_name)
        TextView tvPrItemName;
        @BindView(R.id.tv_pr_item_time)
        TextView tvPrItemTime;
        @BindView(R.id.tv_pr_item_type)
        TextView tvPrItemType;
        @BindView(R.id.tv_pr_item_join)
        TextView tvPrItemJoin;
        @BindView(R.id.tv_pr_item_xx)
        TextView tvPrItemXx;
        @BindView(R.id.tv_pr_item_ly)
        TextView tvPrItemLy;
        @BindView(R.id.iv_pr_item_no)
        ImageView ivPrItemNo;
        @BindView(R.id.iv_pr_item_yes)
        ImageView ivPrItemYes;
        @BindView(R.id.ll_pr_item_show)
        LinearLayout llPrItemShow;

        public MsgBusinessHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnNoItemClickListener {
        void onNoItemClick(View view, int position);
    }

    public void setOnNoItemClickListener(OnNoItemClickListener listener) {
        this.onNoItemClickListener = listener;
    }

    public interface OnYesItemClickListener {
        void onYesItemClick(View view, int position);
    }

    public void setOnYesItemClickListener(OnYesItemClickListener listener) {
        this.onYesItemClickListener = listener;
    }
}
