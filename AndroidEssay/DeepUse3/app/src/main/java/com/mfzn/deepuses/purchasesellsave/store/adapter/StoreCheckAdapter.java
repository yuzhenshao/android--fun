package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.response.store.StoreCheckResponse;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

import butterknife.BindView;

public class StoreCheckAdapter extends BaseQuickAdapter<StoreCheckResponse, BaseViewHolder> {

    protected Context context;

    public StoreCheckAdapter(Context context, @Nullable List<StoreCheckResponse> data) {
        super(R.layout.store_check_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreCheckResponse item) {
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(new ImageAdapter(item.getGoodsMainImageList()));

        helper.setText(R.id.name, item.getStoreName())
                .setText(R.id.store_check_id, item.getOrderNum())
                .setText(R.id.time, DateUtils.longToString("yyyy/MM/dd", item.getOrderTime()))
                .setImageResource(R.id.store_check_icon, item.getStatusResId());
    }


    class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public ImageAdapter(@Nullable List<String> data) {
            super(R.layout.view_image_item, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, String bitmap) {
            Glide.with(context).load(ApiHelper.BASE_URL + bitmap).into((ImageView) holder.getView(R.id.image));
        }
    }
}
