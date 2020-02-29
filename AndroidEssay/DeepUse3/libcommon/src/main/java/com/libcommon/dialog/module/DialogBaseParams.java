package com.libcommon.dialog.module;

import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.libcommon.dialog.adapter.DialogAdapter;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.listener.OnViewClickListener;

/**
 * Created by syz on 2019/7/16.
 */
public class DialogBaseParams {

    private int layoutRes;
    private View dialogView;
    private int height = WindowManager.LayoutParams.WRAP_CONTENT;
    private int width = WindowManager.LayoutParams.WRAP_CONTENT;
    private float dimAmount = 0.7F;
    private int gravity = Gravity.CENTER;
    private int[] ids;
    private boolean isCancelableOutside = true;
    private int dialogAnimationRes;
    private OnViewClickListener onViewClickListener;
    private OnBindViewListener onBindViewListener;
    private DialogAdapter.OnAdapterItemClickListener mOnItemClickListener;
    private DialogAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    //can be cancel by back key
    private boolean cancelAble = true;

    public DialogBaseParams() {

    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public View getDialogView() {
        return dialogView;
    }

    public void setDialogView(View dialogView) {
        this.dialogView = dialogView;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    public void setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public boolean isCancelableOutside() {
        return isCancelableOutside;
    }

    public int getDialogAnimationRes() {
        return dialogAnimationRes;
    }

    public void setDialogAnimationRes(int dialogAnimationRes) {
        this.dialogAnimationRes = dialogAnimationRes;
    }

    public void setCancelableOutside(boolean cancelableOutside) {
        isCancelableOutside = cancelableOutside;
    }

    public OnViewClickListener getOnViewClickListener() {
        return onViewClickListener;
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
    }

    public OnBindViewListener getOnBindViewListener() {
        return onBindViewListener;
    }

    public void setOnBindViewListener(OnBindViewListener onBindViewListener) {
        this.onBindViewListener = onBindViewListener;
    }

    public DialogAdapter.OnAdapterItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(DialogAdapter.OnAdapterItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public DialogAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DialogAdapter adapter) {
        this.adapter = adapter;
    }

    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    public boolean isCancelAble() {
        return cancelAble;
    }

    public void setCancelAble(boolean cancelAble) {
        this.cancelAble = cancelAble;
    }

    public void apply(DialogBaseParams params) {
        params.layoutRes = layoutRes;
        params.dialogView=dialogView;
        params.width = width;
        params.height = height;
        params.dimAmount = dimAmount;
        params.gravity = gravity;
        if (ids != null) {
            params.ids = ids;
        }
        params.isCancelableOutside = isCancelableOutside;
        params.onViewClickListener = onViewClickListener;
        params.onBindViewListener = onBindViewListener;
        params.dialogAnimationRes = dialogAnimationRes;

        if (adapter != null) {
            params.setAdapter(adapter);
        }
        params.mLayoutManager = mLayoutManager;
        params.mOnItemClickListener = mOnItemClickListener;
        params.cancelAble=cancelAble;
    }
}
