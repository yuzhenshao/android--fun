package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.OrderOtherInOutListResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class OrderOtherInOutAdapter extends BaseQuickAdapter<OrderOtherInOutListResponse.OrderOtherInOutResponse, BaseViewHolder> {

    protected Context context;

    public OrderOtherInOutAdapter(Context context, @Nullable List<OrderOtherInOutListResponse.OrderOtherInOutResponse> data) {
        super(R.layout.store_check_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderOtherInOutListResponse.OrderOtherInOutResponse item) {

    }
}
