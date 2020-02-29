package com.libcommon.dialog.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.libcommon.dialog.module.DialogBaseParams;

/**
 * Created by syz on 2019/7/16.
 */
public abstract class BaseDialogFragment<T extends DialogBaseParams> extends DialogFragment {

    protected abstract void bindView(View view);

    protected T mParams;

    public BaseDialogFragment(T params) {
        mParams = params;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getDialogView();
        if (view == null && getLayoutRes() != 0) {
            view = inflater.inflate(getLayoutRes(), container, false);
        }
        if (view != null) {
            bindView(view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mParams.getOnViewClickListener() == null) {
            dismiss();
        }
    }

    protected int getLayoutRes() {
        return mParams.getLayoutRes();
    }

    protected View getDialogView() {
        return mParams.getDialogView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(isParamsCancelable());
        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        dialog.setCanceledOnTouchOutside(isCancelableOutside());
        dialog.setCancelable(isCancelable());
        if (dialog.getWindow() != null && getDialogAnimationRes() > 0) {
            dialog.getWindow().setWindowAnimations(getDialogAnimationRes());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = getDialogWidth();
            layoutParams.height = getDialogHeight();
            layoutParams.dimAmount = getDimAmount();
            layoutParams.gravity = getGravity();
            window.setAttributes(layoutParams);
        }
    }

    public int getGravity() {
        return mParams.getGravity();
    }

    public float getDimAmount() {
        return mParams.getDimAmount();
    }

    public int getDialogHeight() {
        return mParams.getHeight();
    }

    public int getDialogWidth() {
        return mParams.getWidth();
    }

    protected boolean isCancelableOutside() {
        return mParams.isCancelableOutside();
    }

    protected int getDialogAnimationRes() {
        return mParams.getDialogAnimationRes();
    }

    protected boolean isParamsCancelable() {
        return mParams.isCancelAble();
    }
}
