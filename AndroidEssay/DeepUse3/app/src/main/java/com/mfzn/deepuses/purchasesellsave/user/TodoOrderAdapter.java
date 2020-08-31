package com.mfzn.deepuses.purchasesellsave.user;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.user.WaitingCheckListResponse.WaitingCheckOrderPurchaseResponse;
import com.mfzn.deepuses.purchasesellsave.store.adapter.ImageAdapter;

import java.util.List;

public class TodoOrderAdapter extends BaseQuickAdapter<WaitingCheckOrderPurchaseResponse, BaseViewHolder> {

    protected Context context;

    public TodoOrderAdapter(Context context, @Nullable List<WaitingCheckOrderPurchaseResponse> data) {
        super(R.layout.order_todo_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WaitingCheckOrderPurchaseResponse item) {
        List<String> images = item.getGoodsMainImageList();
        if (!ListUtil.isEmpty(images)) {
            RecyclerView recyclerView = helper.getView(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            ImageAdapter imageAdapter = new ImageAdapter(context, images);
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
                .setText(R.id.goods_size, "共计" + item.getGoodsAllCount() + "件")
                .setText(R.id.total_money, "总计："+item.getRealMoney())
                .addOnClickListener(R.id.order_offer_container);
    }

}
