package com.mfzn.deepuses.purchasesellsave.capital.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class SalesPayAdapter extends BaseQuickAdapter<PayerPayeeDetailResponse.PayLogResponse, BaseViewHolder> {

    public SalesPayAdapter(@Nullable List<PayerPayeeDetailResponse.PayLogResponse> data) {
        super(R.layout.pay_log_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayerPayeeDetailResponse.PayLogResponse item) {
        helper.setText(R.id.date, DateUtils.longToString(item.getAddTime()))
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.money, item.getMoney())
                .setText(R.id.account_name, item.getAccountName());
    }
}
