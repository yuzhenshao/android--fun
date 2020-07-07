package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.SupplierCustomerInfoResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class SupplierOrderAdapter extends BaseMultiItemQuickAdapter<SupplierCustomerInfoResponse.OrderListBean, BaseViewHolder> {

    private int one = 1;
    private int two = 2;

    public SupplierOrderAdapter(@Nullable List<SupplierCustomerInfoResponse.OrderListBean> data) {
        super(data);
        addItemType(one, R.layout.supplier_order_list_item);
        addItemType(two, R.layout.supplier_order_pay_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierCustomerInfoResponse.OrderListBean item) {
        helper.setText(R.id.supplier_order_id, item.getOrderID())
        .setText(R.id.supplier_order_name, item.getOrderName())
        .setText(R.id.supplier_order_num, item.getOrderNum())
        .setText(R.id.total_money, item.getMoney())
        .setText(R.id.store_name, item.getStatus()+"")
        .setText(R.id.time, DateUtils.longToString("yyyy/MM/dd",item.getOrderTime()));
    }
}
