package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.OrderStockCheckInfoResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class StoreGoodsCheckAdapter extends BaseQuickAdapter<OrderStockCheckInfoResponse.GoodsInfoBean, BaseViewHolder> {

    protected Context context;

    public StoreGoodsCheckAdapter(Context context, @Nullable List<OrderStockCheckInfoResponse.GoodsInfoBean> data) {
        super(R.layout.store_goods_check_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderStockCheckInfoResponse.GoodsInfoBean item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        }
        helper.setText(R.id.name, item.getGoodsName())
                .setText(R.id.goods_num, item.getGoodsNum())
                .setText(R.id.check_stock_num, "盘点库存：" + item.getCheckStockNum())
                .setText(R.id.system_stock_num, "系统库存：" + item.getSystemStockNum())
                .setText(R.id.check_change_num, "盘盈盘亏  " + item.getCheckChangeNum());
    }
}
