package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.CustomerDetailResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class CustomerPlayLogAdapter extends BaseQuickAdapter<CustomerDetailResponse.PayLogListBean, BaseViewHolder> {

    public CustomerPlayLogAdapter(@Nullable List<CustomerDetailResponse.PayLogListBean> data) {
        super(R.layout.customer_pay_log_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerDetailResponse.PayLogListBean item) {
        helper.setText(R.id.money, item.getMoney())
                .setText(R.id.account_name, item.getAccountName())
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.date_time, DateUtils.longToString("yyyy/MM/dd", item.getAddTime()));
    }
}
