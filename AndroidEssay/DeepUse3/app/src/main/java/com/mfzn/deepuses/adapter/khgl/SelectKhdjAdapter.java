package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.home.HomeShowModel;
import com.wevey.selector.dialog.bean.SelectModel;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;


/**
 * Created by sun on 2018/6/12.
 */

public class SelectKhdjAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<SelectModel.CustomerLevelBean> others;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener = null;

    public SelectKhdjAdapter(Context mContext, List<SelectModel.CustomerLevelBean> others) {
        this.context = mContext;
        this.others = others;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_select_khdj, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MoreViewHolder bbnViewHolder = (MoreViewHolder) holder;

        SelectModel.CustomerLevelBean othersBean = others.get(position);

        bbnViewHolder.tv_iten_bane.setText(othersBean.getLevelName());

        if(othersBean.getSelect()) {
            bbnViewHolder.tv_iten_bane.setSelected(true);
        }else {
            bbnViewHolder.tv_iten_bane.setSelected(false);
        }

        bbnViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return others.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_iten_bane)
        TextView tv_iten_bane;

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
