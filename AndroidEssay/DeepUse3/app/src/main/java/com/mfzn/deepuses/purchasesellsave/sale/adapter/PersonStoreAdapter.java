package com.mfzn.deepuses.purchasesellsave.sale.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.MyStoreResponse;

import java.util.List;

public class PersonStoreAdapter extends BaseQuickAdapter<MyStoreResponse, BaseViewHolder> {


    public PersonStoreAdapter( @Nullable List<MyStoreResponse> data) {
        super(R.layout.my_store_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyStoreResponse item) {
        helper.setText(R.id.store_name, item.getStoreName());
    }
}
