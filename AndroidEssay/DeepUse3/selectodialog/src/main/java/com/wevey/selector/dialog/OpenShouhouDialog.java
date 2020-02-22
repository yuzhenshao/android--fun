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
public class OpenShouhouDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private LinearLayout ll_finsh;
    private TextView tv_open_brick;
    private TextView tv_open_name;
    private TextView tv_open_price;
    private Button but_open;

    public OpenShouhouDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.open_shouhou_dialog, null);
        mDialog.setContentView(mDialogView); // 一定要在setAttributes(lp)之前才有效

        ll_finsh = (LinearLayout) mDialogView.findViewById(R.id.ll_finsh);
        tv_open_brick = (TextView) mDialogView.findViewById(R.id.tv_open_brick);
        tv_open_name = (TextView) mDialogView.findViewById(R.id.tv_open_name);
        tv_open_price = (TextView) mDialogView.findViewById(R.id.tv_open_price);
        but_open = (Button) mDialogView.findViewById(R.id.but_open);

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

        tv_open_name.setText(builder.getName());
        tv_open_brick.setText("余额：" + builder.getBrick() + "砖");
        tv_open_price.setText(builder.getPrice() + "砖");

        //新的点击事件
        ll_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickLeftButton(OpenShouhouDialog.this,
                            ll_finsh);
                }
            }
        });
        but_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickRightButton(OpenShouhouDialog.this,
                            but_open);
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

        private DialogInterface.OnLeftAndRightClickListener<OpenShouhouDialog> onclickListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private String name;
        private String brick;
        private String price;

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

        public String getName() {
            return name;
        }

        public Builder setName(String title) {
            this.name = title;
            return this;
        }

        public String getBrick() {
            return brick;
        }

        public Builder setBrick(String brick) {
            this.brick = brick;
            return this;
        }
        public String getPrice() {
            return price;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public DialogInterface.OnLeftAndRightClickListener<OpenShouhouDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnLeftAndRightClickListener<OpenShouhouDialog> onclickListener) {
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

        public OpenShouhouDialog build() {

            return new OpenShouhouDialog(this);
        }
    }


}
