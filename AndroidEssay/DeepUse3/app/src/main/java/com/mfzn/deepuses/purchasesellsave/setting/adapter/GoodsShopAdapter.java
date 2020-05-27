package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.response.shop.ShopListResponse;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.OnInputChangeListener;

import java.util.List;

public class GoodsShopAdapter extends BaseQuickAdapter<ShopListResponse, BaseViewHolder> {

    protected Context context;
    private ShopPriceListener mShopPriceListener;

    public GoodsShopAdapter(Context context, @Nullable List<ShopListResponse> data) {
        super(R.layout.goods_shop_price_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopListResponse item) {
        helper.setText(R.id.store_name_tab, item.getShopName());
        EditText editText = helper.getView(R.id.store_name);
        editText.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mShopPriceListener.setShopPrice(item, editText.getText().toString().trim());
            }
        });
    }

    public void setShopPriceListener(ShopPriceListener shopPriceListener) {
        mShopPriceListener = shopPriceListener;
    }

    public interface ShopPriceListener {
        void setShopPrice(ShopListResponse item, String price);
    }
}