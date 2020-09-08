package com.mfzn.deepuses.purchasesellsave.capital.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeListResponse;

import java.util.List;

public class ShouldGatherePayAdapter extends BaseQuickAdapter<PayerPayeeListResponse.ListBean.PayerPayeeResponse, BaseViewHolder> {
    private int type;
    protected Context context;

    public ShouldGatherePayAdapter(Context context, int type, @Nullable List<PayerPayeeListResponse.ListBean.PayerPayeeResponse> data) {
        super(R.layout.should_gather_pay_itemview, data);
        this.context = context;
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, PayerPayeeListResponse.ListBean.PayerPayeeResponse item) {
        helper.setText(R.id.name, item.getCustomerOrSupplier())
                .setText(R.id.user_name, context.getResources().getString(R.string.store_user_name_phone,
                        item.getChargePerson(), item.getChargePersonPhone()))
                .setText(R.id.money, (type == 0 ? "应收：" : "应付") + item.getPayeeSumMoney() + " 元");
    }
}
