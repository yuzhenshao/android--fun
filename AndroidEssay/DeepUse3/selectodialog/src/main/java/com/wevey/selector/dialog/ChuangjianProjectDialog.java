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
public class ChuangjianProjectDialog {

//    private TextView mTitle;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;
    private LinearLayout ll_pro_fuwu;
    private LinearLayout ll_pro_geren;
    private LinearLayout ll_pro_zhuan;
    private ImageView iv_pro_fuwu;
    private ImageView iv_pro_geren;
    private ImageView iv_pro_zhuan;
    private Button but_pro_complete;
    private TextView tv_pro_title;

    public ChuangjianProjectDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.chuangjian_project_dialog, null);
        ll_pro_fuwu = (LinearLayout) mDialogView.findViewById(R.id.ll_pro_fuwu);
        ll_pro_geren = (LinearLayout) mDialogView.findViewById(R.id.ll_pro_geren);
        ll_pro_zhuan = (LinearLayout) mDialogView.findViewById(R.id.ll_pro_zhuan);
        iv_pro_fuwu = (ImageView) mDialogView.findViewById(R.id.iv_pro_fuwu);
        iv_pro_geren = (ImageView) mDialogView.findViewById(R.id.iv_pro_geren);
        iv_pro_zhuan = (ImageView) mDialogView.findViewById(R.id.iv_pro_zhuan);
        but_pro_complete = (Button) mDialogView.findViewById(R.id.but_pro_complete);
        tv_pro_title = (TextView) mDialogView.findViewById(R.id.tv_pro_title);
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

        ll_pro_fuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickFuwuButton(ChuangjianProjectDialog.this,
                            ll_pro_fuwu,iv_pro_fuwu,iv_pro_geren);
                }
            }
        });
        ll_pro_geren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickGerenButton(ChuangjianProjectDialog.this,
                            ll_pro_geren,iv_pro_geren,iv_pro_fuwu);
                }

            }
        });
        but_pro_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickCompleteButton(ChuangjianProjectDialog.this,
                            but_pro_complete);
                }
            }
        });
        ll_pro_zhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickZhuanButton(ChuangjianProjectDialog.this,
                            ll_pro_zhuan,iv_pro_zhuan,tv_pro_title);
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


        private DialogInterface.OnProjectClickListener<ChuangjianProjectDialog> onclickListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private int noJiaodi;

        public Builder(Context context) {

            mContext = context;

            onclickListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;
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

        public DialogInterface.OnProjectClickListener<ChuangjianProjectDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnProjectClickListener<ChuangjianProjectDialog> onclickListener) {
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

        public ChuangjianProjectDialog build() {

            return new ChuangjianProjectDialog(this);
        }
    }


}
