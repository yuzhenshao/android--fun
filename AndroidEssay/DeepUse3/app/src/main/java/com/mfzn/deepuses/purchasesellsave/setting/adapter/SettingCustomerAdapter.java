package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.CustomerListResponse;

import java.util.List;

public class SettingCustomerAdapter extends BaseQuickAdapter<CustomerListResponse.CustomerResponse, BaseViewHolder> {
    public SettingCustomerAdapter(@Nullable List<CustomerListResponse.CustomerResponse> data) {
        super(R.layout.setting_customer_item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, CustomerListResponse.CustomerResponse response) {
        holder.setText(R.id.name, response.getCustomerName())
                .setText(R.id.number, response.getCustomerPhone());
        int iconRes=response.getLevelIcon();
        holder.setGone(R.id.icon_level,iconRes!=0);
        if(iconRes!=0){
            holder.setImageResource(R.id.icon_level, iconRes);
        }
    }
}
