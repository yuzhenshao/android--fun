package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class ChakanBeizhuDialog {

    private TextView tv_chakan_huanjing;
    private TextView tv_chakan_xitong;
    private TextView tv_chakan_shuoming;
    private TextView tv_chakan_beizhu;
    private TextView tv_chakan_queren;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    public ChakanBeizhuDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.chakan_beizhu_dialog, null);
        tv_chakan_huanjing = (TextView) mDialogView.findViewById(R.id.tv_chakan_huanjing);
        tv_chakan_xitong = (TextView) mDialogView.findViewById(R.id.tv_chakan_xitong);
        tv_chakan_shuoming = (TextView) mDialogView.findViewById(R.id.tv_chakan_shuoming);
        tv_chakan_beizhu = (TextView) mDialogView.findViewById(R.id.tv_chakan_beizhu);
        tv_chakan_queren = (TextView) mDialogView.findViewById(R.id.tv_chakan_queren);
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

        tv_chakan_huanjing.setText(builder.getHuanjing());
        tv_chakan_xitong.setText(builder.getXitong());
        tv_chakan_shuoming.setText(builder.getShuoming());
        tv_chakan_beizhu.setText(builder.getBeizhu());

        tv_chakan_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickSingleButton(ChakanBeizhuDialog.this, tv_chakan_queren);
                }
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

        private String leftButtonText;
        private int leftButtonTextColor;
        private String rightButtonText;

        private int rightButtonTextColor;
        private int buttonTextSize;
        private DialogInterface.OnSingleClickListener<ChakanBeizhuDialog> onclickListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private int noJiaodi;

        private String huanjing;
        private String xitong;
        private String shuoming;
        private String beizhu;

        public Builder(Context context) {

            mContext = context;

            leftButtonText = "确定";
            leftButtonTextColor = ContextCompat.getColor(mContext, R.color.color_203B64);
            rightButtonText = "取消";
            rightButtonTextColor = ContextCompat.getColor(mContext, R.color.color_888888);
            onclickListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;
            buttonTextSize = 14;
            noJiaodi = 1;
        }

        public Context getContext() {

            return mContext;
        }

        public int getNoJiaodi() {
            return noJiaodi;
        }

        public Builder setNoJiaodi(int noJiaodi) {
            this.noJiaodi = noJiaodi;
            return this;
        }

        public String getHuanjing() {
            return huanjing;
        }

        public Builder setHuanjing(String huanjing) {
            this.huanjing = huanjing;
            return this;
        }

        public String getXitong() {
            return xitong;
        }

        public Builder setXitong(String xitong) {
            this.xitong = xitong;
            return this;
        }

        public String getShuoming() {
            return shuoming;
        }

        public Builder setShuoming(String shuoming) {
            this.shuoming = shuoming;
            return this;
        }

        public String getBeizhu() {
            return beizhu;
        }

        public Builder setBeizhu(String beizhu) {
            this.beizhu = beizhu;
            return this;
        }

        public String getLeftButtonText() {
            return leftButtonText;
        }

        public Builder setLeftButtonText(String leftButtonText) {
            this.leftButtonText = leftButtonText;
            return this;
        }

        public int getLeftButtonTextColor() {
            return leftButtonTextColor;
        }

        public Builder setLeftButtonTextColor(@ColorRes int leftButtonTextColor) {
            this.leftButtonTextColor = ContextCompat.getColor(mContext, leftButtonTextColor);
            return this;
        }

        public String getRightButtonText() {
            return rightButtonText;
        }

        public Builder setRightButtonText(String rightButtonText) {
            this.rightButtonText = rightButtonText;
            return this;
        }

        public int getRightButtonTextColor() {
            return rightButtonTextColor;
        }

        public Builder setRightButtonTextColor(@ColorRes int rightButtonTextColor) {
            this.rightButtonTextColor = ContextCompat.getColor(mContext, rightButtonTextColor);
            return this;
        }

        public DialogInterface.OnSingleClickListener<ChakanBeizhuDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnSingleClickListener<ChakanBeizhuDialog> onclickListener) {
            this.onclickListener = onclickListener;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {

            this.isTouchOutside = isTouchOutside;
            return this;
        }

        public int getButtonTextSize() {
            return buttonTextSize;
        }

        public Builder setButtonTextSize(int buttonTextSize) {
            this.buttonTextSize = buttonTextSize;
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

        public ChakanBeizhuDialog build() {

            return new ChakanBeizhuDialog(this);
        }
    }


}
