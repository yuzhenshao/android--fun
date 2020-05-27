package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;

import java.util.List;

public class StoreCheckFilterAdapter extends BaseQuickAdapter<StoreResponse, BaseViewHolder> {

    private String curStoreId;

    public StoreCheckFilterAdapter(@Nullable List<StoreResponse> data) {
        super(R.layout.check_store_filter_item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreResponse item) {
        helper.setText(R.id.store_name, item.getStoreName());
        helper.setBackgroundColor(R.id.store_name, isCurSelected(item.getStoreID()) ?
                R.drawable.check_store_filter_selected_shape :
                R.drawable.check_store_filter_unselect_shape);
    }

    private boolean isCurSelected(String storeId) {
        return !TextUtils.isEmpty(curStoreId) && curStoreId.equals(storeId);
    }

    public void setStoreId(String storeId) {
        this.curStoreId = storeId;
    }
}
