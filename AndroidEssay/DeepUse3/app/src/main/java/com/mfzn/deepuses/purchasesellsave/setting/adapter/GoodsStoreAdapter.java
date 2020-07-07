package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.StoreResponse;

import java.util.List;

public class GoodsStoreAdapter extends BaseQuickAdapter<StoreResponse, BaseViewHolder> {


    public GoodsStoreAdapter(@Nullable List<StoreResponse> data) {
        super(R.layout.goods_inventory_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreResponse item) {
        helper.setText(R.id.shop_name, item.getStoreName());
        EditText editText = helper.getView(R.id.store);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable number) {
                item.setStoreNum(number.toString());
            }
        });
    }
}