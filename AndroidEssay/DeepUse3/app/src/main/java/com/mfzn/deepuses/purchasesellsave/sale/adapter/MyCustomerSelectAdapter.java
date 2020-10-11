package com.mfzn.deepuses.purchasesellsave.sale.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.settings.CustomerListResponse;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;

import java.util.List;

public class MyCustomerSelectAdapter extends BaseQuickAdapter<CustomerListResponse.CustomerResponse, BaseViewHolder> {

    public MyCustomerSelectAdapter(@Nullable List<CustomerListResponse.CustomerResponse> data) {
        super(R.layout.adapter_my_select_customer, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerListResponse.CustomerResponse item) { //level  project   selecte_btn

        helper.setText(R.id.name, item.getCustomerName())
                .setText(R.id.phone, item.getCustomerPhone());

        ImageView levelImage = helper.getView(R.id.level);
        levelImage.setImageResource(item.getLevelIcon());
    }
}