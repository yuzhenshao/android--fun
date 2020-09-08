package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.OrderOtherInOutListResponse;
import com.mfzn.deepuses.bean.response.store.WaitingInOutListResponse;

import java.util.List;

public class OrderWaitInOutAdapter extends BaseQuickAdapter<WaitingInOutListResponse.WaitingInOutResponse, BaseViewHolder> {

    protected Context context;

    public OrderWaitInOutAdapter(Context context, @Nullable List<WaitingInOutListResponse.WaitingInOutResponse> data) {
        super(R.layout.order_other_in_out_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WaitingInOutListResponse.WaitingInOutResponse item) {
        List<String> images = item.getGoodsMainImageList();

        if (!ListUtil.isEmpty(images)) {
            RecyclerView recyclerView = helper.getView(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(new ImageAdapter(context, images));
        }

        helper.setText(R.id.name, item.getReceiverName())
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.goods_size, "共计" + (ListUtil.isEmpty(item.getGoodsInfo())?0: item.getGoodsInfo().size()) + "件")
                .setVisible(R.id.total_price, false)
                .setVisible(R.id.defaule_image, ListUtil.isEmpty(images));

        int statusResId = getStatusResId(item);
        helper.setVisible(R.id.store_check_icon, statusResId != 0);
        if (statusResId != 0) {
            helper.setImageResource(R.id.store_check_icon, statusResId);
        }

    }


    public int getStatusResId(WaitingInOutListResponse.WaitingInOutResponse response) {//0.待收款；1.已收款；2.无

        switch (response.getIsGathering()) {
            case 0:
                return R.mipmap.icon_has_gather;
            case 1:
                return R.mipmap.icon_no_gather;
            case 2:
                return 0;
        }
        return 0;
    }
}
