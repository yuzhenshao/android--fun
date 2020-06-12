package com.mfzn.deepuses.purchasesellsave.sale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.purchasesellsave.sale.Module.OtherCostModule;
import com.mfzn.deepuses.purchasesellsave.sale.adapter.OtherCostSelectAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityOtherCostSelect extends BasicActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private OtherCostSelectAdapter adapter;
    private List<OtherCostModule> otherCostList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("选择其他费用");
        adapter = new OtherCostSelectAdapter(this, otherCostList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int i) {
                OtherCostModule otherCost = otherCostList.get(i);
                otherCost.setCostMoney(((EditText) view.findViewById(R.id.cost_price)).getText().toString());

                EditText costType = view.findViewById(R.id.cost_type);
                ImageView costTypeSelect = view.findViewById(R.id.cost_type_select);
                EditText costPrice = view.findViewById(R.id.cost_price);
                TextView hasTaxRate = view.findViewById(R.id.has_tax_rate);
                TextView noTaxRate = view.findViewById(R.id.no_tax_rate);
                EditText taxRate = view.findViewById(R.id.tax_rate);
                ImageView taxRateSelect = view.findViewById(R.id.tax_rate_select);


                switch (view.getId()) {
                    case R.id.cost_type_select:

                        break;
                    case R.id.has_tax_rate:
                        hasTaxRate.setCompoundDrawables(getResources().getDrawable(R.mipmap.icon_other_cost_selected), null, null, null);
                        noTaxRate.setCompoundDrawables(getResources().getDrawable(R.mipmap.icon_other_cost_unselect), null, null, null);
                        break;
                    case R.id.no_tax_rate:
                        hasTaxRate.setCompoundDrawables(getResources().getDrawable(R.mipmap.icon_other_cost_unselect), null, null, null);
                        noTaxRate.setCompoundDrawables(getResources().getDrawable(R.mipmap.icon_other_cost_selected), null, null, null);
                        break;
                    case R.id.tax_rate_select:
                        //startActivityForResult(new Intent(ActivityOtherCostSelect.this,TaxRateActivity));
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_other_cost_list;
    }


    @OnClick({R.id.icon_add_other_cost, R.id.btn_commit})
    public void viewClick(View v) {
        switch (v.getId()) {
            case R.id.icon_add_other_cost:
                otherCostList.add(new OtherCostModule());
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_commit:
                Intent intent = new Intent();
                intent.putExtra("data", (Serializable) otherCostList);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
