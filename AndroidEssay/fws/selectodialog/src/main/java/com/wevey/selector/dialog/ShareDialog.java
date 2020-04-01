package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class ShareDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView ll_hide;
    private LinearLayout ll_sha_detele;
    private LinearLayout ll_sha_weixin;
    private LinearLayout ll_sha_pengyou;
    private LinearLayout ll_sha_qq;
    private LinearLayout ll_sha_weibo;
    private LinearLayout ll_sha_kongjian;
    private LinearLayout ll_sha_duandin;

    public ShareDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.share_dialog, null);
        mDialog.setContentView(mDialogView); // 一定要在setAttributes(lp)之前才有效

        ll_hide = (TextView) mDialogView.findViewById(R.id.ll_hide);
        ll_sha_detele = (LinearLayout) mDialogView.findViewById(R.id.ll_sha_detele);
        ll_sha_weixin = (LinearLayout) mDialogView.findViewById(R.id.ll_sha_weixin);
        ll_sha_pengyou = (LinearLayout) mDialogView.findViewById(R.id.ll_sha_pengyou);
        ll_sha_qq = (LinearLayout) mDialogView.findViewById(R.id.ll_sha_qq);
        ll_sha_weibo = (LinearLayout) mDialogView.findViewById(R.id.ll_sha_weibo);
        ll_sha_kongjian = (LinearLayout) mDialogView.findViewById(R.id.ll_sha_kongjian);
        ll_sha_duandin = (LinearLayout) mDialogView.findViewById(R.id.ll_sha_duandin);

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

        ll_sha_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickWeixinButton(ShareDialog.this, ll_sha_weixin);
                }
            }
        });
        ll_sha_pengyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickPengyouButton(ShareDialog.this, ll_sha_pengyou);
                }
            }
        });
        ll_sha_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickQQButton(ShareDialog.this, ll_sha_qq);
                }
            }
        });
        ll_sha_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickWeiboButton(ShareDialog.this, ll_sha_weibo);
                }
            }
        });
        ll_sha_kongjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickKongjianButton(ShareDialog.this, ll_sha_kongjian);
                }
            }
        });
        ll_sha_duandin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickDuanxinButton(ShareDialog.this, ll_sha_duandin);
                }
            }
        });
        ll_sha_detele.setOnClickListener(new View.OnClickListener() {
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

        private int titleTextColor;
        private DialogInterface.OnShareClickListener<ShareDialog> onclickListener;
        private DialogInterface.OnShareClickListener<ShareDialog> singleListener;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
            onclickListener = null;
            singleListener = null;
            isTitleVisible = false;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;
        }

        public Context getContext() {

            return mContext;
        }

        public int getTitleTextColor() {
            return titleTextColor;
        }

        public Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.titleTextColor = ContextCompat.getColor(mContext, titleTextColor);
            return this;
        }

        public DialogInterface.OnShareClickListener<ShareDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnShareClickListener<ShareDialog> onclickListener) {
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

        public ShareDialog build() {

            return new ShareDialog(this);
        }
    }


}
