package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.slidemenu.MenuQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

/**
 * @author yz @date 2020-05-03
 */
public class GoodsAdapter extends MenuQuickAdapter<GoodsInfoResponse, BaseViewHolder> {

    protected Context context;

    public GoodsAdapter(Context context, @Nullable List<GoodsInfoResponse> data) {
        super(R.layout.goods_item_view,R.layout.delete_menu, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfoResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        } else {
            helper.setImageResource(R.id.icon_goods, R.mipmap.icon_no_data);
        }

        String content = context.getResources().getString(R.string.goods_sale_price, item.getSalePrice());
        if (item.isHasTaxRate()) {
            content += "(含税" + item.getTaxRate() * 100 + "%)";
        }
        helper.setText(R.id.name, item.getGoodsName()+"  ["+item.getGoodsNum()+"]")
                .setText(R.id.price, content)
                .setText(R.id.goods_stock_num, context.getResources().getString(R.string.goods_sum_stock, item.getGoodsSumStockNum()));
    }
}
