package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.purchasesellsave.manager.JXCDataManager;
import com.mfzn.deepuses.purchasesellsave.setting.model.StoreWarnModel;

import java.util.List;

public class InventorySetAdapter extends BaseQuickAdapter<StoreResponse, BaseViewHolder> {

    protected Context context;

    public InventorySetAdapter(Context context, @Nullable List<StoreResponse> data) {
        super(R.layout.goods_inventory_set, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreResponse item) {
        helper.setText(R.id.shop_name, item.getStoreName());
        StoreWarnModel storeModel = JXCDataManager.getInstance().getStoreModel(item.getStoreID());
        helper.setText(R.id.setting_store, storeModel == null ? "去设置" : storeModel.getNumber())
                .setTextColor(R.id.setting_store, context.getResources().getColor(
                        storeModel==null ? R.color.color_909399 : R.color.color_303133));
    }
}