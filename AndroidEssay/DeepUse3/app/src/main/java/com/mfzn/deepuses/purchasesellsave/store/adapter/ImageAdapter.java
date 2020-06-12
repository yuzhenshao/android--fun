package com.mfzn.deepuses.purchasesellsave.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    protected Context context;

    public ImageAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.view_image_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, String bitmap) {
        Glide.with(context).load(ApiHelper.BASE_URL + bitmap).into((ImageView) holder.getView(R.id.image));
    }
}
