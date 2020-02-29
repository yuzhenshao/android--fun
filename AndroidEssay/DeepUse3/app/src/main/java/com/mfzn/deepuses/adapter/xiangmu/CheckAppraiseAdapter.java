package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;


/**
 * Created by sun on 2018/6/12.
 */

public class CheckAppraiseAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> others;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private ShouliPhotoAdapter.OnItemClickListener mOnItemClickListener = null;

    public CheckAppraiseAdapter(Context mContext, List<String> others) {
        this.context = mContext;
        this.others = others;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_check_appraise, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MoreViewHolder bbnViewHolder = (MoreViewHolder) holder;

        String othersBean = others.get(position);

        Glide.with(context).load(ApiHelper.BASE_URL + othersBean).into(bbnViewHolder.iv_ch_item_icon);

        bbnViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return others.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_ch_item_icon)
        ImageView iv_ch_item_icon;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }

//        public void setData(HomePageBean.DataBean.TwoBean bean) {
//            //已得到数据了
//            //设置适配器
//            //初始化应用列表
//            Glide.with(context).load(bean.icon).into(iv_more_icon);
//            tv_more_name.setText(bean.title);
//        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(ShouliPhotoAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
