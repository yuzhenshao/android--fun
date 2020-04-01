package com.mfzn.deepusesSer.adapter.shouhou;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class ShouhouDjdAdapter extends RecyclerAdapter<WorkorderListModel.DataBean, ShouhouDjdAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public ShouhouDjdAdapter(Context context) {
        super(context);
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shouhou_gongdan_wjd, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        WorkorderListModel.DataBean model = data.get(position);
//
        holder.tvWoItemTitle.getPaint().setFakeBoldText(true);

        int shType = model.getAsType();
        if(shType == 1) {//0全部  1故障保修  2维护升级
            holder.tvWoItemTitle.setTextColor(context.getResources().getColor(R.color.color_3D7EFF));
        }else if(shType == 2) {
            holder.tvWoItemTitle.setTextColor(context.getResources().getColor(R.color.color_62C33A));
        }
        holder.tvWoItemTitle.setText(model.getShTypeName());
        holder.tvWoItemType.setTextColor(context.getResources().getColor(R.color.white));
        holder.tvWoItemType.setText(model.getOrderNo());
        holder.ll_wo_item_gcs.setVisibility(View.GONE);
        holder.ll_wo_item_pai.setVisibility(View.VISIBLE);
        holder.tv_wo_item_jd.setVisibility(View.VISIBLE);
        holder.ll_wo_item_qux.setVisibility(View.VISIBLE);
        holder.ll_wo_item_slr.setVisibility(View.VISIBLE);
        String u_name = model.getReceiverInfo().getUserName();
        if (u_name!= null && u_name.length() > 0){
            holder.tvWoItemSltx.setText(u_name.substring(0,1));
        }else{
            holder.tvWoItemSltx.setText(u_name);
        }

        holder.tvWoItemSlname.setText(u_name);

        holder.tvWoItemContent.setText(model.getContent());
        holder.tvWoItemAddress.setText(model.getDetailAddress());
        holder.tvWoItemTime.setText(model.getWishTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(v, v.getTag().toString(), position);
                }
            }
        });
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_wo_item_title)
        TextView tvWoItemTitle;
        @BindView(R.id.tv_wo_item_type)
        TextView tvWoItemType;
        @BindView(R.id.tv_wo_item_content)
        TextView tvWoItemContent;
        @BindView(R.id.tv_wo_item_address)
        TextView tvWoItemAddress;
        @BindView(R.id.tv_wo_item_time)
        TextView tvWoItemTime;
        @BindView(R.id.tv_wo_item_sltx)
        TextView tvWoItemSltx;
        @BindView(R.id.tv_wo_item_slname)
        TextView tvWoItemSlname;
        @BindView(R.id.tv_wo_item_sctx)
        TextView tvWoItemSctx;
        @BindView(R.id.tv_wo_item_gcname)
        TextView tvWoItemGcname;
        @BindView(R.id.tv_wo_item_pg)
        TextView tv_wo_item_pg;
        @BindView(R.id.tv_wo_item_sl)
        TextView tv_wo_item_sl;
        @BindView(R.id.ll_wo_item_slr)
        LinearLayout ll_wo_item_slr;
        @BindView(R.id.ll_wo_item_qux)
        LinearLayout ll_wo_item_qux;
        @BindView(R.id.ll_wo_item_gcs)
        LinearLayout ll_wo_item_gcs;
        @BindView(R.id.ll_wo_item_pai)
        LinearLayout ll_wo_item_pai;
        @BindView(R.id.tv_wo_item_jd)
        TextView tv_wo_item_jd;

        public MsgBusinessHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
