package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.StoresInfoResponse;

import java.util.List;

public class StockLogAdapter extends BaseQuickAdapter<StoresInfoResponse, BaseViewHolder> {
    public StockLogAdapter(@Nullable List<StoresInfoResponse> data) {
        super(R.layout.goods_stores_log_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoresInfoResponse item) {
        helper.setText(R.id.store_name, item.getStoreName())
                .setText(R.id.store_size, item.getStockNum() + "")
                .addOnClickListener(R.id.log_btn);
    }
}
