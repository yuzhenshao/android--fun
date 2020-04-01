package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

public class CustomerProAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private List<DetailsModel.ProsBean> datas;

    public CustomerProAdapter(Context mContext, List<DetailsModel.ProsBean> datas) {
        this.context = mContext;
        this.datas = datas;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_customer_pro, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        DetailsModel.ProsBean bean = datas.get(position);

        viewHolder.tvPrItemSum.setText(String.valueOf(position + 1));
        viewHolder.tvPrItemName.setText(bean.getProName());
        viewHolder.tvPrItemAddress.setText(bean.getAreaName());
        viewHolder.tvPrItemDetails.setText(bean.getDetailAddress());

        List<DetailsModel.ProsBean.OtherCustomersBean> otherCustomers = bean.getOtherCustomers();
        if(otherCustomers != null && otherCustomers.size() != 0) {
            viewHolder.llPrItemKh.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.khRecycleview.setLayoutManager(layoutManager);
            CustomerKhAdapter recycleAdapter = new CustomerKhAdapter(context,otherCustomers);
            viewHolder.khRecycleview.setAdapter(recycleAdapter);
        }else {
            viewHolder.llPrItemKh.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pr_item_sum)
        TextView tvPrItemSum;
        @BindView(R.id.tv_pr_item_name)
        TextView tvPrItemName;
        @BindView(R.id.tv_pr_item_address)
        TextView tvPrItemAddress;
        @BindView(R.id.tv_pr_item_details)
        TextView tvPrItemDetails;
        @BindView(R.id.kh_recycleview)
        RecyclerView khRecycleview;
        @BindView(R.id.ll_pr_item_kh)
        LinearLayout llPrItemKh;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
