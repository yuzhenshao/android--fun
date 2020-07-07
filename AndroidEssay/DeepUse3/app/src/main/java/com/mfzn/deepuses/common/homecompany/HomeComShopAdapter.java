package com.mfzn.deepuses.common.homecompany;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.company.CompanyRepository;
import com.mfzn.deepuses.model.company.SelectCompanyModel;

import java.util.List;

public class HomeComShopAdapter extends BaseQuickAdapter<SelectCompanyModel.ShopResponse, BaseViewHolder> {

    public HomeComShopAdapter(@Nullable List<SelectCompanyModel.ShopResponse> data) {
        super(R.layout.home_shop_item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectCompanyModel.ShopResponse model) {

        helper.setText(R.id.shop_name,model.getShopName());

        SelectCompanyModel.ShopResponse shop = CompanyRepository.getInstance().getCurShopResponse();

        helper.setVisible(R.id.icon_selected, shop != null && !TextUtils.isEmpty(shop.getShopID())
                && shop.getShopID().equals(model.getShopID()));


    }
}
