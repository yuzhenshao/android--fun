package com.mfzn.deepuses.bass;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.libcommon.titlebar.TitleBar;
import com.libcommon.titlebar.TitlebarPressedListener;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.utils.ToastUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * @author yz @date 2020-04-02
 */
public abstract class BasicActivity extends BaseActivity {

    public TitleBar mTitleBar;
    private QMUITipDialog mTipDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleBar();
    }

    protected void initTitleBar() {
        mTitleBar = findViewById(R.id.titlebar);
        if (mTitleBar != null) {
            mTitleBar.setElementPressedListener(new TitlebarPressedListener() {

                @Override
                public void leftPressed() {
                    finish();
                }

                @Override
                public void rightPressed() {
                    rightPressedAction();
                }
            });
        }

    }

    protected void rightPressedAction() {

    }

    public void showDialog() {
        try {
            hideDialog();
            mTipDialog = new QMUITipDialog.Builder(context)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .create();
            mTipDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideDialog() {
        try {
            if (null != mTipDialog && mTipDialog.isShowing()) {
                mTipDialog.dismiss();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    protected void showToast(String message){
        ToastUtil.showToast(this, message);
    }

}
