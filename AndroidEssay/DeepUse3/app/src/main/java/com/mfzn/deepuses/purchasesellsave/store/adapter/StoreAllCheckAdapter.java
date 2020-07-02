package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.StoreCheckResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class StoreAllCheckAdapter extends BaseQuickAdapter<StoreCheckResponse, BaseViewHolder> {

    protected Context context;

    public StoreAllCheckAdapter(Context context, @Nullable List<StoreCheckResponse> data) {
        super(R.layout.store_all_check_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreCheckResponse item) {
        helper.setText(R.id.store_name, item.getStoreName())
                .setText(R.id.start_time, context.getResources().getString(R.string.start_time, DateUtils.longToString(item.getStartTime())))
                .setText(R.id.end_time, context.getResources().getString(R.string.end_time, DateUtils.longToString(item.getEndTime())))
                .setImageResource(R.id.store_check_icon, item.getStatusResId());
    }
}
