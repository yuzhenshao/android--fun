package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class GoodsCheckAdapter extends BaseQuickAdapter<GoodsInfoResponse, BaseViewHolder> {

    protected Context context;

    public GoodsCheckAdapter(Context context, @Nullable List<GoodsInfoResponse> data) {
        super(R.layout.goods_check_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfoResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        }
        helper.setText(R.id.name, item.getGoodsName())
                .setText(R.id.price, "¥" + item.getSalePriceWithTax())
                .setText(R.id.number, "*" + item.getGoodsCount());
    }
}
