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

public class SupplierLogAdapter extends BaseQuickAdapter<SupplierDetailResponse.OrderListBean, BaseViewHolder> {

    public SupplierLogAdapter(@Nullable List<SupplierDetailResponse.OrderListBean> data) {
        super(R.layout.sales_log_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierDetailResponse.OrderListBean item) {
        helper.setBackgroundColor(R.id.line, Color.parseColor("#E02020"))
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.date_time, DateUtils.longToString(item.getOrderTime()))
                .setText(R.id.money, "订单金额：" + item.getRealMoney())
                .setText(R.id.has_done_money, "已收款：" + item.getHasDoneMoney())
                .setText(R.id.in_or_out_store, "出入库：" + storeStatus(item))
                .setText(R.id.pay_name,  "收款")
                .setVisible(R.id.pay_btn, Double.parseDouble(item.getNeedDoneMoney()) != 0)
                .addOnClickListener(R.id.pay_btn);
    }

    private String storeStatus(SupplierDetailResponse.OrderListBean item) {
        String storeStatus = "";
        switch (item.getIsInStore()) {
            case 0:
                storeStatus = "未入库";
                break;
            case 1:
                storeStatus = "入库中";
                break;
            case 2:
                storeStatus = "已入库";
                break;
        }
        return storeStatus;
    }
}
