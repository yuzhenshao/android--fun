package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.sale.OrderOfferListResponse.OrderOfferResponse;
import com.mfzn.deepuses.bean.response.store.OrderAllotListResponse;

import java.util.List;

public class OrderAllotAdapter extends BaseQuickAdapter<OrderAllotListResponse.OrderAllotResponse, BaseViewHolder> {

    protected Context context;

    public OrderAllotAdapter(Context context, @Nullable List<OrderAllotListResponse.OrderAllotResponse> data) {
        super(R.layout.order_offer_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderAllotListResponse.OrderAllotResponse item) {
        List<String> images = item.getGoodsMainImageList();

        if (!ListUtil.isEmpty(images)) {
            RecyclerView recyclerView = helper.getView(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(new ImageAdapter(context, images));
        }

        helper.setText(R.id.name, item.getCheckUserName())
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.goods_size, "共计" + images.size() + "件")
                .setImageResource(R.id.store_check_icon, getStatusResId(item.getIsCheck()))
                .setVisible(R.id.defaule_image, ListUtil.isEmpty(images));
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
