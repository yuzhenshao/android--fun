package com.mfzn.deepuses.purchasesellsave.sale.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.purchasesellsave.sale.Module.OtherCostModule;

import java.util.List;

public class OtherCostSelectAdapter extends BaseQuickAdapter<OtherCostModule, BaseViewHolder> {

    protected Context context;

    public OtherCostSelectAdapter(Context context, @Nullable List<OtherCostModule> data) {
        super(R.layout.other_cost_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherCostModule item) {
        helper.setText(R.id.cost_num, "费用" + helper.getAdapterPosition() + 1)
                .addOnClickListener(R.id.cost_type_select)
                .addOnClickListener(R.id.has_tax_rate)
                .addOnClickListener(R.id.no_tax_rate)
                .addOnClickListener(R.id.tax_rate_select);


//        EditText costType = helper.getView(R.id.cost_type);
//        ImageView costTypeSelect = helper.getView(R.id.cost_type_select);
//        EditText costPrice = helper.getView(R.id.cost_price);
//        TextView hasTaxRate = helper.getView(R.id.has_tax_rate);
//        TextView noTaxRate = helper.getView(R.id.no_tax_rate);
//        EditText taxRate = helper.getView(R.id.tax_rate);
//        ImageView taxRateSelect = helper.getView(R.id.tax_rate_select);

    }
}