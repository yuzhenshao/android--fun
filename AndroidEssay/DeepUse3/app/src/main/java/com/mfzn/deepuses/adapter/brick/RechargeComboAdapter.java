package com.mfzn.deepuses.adapter.brick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.brick.RechargeComboModel;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;


/**
 * Created by sun on 2018/6/12.
 */

public class RechargeComboAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RechargeComboModel> others;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener = null;

    public RechargeComboAdapter(Context mContext, List<RechargeComboModel> others) {
        this.context = mContext;
        this.others = others;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_recharge_combo, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MoreViewHolder bbnViewHolder = (MoreViewHolder) holder;

        RechargeComboModel othersBean = others.get(position);

        bbnViewHolder.tvRachItemZ.setText(othersBean.getZhuan() + "砖");

        bbnViewHolder.tvRachItemM.setText("￥" + doubleTrans1(Double.parseDouble(othersBean.getPrice())));

        int onlyOnce = othersBean.getOnlyOnce();//是否只能充值一次 1 shi
        if(onlyOnce == 1) {
            int isBuy = othersBean.getIsBuy();//0 首冲
            if(isBuy == 0) {
                bbnViewHolder.tvRachItemMs.setText("首充额外赠送" + othersBean.getGiftZhuan() +  "砖");
                bbnViewHolder.iv_rach_item_th.setVisibility(View.VISIBLE);

                bbnViewHolder.llRachItemM.setBackgroundResource(R.drawable.brick_recharge_screen_choice);
                bbnViewHolder.tvRachItemZ.setTextColor(context.getResources().getColor(R.color.color_8B572A));
                bbnViewHolder.tvRachItemMs.setTextColor(context.getResources().getColor(R.color.color_8B572A));
                bbnViewHolder.tvRachItemM.setTextColor(context.getResources().getColor(R.color.color_8B572A));
                if(othersBean.getClickType()) {
                    bbnViewHolder.llRachItemM.setSelected(true);
                }else {
                    bbnViewHolder.llRachItemM.setSelected(false);
                }
            }else {
                bbnViewHolder.tvRachItemMs.setText("赠送" + othersBean.getGiftZhuan() +  "砖");
                bbnViewHolder.iv_rach_item_th.setVisibility(View.GONE);

                bbnViewHolder.llRachItemM.setBackgroundResource(R.drawable.brick_edeff2__bg_shape);
                bbnViewHolder.tvRachItemZ.setTextColor(context.getResources().getColor(R.color.color_909399));
                bbnViewHolder.tvRachItemMs.setTextColor(context.getResources().getColor(R.color.color_909399));
                bbnViewHolder.tvRachItemM.setTextColor(context.getResources().getColor(R.color.color_909399));
            }
        }else {
            bbnViewHolder.tvRachItemMs.setText("赠送" + othersBean.getGiftZhuan() +  "砖");
            bbnViewHolder.iv_rach_item_th.setVisibility(View.GONE);

            bbnViewHolder.llRachItemM.setBackgroundResource(R.drawable.brick_recharge_screen_choice);
            bbnViewHolder.tvRachItemZ.setTextColor(context.getResources().getColor(R.color.color_8B572A));
            bbnViewHolder.tvRachItemMs.setTextColor(context.getResources().getColor(R.color.color_8B572A));
            bbnViewHolder.tvRachItemM.setTextColor(context.getResources().getColor(R.color.color_8B572A));
            if(othersBean.getClickType()) {
                bbnViewHolder.llRachItemM.setSelected(true);
            }else {
                bbnViewHolder.llRachItemM.setSelected(false);
            }
        }

        bbnViewHolder.llRachItemM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
            }
        });

//        bbnViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClick(v, position);
//            }
//        });
    }

    private String doubleTrans1(double num){
        if(num % 1.0 == 0){
            return String.valueOf((long)num);
        }
        return String.valueOf(num);
    }

    @Override
    public int getItemCount() {
        return others.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_rach_item_z)
        TextView tvRachItemZ;
        @BindView(R.id.tv_rach_item_m)
        TextView tvRachItemM;
        @BindView(R.id.tv_rach_item_ms)
        TextView tvRachItemMs;
        @BindView(R.id.ll_rach_item_m)
        LinearLayout llRachItemM;
        @BindView(R.id.iv_rach_item_th)
        ImageView iv_rach_item_th;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
