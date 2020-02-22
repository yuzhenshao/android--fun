package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.model.xmhf.VisitRrcordModel;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class VisitRecordAdapter extends RecyclerAdapter<VisitRrcordModel.DataBean, VisitRecordAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public VisitRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_visit_record, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        VisitRrcordModel.DataBean bean = data.get(position);

        holder.tvReItemTitle.getPaint().setFakeBoldText(true);

        String title = bean.getTitle();
        if(title.equals("无回访问题")) {
            holder.tvReItemTitle.setText("无回访问题");
            holder.tvReItemTitle.setTextColor(context.getResources().getColor(R.color.color_3D7EFF));
        }else if(title.equals("有回访问题")) {
            holder.tvReItemTitle.setText("有回访问题");
            holder.tvReItemTitle.setTextColor(context.getResources().getColor(R.color.color_FBBD27));
        }

        holder.tvReItemContent.setText(bean.getContent());

        String u_name = bean.getU_name();
        String substring = u_name.substring(0, 1);
        holder.tvReItemTx.setText(substring);
        holder.tvReItemName.setText(u_name);
        holder.tvReItemTime.setText(bean.getNowDate());
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_re_item_title)
        TextView tvReItemTitle;
        @BindView(R.id.tv_re_item_content)
        TextView tvReItemContent;
        @BindView(R.id.tv_re_item_tx)
        TextView tvReItemTx;
        @BindView(R.id.tv_re_item_name)
        TextView tvReItemName;
        @BindView(R.id.tv_re_item_time)
        TextView tvReItemTime;

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
