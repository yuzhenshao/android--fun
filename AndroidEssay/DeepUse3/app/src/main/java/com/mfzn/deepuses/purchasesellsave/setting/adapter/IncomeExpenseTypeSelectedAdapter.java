package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.slidemenu.MenuQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.IncomeExpenseTypeResponse;

import java.util.List;


public class IncomeExpenseTypeSelectedAdapter extends BaseQuickAdapter<IncomeExpenseTypeResponse, BaseViewHolder> {
    public IncomeExpenseTypeSelectedAdapter(@Nullable List<IncomeExpenseTypeResponse> data) {
        super(R.layout.item_other_cost_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IncomeExpenseTypeResponse item) {
        helper.setText(R.id.title, item.getTypeName());
    }
}