package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

import static com.wevey.selector.dialog.R.id.but_zhaopian_paizhao;
import static com.wevey.selector.dialog.R.id.but_zhaopian_quxiao;
import static com.wevey.selector.dialog.R.id.but_zhaopian_xiangce;

/**
 * Created by Weavey on 2016/9/3.
 */
public class ApplyEquTypeDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView tv_finish;
    private TextView tv_app_print;
    private TextView tv_app_scan;
    private TextView tv_app_bobao;
    private TextView tv_app_quxiao;

    public ApplyEquTypeDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.dialog_zhaopian, null);
        mDialog.setContentView(mDialogView); // 一定要在setAttributes(lp)之前才有效

        tv_finish = (TextView) mDialogView.findViewById(R.id.tv_finish);//新
        tv_app_print = (TextView) mDialogView.findViewById(R.id.tv_app_print);//新
        tv_app_scan = (TextView) mDialogView.findViewById(R.id.tv_app_scan);//新
        tv_app_bobao = (TextView) mDialogView.findViewById(R.id.tv_app_bobao);//新
        tv_app_quxiao = (TextView) mDialogView.findViewById(R.id.tv_app_quxiao);//新


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
        tv_app_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getApplyEquListener() != null) {
                    mBuilder.getApplyEquListener().clickPrintButton(ApplyEquTypeDialog.this,
                            tv_app_print);
                }
            }
        });
        tv_app_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getApplyEquListener() != null) {
                    mBuilder.getApplyEquListener().clickScanButton(ApplyEquTypeDialog.this,
                            tv_app_scan);
                }
            }
        });
        tv_app_bobao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getApplyEquListener() != null) {
                    mBuilder.getApplyEquListener().clickBobaoButton(ApplyEquTypeDialog.this,
                            tv_app_bobao);
                }
            }
        });
        tv_app_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mBuilder.getZhaopianListener() != null) {
//                    mBuilder.getZhaopianListener().clickQuxiaoButton(ApplyEquTypeDialog.this,
//                            tv_app_quxiao);
//                }
                dismiss();
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

        private DialogInterface.OnApplyEquTypeClickListener<ApplyEquTypeDialog> zhaopianListener;
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

        public Builder setOnclickListener(DialogInterface.OnApplyEquTypeClickListener<ApplyEquTypeDialog> onclickListener) {
            this.zhaopianListener = onclickListener;
            return this;
        }

        public DialogInterface.OnApplyEquTypeClickListener<ApplyEquTypeDialog> getApplyEquListener() {
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

        public ApplyEquTypeDialog build() {

            return new ApplyEquTypeDialog(this);
        }
    }


}
