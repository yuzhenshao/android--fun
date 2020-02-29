package com.libcommon.dialog.module;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.libcommon.dialog.adapter.DialogAdapter;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.listener.OnViewClickListener;

/**
 * Created by syz on 2019/7/16.
 */
public abstract class DialogBaseBuilder {
    protected DialogBaseParams params;

    public DialogBaseBuilder(DialogBaseParams params) {
        this.params = params;
    }

    public DialogBaseBuilder setLayoutRes(@LayoutRes int layoutRes) {
        params.setLayoutRes(layoutRes);
        return this;
    }

    public DialogBaseBuilder setDialogView(View dialogView) {
        params.setDialogView(dialogView);
        return this;
    }

    public DialogBaseBuilder setWidth(int widthPx) {
        params.setWidth(widthPx);
        return this;
    }

    public DialogBaseBuilder setHeight(int height) {
        params.setHeight(height);
        return this;
    }

    public DialogBaseBuilder setGravity(int gravity) {
        params.setGravity(gravity);
        return this;
    }

    public DialogBaseBuilder setCancelableOutside(boolean cancel) {
        params.setCancelableOutside(cancel);
        return this;
    }

    public DialogBaseBuilder setDialogAnimationRes(int animationRes) {
        params.setDialogAnimationRes(animationRes);
        return this;
    }

    public DialogBaseBuilder setDimAmount(float dim) {
        params.setDimAmount(dim);
        return this;
    }

    public DialogBaseBuilder setAdapter(DialogAdapter adapter) {
        params.setAdapter(adapter);
        return this;
    }

    public DialogBaseBuilder setLayoutManager(LinearLayoutManager layoutManager) {
        params.setLayoutManager(layoutManager);
        return this;
    }

    public DialogBaseBuilder setOnBindViewListener(OnBindViewListener listener) {
        params.setOnBindViewListener(listener);
        return this;
    }

    public DialogBaseBuilder addOnClickListener(int... ids) {
        params.setIds(ids);
        return this;
    }

    public DialogBaseBuilder setOnViewClickListener(OnViewClickListener listener) {
        params.setOnViewClickListener(listener);
        return this;
    }

    public DialogBaseBuilder setOnItemClickListener(DialogAdapter.OnAdapterItemClickListener onItemClickListener) {
        params.setOnItemClickListener(onItemClickListener);
        return this;
    }

    public DialogBaseBuilder setCancelAble(boolean cancelAble) {
        params.setCancelAble(cancelAble);
        return this;
    }

    public abstract BaseDialogFragment create();
}
