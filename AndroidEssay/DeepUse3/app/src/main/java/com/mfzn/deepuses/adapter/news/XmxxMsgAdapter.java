package com.mfzn.deepuses.adapter.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xx.MsgTdxxModel;
import com.mfzn.deepuses.utils.DateUtils;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class XmxxMsgAdapter extends RecyclerAdapter<MsgTdxxModel.DataBean, XmxxMsgAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnRecyclerViewItemLongClickListener mOnItemLongClickListener = null;
    private OnItemLookClickListener onItemLookClickListener = null;

    public XmxxMsgAdapter(Context context) {
        super(context);
    }
    private ImageWatcherHelper iwHelper;

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_tdxx_msg_item, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        MsgTdxxModel.DataBean model = data.get(position);
        if (model.getIsRead() == 0){
            holder.vTip.setVisibility(View.VISIBLE);
        }else{
            holder.vTip.setVisibility(View.GONE);
        }

        int type = model.getType(); //1 项目消息 2团队消息 3系统消息
        if(type == 3) {
            holder.tv_xx_item_title.setText("系统消息");
        }else if(type == 2) {
            holder.tv_xx_item_title.setText("团队消息");
        }else if(type == 1) {
            holder.tv_xx_item_title.setText("项目消息");
        }

        int pageType = model.getExtra().getPageType();
        if(pageType == 1) {
            holder.ll_item_msg_look.setVisibility(View.VISIBLE);
        }else {
            holder.ll_item_msg_look.setVisibility(View.GONE);
        }

        holder.tvTime.setText(DateUtils.stampToDateTime(String.valueOf(model.getAddTime())));
        holder.tvContent.setText(model.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(v, v.getTag().toString(), position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemLongClickListener.onItemLongClick(v, v.getTag().toString(), position);
                }
                return true;
            }
        });

        holder.ll_item_msg_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemLookClickListener.onItemLookClick(v,model.getExtra().getOrderNo(),position);
            }
        });
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.v_tip)
        View vTip;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_xx_item_title)
        TextView tv_xx_item_title;
        @BindView(R.id.ll_item_msg_look)
        LinearLayout ll_item_msg_look;

        public MsgBusinessHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);

        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(View view, String data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public interface OnItemLookClickListener {
        void onItemLookClick(View view, String data, int position);
    }

    public void setOnItemLookClickListener(OnItemLookClickListener listener) {
        this.onItemLookClickListener = listener;
    }
}
