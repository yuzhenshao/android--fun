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
import com.mfzn.deepuses.bean.response.settings.StoreResponse;

import java.util.List;

public class StoreAdapter extends BaseQuickAdapter<StoreResponse, BaseViewHolder> {

    protected Context context;

    public StoreAdapter(Context context, @Nullable List<StoreResponse> data) {
        super(R.layout.store_list_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreResponse item) {
        helper.setText(R.id.store_name, item.getStoreName())
                .setText(R.id.user_name, context.getResources().getString(R.string.store_user_name_phone, item.getUserName(), item.getContactPhone()))
                .setText(R.id.store_address, context.getResources().getString(R.string.store_address,
                        TextUtils.isEmpty(item.getStoreAddress()) ? "暂无" : item.getStoreAddress()));
    }
}
