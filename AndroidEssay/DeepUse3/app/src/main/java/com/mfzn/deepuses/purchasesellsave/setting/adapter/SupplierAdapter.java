package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;
import com.mfzn.deepuses.bean.response.settings.SupplierListResponse;

import java.util.List;

public class SupplierAdapter extends BaseQuickAdapter<SupplierListResponse.SupplierResponse, BaseViewHolder> {

    protected Context context;

    public SupplierAdapter(Context context, @Nullable List<SupplierListResponse.SupplierResponse> data) {
        super(R.layout.supplier_list_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierListResponse.SupplierResponse item) {
        helper.setText(R.id.supplier_name, item.getSupplierName())
                .setText(R.id.user_name, context.getResources().getString(R.string.supplier_name_phone, item.getChargePerson(), item.getChargePersonPhone()))
        .addOnClickListener(R.id.root_container)
        .addOnClickListener(R.id.phone);
    }
}
