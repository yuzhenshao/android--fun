package com.mfzn.deepuses.purchasesellsave.capital.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class IncomeExpenseAdapter extends BaseQuickAdapter<IncomeExpenseListResponse.ListBean.IncomeExpenseResponse, BaseViewHolder> {

    protected Context context;

    public IncomeExpenseAdapter(Context context, @Nullable List<IncomeExpenseListResponse.ListBean.IncomeExpenseResponse> data) {
        super(R.layout.income_expense_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, IncomeExpenseListResponse.ListBean.IncomeExpenseResponse item) {
        helper.setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.money, "金额：" + item.getMoney() + " 元")
                .setText(R.id.type_name, item.getTypeName())
                .setText(R.id.account_name, "结算账户：" + item.getAccountName())
                .setText(R.id.time, DateUtils.longToString(item.getDataTime()))
                .setImageResource(R.id.check_icon, getStatusResId(item.getCheckStatus()));
    }

    public int getStatusResId(int status) {//0.待审核1通过2拒绝
        switch (status) {
            case 0:
                return R.mipmap.examine_pending;
            case 1:
                return R.mipmap.examine_pass;
            case 2:
                return R.mipmap.examine_unpass;
        }
        return R.mipmap.examine_pending;
    }
}
