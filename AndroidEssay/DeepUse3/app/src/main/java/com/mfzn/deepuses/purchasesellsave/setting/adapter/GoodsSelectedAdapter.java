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
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class GoodsSelectedAdapter extends BaseQuickAdapter<GoodsInfoResponse, BaseViewHolder> {

    protected Context context;

    public GoodsSelectedAdapter(Context context, @Nullable List<GoodsInfoResponse> data) {
        super(R.layout.goods_selected_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfoResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL +item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        }
        helper.setText(R.id.name, item.getGoodsName())
                .setText(R.id.price, context.getResources().getString(R.string.goods_price, item.getCostPrice()))
                .setText(R.id.number, context.getResources().getString(R.string.goods_size, item.getGoodsSumStockNum()));
    }
}
