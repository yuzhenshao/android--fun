package com.mfzn.deepusesSer.adapter.fg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.xiangmu.XiangmuModel;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class XiangmuAdapter extends RecyclerAdapter<XiangmuModel.DataBean, XiangmuAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public XiangmuAdapter(Context context) {
        super(context);
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_xiangmu, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        XiangmuModel.DataBean bean = data.get(position);

        holder.tvXmItemTitle.getPaint().setFakeBoldText(true);
        holder.tvXmItemTitle.setText(bean.getPro_name());
        holder.tvXmItemName.setText(bean.getCustomName());
        holder.tvXmItemTime.setText(bean.getStart_time() + "~" + bean.getEnd_time());

        int customLevel = bean.getCustomLevel();
        if (customLevel == 4) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_yixing);
        } else if (customLevel == 5) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_erxing);
        } else if (customLevel == 6) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_sanxing);
        } else if (customLevel == 7) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_sixing);
        } else if (customLevel == 8) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_wuxing);
        }

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

        @BindView(R.id.tv_xm_item_title)
        TextView tvXmItemTitle;
        @BindView(R.id.tv_xm_item_name)
        TextView tvXmItemName;
        @BindView(R.id.iv_xm_item_type)
        ImageView ivXmItemType;
        @BindView(R.id.tv_xm_item_time)
        TextView tvXmItemTime;

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
