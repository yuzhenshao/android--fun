package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.slidemenu.MenuQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.IncomeExpenseTypeResponse;

import java.util.List;

/**
 * @author yz @date 2020-03-30
 */
public class IncomeExpenseTypeAdapter extends MenuQuickAdapter<IncomeExpenseTypeResponse, BaseViewHolder> {
    public IncomeExpenseTypeAdapter(@Nullable List<IncomeExpenseTypeResponse> data) {
        super(R.layout.item_select_view, R.layout.edit_delete_menu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IncomeExpenseTypeResponse item) {
        helper.setText(R.id.title, item.getTypeName())
        .setImageResource(R.id.selecte_btn,R.mipmap.join_you);
    }
}