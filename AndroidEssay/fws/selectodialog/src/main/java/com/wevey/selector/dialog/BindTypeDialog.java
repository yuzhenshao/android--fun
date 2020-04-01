package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class BindTypeDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

//    private TextView tv_bind_siren;
//    private TextView tv_bind_gongsi;

    private TextView tv_type_title;
    private TextView tv_type_content1;
    private TextView tv_type_content2;

    public BindTypeDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.bind_type_dialog, null);
        mDialog.setContentView(mDialogView); // 一定要在setAttributes(lp)之前才有效

//        tv_bind_siren = (TextView) mDialogView.findViewById(R.id.tv_bind_siren);
//        tv_bind_gongsi = (TextView) mDialogView.findViewById(R.id.tv_bind_gongsi);
        tv_type_title = (TextView) mDialogView.findViewById(R.id.tv_type_title);
        tv_type_content1 = (TextView) mDialogView.findViewById(R.id.tv_type_content1);
        tv_type_content2 = (TextView) mDialogView.findViewById(R.id.tv_type_content2);

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

        tv_type_title.setText(builder.getTitle());
        tv_type_content1.setText(builder.getContent1());
        tv_type_content2.setText(builder.getContent2());

        //新的点击事件
        tv_type_content1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getOnclickListener() != null) {

                    mBuilder.getOnclickListener().clickLeftButton(BindTypeDialog.this,
                            tv_type_content1);
                }
            }
        });

        tv_type_content2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getOnclickListener() != null) {

                    mBuilder.getOnclickListener().clickRightButton(BindTypeDialog.this,
                            tv_type_content2);
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

        private DialogInterface.OnLeftAndRightClickListener<BindTypeDialog> onclickListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private String title;
        private String content1;
        private String content2;

        public Builder(Context context) {

            mContext = context;
            onclickListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;

        }

        public Context getContext() {
            return mContext;
        }

        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getContent1() {
            return content1;
        }

        public Builder setContent1(String content1) {
            this.content1 = content1;
            return this;
        }

        public String getContent2() {
            return content2;
        }

        public Builder setContent2(String content2) {
            this.content2 = content2;
            return this;
        }

        public DialogInterface.OnLeftAndRightClickListener<BindTypeDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnLeftAndRightClickListener<BindTypeDialog> onclickListener) {
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

        public BindTypeDialog build() {

            return new BindTypeDialog(this);
        }
    }


}
