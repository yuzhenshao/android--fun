package com.mfzn.deepuses.purchasesellsave.sale.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse.OrderOfferResponse;
import com.mfzn.deepuses.purchasesellsave.store.adapter.ImageAdapter;

import java.util.List;

public class OrderSalesAdapter extends BaseQuickAdapter<OrderOfferResponse, BaseViewHolder> {

    protected Context context;

    public OrderSalesAdapter(Context context, @Nullable List<OrderOfferResponse> data) {
        super(R.layout.order_offer_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderOfferResponse item) {
        List<String> images = item.getGoodsMainImageList();
        if (!ListUtil.isEmpty(images)) {
            RecyclerView recyclerView = helper.getView(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(new ImageAdapter(context, images));
        }

        helper.setText(R.id.name, item.getCustomerName())
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.goods_size, "共" + images.size() + "件")
                .setImageResource(R.id.store_check_icon, getStatusResId(item.getIsCheck()));
    }


    public int getStatusResId(int status) {
        switch (status) {
            case 0:
                return R.mipmap.examine_pending;
            case 1:
                return R.mipmap.profit_loss_successed;
            case 2:
                return R.mipmap.profit_loss_failed;
        }
        return R.mipmap.examine_pending;
    }
}
