package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.StockLogListResponse;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

public class StockLogsAdapter extends BaseQuickAdapter<StockLogListResponse.ListBean.StockLogResponse, BaseViewHolder> {

    protected Context context;

    public StockLogsAdapter(Context context, @Nullable List<StockLogListResponse.ListBean.StockLogResponse> data) {
        super(R.layout.stock_logs_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StockLogListResponse.ListBean.StockLogResponse item) {
        if (!TextUtils.isEmpty(item.getGoodsMainImage())) {
            Glide.with(context).load(ApiHelper.BASE_URL + item.getGoodsMainImage()).into((ImageView) helper.getView(R.id.icon_goods));
        } else {
            helper.setImageResource(R.id.icon_goods, R.mipmap.icon_no_data);
        }

        helper.setText(R.id.name, item.getGoodsName()+"  ["+item.getGoodsNum()+"]")
                .setText(R.id.in_or_out_store, item.getInOrOutText() + ":" + item.getNum())
                .setText(R.id.time, DateUtils.longToString(item.getAddTime()));
    }
}