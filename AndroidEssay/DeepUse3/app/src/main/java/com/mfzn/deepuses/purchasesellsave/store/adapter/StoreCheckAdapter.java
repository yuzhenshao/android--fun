package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.StoreCheckResponse;

import java.util.List;

public class StoreCheckAdapter extends BaseQuickAdapter<StoreCheckResponse, BaseViewHolder> {

    protected Context context;

    public StoreCheckAdapter(Context context, @Nullable List<StoreCheckResponse> data) {
        super(R.layout.goods_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreCheckResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        }
        helper.setText(R.id.name, item.getGoodsName())
                .setText(R.id.price, context.getResources().getString(R.string.goods_sale_price, item.getSalePrice()))
                .setText(R.id.goods_stock_num, context.getResources().getString(R.string.goods_sum_stock, item.getGoodsSumStockNum()));
    }
}
