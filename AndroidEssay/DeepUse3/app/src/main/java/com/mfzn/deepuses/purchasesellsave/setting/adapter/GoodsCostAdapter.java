package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.purchasesellsave.sale.Module.OtherCostModule;

import java.util.List;

public class GoodsCostAdapter extends BaseQuickAdapter<OtherCostModule, BaseViewHolder> {


    public GoodsCostAdapter(@Nullable List<OtherCostModule> data) {
        super(R.layout.goods_cost_item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherCostModule item) {
        String content = "¥" + item.getCostMoney();
        if (item.isTaxRate()) {
            content += "(" + "含税" + item.getTaxRate() * 100 + "%)";
        }
        helper.setText(R.id.name, item.getCostName())
                .setText(R.id.price, content);

    }
}
