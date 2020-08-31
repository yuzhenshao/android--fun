package com.mfzn.deepuses.purchasesellsave.capital.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.capital.BorrowListResponse;
import com.mfzn.deepuses.bean.response.capital.IncomeExpenseListResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class BorrowAdapter extends BaseQuickAdapter<BorrowListResponse.ListBean.BorrowResponse, BaseViewHolder> {

    protected Context context;

    public BorrowAdapter(Context context, @Nullable List<BorrowListResponse.ListBean.BorrowResponse> data) {
        super(R.layout.borrow_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BorrowListResponse.ListBean.BorrowResponse item) {
        helper.setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.borrow_user,"借贷人：" +item.getBorrowUser())
                .setText(R.id.money, "金额：" + item.getMoney() + " 元")
                .setText(R.id.account_name, "结算账户：" + item.getAccountName())
                .setText(R.id.time, DateUtils.longToString(item.getDataTime()));

        TextView statusView = helper.getView(R.id.status);
        if (item.getIsFinished() == 2) {
            statusView.setText("已结清");
            statusView.setTextColor(Color.parseColor("#62C33A"));
        } else if (item.getHasDone() == 0) {
            statusView.setText("待结");
            statusView.setTextColor(Color.parseColor("#E02020"));
        } else {
            statusView.setText("已结：" + item.getHasDone() + " 元");
            statusView.setTextColor(Color.parseColor("#FF9815"));
        }
    }
}
