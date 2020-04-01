package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class UploadPhotoDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView but_zhaopian_paizhao;
    private TextView but_zhaopian_xiangce;
    private TextView but_zhaopian_quxiao;
    private TextView tv_finish;

    public UploadPhotoDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.upload_photo_dialog, null);
        mDialog.setContentView(mDialogView); // 一定要在setAttributes(lp)之前才有效

        but_zhaopian_paizhao = (TextView) mDialogView.findViewById(R.id.but_zhaopian_paizhao);//新
        but_zhaopian_xiangce = (TextView) mDialogView.findViewById(R.id.but_zhaopian_xiangce);//新
        but_zhaopian_quxiao = (TextView) mDialogView.findViewById(R.id.but_zhaopian_quxiao);//新
        tv_finish = (TextView) mDialogView.findViewById(R.id.tv_finish);//新


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
        but_zhaopian_paizhao.setTextColor(mBuilder.getTitleTextColor());
        but_zhaopian_paizhao.setText(mBuilder.getTitleText());
        but_zhaopian_xiangce.setText(mBuilder.getContexttext());

        //新的点击事件
        but_zhaopian_paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getZhaopianListener() != null) {

                    mBuilder.getZhaopianListener().clickPaizhaoButton(UploadPhotoDialog.this,
                            but_zhaopian_paizhao);
                }
            }
        });

        but_zhaopian_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getZhaopianListener() != null) {

                    mBuilder.getZhaopianListener().clickXiangceButton(UploadPhotoDialog.this,
                            but_zhaopian_xiangce);
                }
            }
        });

        but_zhaopian_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getZhaopianListener() != null) {

                    mBuilder.getZhaopianListener().clickQuxiaoButton(UploadPhotoDialog.this,
                            but_zhaopian_quxiao);
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

        private DialogInterface.OnLeftAndRightClickListener<UploadPhotoDialog> onclickListener;
        private DialogInterface.OnSingleClickListener<UploadPhotoDialog> singleListener;
        private DialogInterface.OnShangchuanZhaopianClickListener<UploadPhotoDialog> zhaopianListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private int titleTextColor;
        private String titleText;
        private String contextText;

        public Builder(Context context) {

            mContext = context;
            onclickListener = null;
            singleListener = null;
            isTouchOutside = true;
            zhaopianListener = null;
            height = 0.23f;
            width = 0.65f;
            titleTextColor = ContextCompat.getColor(context, R.color.color_4F7DBC);
            titleText = "拍照";
            contextText = "从手机相册中选择";
        }

        public int getTitleTextColor() {
            return titleTextColor;
        }

        public Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.titleTextColor = ContextCompat.getColor(mContext, titleTextColor);
            return this;
        }

        public String getTitleText() {
            return titleText;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public String getContexttext() {
            return contextText;
        }

        public Builder setContexttext(String contextText) {
            this.contextText = contextText;
            return this;
        }

        public Context getContext() {

            return mContext;
        }

        public DialogInterface.OnLeftAndRightClickListener<UploadPhotoDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnShangchuanZhaopianClickListener<UploadPhotoDialog> onclickListener) {
            this.zhaopianListener = onclickListener;
            return this;
        }

        public DialogInterface.OnSingleClickListener<UploadPhotoDialog> getSingleListener() {
            return singleListener;
        }

        public DialogInterface.OnShangchuanZhaopianClickListener<UploadPhotoDialog> getZhaopianListener() {
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

        public UploadPhotoDialog build() {

            return new UploadPhotoDialog(this);
        }
    }


}
