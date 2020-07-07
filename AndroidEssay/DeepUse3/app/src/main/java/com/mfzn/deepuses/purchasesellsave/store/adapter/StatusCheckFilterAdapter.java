package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.purchasesellsave.store.model.StoreStatusFilter;

import java.util.List;

public class StatusCheckFilterAdapter extends BaseQuickAdapter<StoreStatusFilter, BaseViewHolder> {

    private int curStatusId=-1;

    public StatusCheckFilterAdapter(@Nullable List<StoreStatusFilter> data) {
        super(R.layout.check_store_filter_item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreStatusFilter item) {
        helper.setText(R.id.store_name, item.getStatusText());
        helper.setBackgroundColor(R.id.store_name, isCurSelected(item.getStatus()) ?
                R.drawable.check_store_filter_selected_shape :
                R.drawable.check_store_filter_unselect_shape);
    }

    private boolean isCurSelected(int statusId) {
        return curStatusId==statusId;
    }

    public void setStoreId(int statusId) {
        this.curStatusId = statusId;
    }
}
