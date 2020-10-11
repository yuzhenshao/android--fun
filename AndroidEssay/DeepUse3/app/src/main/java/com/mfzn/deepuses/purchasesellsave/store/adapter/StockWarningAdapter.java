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
import com.mfzn.deepuses.bean.response.store.StockWarningResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

/**
 * @author yz @date 2020-05-03
 */
public class StockWarningAdapter extends BaseQuickAdapter<StockWarningResponse.StockWarning, BaseViewHolder> {

    protected Context context;

    public StockWarningAdapter(Context context, @Nullable List<StockWarningResponse.StockWarning> data) {
        super(R.layout.stock_warn_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StockWarningResponse.StockWarning item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL +item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        }
        helper.setText(R.id.name, item.getGoodsName()+"  ["+item.getGoodsNum()+"]")
                .setText(R.id.goods_stock_num, context.getResources().getString(R.string.goods_sum_stock, item.getStockNum()))
        .addOnClickListener(R.id.status);
    }
}
