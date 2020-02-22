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
public class HuabeiFenqiDialog {

//    private TextView mTitle;
//    private EditText mContent;
    private Button mLeftBtn;
    private Button mRightBtn;
//    private Button mSingleBtn;
    private TextView mLine;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView tv_fenqi_shangpin;
    private TextView tv_fenqi_qishu;
    private TextView tv_fenqi_chengdan;
    private TextView tv_fenqi_zhifu;
    private TextView tv_fenqi_meiqi;
    private LinearLayout ll_finish;
    private LinearLayout ll_show;

    public HuabeiFenqiDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.huabei_fenqi_dialog, null);
        mLeftBtn = (Button) mDialogView.findViewById(R.id.dialog_normal_leftbtn);
        mRightBtn = (Button) mDialogView.findViewById(R.id.dialog_normal_rightbtn);
        mLine = (TextView) mDialogView.findViewById(R.id.dialog_normal_line);
        ll_finish = (LinearLayout) mDialogView.findViewById(R.id.ll_finish);
        ll_show = (LinearLayout) mDialogView.findViewById(R.id.ll_show);

        tv_fenqi_shangpin = (TextView) mDialogView.findViewById(R.id.tv_fenqi_shangpin);
        tv_fenqi_qishu = (TextView) mDialogView.findViewById(R.id.tv_fenqi_qishu);
        tv_fenqi_chengdan = (TextView) mDialogView.findViewById(R.id.tv_fenqi_chengdan);
        tv_fenqi_zhifu = (TextView) mDialogView.findViewById(R.id.tv_fenqi_zhifu);
        tv_fenqi_meiqi = (TextView) mDialogView.findViewById(R.id.tv_fenqi_meiqi);

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

//    public String getmContent(){
//        return mContent.getText().toString().trim();
//    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        tv_fenqi_shangpin.setText(builder.getShanghuText());
        tv_fenqi_qishu.setText(builder.getQishuText());
        tv_fenqi_chengdan.setText(builder.getChengdanText());
        tv_fenqi_zhifu.setText(builder.getZhifuText());
        tv_fenqi_meiqi.setText(builder.getMeiqiText());

        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getOnclickListener() != null) {

                    mBuilder.getOnclickListener().clickLeftButton(HuabeiFenqiDialog.this, mLeftBtn);
                }
            }
        });
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getOnclickListener() != null) {

                    mBuilder.getOnclickListener().clickRightButton(HuabeiFenqiDialog.this,
                            mRightBtn);
                }

            }
        });
        ll_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ll_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        private String shanghu;
        private String qishu;
        private String chengdan;
        private String zhifu;
        private String meiqi;


        private DialogInterface.OnLeftAndRightClickListener<HuabeiFenqiDialog> onclickListener;
        private DialogInterface.OnSingleClickListener<HuabeiFenqiDialog> singleListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;

        public Builder(Context context) {

            mContext = context;

            onclickListener = null;
            singleListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;

        }

        public Context getContext() {

            return mContext;
        }

        public String getMeiqiText() {
            return meiqi;
        }

        public Builder setMeiqiText(String meiqi) {
            this.meiqi = meiqi;
            return this;
        }

        public String getShanghuText() {
            return shanghu;
        }

        public Builder setShanghuText(String shanghu) {
            this.shanghu = shanghu;
            return this;
        }

        public String getQishuText() {
            return qishu;
        }

        public Builder setQishuText(String qishu) {
            this.qishu = qishu;
            return this;
        }

        public String getChengdanText() {
            return chengdan;
        }

        public Builder setChengdanText(String chengdan) {
            this.chengdan = chengdan;
            return this;
        }

        public String getZhifuText() {
            return zhifu;
        }

        public Builder setZhifuText(String zhifu) {
            this.zhifu = zhifu;
            return this;
        }







//        public boolean isSingleMode() {
//            return isSingleMode;
//        }
//
//        public Builder setSingleMode(boolean singleMode) {
//            isSingleMode = singleMode;
//            return this;
//        }

        public DialogInterface.OnLeftAndRightClickListener<HuabeiFenqiDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnLeftAndRightClickListener<HuabeiFenqiDialog> onclickListener) {
            this.onclickListener = onclickListener;
            return this;
        }

        public DialogInterface.OnSingleClickListener<HuabeiFenqiDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnSingleClickListener<HuabeiFenqiDialog>
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

        public HuabeiFenqiDialog build() {

            return new HuabeiFenqiDialog(this);
        }
    }


}
