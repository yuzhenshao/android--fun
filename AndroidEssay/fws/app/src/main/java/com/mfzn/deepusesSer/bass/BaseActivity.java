package com.mfzn.deepusesSer.bass;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.mfzn.deepusesSer.AppManager;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import cn.droidlover.xdroidmvp.mvp.XActivity;
import io.reactivex.functions.Consumer;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public abstract class BaseActivity extends XActivity {
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    @Override
    public Object newP() {
        return null;
    }

    /**
     * Flag只有在使用了FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
     * 并且没有使用 FLAG_TRANSLUCENT_STATUS的时候才有效，也就是只有在状态栏全透明的时候才有效。
     * <p>
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
}
