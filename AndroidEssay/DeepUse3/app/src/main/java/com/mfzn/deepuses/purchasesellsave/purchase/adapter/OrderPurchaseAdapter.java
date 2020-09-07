package com.mfzn.deepuses.purchasesellsave.purchase.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.purchase.OrderPurchaseListResponse;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse.OrderOfferResponse;
import com.mfzn.deepuses.purchasesellsave.store.adapter.ImageAdapter;

import java.util.List;

public class OrderPurchaseAdapter extends BaseQuickAdapter<OrderPurchaseListResponse.OrderPurchaseResponse, BaseViewHolder> {

    protected Context context;

    public OrderPurchaseAdapter(Context context, @Nullable List<OrderPurchaseListResponse.OrderPurchaseResponse> data) {
        super(R.layout.order_offer_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderPurchaseListResponse.OrderPurchaseResponse item) {
        List<String> images = item.getGoodsMainImageList();

        if (!ListUtil.isEmpty(images)) {
            RecyclerView recyclerView = helper.getView(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            ImageAdapter imageAdapter= new ImageAdapter(context, images);
            recyclerView.setAdapter(imageAdapter);
            imageAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int i) {
                    getOnItemChildClickListener().onItemChildClick(adapter, view, i);
                }
            });
        }

        helper.setText(R.id.name, item.getSupplierName())
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.goods_size, "共计" + images.size() + "件")
                .setImageResource(R.id.store_check_icon, getStatusResId(item.getIsCheck()))
                .setVisible(R.id.defaule_image, ListUtil.isEmpty(images))
                .addOnClickListener(R.id.order_offer_container);
    }

    public int getStatusResId(int status) {//0.待审核1通过2拒绝
        switch (status) {
            case 0:
                return R.mipmap.examine_pending;
            case 1:
                return R.mipmap.examine_pass;
            case 2:
                return R.mipmap.examine_unpass;
        }
        return R.mipmap.examine_pending;
    }
}
