package com.mfzn.deepuses.purchasesellsave.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.ShopCheckerResponse;
import com.mfzn.deepuses.bean.response.shop.ShopUserListResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class ShopAuthSetAdapter extends BaseQuickAdapter<ShopCheckerResponse, BaseViewHolder> {

    protected Context context;

    public ShopAuthSetAdapter(Context context, @Nullable List<ShopCheckerResponse> data) {
        super(R.layout.shop_auth_set_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCheckerResponse item) {
        helper.setText(R.id.order_name, item.getOrderName())
                .setText(R.id.user_name, item.getUserName());
    }
}
