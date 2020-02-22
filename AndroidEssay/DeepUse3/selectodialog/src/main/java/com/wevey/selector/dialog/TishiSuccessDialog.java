package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class TishiSuccessDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView tv_tishi_content;
    private TextView tv_title;
    private Button but_tishi;
    private LinearLayout ll_finish;
    private LinearLayout ll_show;

    public TishiSuccessDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.tishi_dialog, null);

        tv_tishi_content = (TextView) mDialogView.findViewById(R.id.tv_tishi_content);
        tv_title = (TextView) mDialogView.findViewById(R.id.tv_title);
        but_tishi = (Button) mDialogView.findViewById(R.id.but_tishi);
        ll_finish = (LinearLayout) mDialogView.findViewById(R.id.ll_finish);
        ll_show = (LinearLayout) mDialogView.findViewById(R.id.ll_show);

        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        lp.y = 500;

        dialogWindow.setAttributes(lp);

        initDialog(builder);
    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        tv_title.setText(builder.getTitle());

        tv_tishi_content.setText(builder.getContentText());
        but_tishi.setText(builder.getbuttext());

        but_tishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getSingleListener() != null) {

                    mBuilder.getSingleListener().clickSingleButton(TishiSuccessDialog.this,
                            but_tishi);
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

        private String content;
        private String title;
        private DialogInterface.OnLeftAndRightClickListener<TishiSuccessDialog> onclickListener;
        private DialogInterface.OnSingleClickListener<TishiSuccessDialog> singleListener;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private String buttext;

        public Builder(Context context) {

            mContext = context;
            onclickListener = null;
            singleListener = null;
            isTitleVisible = false;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;
            buttext = "我知道了";
            title = "提示";
        }

        public Context getContext() {

            return mContext;
        }

        public String getbuttext() {
            return buttext;
        }

        public Builder setbuttext(String buttext) {
            this.buttext = buttext;
            return this;
        }

        public String getContentText() {
            return content;
        }

        public Builder setContentText(String content) {
            this.content = content;
            return this;
        }
        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public DialogInterface.OnSingleClickListener<TishiSuccessDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnSingleClickListener<TishiSuccessDialog>
                                                 singleListener) {
            this.singleListener = singleListener;
            return this;
        }

        public boolean getTitleVisible() {
            return isTitleVisible;
        }

        public Builder setTitleVisible(boolean isVisible) {
            isTitleVisible = isVisible;
            return this;
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

        public TishiSuccessDialog build() {

            return new TishiSuccessDialog(this);
        }
    }


}
