package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.wevey.selector.dialog.bean.DetailsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sun on 2018/6/12.
 */

public class CustomerKhAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private List<DetailsModel.ProsBean.OtherCustomersBean> datas;
    private OnSeeItemClickListener mOnSeeItemClickListener = null;

    public CustomerKhAdapter(Context mContext, List<DetailsModel.ProsBean.OtherCustomersBean> datas) {
        this.context = mContext;
        this.datas = datas;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_customer_kh, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        DetailsModel.ProsBean.OtherCustomersBean bean = datas.get(position);

        viewHolder.tv_pr_kh_name.setText(bean.getCustomerName());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pr_kh_name)
        TextView tv_pr_kh_name;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnSeeItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnSeeItemClickListener(OnSeeItemClickListener listener) {
        this.mOnSeeItemClickListener = listener;
    }
}
