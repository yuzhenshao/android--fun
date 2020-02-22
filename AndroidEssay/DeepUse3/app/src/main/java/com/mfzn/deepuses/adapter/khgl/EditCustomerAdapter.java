package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sun on 2018/6/12.
 */

public class EditCustomerAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private List<XiangmuModel.DataBean.CustomersBean> datas;
    private OnAddItemClickListener mOnAddItemClickListener = null;
    private OnDeleteClickListener mOnDeleteClickListener = null;

    public EditCustomerAdapter(Context mContext, List<XiangmuModel.DataBean.CustomersBean> datas) {
        this.context = mContext;
        this.datas = datas;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_edit_customer, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        if(0 == position) {
            viewHolder.iv_add.setVisibility(View.VISIBLE);
            viewHolder.llshow.setVisibility(View.GONE);
            viewHolder.iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnAddItemClickListener.onItemAddClick(v, position);
                }
            });
        }else {
            viewHolder.iv_add.setVisibility(View.GONE);
            viewHolder.llshow.setVisibility(View.VISIBLE);

            XiangmuModel.DataBean.CustomersBean bean = datas.get(position);

            viewHolder.tv_name.setText(bean.getCustomerName());

            viewHolder.tv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnDeleteClickListener.onDeleteClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_del)
        ImageView tv_del;
        @BindView(R.id.iv_add)
        LinearLayout iv_add;
        @BindView(R.id.llshow)
        LinearLayout llshow;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnAddItemClickListener {
        void onItemAddClick(View view, int position);
    }

    public void setOnAddClickListener(OnAddItemClickListener listener) {
        this.mOnAddItemClickListener = listener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(View view, int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.mOnDeleteClickListener = listener;
    }
}
