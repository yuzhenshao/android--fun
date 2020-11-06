package com.mfzn.deepuses.purchasesellsave.capital.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.capital.PayerPayeeDetailResponse;
import com.mfzn.deepuses.bean.response.settings.SalesRecordResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class SalesLogAdapter extends BaseQuickAdapter<PayerPayeeDetailResponse.OrderListResponse, BaseViewHolder> {
    private int capitalType;

    public SalesLogAdapter(@Nullable List<PayerPayeeDetailResponse.OrderListResponse> data, int capitalType) {
        super(R.layout.sales_log_item, data);
        this.capitalType = capitalType;
    }

    @Override
    protected void convert(BaseViewHolder helper, PayerPayeeDetailResponse.OrderListResponse item) {
        helper.setBackgroundColor(R.id.line, Color.parseColor(item.getStatus() == 0 ? "#E02020" : "#3D7EFF"))
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.date_time, DateUtils.longToString(item.getOrderTime()))
                .setText(R.id.money, "订单金额：" + item.getMoney())
                .setText(R.id.has_done_money, "已收款：" + item.getHasDoneMoney())
                .setText(R.id.in_or_out_store, "出入库：" + storeStatus(item))
                .setText(R.id.pay_name, capitalType == 0 ? "应收" : "应付")
                .setVisible(R.id.pay_btn, Double.parseDouble(item.getNeedDoneMoney()) != 0)
                .addOnClickListener(R.id.pay_btn);
    }

    private String storeStatus(PayerPayeeDetailResponse.OrderListResponse item) {
        String storeStatus = "";
        switch (item.getInOrOutStoreStatus()) {
            case 0:
                storeStatus += "未";
                break;
            case 1:
                storeStatus += "正在";
                break;
            case 2:
                storeStatus += "已";
                break;
        }
        switch (item.getInOrOutStore()) {
            case 1:
                storeStatus += "入库";
                break;
            case 2:
                storeStatus += "出库";
                break;
        }
        return storeStatus;
    }
}
