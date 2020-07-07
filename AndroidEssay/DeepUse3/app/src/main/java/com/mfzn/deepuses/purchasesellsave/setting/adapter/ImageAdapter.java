package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;

import java.util.List;

public class ImageAdapter extends BaseQuickAdapter<Bitmap, BaseViewHolder> {
    public ImageAdapter(@Nullable List<Bitmap> data) {
        super(R.layout.view_add_image_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Bitmap bitmap) {
        holder.setImageBitmap(R.id.image, bitmap)
                .addOnClickListener(R.id.delete);
    }
}
