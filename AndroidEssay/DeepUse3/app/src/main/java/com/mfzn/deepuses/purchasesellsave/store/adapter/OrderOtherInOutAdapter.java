package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.OrderOtherInOutListResponse;

import java.util.List;

public class OrderOtherInOutAdapter extends BaseQuickAdapter<OrderOtherInOutListResponse.OrderOtherInOutResponse, BaseViewHolder> {

    protected Context context;

    public OrderOtherInOutAdapter(Context context, @Nullable List<OrderOtherInOutListResponse.OrderOtherInOutResponse> data) {
        super(R.layout.order_other_in_out_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderOtherInOutListResponse.OrderOtherInOutResponse item) {
        List<String> images = item.getGoodsMainImageList();

        if (!ListUtil.isEmpty(images)) {
            RecyclerView recyclerView = helper.getView(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(new ImageAdapter(context, images));
        }

        helper.setText(R.id.name, item.getReceiverInfo()==null?"":item.getReceiverInfo().getReceiverName())
                .setText(R.id.order_num, item.getOrderNum())
                .setText(R.id.goods_size, "共计" + (ListUtil.isEmpty(item.getGoodsInfo())?0: item.getGoodsInfo().size()) + "件")
                .setText(R.id.total_price, "总计：" + item.getTotalMoney())
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
