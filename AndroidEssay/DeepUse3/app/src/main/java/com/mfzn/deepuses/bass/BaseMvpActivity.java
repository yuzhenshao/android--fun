package com.mfzn.deepuses.bass;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * @author zhangbostay
 * @date 2019/4/10
 */
public abstract class BaseMvpActivity<P extends IPresent> extends XActivity<P> {

    protected Handler mHandler = new Handler();

    private QMUITipDialog mTipDialog;

    @Override
    public void initData(Bundle savedInstanceState) {
//        ScreenUtil.fullScreen(this);
////        //可以在输入edittext的时候把界面往上提
//        AndroidKeyWorkaround.assistActivity(findViewById(android.R.id.content));

        QMUIStatusBarHelper.translucent(this);
        setStatusBarMode(this);//改变顶部系统文字的颜色  黑色  安卓6.0以上才有效果
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTipDialog = null;

        mHandler.removeCallbacksAndMessages(null);

        AppManager.getInstance().removeActivity(this);
    }

    /**
     * Flag只有在使用了FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
     * 并且没有使用 FLAG_TRANSLUCENT_STATUS的时候才有效，也就是只有在状态栏全透明的时候才有效。
     *
     * 改变顶部系统文字的颜色  黑色  安卓6.0以上才有效果
     *
     * @param activity
     */
    public static void setStatusBarMode(Activity activity) {
        //6.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                decorView.setSystemUiVisibility(vis);
            }

        }
    }

    public void showDialog() {
        try {
            hideDialog();
            mTipDialog = null;
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

    public void showMessage(String msg) {
        showMessageDialog(msg, QMUITipDialog.Builder.ICON_TYPE_INFO);
    }

    public void showFailMsg(String msg) {
        showMessageDialog(msg, QMUITipDialog.Builder.ICON_TYPE_FAIL);
    }

    public void showSuccessMsg(String msg) {

        showMessageDialog(msg, QMUITipDialog.Builder.ICON_TYPE_SUCCESS);
    }

    private void showMessageDialog(String msg, int iconType) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        try {
            hideDialog();
            mTipDialog = null;
            mTipDialog = new QMUITipDialog.Builder(context)
                    .setIconType(iconType)
                    .setTipWord(msg)
                    .create();
            mTipDialog.show();
            mHandler.postDelayed(this::hideDialog, 1500);
        } catch (Exception ignored) {
            String message = ignored.getMessage();
            ToastUtil.showToast(context,ignored.getMessage());
        }
    }

    public void showError(NetError error) {
        hideDialog();
        if (error != null) {
            switch (error.getType()) {
                case NetError.ParseError:
                    showFailMsg("数据解析异常");
                    break;

                case NetError.AuthError:
                    showFailMsg("身份验证异常,请重新登录");
                    UserHelper.logout();
                    UserHelper.setOut(true);
                    Router.newIntent(context).to(LoginActivity.class).launch();
                    break;

                case NetError.BusinessError:
                    showFailMsg(error.getMessage());
                    break;

                case NetError.NoConnectError:
                    showFailMsg("网络无连接");
                    break;

                case NetError.NoDataError:
                    showFailMsg("数据为空");
                    break;

                case NetError.OtherError:
                    showFailMsg("其他异常");
                    break;
                default:
                    break;
            }
//            contentLayout.showError();
        }
    }

    protected Context getContext() {
        return context;
    }

}
