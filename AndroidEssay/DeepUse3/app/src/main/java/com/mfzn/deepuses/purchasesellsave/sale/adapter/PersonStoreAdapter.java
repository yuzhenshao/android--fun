package com.mfzn.deepuses.purchasesellsave.sale.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.sale.PersonalStoreListResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class PersonStoreAdapter extends BaseQuickAdapter<PersonalStoreListResponse.PersonalStoreResponse, BaseViewHolder> {

    protected Context context;

    public PersonStoreAdapter(Context context, @Nullable List<PersonalStoreListResponse.PersonalStoreResponse> data) {
        super(R.layout.goods_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonalStoreListResponse.PersonalStoreResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        } else {
            helper.setImageResource(R.id.icon_goods, R.mipmap.icon_no_data);
        }
        helper.setText(R.id.name, item.getGoodsName())
                .setText(R.id.price, "领取总量："+item.getTakeSumNum())
                .setText(R.id.goods_stock_num, "剩余量："+(item.getStockNum()-item.getTakeSumNum()));
    }
}
