package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.WaitingInDetailResponse;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class GoodsInOutAdapter extends BaseQuickAdapter<WaitingInDetailResponse, BaseViewHolder> {

    protected Context context;

    public GoodsInOutAdapter(Context context, @Nullable List<WaitingInDetailResponse> data) {
        super(R.layout.goods_wait_in_out_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WaitingInDetailResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        }
        helper.setText(R.id.goods_name, item.getGoodsName())
                .setText(R.id.goods_num, item.getGoodsNum())
                .setText(R.id.should_done, "应出库数量:" + item.getShouldDone())
                .setText(R.id.has_done, "已出库数量:" + item.getHasDone())
                .setText(R.id.number, item.getSize() + "")
                .addOnClickListener(R.id.subtraction)
                .addOnClickListener(R.id.plus);
    }
}
