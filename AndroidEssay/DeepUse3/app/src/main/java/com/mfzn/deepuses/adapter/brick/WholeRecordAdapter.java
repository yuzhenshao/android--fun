package com.mfzn.deepuses.adapter.brick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.model.brick.TransactionRecordModel;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.DoubleUtils;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class WholeRecordAdapter extends RecyclerAdapter<TransactionRecordModel.FinancialLogBean.DataBean, WholeRecordAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public WholeRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_whole_brick, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        TransactionRecordModel.FinancialLogBean.DataBean model = data.get(position);

        holder.tvWoItemTitle.setText(model.getNote());

        int type = model.getType();//1收入2支出
        int isZhuan = model.getIsZhuan();//1砖 2人民币
        if(type == 1) {
            holder.tvWoItemType.setTextColor(context.getResources().getColor(R.color.color_3D7EFF));
            holder.tvWoItemMz.setTextColor(context.getResources().getColor(R.color.color_3D7EFF));
            holder.tvWoItemMz.setText("+" + DoubleUtils.doubleTrans1(Double.parseDouble(model.getMoney())));
        }else if(type == 2) {
            holder.tvWoItemType.setTextColor(context.getResources().getColor(R.color.color_d0021b));
            holder.tvWoItemMz.setTextColor(context.getResources().getColor(R.color.color_d0021b));
            holder.tvWoItemMz.setText("-" + DoubleUtils.doubleTrans1(Double.parseDouble(model.getMoney())));
        }
        if(isZhuan == 1) {//0全部  1故障保修  2维护升级
            holder.tvWoItemType.setText("砖");
        }else if(isZhuan == 2) {
            holder.tvWoItemType.setText("元");
        }
        holder.tvWoItemTime.setText(DateUtils.stampToDateTime2(String.valueOf(model.getAddTime())));

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnItemClickListener != null) {
//                    //注意这里使用getTag方法获取数据
//                    mOnItemClickListener.onItemClick(v, v.getTag().toString(), position);
//                }
//            }
//        });
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_wo_item_title)
        TextView tvWoItemTitle;
        @BindView(R.id.tv_wo_item_type)
        TextView tvWoItemType;
        @BindView(R.id.tv_wo_item_time)
        TextView tvWoItemTime;
        @BindView(R.id.tv_wo_item_mz)
        TextView tvWoItemMz;

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
