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
public class YanJuanDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView tv_yan_quxiao;
    private TextView tv_yan_queding;
    private EditText et_yan_input;
    private LinearLayout ll_finish;
    private LinearLayout ll_show;


    public YanJuanDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.yanjuan_dialog, null);

        et_yan_input = (EditText) mDialogView.findViewById(R.id.et_yan_input);
        tv_yan_quxiao =  mDialogView.findViewById(R.id.tv_yan_quxiao);
        tv_yan_queding =  mDialogView.findViewById(R.id.tv_yan_queding);
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

        et_yan_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String textContent = et_yan_input.getText().toString().trim();
                if (textContent.length() > 13){
                    Toast.makeText(builder.getContext(),"请不要超过15个字",Toast.LENGTH_SHORT);
                    et_yan_input.getText().delete(13, et_yan_input.getSelectionEnd());
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

        tv_yan_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getSingleListener() != null) {

                    mBuilder.getSingleListener().clickLeftButton(YanJuanDialog.this, tv_yan_quxiao);
                }
            }
        });
        tv_yan_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getSingleListener() != null) {

                    mBuilder.getSingleListener().clickRightButton(YanJuanDialog.this,
                            tv_yan_queding,et_yan_input);
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
        private DialogInterface.OnLeftAndRightClick2Listener<YanJuanDialog> singleListener;
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

        public DialogInterface.OnLeftAndRightClick2Listener<YanJuanDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnLeftAndRightClick2Listener<YanJuanDialog>
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

        public YanJuanDialog build() {

            return new YanJuanDialog(this);
        }
    }


}
