package com.libcommon.dialog.listener;

import android.view.View;

import com.libcommon.dialog.fragment.BaseDialogFragment;
import com.libcommon.dialog.view.BindViewHolder;


/**
 * Created by syz on 2019/7/16.
 */
public interface OnViewClickListener {
    void onViewClick(BaseDialogFragment dialog, BindViewHolder viewHolder, View view);
}
