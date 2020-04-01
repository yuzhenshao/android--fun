package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/3.
 */
public class XxskDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private Button dialog_normal_leftbtn;
    private Button dialog_normal_rightbtn;
    private EditText et_je;
    private EditText et_bz;
    private LinearLayout ll_finish;
    private LinearLayout ll_show;


    public XxskDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.xxsk_dialog, null);

        et_je = (EditText) mDialogView.findViewById(R.id.et_je);
        et_bz = (EditText) mDialogView.findViewById(R.id.et_bz);
        dialog_normal_leftbtn =  mDialogView.findViewById(R.id.dialog_normal_leftbtn);
        dialog_normal_rightbtn =  mDialogView.findViewById(R.id.dialog_normal_rightbtn);
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

        et_bz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textContent = et_bz.getText().toString().trim();
                if (textContent.length() > 13){
                    Toast.makeText(builder.getContext(),"请不要超过50个字",Toast.LENGTH_SHORT);
                    et_bz.getText().delete(13, et_bz.getSelectionEnd());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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

        dialog_normal_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getSingleListener() != null) {

                    mBuilder.getSingleListener().clickLeftButton(XxskDialog.this, dialog_normal_leftbtn);
                }
            }
        });
        dialog_normal_rightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getSingleListener() != null) {

                    mBuilder.getSingleListener().clickRightButton2(XxskDialog.this,
                            dialog_normal_rightbtn,et_je,et_bz);
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
        private DialogInterface.OnLeftAndRightClick2Listener<XxskDialog> singleListener;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private String buttext;
        private String textContent;

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

        public String getbuttext() {
            return buttext;
        }

        public Builder setbuttext(String buttext) {
            this.buttext = buttext;
            return this;
        }

        public String getTextContent() {
            return textContent;
        }

        public Builder setTextContent(String textContent) {
            this.textContent = textContent;
            return this;
        }
        public String getContentText() {
            return content;
        }

        public Builder setContentText(String content) {
            this.content = content;
            return this;
        }

        public DialogInterface.OnLeftAndRightClick2Listener<XxskDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnLeftAndRightClick2Listener<XxskDialog>
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

        public XxskDialog build() {

            return new XxskDialog(this);
        }
    }


}
