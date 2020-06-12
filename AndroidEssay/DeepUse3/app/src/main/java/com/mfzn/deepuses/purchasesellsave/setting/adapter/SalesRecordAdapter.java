package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.SalesRecordResponse;
import com.mfzn.deepuses.bean.response.settings.StoresInfoResponse;

import java.util.List;

public class SalesRecordAdapter extends BaseQuickAdapter<SalesRecordResponse, BaseViewHolder> {
    public SalesRecordAdapter(@Nullable List<SalesRecordResponse> data) {
        super(R.layout.goods_sales_record_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SalesRecordResponse item) {
        helper.setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.sales_user_name, item.getCustomerName())
                .setText(R.id.goods_count, "数量：" + item.getGoodsCount())
                .setText(R.id.shop_name, item.getShopName())
                .setText(R.id.show_sale_price, item.getShowSalePrice())
                .setText(R.id.profit_money, item.getProfitMoney());
    }
}
