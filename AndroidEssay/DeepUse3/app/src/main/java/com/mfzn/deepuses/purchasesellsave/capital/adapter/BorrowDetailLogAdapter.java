package com.mfzn.deepuses.purchasesellsave.capital.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.capital.BorrowInfoResponse;
import com.mfzn.deepuses.bean.response.settings.CustomerDetailResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class BorrowDetailLogAdapter extends BaseQuickAdapter<BorrowInfoResponse.HandleLogResponse, BaseViewHolder> {

    public BorrowDetailLogAdapter(@Nullable List<BorrowInfoResponse.HandleLogResponse> data) {
        super(R.layout.customer_pay_log_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BorrowInfoResponse.HandleLogResponse item) {
        helper.setText(R.id.money, item.getMoney())
                .setText(R.id.account_name, item.getUserName())
                .setText(R.id.order_num, item.getAccountName())
                .setText(R.id.date_time, DateUtils.longToString("yyyy/MM/dd", item.getAddTime()));
    }
}
