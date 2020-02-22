package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class AddBranchDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView tv_finish;
    private LinearLayout ll_br_delete;
    private LinearLayout ll_br_cj;
    private LinearLayout ll_br_pt;

    public AddBranchDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.dialog_add_branch, null);
        mDialog.setContentView(mDialogView); // 一定要在setAttributes(lp)之前才有效

        tv_finish = (TextView) mDialogView.findViewById(R.id.tv_finish);//新
        ll_br_delete = (LinearLayout) mDialogView.findViewById(R.id.ll_br_delete);//新
        ll_br_cj = (LinearLayout) mDialogView.findViewById(R.id.ll_br_cj);//新
        ll_br_pt = (LinearLayout) mDialogView.findViewById(R.id.ll_br_pt);//新

        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);

        initDialog(builder);
    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        //新的点击事件
        ll_br_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getApplyEquListener() != null) {
                    mBuilder.getApplyEquListener().clickPrintButton(AddBranchDialog.this,
                            ll_br_delete);
                }
            }
        });
        ll_br_cj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getApplyEquListener() != null) {
                    mBuilder.getApplyEquListener().clickScanButton(AddBranchDialog.this,
                            ll_br_cj);
                }
            }
        });
        ll_br_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getApplyEquListener() != null) {
                    mBuilder.getApplyEquListener().clickBobaoButton(AddBranchDialog.this,
                            ll_br_pt);
                }
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show() {

        mDialog.show();
    }

    public void dismiss() {

        mDialog.dismiss();
    }

    public Dialog getDialog() {

        return mDialog;
    }

    public static class Builder {

        private DialogInterface.OnApplyEquTypeClickListener<AddBranchDialog> zhaopianListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;

        public Builder(Context context) {

            mContext = context;
            isTouchOutside = true;
            zhaopianListener = null;
            height = 0.23f;
            width = 0.65f;

        }

        public Context getContext() {

            return mContext;
        }

        public Builder setOnclickListener(DialogInterface.OnApplyEquTypeClickListener<AddBranchDialog> onclickListener) {
            this.zhaopianListener = onclickListener;
            return this;
        }

        public DialogInterface.OnApplyEquTypeClickListener<AddBranchDialog> getApplyEquListener() {
            return zhaopianListener;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {

            this.isTouchOutside = isTouchOutside;
            return this;
        }

        public float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public AddBranchDialog build() {

            return new AddBranchDialog(this);
        }
    }


}
