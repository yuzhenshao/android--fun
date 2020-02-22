package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sun on 2018/6/12.
 */

public class ShouliPhotoAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<String> lists;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnItemClickListener mOnItemClickListener = null;

    public ShouliPhotoAdapter(Context mContext, ArrayList<String> lists) {
        this.context = mContext;
        this.lists = lists;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_shouli_photo, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        if(position == 0) {
            viewHolder.tvAddItemHide2.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvAddItemHide2.setVisibility(View.GONE);
        }

        String s = lists.get(position);

        Glide.with(context).load(ApiHelper.BASE_URL + s).into(viewHolder.iv_acc_item_photo);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_add_item_hide2)
        TextView tvAddItemHide2;
        @BindView(R.id.iv_acc_item_photo)
        ImageView iv_acc_item_photo;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
