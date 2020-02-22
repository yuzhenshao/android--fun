package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class ModifyBmNameDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView tv_bm_title;
    private TextView tv_bm_quxiao;
    private TextView tv_bm_add;
    private EditText et_bm_content;
    private LinearLayout ll_finish;
    private LinearLayout ll_show;


    public ModifyBmNameDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.modify_bm_name_dialog, null);

        et_bm_content = (EditText) mDialogView.findViewById(R.id.et_bm_content);
        tv_bm_title =  mDialogView.findViewById(R.id.tv_bm_title);
        tv_bm_quxiao =  mDialogView.findViewById(R.id.tv_bm_quxiao);
        tv_bm_add =  mDialogView.findViewById(R.id.tv_bm_add);
        ll_finish =  mDialogView.findViewById(R.id.ll_finish);
        ll_show =  mDialogView.findViewById(R.id.ll_show);

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

    private void initDialog(final Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());
//        mDialog.setCancelable(builder.isTouchOutside());
        et_bm_content.setHint(builder.getContentHint());
        tv_bm_title.setText(builder.getmTitle());
        tv_bm_quxiao.setText(builder.getLetfText());
        tv_bm_add.setText(builder.getRightText());

//        et_yan_input.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String textContent = et_yan_input.getText().toString().trim();
//                if (textContent.length() > 13){
//                    Toast.makeText(builder.getContext(),"请不要超过15个字",Toast.LENGTH_SHORT);
//                    et_yan_input.getText().delete(13, et_yan_input.getSelectionEnd());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
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

        tv_bm_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getSingleListener() != null) {
                    mBuilder.getSingleListener().clickLeftButton(ModifyBmNameDialog.this, tv_bm_quxiao);
                }
            }
        });
        tv_bm_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getSingleListener() != null) {
                    mBuilder.getSingleListener().clickRightButton(ModifyBmNameDialog.this,
                            tv_bm_add,et_bm_content);
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

        private DialogInterface.OnLeftAndRightClick2Listener<ModifyBmNameDialog> singleListener;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private String mTitle;
        private String contentHint;
        private String letfText;

        private String rightText;

        public Builder(Context context) {

            mContext = context;
            singleListener = null;
            isTitleVisible = false;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;
        }

        public Context getContext() {

            return mContext;
        }

        public String getLetfText() {
            return letfText;
        }

        public Builder setLetfText(String letfText) {
            this.letfText = letfText;
            return this;
        }

        public String getRightText() {
            return rightText;
        }

        public Builder setRightText(String rightText) {
            this.rightText = rightText;
            return this;
        }

        public String getmTitle() {
            return mTitle;
        }

        public Builder setmTitle(String mTitle) {
            this.mTitle = mTitle;
            return this;
        }

        public String getContentHint() {
            return contentHint;
        }

        public Builder setContentHint(String contentHint) {
            this.contentHint = contentHint;
            return this;
        }

        public DialogInterface.OnLeftAndRightClick2Listener<ModifyBmNameDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnLeftAndRightClick2Listener<ModifyBmNameDialog>
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

        public ModifyBmNameDialog build() {

            return new ModifyBmNameDialog(this);
        }
    }


}
