package com.mfzn.deepuses.purchasesellsave.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;

import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<ShopListResponse, BaseViewHolder> {

    protected Context context;

    public ShopAdapter(Context context, @Nullable List<ShopListResponse> data) {
        super(R.layout.shop_list_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopListResponse item) {
        helper.setText(R.id.shop_name, item.getShopName())
                .setText(R.id.shop_num, item.getShopNum())
                .setText(R.id.user_name, context.getResources().getString(R.string.shop_user_name, item.getUserName()))
                .setText(R.id.user_phone, context.getResources().getString(R.string.shop_user_phone, item.getContactPhone()));
    }
}
