package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.GoodsStockResponse;
import com.mfzn.deepuses.bean.response.store.StockWarningResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class GoodsStockAdapter extends BaseQuickAdapter<GoodsStockResponse.ListBean.StockResponse, BaseViewHolder> {

    protected Context context;

    public GoodsStockAdapter(Context context, @Nullable List<GoodsStockResponse.ListBean.StockResponse> data) {
        super(R.layout.stock_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsStockResponse.ListBean.StockResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        }
        helper.setText(R.id.name, item.getGoodsName())
                .setText(R.id.goods_stock_num, context.getResources().getString(R.string.goods_sum_stock, item.getSumStockNum()));

        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VISIBLE, false));
        recyclerView.setAdapter(new StockAdapter(item.getStoreStockNum()));
        recyclerView.getAdapter().notifyDataSetChanged();
        ImageView imageView = helper.getView(R.id.icon_store_container);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(recyclerView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
    }

    class StockAdapter extends BaseQuickAdapter<GoodsStockResponse.ListBean.StockResponse.StoreStockNumBean, BaseViewHolder> {
        public StockAdapter(@Nullable List<GoodsStockResponse.ListBean.StockResponse.StoreStockNumBean> data) {
            super(R.layout.store_stock_item_view, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, GoodsStockResponse.ListBean.StockResponse.StoreStockNumBean store) {
            holder.setText(R.id.store_name, store.getStoreName())
                    .setText(R.id.store_num, store.getStockNum() + "");
        }
    }
}
