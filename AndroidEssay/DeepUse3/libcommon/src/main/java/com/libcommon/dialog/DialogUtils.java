package com.libcommon.dialog;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.libcommon.R;
import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.dialog.listener.OnBindViewListener;
import com.libcommon.dialog.listener.OnViewClickListener;
import com.libcommon.dialog.view.BindViewHolder;

/**
 * @author yz @date 2020-03-24
 */
public class DialogUtils {

    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public static void showEditDialog(final FragmentActivity activity, final String title, final String hint, final OnViewClickListener listener) {
        showEditDialog(activity,title,null,hint,listener);
    }

    public static void showEditDialog(final FragmentActivity activity, final String title, final String message,final String hint, final OnViewClickListener listener) {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_edit)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setWidth((int) (0.8 * getDisplayMetrics(activity).widthPixels))
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder bindViewHolder) {
                        bindViewHolder.setText(R.id.title, title);
                        EditText editText = bindViewHolder.getView(R.id.message);
                        editText.setHint(hint);
                        editText.setText(message);
                    }
                })
                .addOnClickListener(R.id.cancel_btn, R.id.confirm_btn)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment customDialog, BindViewHolder bindViewHolder, View view) {
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                        if (view.getId() == R.id.confirm_btn && listener != null) {
                            listener.onViewClick(customDialog, bindViewHolder, view);
                        }
                    }
                }).create().show(activity.getSupportFragmentManager(), activity.getClass().getName());
    }

    public static void showConfirmDialog(final FragmentActivity activity, final String title, final OnViewClickListener listener) {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_edit)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setWidth((int) (0.8 * getDisplayMetrics(activity).widthPixels))
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder bindViewHolder) {
                        bindViewHolder.setText(R.id.title, title);
                        bindViewHolder.setGone(R.id.message, false);
                    }
                })
                .addOnClickListener(R.id.cancel_btn, R.id.confirm_btn)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BaseDialogFragment customDialog, BindViewHolder bindViewHolder, View view) {
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                        if (view.getId() == R.id.confirm_btn && listener != null) {
                            listener.onViewClick(customDialog, bindViewHolder, view);
                        }
                    }
                }).create().show(activity.getSupportFragmentManager(), activity.getClass().getName());
    }
}
