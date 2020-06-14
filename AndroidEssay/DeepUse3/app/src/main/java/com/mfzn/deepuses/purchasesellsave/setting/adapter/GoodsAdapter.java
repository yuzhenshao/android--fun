package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

/**
 * @author yz @date 2020-05-03
 */
public class GoodsAdapter extends BaseQuickAdapter<CommodityRequest, BaseViewHolder> {

    protected Context context;

    public GoodsAdapter(Context context, @Nullable List<CommodityRequest> data) {
        super(R.layout.goods_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityRequest item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        } else {
            helper.setImageResource(R.id.icon_goods, R.mipmap.icon_no_data);
        }
        helper.setText(R.id.name, item.getGoodsName())
                .setText(R.id.price, context.getResources().getString(R.string.goods_sale_price, item.getSalePrice()))
                .setText(R.id.goods_stock_num, context.getResources().getString(R.string.goods_sum_stock, item.getGoodsSumStockNum()));
    }
}
