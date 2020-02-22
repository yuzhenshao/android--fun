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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class ProjectConfirmDialog {

    private TextView tv_pro_title;
    private TextView tv_pro_start;
    private TextView tv_pro_end;
    private TextView tv_pro_name;
    private Button tv_pro_left;
    private Button tv_pro_right;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private LinearLayout ll_finish;
    private LinearLayout ll_show;

    public ProjectConfirmDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.project_confirm_dialog, null);

        tv_pro_title = (TextView) mDialogView.findViewById(R.id.tv_pro_title);
        tv_pro_start = (TextView) mDialogView.findViewById(R.id.tv_pro_start);
        tv_pro_end = (TextView) mDialogView.findViewById(R.id.tv_pro_end);
        tv_pro_name = (TextView) mDialogView.findViewById(R.id.tv_pro_name);
        tv_pro_left = (Button) mDialogView.findViewById(R.id.tv_pro_left);
        tv_pro_right = (Button) mDialogView.findViewById(R.id.tv_pro_right);
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
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);

        initDialog(builder);
    }

//    public String getmContent(){
//        return mContent.getText().toString().trim();
//    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        tv_pro_title.getPaint().setFakeBoldText(true);

        tv_pro_start.setText(builder.getStartTime());
        tv_pro_end.setText(builder.getEndTime());
        tv_pro_name.setText(builder.getProName());
//        tv_shuang_content.setText(builder.getContentText());
//        mContent.setTextColor(builder.getContentTextColor());
//        mContent.setTextSize(builder.getContentTextSize());
//        mLeftBtn.setText(builder.getLeftButtonText());
//        mLeftBtn.setTextColor(builder.getLeftButtonTextColor());
//        mLeftBtn.setTextSize(builder.getButtonTextSize());
//        mRightBtn.setText(builder.getRightButtonText());
//        mRightBtn.setTextColor(builder.getRightButtonTextColor());
//        mRightBtn.setTextSize(builder.getButtonTextSize());

        tv_pro_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickLeftButton(ProjectConfirmDialog.this, tv_pro_left);
                }
            }
        });
        tv_pro_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickRightButton(ProjectConfirmDialog.this,
                            tv_pro_right);
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

        private String startTime;
        private String endTime;
        private boolean isSingleMode;
        private String proName;
        private DialogInterface.OnLeftAndRightClickListener<ProjectConfirmDialog> onclickListener;
        private DialogInterface.OnSingleClickListener<ProjectConfirmDialog> singleListener;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;

        public Builder(Context context) {

            mContext = context;

            isSingleMode = false;
//            singleButtonText = "确定";
//            singleButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
//            leftButtonText = "取消";
//            leftButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
//            rightButtonText = "确定";
//            rightButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            onclickListener = null;
            singleListener = null;
            isTitleVisible = false;
            isTouchOutside = true;
//            height = 0.23f;
//            width = 0.65f;

        }

        public Context getContext() {

            return mContext;
        }

        public String getStartTime() {
            return startTime;
        }

        public Builder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public String getEndTime() {
            return endTime;
        }

        public Builder setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public boolean isSingleMode() {
            return isSingleMode;
        }

        public Builder setSingleMode(boolean singleMode) {
            isSingleMode = singleMode;
            return this;
        }

        public String getProName() {
            return proName;
        }

        public Builder setProName(String proName) {
            this.proName = proName;
            return this;
        }

        public DialogInterface.OnLeftAndRightClickListener<ProjectConfirmDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogInterface.OnLeftAndRightClickListener<ProjectConfirmDialog> onclickListener) {
            this.onclickListener = onclickListener;
            return this;
        }

        public DialogInterface.OnSingleClickListener<ProjectConfirmDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnSingleClickListener<ProjectConfirmDialog>
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

        public ProjectConfirmDialog build() {

            return new ProjectConfirmDialog(this);
        }
    }


}
