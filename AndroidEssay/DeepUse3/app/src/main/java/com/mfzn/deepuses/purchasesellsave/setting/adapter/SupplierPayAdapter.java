package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.SupplierDetailResponse;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class SupplierPayAdapter extends BaseQuickAdapter<SupplierDetailResponse.PayOrGatheringLogListBean, BaseViewHolder> {

    public SupplierPayAdapter(@Nullable List<SupplierDetailResponse.PayOrGatheringLogListBean> data) {
        super(R.layout.pay_log_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierDetailResponse.PayOrGatheringLogListBean item) {
        helper.setText(R.id.date, DateUtils.longToString(item.getAddTime()))
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.money, item.getMoney())
                .setTextColor(R.id.money, Color.parseColor("#E02020"))
                .setText(R.id.account_name, item.getAccountName());
    }
}
