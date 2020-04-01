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
public class NoDingweiDialog {

//    private TextView mTitle;
    private Button mLeftBtn;
    private Button mRightBtn;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;
    private LinearLayout ll_ding_youwu;
    private LinearLayout ll_ding_chongtu;
    private ImageView iv_ding_youwu;
    private ImageView iv_ding_chongtu;

    public NoDingweiDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.no_dingwei_dialog, null);
        mLeftBtn = (Button) mDialogView.findViewById(R.id.dialog_normal_leftbtn);
        mRightBtn = (Button) mDialogView.findViewById(R.id.dialog_normal_rightbtn);
        ll_ding_youwu = (LinearLayout) mDialogView.findViewById(R.id.ll_ding_youwu);
        ll_ding_chongtu = (LinearLayout) mDialogView.findViewById(R.id.ll_ding_chongtu);
        iv_ding_youwu = (ImageView) mDialogView.findViewById(R.id.iv_ding_youwu);
        iv_ding_chongtu = (ImageView) mDialogView.findViewById(R.id.iv_ding_chongtu);
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

        mLeftBtn.setText(builder.getLeftButtonText());
        mLeftBtn.setTextColor(builder.getLeftButtonTextColor());
        mLeftBtn.setTextSize(builder.getButtonTextSize());
        mRightBtn.setText(builder.getRightButtonText());
        mRightBtn.setTextColor(builder.getRightButtonTextColor());
        mRightBtn.setTextSize(builder.getButtonTextSize());

        if(builder.getNoJiaodi() == 1){
            iv_ding_youwu.setImageResource(R.drawable.ding_xuan);
            iv_ding_chongtu.setImageResource(R.drawable.ding_buxuan);
        }else if(builder.getNoJiaodi() == 2){
            iv_ding_chongtu.setImageResource(R.drawable.ding_xuan);
            iv_ding_youwu.setImageResource(R.drawable.ding_buxuan);
        }

        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickLeftButton(NoDingweiDialog.this, mLeftBtn);
                }
            }
        });
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickRightButton(NoDingweiDialog.this,
                            mRightBtn);
                }

            }
        });
        ll_ding_youwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickYouwuButton(NoDingweiDialog.this,
                            ll_ding_youwu,iv_ding_youwu,iv_ding_chongtu);
                }
            }
        });
        ll_ding_chongtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickChongtuButton(NoDingweiDialog.this,
                            ll_ding_chongtu,iv_ding_chongtu,iv_ding_youwu);
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
        private DialogInterface.OnDingweiJiaodiClickListener<NoDingweiDialog> onclickListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private int noJiaodi;

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

        public DialogInterface.OnDingweiJiaodiClickListener<NoDingweiDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnDingweiJiaodiClickListener<NoDingweiDialog> onclickListener) {
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

        public NoDingweiDialog build() {

            return new NoDingweiDialog(this);
        }
    }


}
