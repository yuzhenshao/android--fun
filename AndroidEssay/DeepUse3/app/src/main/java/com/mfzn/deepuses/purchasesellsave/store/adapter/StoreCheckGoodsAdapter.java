package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckGoodsResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class StoreCheckGoodsAdapter extends BaseQuickAdapter<GoodsInfoResponse, BaseViewHolder> {

    protected Context context;

    public StoreCheckGoodsAdapter(Context context, @Nullable List<GoodsInfoResponse> data) {
        super(R.layout.stock_check_goods_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfoResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        }
        helper.setText(R.id.name, item.getGoodsName())
                .setText(R.id.goods_num, item.getGoodsNum())
                .setText(R.id.system_sum_count, context.getResources().getString(R.string.system_sum_count, item.getSystemStockNum()+""))
                .setText(R.id.check_sum_count, context.getResources().getString(R.string.check_sum_count, item.getCheckStockNum()+""))
                .setText(R.id.system_check_stock_num, context.getResources().getString(R.string.system_check_sum_count, item.getCheckStockNum() - item.getSystemStockNum()));
    }
}
