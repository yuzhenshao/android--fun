package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.OrderStockCheckResponse;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class StoreCheckAdapter extends BaseQuickAdapter<OrderStockCheckResponse, BaseViewHolder> {

    protected Context context;

    public StoreCheckAdapter(Context context, @Nullable List<OrderStockCheckResponse> data) {
        super(R.layout.store_check_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderStockCheckResponse item) {
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(new ImageAdapter(context, item.getGoodsMainImageList()));

        helper.setText(R.id.name, item.getStoreName())
                .setText(R.id.store_check_id, item.getOrderNum())
                .setText(R.id.time, DateUtils.longToString("yyyy/MM/dd", item.getOrderTime()));
        int resId=getStatusResId(item.getIsCheck());
        helper.setGone(R.id.store_check_icon,resId!=0);
        if(resId!=0){
            helper.setImageResource(R.id.store_check_icon, resId);
        }
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
        return 0;
    }
}
