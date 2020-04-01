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
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class NoTitleAndDialog {

//    private TextView mTitle;
    private TextView mContent;
    private Button mLeftBtn;
    private Button mRightBtn;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    public NoTitleAndDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.notitle_and_dialog, null);
        mContent = (TextView) mDialogView.findViewById(R.id.dialog_normal_content);
        mLeftBtn = (Button) mDialogView.findViewById(R.id.dialog_normal_leftbtn);
        mRightBtn = (Button) mDialogView.findViewById(R.id.dialog_normal_rightbtn);
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

    public String getmContent(){
        return mContent.getText().toString().trim();
    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        mContent.setText(builder.getContentText());
        mContent.setTextColor(builder.getContentTextColor());
        mContent.setTextSize(builder.getContentTextSize());
        mLeftBtn.setText(builder.getLeftButtonText());
        mLeftBtn.setTextColor(builder.getLeftButtonTextColor());
        mLeftBtn.setTextSize(builder.getButtonTextSize());
        mRightBtn.setText(builder.getRightButtonText());
        mRightBtn.setTextColor(builder.getRightButtonTextColor());
        mRightBtn.setTextSize(builder.getButtonTextSize());

        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getOnclickListener() != null) {

                    mBuilder.getOnclickListener().clickLeftButton(NoTitleAndDialog.this, mLeftBtn);
                }
            }
        });
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getOnclickListener() != null) {

                    mBuilder.getOnclickListener().clickRightButton(NoTitleAndDialog.this,
                            mRightBtn);
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

        private String contentText;
        private int contentTextColor;
        private int contentTextSize;

        private String leftButtonText;
        private int leftButtonTextColor;
        private String rightButtonText;

        private int rightButtonTextColor;
        private int buttonTextSize;
        private DialogInterface.OnLeftAndRightClickListener<NoTitleAndDialog> onclickListener;
        private DialogInterface.OnSingleClickListener<NoTitleAndDialog> singleListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;

        public Builder(Context context) {

            mContext = context;

            contentText = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.biaoti);
            leftButtonText = "确定";
            leftButtonTextColor = ContextCompat.getColor(mContext, R.color.color_203B64);
            rightButtonText = "取消";
            rightButtonTextColor = ContextCompat.getColor(mContext, R.color.color_888888);
            onclickListener = null;
            singleListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;
            contentTextSize = 15;
            buttonTextSize = 14;

        }

        public Context getContext() {

            return mContext;
        }

        public String getContentText() {
            return contentText;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public int getContentTextColor() {
            return contentTextColor;
        }

        public Builder setContentTextColor(@ColorRes int contentTextColor) {
            this.contentTextColor = ContextCompat.getColor(mContext, contentTextColor);
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

        public DialogInterface.OnLeftAndRightClickListener<NoTitleAndDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnLeftAndRightClickListener<NoTitleAndDialog> onclickListener) {
            this.onclickListener = onclickListener;
            return this;
        }

        public DialogInterface.OnSingleClickListener<NoTitleAndDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnSingleClickListener<NoTitleAndDialog>
                                                 singleListener) {
            this.singleListener = singleListener;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {

            this.isTouchOutside = isTouchOutside;
            return this;
        }

        public int getContentTextSize() {
            return contentTextSize;
        }

        public Builder setContentTextSize(int contentTextSize) {
            this.contentTextSize = contentTextSize;
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

        public NoTitleAndDialog build() {

            return new NoTitleAndDialog(this);
        }
    }


}
