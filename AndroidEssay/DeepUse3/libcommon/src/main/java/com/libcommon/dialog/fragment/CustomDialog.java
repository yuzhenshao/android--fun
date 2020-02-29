package com.libcommon.dialog.fragment;

import android.view.View;

import com.libcommon.dialog.module.DialogBaseBuilder;
import com.libcommon.dialog.module.DialogBaseParams;
import com.libcommon.dialog.view.BindViewHolder;

/**
 * Created by syz on 2019/7/16.
 */
public class CustomDialog extends BaseDialogFragment {

    public CustomDialog() {
        super(new DialogBaseParams());
    }

    @Override
    protected void bindView(View view) {
        final BindViewHolder viewHolder = new BindViewHolder(view);
        if (mParams.getOnViewClickListener() != null) {
            if (mParams.getIds() != null && mParams.getIds().length > 0) {
                for (int id : mParams.getIds()) {
                    View v = viewHolder.getView(id);
                    if (v != null) {
                        v.setClickable(true);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mParams.getOnViewClickListener().onViewClick(CustomDialog.this, viewHolder, v);
                            }
                        });
                    }
                }
            }
        }

        if (mParams.getOnBindViewListener() != null) {
            mParams.getOnBindViewListener().bindView(viewHolder);
        }
    }

    public static final class Builder extends DialogBaseBuilder {

        public Builder() {
            super(new DialogBaseParams());
        }

        @Override
        public CustomDialog create() {
            CustomDialog customDialog = new CustomDialog();
            params.apply(customDialog.mParams);
            return customDialog;
        }
    }
}
