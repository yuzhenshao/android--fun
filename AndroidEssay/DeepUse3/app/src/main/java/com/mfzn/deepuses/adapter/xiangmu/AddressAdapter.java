package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.amap.api.services.core.PoiItem;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.utils.OnItemClickLisenter;

import java.util.List;

/**
 * 地址的适配器
 * Created by XiaoFu on 2018-01-11 15:00.
 * 注释：
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyHolder> {
    private Context mContext;
    private List<PoiItem> mList;
    private int selectPosition = -1;
    private OnItemClickLisenter mOnItemClickLisenter;

    public AddressAdapter(Context context, List<PoiItem> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setList(List<PoiItem> list) {
        this.mList = list;
        selectPosition = -1;
        notifyDataSetChanged();
    }

    public void setSelectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }

    public int getSelectPositon(){
        return selectPosition;
    }

    public void setOnItemClickLisenter(OnItemClickLisenter onItemClickLisenter) {
        this.mOnItemClickLisenter = onItemClickLisenter;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder myHolder = new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_address_info, parent, false));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemView.setTag(position);
        PoiItem poiItem = mList.get(position);
        if (position == 0) {
            holder.iv_dui.setVisibility(View.VISIBLE);
        } else {
            holder.iv_dui.setVisibility(View.INVISIBLE);
        }
        holder.mTvTitle.setText(poiItem.getTitle());
        holder.mTvMessage.setText(poiItem.getSnippet());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                setSelectPosition(position);
                if (null != mOnItemClickLisenter) {
                    mOnItemClickLisenter.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        TextView mTvMessage;
        ImageView iv_dui;


        public MyHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvMessage = (TextView) itemView.findViewById(R.id.tv_message);
            iv_dui = (ImageView) itemView.findViewById(R.id.iv_dui);
        }
    }
}
