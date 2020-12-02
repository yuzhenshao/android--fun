package com.mfzn.deepuses.purchasesellsave.sale.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.purchasesellsave.sale.Module.FilterModule;

import java.util.List;

public class FilterAdapter extends BaseQuickAdapter<FilterModule, BaseViewHolder> {

    private int typeId;

    public FilterAdapter(@Nullable List<FilterModule> data) {
        super(R.layout.recyleview_select_khdj, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterModule item) {
        TextView nameView = helper.getView(R.id.tv_iten_bane);
        nameView.setText(item.getTypeName());
        nameView.setSelected(item.getTypeId()==typeId);
    }

    public void setCurFilterModule(int typeId) {
        this.typeId = typeId;
    }
}
