package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.purchasesellsave.manager.JXCDataManager;
import com.mfzn.deepuses.purchasesellsave.setting.model.StoreWarnModel;

import java.util.List;

public class InventorySetWarnAdapter extends BaseQuickAdapter<StoreResponse, BaseViewHolder> {

    public InventorySetWarnAdapter(@Nullable List<StoreResponse> data) {
        super(R.layout.store_warn_setting_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreResponse item) {
        helper.setText(R.id.store_name, item.getStoreName());
        StoreWarnModel storeModel = JXCDataManager.getInstance().getWarnStoreModel(item.getStoreID());
        helper.setGone(R.id.setting_store, storeModel == null);
        helper.setGone(R.id.set_container, storeModel != null);
        if (storeModel != null) {
            helper.setText(R.id.min_warn, storeModel.getMin() + "")
                    .setText(R.id.max_warn, storeModel.getMax() + "");
        }

    }
}