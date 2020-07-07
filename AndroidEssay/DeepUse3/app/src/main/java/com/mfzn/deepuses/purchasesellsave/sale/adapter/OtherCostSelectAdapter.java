package com.mfzn.deepuses.purchasesellsave.sale.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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
                .setText(R.id.cost_type, item.getCostName())
                .setText(R.id.cost_price, item.getCostMoney())
                .setText(R.id.tax_rate, item.getTaxRate() + "")
                .setChecked(R.id.switch_button, item.isTaxRate())
                .addOnClickListener(R.id.cost_type_select)
                .addOnClickListener(R.id.switch_button)
                .addOnClickListener(R.id.tax_rate_select)
        .addOnClickListener(R.id.delete);


//        EditText costType = helper.getView(R.id.cost_type);
//        ImageView costTypeSelect = helper.getView(R.id.cost_type_select);
        EditText costPrice = helper.getView(R.id.cost_price);
        costPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setCostMoney(s.toString());
            }
        });
//        TextView hasTaxRate = helper.getView(R.id.has_tax_rate);
//        TextView noTaxRate = helper.getView(R.id.no_tax_rate);
//        EditText taxRate = helper.getView(R.id.tax_rate);
//        ImageView taxRateSelect = helper.getView(R.id.tax_rate_select);

    }
}