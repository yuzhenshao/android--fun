package com.mfzn.deepuses.purchasesellsave.capital.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.capital.MoneyAccountFinancialLogListResponse.ListBean.MoneyAccountFinancialLogResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class MoneyAccountFinancialLogAdapter extends BaseQuickAdapter<MoneyAccountFinancialLogResponse, BaseViewHolder> {
    public MoneyAccountFinancialLogAdapter(@Nullable List<MoneyAccountFinancialLogResponse> data) {
        super(R.layout.money_account_financial_log_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyAccountFinancialLogResponse item) {
        helper.setText(R.id.date, DateUtils.longToString(item.getAddTime()))
                .setText(R.id.money, (item.getType()==1?"+":"-") + item.getMoney())
                .setTextColor(R.id.money, Color.parseColor(item.getType()==1?"#62C33A":"#E02020"))
                .setText(R.id.nowMoney, "余额："+item.getNowMoney())
                .setText(R.id.order_num, item.getOrderNum() )
                .setText(R.id.show_name, item.getDataUserName()+"("+item.getOrderTypeName()+")");
    }
}