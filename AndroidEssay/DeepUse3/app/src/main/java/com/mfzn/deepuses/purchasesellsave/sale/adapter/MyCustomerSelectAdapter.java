package com.mfzn.deepuses.purchasesellsave.sale.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;

import java.util.List;

public class MyCustomerSelectAdapter extends BaseQuickAdapter<WholeCustomerModel.DataBean, BaseViewHolder> {

    public MyCustomerSelectAdapter(@Nullable List<WholeCustomerModel.DataBean> data) {
        super(R.layout.adapter_my_select_customer, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WholeCustomerModel.DataBean item) { //level  project   selecte_btn

        helper.setText(R.id.name, item.getU_name())
                .setText(R.id.phone, item.getCustomerPhone());

        ImageView levelImage = helper.getView(R.id.level);
        int customerLevelID = item.getCustomerLevelID();
        if (customerLevelID == 1) {
            levelImage.setImageResource(R.mipmap.cus_level1);
        } else if (customerLevelID == 2) {
            levelImage.setImageResource(R.mipmap.cus_level2);
        } else if (customerLevelID == 3) {
            levelImage.setImageResource(R.mipmap.cus_level3);
        } else if (customerLevelID == 4) {
            levelImage.setImageResource(R.mipmap.cus_level4);
        } else if (customerLevelID == 5) {
            levelImage.setImageResource(R.mipmap.cus_level5);
        } else {
            levelImage.setImageResource(0);
        }
    }
}