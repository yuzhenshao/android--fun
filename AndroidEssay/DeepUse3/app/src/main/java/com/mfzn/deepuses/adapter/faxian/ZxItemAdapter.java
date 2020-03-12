package com.mfzn.deepuses.adapter.faxian;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.DateUtils;


import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class ZxItemAdapter extends RecyclerAdapter<News.DataBean, ZxItemAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public ZxItemAdapter(Context context) {
        super(context);
    }
    private ImageWatcherHelper iwHelper;

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_faxian_news_item, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        News.DataBean model = data.get(position);
        holder.tvTitle.setText(model.getNewsTitle());
        holder.tvFbr.setText(model.getSourceName());
        holder.tvFbsj.setText(DateUtils.stampToDate(String.valueOf(model.getAddTime()),"yyyy.MM.dd"));
        holder.tvBiaoqian.setText(model.getNewsCategoryName());
        if (model.getHits() > 999){
            holder.tvYdl.setText("999+");
        }else{
            holder.tvYdl.setText(String.valueOf(model.getHits()));
        }
        holder.tvDes.setText(model.getSummary());
        if (!TextUtils.isEmpty(model.getTitleImage())){
            Glide.with(context).load(ApiHelper.BASE_URL + model.getTitleImage()).into(holder.ivImg);
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

        @BindView(R.id.tv_fbr)
        TextView tvFbr;
        @BindView(R.id.tv_fbsj)
        TextView tvFbsj;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_biaoqian)
        TextView tvBiaoqian;
        @BindView(R.id.tv_ydl)
        TextView tvYdl;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.iv_img)
        ImageView ivImg;

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
